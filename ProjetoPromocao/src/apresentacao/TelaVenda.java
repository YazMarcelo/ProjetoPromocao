package apresentacao;

import entidade.ItemPedido;
import entidade.Pedido;
import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import negocio.NProduto;
import negocio.NPromocao;
import negocio.NVendedor;
import util.BarcodeReader;
import util.BarcodeReader.BarcodeListener;
import util.Utilitarios;

public class TelaVenda extends javax.swing.JInternalFrame {

    Pedido pedido;
    List<Promocao> promocoes;

    NVendedor nVendedor;
    NProduto nProduto;
    NPromocao nPromocao;

    BarcodeReader barcodeReader;

    public TelaVenda() {
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
//                        if (pedido.getVendedor() == null) {
//                            return;
//                        }
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
                            if (promocoesDoProduto.isEmpty()) { //nenhuma promoção cadastrada para o produto
                                pedido.addValorTotal(produto.getValor());
                            } else { //há promoções cadastradas para o produto
                                Promocao promocao;
                                if (promocoesDoProduto.size() == 1) {
                                    promocao = promocoesDoProduto.get(0);
                                } else {
                                    Promocao promocaoQuantidade = promocoesDoProduto.get(0).getTipo() == TipoPromocao.QUANTIDADE ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                    Promocao promocaoDesconto = promocoesDoProduto.get(0).getTipo() == TipoPromocao.DESCONTO ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                    if (item.getQtd() >= promocaoQuantidade.getQtdPaga()) {
                                        promocao = promocaoQuantidade;
                                        item.setPromocao(promocaoQuantidade);
                                        if (item.getQtd() == promocaoQuantidade.getQtdPaga()) {
                                            item.setDesconto(0);
                                            pedido.setValorTotal(pedido.recalcularValorTotal());
                                        }
                                    } else if (item.getQtd() >= promocaoDesconto.getQtdPaga()) {
                                        promocao = promocaoDesconto;
                                        item.setPromocao(promocaoDesconto);
                                    } else {
                                        pedido.addValorTotal(produto.getValor());
                                        jLabelValorTotal.setText(String.format("R$ %.2f", pedido.getValorTotal()));
                                        preencherTabela();
                                        return;
                                    }
                                }
                                switch (promocao.getTipo()) {
                                    case DESCONTO:
                                        double valorDesconto = 0;
                                        if (item.getQtd() >= promocao.getQtdPaga()) {
                                            item.recalcularDesconto();
                                            valorDesconto = produto.getValor() * (promocao.getDesconto() / 100);
                                        }
                                        pedido.addValorTotal(produto.getValor() - valorDesconto);
                                        break;
                                    case QUANTIDADE:
                                        if ((item.getQtd() - (item.getQtd() / promocao.getQtdPaga())) % promocao.getQtdPaga() == 0) {
                                            if (promocao.getProdPaga().equals(promocao.getProdLeva())) {
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
                                        pedido.addValorTotal(produto.getValor());
                                        break;
                                }

                            }
                            jLabelValorTotal.setText(String.format("R$ %.2f", pedido.getValorTotal()));
                            preencherTabela();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(TelaVenda.this, ex);
                        }
                        break;
                    case 2: //Vendedor
                        if (!pedido.getItens().isEmpty()) {
                            return;
                        }
                        try {
                            pedido.setVendedor(nVendedor.consultarByCPF(barcode));
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
                linha.add(item.getValorUnitario());
                linha.add(item.getDesconto() + "%");
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
        jLabel3 = new javax.swing.JLabel();
        jLabelVendedor = new javax.swing.JLabel();
        jButtonFinalizar = new javax.swing.JButton();
        jLabelValorTotal = new javax.swing.JLabel();
        jButtonLimpar = new javax.swing.JButton();

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

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
        jLabel2.setText("Valor Total:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Vendedor:");

        jLabelVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelVendedor.setText("\"\"");

        jButtonFinalizar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFinalizar.setText("Finalizar");

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelValorTotal.setText("0");

        jButtonLimpar.setBackground(new java.awt.Color(210, 50, 45));
        jButtonLimpar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonLimpar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLimpar.setText("Limpar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addComponent(jLabelValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabelValorTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabelVendedor))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItens;
    // End of variables declaration//GEN-END:variables

}
