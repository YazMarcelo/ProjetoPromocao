package apresentacao;

import entidade.FormaPagamento;
import entidade.ItemPedido;
import entidade.Pedido;
import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import entidade.Vendedor;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_DELETE;
import static java.awt.event.KeyEvent.VK_INSERT;
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
    List<Promocao> promocoesDoPedido = null;

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
                            if (jToggleButtonIncluir.isSelected()) {
                                List<Promocao> promocoesDoProduto;
                                ItemPedido item = getItem(produto);
                                if (item == null) {
                                    item = new ItemPedido();
                                    item.setPedido(pedido);
                                    item.setProduto(produto);
                                    item.setValorUnitario(produto.getValor());
                                    pedido.getItens().add(item);
                                    promocoesDoProduto = nPromocao.listarByProduto(produto.getId());
                                    promocoes.addAll(promocoesDoProduto);
                                } else {
                                    promocoesDoProduto = getPromocoesDoProduto(produto);
                                }
                                if (item.getQtd() + item.getQtdBrinde() + 1 > produto.getSaldoEstoque()) {
                                    Mensagem.msg19(TelaVenda.this);
                                    return;
                                }
                                item.addQtd(1);
                                if (!promocoesDoProduto.isEmpty()) { //há promoções cadastradas para o produto
                                    if (promocoesDoProduto.size() == 1) {
                                        Promocao promocao = promocoesDoProduto.get(0);
                                        if (item.getPromocao() == null && item.getQtd() >= promocao.getQtdPaga()) {
                                            item.setPromocao(promocao);
                                        }
                                    } else {
                                        Promocao promocaoQuantidade = promocoesDoProduto.get(0).getTipo() == TipoPromocao.QUANTIDADE ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                        Promocao promocaoDesconto = promocoesDoProduto.get(0).getTipo() == TipoPromocao.DESCONTO ? promocoesDoProduto.get(0) : promocoesDoProduto.get(1);
                                        if (item.getQtd() >= promocaoQuantidade.getQtdPaga()) {
                                            removerPromocaoDaLista(promocaoDesconto);
                                            if (item.getPromocao() == null) {
                                                item.setPromocao(promocaoQuantidade);
                                            } else {
                                                if ((item.getPromocao().getTipo() == TipoPromocao.DESCONTO)
                                                        || item.getPromocao().getProdLeva().getId() == promocaoQuantidade.getProdLeva().getId()) {
                                                    item.setPromocao(promocaoQuantidade);
                                                } else if (item.getPromocao().getProdLeva().getId() != promocaoQuantidade.getProdLeva().getId()) {
                                                    ItemPedido itemBrinde = getItem(promocaoQuantidade.getProdLeva());
                                                    if (itemBrinde == null) { //o produto não estava antes na lista de itens do pedido
                                                        itemBrinde = new ItemPedido();
                                                        itemBrinde.setPedido(pedido);
                                                        itemBrinde.setProduto(promocaoQuantidade.getProdLeva());
                                                        itemBrinde.setValorUnitario(promocaoQuantidade.getProdLeva().getValor());
                                                        pedido.getItens().add(itemBrinde);
                                                    }
                                                }
                                            }
                                        } else if (item.getPromocao() == null && item.getQtd() >= promocaoDesconto.getQtdPaga()) {
                                            item.setPromocao(promocaoDesconto);
                                        }
                                    }
                                    if (item.getPromocao() != null) {
                                        Promocao promocao = item.getPromocao();
                                        switch (item.getPromocao().getTipo()) {
                                            case DESCONTO:
                                                if (item.getQtd() >= promocao.getQtdPaga()) {
                                                    item.recalcularDesconto();
                                                }
                                                break;
                                            case QUANTIDADE:
                                                if (item.getQtd() % promocao.getQtdPaga() == 0) {
                                                    if (promocao.getProdPaga().getId() == promocao.getProdLeva().getId()) {
                                                        item.addQtdBrinde(promocao.getQtdLeva());
                                                        item.recalcularDesconto();
                                                    } else { //o produto leva não é o mesmo que o produto paga
                                                        ItemPedido itemBrinde = getItem(promocao.getProdLeva());
                                                        itemBrinde.addQtdBrinde(promocao.getQtdLeva());
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                }
                            } else {
                                ItemPedido item = getItem(produto);
                                if (item != null) {
                                    if (Mensagem.msg17(TelaVenda.this) == 0) {
                                        if (item.getQtdBrinde() == 0 || (item.getQtdBrinde() > 0 && Mensagem.msg22(TelaVenda.this) == 0)) {
                                            removerItem(produto);
                                        }
                                        jToggleButtonIncluir.doClick();
                                    }
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
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            @Override
            public void eventDispatched(AWTEvent event) {
                if (!TelaVenda.this.isVisible()) {
                    return;
                }
                try {
                    KeyEvent evt = (KeyEvent) event;
                    if (evt.getID() == KeyEvent.KEY_PRESSED) {
                        switch (evt.getKeyCode()) {
                            case VK_INSERT:
                                jToggleButtonIncluir.doClick();
                                break;
                            case VK_DELETE:
                                jToggleButtonExcluir.doClick();
                        }

                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(TelaVenda.this, e);
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
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

    private void removerPromocaoDaLista(Promocao promocaoRemovida) {
        for (int i = 0; i < promocoes.size(); i++) {
            if (promocaoRemovida.getId() == promocoes.get(i).getId()) {
                promocoes.remove(i);
                return;
            }
        }
    }

    private void removerItem(Produto produto) {
        for (int i = 0; i < pedido.getItens().size(); i++) {
            if (pedido.getItens().get(i).getProduto().getId() == produto.getId()) {
                pedido.getItens().remove(i);
                return;
            }
        }
    }

    private void atualizar() {
        pedido.recalcularValorTotal();
        jLabelValorTotal.setText(String.format("R$ %.2f", pedido.getValorTotal()));
        jLabelDescontoGeral.setText(String.format("%.2f", pedido.getDescontoGeral()) + "%");
        preencherTabelaItenseDescontoGeral();
    }

    private void preencherTabelaItenseDescontoGeral() {
        try {
            Iterator<ItemPedido> itens = pedido.getItens().iterator();
            Vector colunas = new Vector();
            colunas.add("Produto");
            colunas.add("Quantidade");
            colunas.add("Valor Unitário");
            colunas.add("Desconto");
            colunas.add("Valor Total");
            Vector detalhes = new Vector();
            while (itens.hasNext()) {
                ItemPedido item = itens.next();
                if (item.getPromocao() != null && item.getPromocao().getTipo() == TipoPromocao.GERAL) {
                    jLabelDescontoGeral.setText(String.format("- %.2f", item.getDesconto()) + "%");
                } else {
                    Vector linha = new Vector();
                    linha.add(item.getProduto().getDescricao());
                    linha.add(item.getQtd() + item.getQtdBrinde());
                    linha.add(String.format("R$ %.2f", item.getValorUnitario()));
                    linha.add(String.format("%.2f", item.getDesconto()) + "%");
                    linha.add(String.format("R$ %.2f", item.getValorTotal()));
                    detalhes.add(linha);
                }
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
        jLabel2 = new javax.swing.JLabel();
        jLabelValorTotal = new javax.swing.JLabel();
        jComboBoxFormaPagamento = new javax.swing.JComboBox<FormaPagamento>();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelVendedor = new javax.swing.JLabel();
        jButtonSair = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItens = new javax.swing.JTable();
        jToggleButtonIncluir = new javax.swing.JToggleButton();
        jToggleButtonExcluir = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jButtonFinalizar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabelDescontoGeral = new javax.swing.JLabel();

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
                .addContainerGap(412, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Valor Total");

        jLabelValorTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelValorTotal.setText("R$ 0,00");

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

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Vendedor");

        jLabelVendedor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelVendedor.setText("<Bipe seu cracha>");

        jButtonSair.setText("Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabelVendedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSair))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        jToggleButtonIncluir.setBackground(new java.awt.Color(51, 255, 51));
        jToggleButtonIncluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButtonIncluir.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonIncluir.setSelected(true);
        jToggleButtonIncluir.setText("Incluir [INSERT]");
        jToggleButtonIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonIncluirActionPerformed(evt);
            }
        });

        jToggleButtonExcluir.setBackground(new java.awt.Color(255, 51, 51));
        jToggleButtonExcluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButtonExcluir.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButtonExcluir.setText("Excluir [DELETE]");
        jToggleButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButtonExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jToggleButtonIncluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButtonExcluir)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jToggleButtonIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButtonFinalizar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonFinalizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonFinalizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFinalizar.setText("Finalizar");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });

        jButtonCancelar.setBackground(new java.awt.Color(210, 50, 45));
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonCancelar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jButtonFinalizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Desconto");

        jLabelDescontoGeral.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabelDescontoGeral.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabelDescontoGeral.setText("0,00%");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelValorTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFormaPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelDescontoGeral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelValorTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabelDescontoGeral, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        if (pedido.getVendedor() == null) {
            return;
        }
        if (pedido.getItens().isEmpty()) {
            return;
        }
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
        promocoesDoPedido = null;
        pedido.setVendedor(vendedor);
        jComboBoxFormaPagamento.setSelectedIndex(0);
        atualizar();
    }

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        try {
            if (pedido.getVendedor() == null) {
                return;
            }
            if (pedido.getItens().isEmpty()) {
                return;
            }
            if (promocoesDoPedido == null) {
                promocoesDoPedido = nPromocao.listarByValorMinimo(pedido.getValorTotal());
                if (!promocoesDoPedido.isEmpty()) {
                    Mensagem.msg16(this);
                    Promocao promocao = promocoesDoPedido.get(promocoesDoPedido.size() - 1);
                    ItemPedido item = new ItemPedido();
                    item.setPedido(pedido);
                    item.setDesconto(promocao.getDesconto());
                    item.setPromocao(promocao);
                    pedido.getItens().add(item);
                    atualizar();
                } else {
                    promocoesDoPedido = null;
                }
            }
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

    private void jToggleButtonIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonIncluirActionPerformed
        jToggleButtonIncluir.setSelected(true);
        jToggleButtonExcluir.setSelected(false);
    }//GEN-LAST:event_jToggleButtonIncluirActionPerformed

    private void jToggleButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButtonExcluirActionPerformed
        jToggleButtonExcluir.setSelected(true);
        jToggleButtonIncluir.setSelected(false);
    }//GEN-LAST:event_jToggleButtonExcluirActionPerformed

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        if (!pedido.getItens().isEmpty()) {
            Mensagem.msg21(this);
            return;
        }
        pedido.setVendedor(null);
        jLabelVendedor.setText("<Bipe seu cracha>");
    }//GEN-LAST:event_jButtonSairActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JComboBox<FormaPagamento> jComboBoxFormaPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelDescontoGeral;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JLabel jLabelVendedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItens;
    private javax.swing.JToggleButton jToggleButtonExcluir;
    private javax.swing.JToggleButton jToggleButtonIncluir;
    // End of variables declaration//GEN-END:variables

}
