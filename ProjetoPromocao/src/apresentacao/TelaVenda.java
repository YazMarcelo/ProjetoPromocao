package apresentacao;

import entidade.FormaPagamento;
import entidade.ItemPedido;
import entidade.Pedido;
import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import entidade.Vendedor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.NFormaPagamento;
import negocio.NPedido;
import negocio.NProduto;
import negocio.NPromocao;
import negocio.NVendedor;
import util.BarcodeReader;
import util.BarcodeReader.BarcodeListener;
import util.Mensagem;
import util.Utilitarios;

public class TelaVenda extends javax.swing.JInternalFrame {

    Pedido pedido;
    List<Promocao> promocoes;

    NVendedor nVendedor;
    NProduto nProduto;
    NPromocao nPromocao;
    NFormaPagamento nFormaPagamento;

    BarcodeReader barcodeReader;

    public TelaVenda() {
        nFormaPagamento = new NFormaPagamento();
        initComponents();
        pedido = new Pedido();
        promocoes = new ArrayList();
        nVendedor = new NVendedor();
        nProduto = new NProduto();
        nPromocao = new NPromocao();

        barcodeReader = new BarcodeReader();
        barcodeReader.addBarcodeListener(new BarcodeListener() {
            @Override
            public void onBarcodeRead(String barcode) {
                int op = Integer.parseInt(barcode.substring(0, 1));
                barcode = barcode.substring(1, barcode.length() - 1);
                switch (op) {
                    case 1: //Produto
                        int codigo = Integer.parseInt(barcode);
                        if (pedido.getVendedor() == null) {
                            return;
                        }
                        try {
                            Produto produto = nProduto.consultar(codigo);
                            if (produto == null) {
                                return;
                            }
                            List<Promocao> promocoesDoProduto;
                            ItemPedido item = getItem(produto);
                            if (item == null) {
                                ItemPedido itemNovo = new ItemPedido();
                                itemNovo.setPedido(pedido);
                                itemNovo.setProduto(produto);
                                itemNovo.setValorUnitario(produto.getValor());
                                item = itemNovo;
                                pedido.getItens().add(itemNovo);
                                promocoesDoProduto = nPromocao.listarByProduto(produto.getId());
                                promocoes.addAll(promocoesDoProduto);
                            } else {
                                promocoesDoProduto = getPromocoesDoProduto(produto);
                            }
                            item.addQtd(1);
                            if (produto.getSaldoEstoque() - item.getQtd() == 0) {
                                throw new Exception("Não há saldo em estoque para o Produto " + produto.getDescricao() + ".");
                            }
                            if (!promocoesDoProduto.isEmpty()) { //há promoções cadastradas para o produto
                                Promocao promocao;
                                if (promocoesDoProduto.size() == 1) {
                                    promocao = promocoesDoProduto.get(0);
                                    if (item.getQtd() >= promocao.getQtdPaga()) {
                                        item.setPromocao(promocao);
                                    }
                                } else {
                                    Promocao promocaoQuantidade = promocoesDoProduto.get(0).getTipo() == TipoPromocao.QUANTIDADE ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                    Promocao promocaoDesconto = promocoesDoProduto.get(0).getTipo() == TipoPromocao.DESCONTO ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                    if (item.getQtd() >= promocaoQuantidade.getQtdPaga()) {
                                        promocao = promocaoQuantidade;
                                        item.setPromocao(promocaoQuantidade);
                                        if (item.getQtd() == promocaoQuantidade.getQtdPaga()) {
                                            item.setDesconto(0);
                                        }
                                    } else if (item.getQtd() >= promocaoDesconto.getQtdPaga()) {
                                        promocao = promocaoDesconto;
                                        item.setPromocao(promocaoDesconto);
                                    } else {
                                        atualizar();
                                        return;
                                    }
                                }
                                switch (promocao.getTipo()) {
                                    case DESCONTO:
                                        if (item.getQtd() >= promocao.getQtdPaga()) {
                                            item.recalcularDesconto();
                                        }
                                        break;
                                    case QUANTIDADE:
                                        if ((item.getQtd() - item.getQtdBrinde()) % promocao.getQtdPaga() == 0) {
                                            if (promocao.getProdPaga().getId() == promocao.getProdLeva().getId()) {
                                                item.addQtd(promocao.getQtdLeva());
                                                item.recalcularDesconto();
                                            } else { //o produto leva não é o mesmo que o produto paga
                                                ItemPedido itemBrinde = getItem(promocao.getProdLeva());
                                                if (itemBrinde == null) { //o produto não estava antes na lista de itens do pedido
                                                    itemBrinde = new ItemPedido();
                                                    itemBrinde.setPedido(pedido);
                                                    itemBrinde.setProduto(promocao.getProdLeva());
                                                    itemBrinde.setValorUnitario(promocao.getProdLeva().getValor());
                                                    pedido.getItens().add(itemBrinde);
                                                }
                                                itemBrinde.addQtd(promocao.getQtdLeva());
                                                itemBrinde.recalcularDesconto();
                                            }
                                        }
                                        break;
                                }

                            }
                            atualizar();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(TelaVenda.this, ex);
                        }
                        break;
                    case 2: //Vendedor
                        if (!pedido.getItens().isEmpty()) {
                            return;
                        }
                        try {
                            String cpf = Utilitarios.mascararCpf(barcode);
                            pedido.setVendedor(nVendedor.consultarByCPF(cpf));
                            jLabelVendedor.setText(pedido.getVendedor().getNome());
                        } catch (Exception ex) {
                            pedido.setVendedor(null);
                            jLabelVendedor.setText("\"\"");
                            JOptionPane.showMessageDialog(TelaVenda.this, ex);
                        }
                        break;
                }
            }
        });
        atualizar();
    }

    private ItemPedido getItem(Produto produto) {
        for (ItemPedido item : pedido.getItens()) {
            if (item.getProduto().getId() == produto.getId()) {
                return item;
            }
        }
        return null;
    }

    private List<Promocao> getPromocoesDoProduto(Produto produto) {
        List<Promocao> lista = new ArrayList();
        for (Promocao promocao : promocoes) {
            if (promocao.getProdPaga().getId() == produto.getId()) {
                lista.add(promocao);
            }
        }
        return lista;
    }

    private void atualizar() {
        pedido.recalcularValorTotal();
        jLabelValorTotal.setText(String.format("R$ %.2f", pedido.getValorTotal()));
        preencherTabela();
    }

    private void preencherTabela() {
        try {
            Iterator<ItemPedido> itens = pedido.getItens().iterator();
            Vector colunas = new Vector();
            colunas.add("Produto");
            colunas.add("Quantidade");
            colunas.add("Valor Unitário");
            colunas.add("Desconto");
            Vector detalhes = new Vector();
            while (itens.hasNext()) {
                ItemPedido item = itens.next();
                Vector linha = new Vector();
                linha.add(item.getProduto().getDescricao());
                linha.add(item.getQtd());
                linha.add(String.format("R$ %.2f", item.getValorUnitario()));
                linha.add(String.format("%.2f", item.getDesconto()) + "%");
                detalhes.add(linha);
            }
            jTableItens.setModel(new DefaultTableModel(detalhes, colunas) {
                public boolean isCellEditable(int i, int j) {
                    return false;
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItens = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButtonFinalizar = new javax.swing.JButton();
        jLabelValorTotal = new javax.swing.JLabel();
        jButtonCancelar = new javax.swing.JButton();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<FormaPagamento>();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelVendedor = new javax.swing.JLabel();

        setClosable(true);
        setAlignmentX(100.0F);
        setAlignmentY(100.0F);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Realizar Venda");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(410, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTableItens.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTableItens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableItens);
        if (jTableItens.getColumnModel().getColumnCount() > 0) {
            jTableItens.getColumnModel().getColumn(0).setResizable(false);
            jTableItens.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTableItens.getColumnModel().getColumn(1).setResizable(false);
            jTableItens.getColumnModel().getColumn(1).setPreferredWidth(430);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Valor Total");

        jButtonFinalizar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFinalizar.setText("Finalizar");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotal.setText("0");

        jButtonCancelar.setBackground(new java.awt.Color(210, 50, 45));
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jComboBoxFormaPagamento.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        try {
            for (FormaPagamento formaPagamento : nFormaPagamento.listar()) {
                jComboBoxFormaPagamento.addItem(formaPagamento);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Forma de Pagamento");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Vendedor");

        jLabelVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelVendedor.setText("\"\"");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelVendedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addComponent(jButtonFinalizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                        .addComponent(jButtonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if (!pedido.getItens().isEmpty() && Mensagem.msg14(this) != 0) {
            return;
        }
        limparTela();
        Mensagem.msg13(this);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void limparTela() {
        Vendedor vendedor = pedido.getVendedor();
        pedido = new Pedido();
        promocoes = new ArrayList<>();
        pedido.setVendedor(vendedor);
        jComboBoxFormaPagamento.setSelectedIndex(0);
        atualizar();
    }

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        try {
            pedido.setFormaPagamento((FormaPagamento) jComboBoxFormaPagamento.getSelectedItem());
            if (Mensagem.msg15(this) != 0) {
                return;
            }
            new NPedido().salvar(pedido);
            Mensagem.msg12(this);
            limparTela();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jButtonFinalizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JComboBox<FormaPagamento> jComboBoxFormaPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItens;
    // End of variables declaration//GEN-END:variables

}
