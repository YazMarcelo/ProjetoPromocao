/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.Cadastro;

import apresentacao.Consulta.TelaConsultaProduto;
import entidade.Produto;
import entidade.UnidadeMedida;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import negocio.NProduto;
import negocio.NUnidadeMedida;
import util.Mensagem;
import util.Utilitarios;

/**
 *
 * @author aluno
 */
public class CadastroProduto extends javax.swing.JFrame {

    int idAlteracao = 0;
    TelaConsultaProduto frmPai;
    
    Produto produto = null;

    
    public CadastroProduto(TelaConsultaProduto frmPai) {
        initComponents();
        this.frmPai = frmPai;
        produto = new Produto();
        setLocationRelativeTo(frmPai);
        adicionandoDadosComboBox();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFundo = new javax.swing.JPanel();
        jPanelSuperior = new javax.swing.JPanel();
        jLabelAcao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldQtd = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldSaldoEstoque = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jComboBoxUnidadeMedida = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jDatePickerFabricacao = new org.jdatepicker.JDatePicker();
        jDatePickerVencimento = new org.jdatepicker.JDatePicker();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelFundo.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelAcao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelAcao.setText("Novo Produto");

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

        jLabel3.setText("Data de Fabricação");

        jButtonSalvar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome*");

        jLabel5.setText("Data de Vencimento");

        jLabel6.setText("Por Produto");

        jLabel8.setText("Saldo em Estoque");

        jLabel9.setText("Valor");

        jLabel10.setText("Desconto MAX %");

        jDatePickerFabricacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatePickerFabricacaoActionPerformed(evt);
            }
        });

        jDatePickerVencimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDatePickerVencimentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelFundoLayout = new javax.swing.GroupLayout(jPanelFundo);
        jPanelFundo.setLayout(jPanelFundoLayout);
        jPanelFundoLayout.setHorizontalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addGap(168, 168, 168))
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(4, 4, 4)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldValor)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                                .addComponent(jTextFieldQtd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldSaldoEstoque)
                            .addComponent(jTextFieldDesconto)
                            .addComponent(jTextFieldNome)
                            .addComponent(jDatePickerFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDatePickerVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanelFundoLayout.setVerticalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDatePickerFabricacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDatePickerVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldQtd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSaldoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        try {
            NProduto neg = new NProduto();

            produto.setId(idAlteracao);
            produto.setDescricao(jTextFieldNome.getText());
            produto.setValor(Double.parseDouble(jTextFieldValor.getText()));
            produto.setDataFabricacao(Utilitarios.stringToDate(jDatePickerFabricacao.getFormattedTextField().getText()));
            produto.setDataVencimento(Utilitarios.stringToDate(jDatePickerVencimento.getFormattedTextField().getText()));

            produto.setQtdUnidade(Integer.parseInt(jTextFieldQtd.getText()));
            produto.setUnidadeMedida((UnidadeMedida) jComboBoxUnidadeMedida.getSelectedItem());
            produto.setSaldoEstoque(Integer.parseInt(jTextFieldSaldoEstoque.getText()));
            produto.setDesconto(Integer.parseInt(jTextFieldDesconto.getText()));

            neg.salvar(produto);

            if (idAlteracao > 0) {
                frmPai.atualizar();
                this.dispose();
            } else {
                limparCampos();
            }
            Mensagem.msg01(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jDatePickerFabricacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatePickerFabricacaoActionPerformed
        try {
            produto.setDataFabricacao(Utilitarios.stringToDate(jDatePickerFabricacao.getFormattedTextField().getText()));
            if (produto.getDataVencimento() != null && produto.getDataVencimento().getTime() < produto.getDataFabricacao().getTime()) {
                jDatePickerFabricacao.getFormattedTextField().setText("");
            }
        } catch (ParseException ex) {

        }
    }//GEN-LAST:event_jDatePickerFabricacaoActionPerformed

    private void jDatePickerVencimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDatePickerVencimentoActionPerformed
        try {
            produto.setDataVencimento(Utilitarios.stringToDate(jDatePickerVencimento.getFormattedTextField().getText()));
            if (produto.getDataFabricacao() != null && produto.getDataVencimento().getTime() < produto.getDataFabricacao().getTime()) {
                Mensagem.msg06(this); //Dado inválido
                jDatePickerVencimento.getFormattedTextField().setText("");
            }
        } catch (ParseException ex) {
        }
    }//GEN-LAST:event_jDatePickerVencimentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox jComboBoxUnidadeMedida;
    private org.jdatepicker.JDatePicker jDatePickerFabricacao;
    private org.jdatepicker.JDatePicker jDatePickerVencimento;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAcao;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldQtd;
    private javax.swing.JTextField jTextFieldSaldoEstoque;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables

    public void alteracao(String acao, int id) throws Exception {
        NProduto neg = new NProduto();
        Produto objeto = (Produto) neg.consultar(id);

        jLabelAcao.setText(acao);
        this.idAlteracao = objeto.getId();
        jTextFieldNome.setText(objeto.getDescricao());
        jTextFieldValor.setText(String.valueOf(objeto.getValor()));

        jDatePickerVencimento.getFormattedTextField().setText(Utilitarios.dateBRFormat(String.valueOf(objeto.getDataVencimento())));
        jDatePickerFabricacao.getFormattedTextField().setText(Utilitarios.dateBRFormat(String.valueOf(objeto.getDataFabricacao())));

        jTextFieldQtd.setText(String.valueOf(objeto.getQtdUnidade()));
        jTextFieldSaldoEstoque.setText(String.valueOf(objeto.getSaldoEstoque()));
        jComboBoxUnidadeMedida.setSelectedItem(objeto.getUnidadeMedida());
        jTextFieldDesconto.setText(String.valueOf(objeto.getDesconto()));
    }

    public void adicionandoDadosComboBox() {
        try {
            ArrayList<UnidadeMedida> lista;
            NUnidadeMedida neg = new NUnidadeMedida();
            lista = neg.listar();

            for (UnidadeMedida objeto : lista) {
                jComboBoxUnidadeMedida.addItem(objeto);
            }

        } catch (Exception ex) {

        }
    }

    public void limparCampos() {
        jTextFieldNome.setText("");
        jTextFieldValor.setText("");
        jDatePickerFabricacao.getFormattedTextField().setText("");
        jDatePickerVencimento.getFormattedTextField().setText("");
        jTextFieldQtd.setText("");
        jComboBoxUnidadeMedida.setSelectedIndex(0);
        jTextFieldSaldoEstoque.setText("");
        jTextFieldDesconto.setText("");
    }

}
