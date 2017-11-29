package apresentacao.Cadastro;

import apresentacao.Consulta.TelaConsultaPromocao;
import entidade.Promocao;
import entidade.TipoPromocao;
import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.NPromocao;

public class CadastroPromocao extends javax.swing.JFrame {

    Promocao promocao = null;
    TelaConsultaPromocao frmPai;

    public CadastroPromocao() {
        initComponents();
        setLocationRelativeTo(null);
        atualizarCampos(TipoPromocao.DESCONTO);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFundo = new javax.swing.JPanel();
        jPanelSuperior = new javax.swing.JPanel();
        jLabelAcao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldDescricao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jComboBoxTipo = new javax.swing.JComboBox<TipoPromocao>();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jLabelDesconto = new javax.swing.JLabel();
        jLabelQtdPaga = new javax.swing.JLabel();
        jTextFieldQtdPaga = new javax.swing.JTextField();
        jTextFieldQtdLeva = new javax.swing.JTextField();
        jLabelQtdLeva = new javax.swing.JLabel();
        jDatePickerInicio = new org.jdatepicker.JDatePicker();
        jDatePickerFim = new org.jdatepicker.JDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldProdPaga = new javax.swing.JTextField();
        jLabelProdPaga = new javax.swing.JLabel();
        jLabelProdLeva = new javax.swing.JLabel();
        jTextFieldProdLeva = new javax.swing.JTextField();
        jLabelValorMinimo = new javax.swing.JLabel();
        jTextFieldValorMinimo = new javax.swing.JTextField();
        jButtonPesquisarProdPaga = new javax.swing.JButton();
        jButtonPesquisarProdLeva = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelFundo.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelAcao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelAcao.setText("Nova Promoção");

        javax.swing.GroupLayout jPanelSuperiorLayout = new javax.swing.GroupLayout(jPanelSuperior);
        jPanelSuperior.setLayout(jPanelSuperiorLayout);
        jPanelSuperiorLayout.setHorizontalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelAcao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelSuperiorLayout.setVerticalGroup(
            jPanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSuperiorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelAcao)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Campos Obrigatórios *");

        jLabel3.setText("Descricao*");

        jButtonSalvar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        for (TipoPromocao tipoPromocao : TipoPromocao.values()) {
            jComboBoxTipo.addItem(tipoPromocao);
        }
        jComboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Tipo*");

        jLabelDesconto.setText("Desconto*");

        jLabelQtdPaga.setText("Qtd. Paga*");

        jTextFieldQtdPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQtdPagaActionPerformed(evt);
            }
        });

        jLabelQtdLeva.setText("Qtd. Leva*");

        jLabel7.setText("Inicio*");

        jLabel8.setText("Fim*");

        jTextFieldProdPaga.setEditable(false);
        jTextFieldProdPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdPagaActionPerformed(evt);
            }
        });

        jLabelProdPaga.setText("Produto Paga*");

        jLabelProdLeva.setText("Produto Leva*");

        jTextFieldProdLeva.setEditable(false);
        jTextFieldProdLeva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdLevaActionPerformed(evt);
            }
        });

        jLabelValorMinimo.setText("Valor mínimo*");

        jButtonPesquisarProdPaga.setText("Buscar");
        jButtonPesquisarProdPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarProdPagaActionPerformed(evt);
            }
        });

        jButtonPesquisarProdLeva.setText("Buscar");

        javax.swing.GroupLayout jPanelFundoLayout = new javax.swing.GroupLayout(jPanelFundo);
        jPanelFundo.setLayout(jPanelFundoLayout);
        jPanelFundoLayout.setHorizontalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addGap(0, 0, 0))
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabelDesconto)
                    .addComponent(jLabelProdPaga)
                    .addComponent(jLabelProdLeva)
                    .addComponent(jLabelQtdPaga)
                    .addComponent(jLabelQtdLeva)
                    .addComponent(jLabelValorMinimo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldValorMinimo)
                    .addComponent(jTextFieldQtdLeva)
                    .addComponent(jTextFieldQtdPaga)
                    .addComponent(jTextFieldDesconto)
                    .addComponent(jDatePickerFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDatePickerInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldDescricao)
                    .addComponent(jComboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldProdLeva)
                            .addComponent(jTextFieldProdPaga))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonPesquisarProdLeva)
                            .addComponent(jButtonPesquisarProdPaga))
                        .addGap(1, 1, 1)))
                .addGap(50, 50, 50))
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jButtonSalvar)
                .addGap(173, 173, 173))
        );
        jPanelFundoLayout.setVerticalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDatePickerInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDatePickerFim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDesconto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProdPaga)
                    .addComponent(jTextFieldProdPaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisarProdPaga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProdLeva)
                    .addComponent(jTextFieldProdLeva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisarProdLeva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQtdPaga)
                    .addComponent(jTextFieldQtdPaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQtdLeva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelQtdLeva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValorMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelValorMinimo))
                .addGap(18, 18, 18)
                .addComponent(jButtonSalvar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        try {
            if (promocao == null) {
                promocao = new Promocao();
            }
            promocao.setDescricao(jTextFieldDescricao.getText());
            promocao.setTipo((TipoPromocao) jComboBoxTipo.getSelectedItem());
            System.out.println(jDatePickerInicio.getFormattedTextField().getText());
            NPromocao neg = new NPromocao();

            //neg.salvar(promocao);
            if (promocao.getId() > 0) {
                frmPai.atualizar();
                this.dispose();
            } else {
                limparCampos();
            }

        } catch (Exception ex) {
            Logger.getLogger(CadastroPromocao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldProdPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdPagaActionPerformed

    }//GEN-LAST:event_jTextFieldProdPagaActionPerformed

    private void jTextFieldProdLevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdLevaActionPerformed

    }//GEN-LAST:event_jTextFieldProdLevaActionPerformed

    private void jComboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoActionPerformed
        atualizarCampos((TipoPromocao) jComboBoxTipo.getSelectedItem());
    }//GEN-LAST:event_jComboBoxTipoActionPerformed

    private void jTextFieldQtdPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQtdPagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldQtdPagaActionPerformed

    private void jButtonPesquisarProdPagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarProdPagaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonPesquisarProdPagaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton jButtonPesquisarProdLeva;
    javax.swing.JButton jButtonPesquisarProdPaga;
    javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<TipoPromocao> jComboBoxTipo;
    org.jdatepicker.JDatePicker jDatePickerFim;
    org.jdatepicker.JDatePicker jDatePickerInicio;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel7;
    javax.swing.JLabel jLabel8;
    javax.swing.JLabel jLabelAcao;
    javax.swing.JLabel jLabelDesconto;
    javax.swing.JLabel jLabelProdLeva;
    javax.swing.JLabel jLabelProdPaga;
    javax.swing.JLabel jLabelQtdLeva;
    javax.swing.JLabel jLabelQtdPaga;
    javax.swing.JLabel jLabelValorMinimo;
    javax.swing.JPanel jPanelFundo;
    javax.swing.JPanel jPanelSuperior;
    javax.swing.JTextField jTextFieldDesconto;
    javax.swing.JTextField jTextFieldDescricao;
    javax.swing.JTextField jTextFieldProdLeva;
    javax.swing.JTextField jTextFieldProdPaga;
    javax.swing.JTextField jTextFieldQtdLeva;
    javax.swing.JTextField jTextFieldQtdPaga;
    javax.swing.JTextField jTextFieldValorMinimo;
    // End of variables declaration//GEN-END:variables

    public void atualizarAposSalvar(TelaConsultaPromocao aux) {
        this.frmPai = aux;
    }

    public void alteracao(String acao, Promocao promocao) throws Exception {
        NPromocao neg = new NPromocao();
        this.promocao = promocao;

        jLabelAcao.setText(acao);

        jTextFieldDescricao.setText(promocao.getDescricao());
        jComboBoxTipo.setSelectedItem(promocao.getTipo());
        //jDatePickerInicio.set
    }

    public void limparCampos() {
        jTextFieldDescricao.setText("");

    }

    private void atualizarCampos(TipoPromocao tipoPromocao) {
        Dimension dimension = new Dimension(437, 0);
        switch (tipoPromocao) {
            case DESCONTO:
                jLabelDesconto.setVisible(true);
                jTextFieldDesconto.setVisible(true);
                jLabelQtdPaga.setVisible(true);
                jTextFieldQtdPaga.setVisible(true);
                jLabelQtdLeva.setVisible(false);
                jTextFieldQtdLeva.setVisible(false);
                jLabelProdPaga.setVisible(true);
                jTextFieldProdPaga.setVisible(true);
                jButtonPesquisarProdPaga.setVisible(true);
                jLabelProdLeva.setVisible(false);
                jTextFieldProdLeva.setVisible(false);
                jButtonPesquisarProdLeva.setVisible(false);
                jLabelValorMinimo.setVisible(false);
                jTextFieldValorMinimo.setVisible(false);
                dimension.height = 478;
                break;
            case QUANTIDADE:
                jLabelDesconto.setVisible(false);
                jTextFieldDesconto.setVisible(false);
                jLabelQtdPaga.setVisible(true);
                jTextFieldQtdPaga.setVisible(true);
                jLabelQtdLeva.setVisible(true);
                jTextFieldQtdLeva.setVisible(true);
                jLabelProdPaga.setVisible(true);
                jTextFieldProdPaga.setVisible(true);
                jButtonPesquisarProdPaga.setVisible(true);
                jLabelProdLeva.setVisible(true);
                jTextFieldProdLeva.setVisible(true);
                jButtonPesquisarProdLeva.setVisible(true);
                jLabelValorMinimo.setVisible(false);
                jTextFieldValorMinimo.setVisible(false);
                dimension.height = 518;
                break;
            default:
                jLabelDesconto.setVisible(true);
                jTextFieldDesconto.setVisible(true);
                jLabelQtdPaga.setVisible(false);
                jTextFieldQtdPaga.setVisible(false);
                jLabelQtdLeva.setVisible(false);
                jTextFieldQtdLeva.setVisible(false);
                jLabelProdPaga.setVisible(false);
                jTextFieldProdPaga.setVisible(false);
                jButtonPesquisarProdPaga.setVisible(false);
                jLabelProdLeva.setVisible(false);
                jTextFieldProdLeva.setVisible(false);
                jButtonPesquisarProdLeva.setVisible(false);
                jLabelValorMinimo.setVisible(true);
                jTextFieldValorMinimo.setVisible(true);
                dimension.height = 438;
        }
        this.setSize(dimension);
        //x 437
        //y 594
        //526 406 446 366
    }

}