/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao.Cadastro;

import apresentacao.Consulta.TelaConsultaVendedor;
import entidade.Vendedor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import negocio.NVendedor;
import util.Mensagem;
import util.Utilitarios;

/**
 *
 * @author aluno
 */
public class CadastroVendedor extends javax.swing.JFrame {

    int idAlteracao = 0;
    String descricao;
    TelaConsultaVendedor aux;
    Vendedor vendedor = null;

    /**
     * Creates new form CadastroMarca
     */
    public CadastroVendedor() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFundo = new javax.swing.JPanel();
        jPanelSuperior = new javax.swing.JPanel();
        jLabelAcao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jFormattedTextFieldCPF = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldTef1 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldLogradouro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldComp = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldBairro = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextFieldCEP = new javax.swing.JFormattedTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jFormattedTextFieldCell = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanelFundo.setBackground(new java.awt.Color(255, 255, 255));

        jPanelSuperior.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelAcao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabelAcao.setText("Novo Vendedor");

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

        jButtonSalvar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonSalvar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome *");

        jLabel5.setText("CPF *");

        try {
            jFormattedTextFieldCPF.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCPF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCPFKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCPFKeyTyped(evt);
            }
        });

        jLabel7.setText("Telefone");

        try {
            jFormattedTextFieldTef1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) ####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTef1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextFieldTef1MouseClicked(evt);
            }
        });
        jFormattedTextFieldTef1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTef1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldTef1KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Endereço");

        jLabel9.setText("Logradouro");

        jLabel10.setText("Complemento");

        jLabel11.setText("Número");

        jLabel12.setText("Bairro");

        jLabel13.setText("CEP");

        try {
            jFormattedTextFieldCEP.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel14.setText("E-mail");

        jLabel15.setText("Celular");

        try {
            jFormattedTextFieldCell.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##) #####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jFormattedTextFieldCellMouseClicked(evt);
            }
        });
        jFormattedTextFieldCell.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCellKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldCellKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanelFundoLayout = new javax.swing.GroupLayout(jPanelFundo);
        jPanelFundo.setLayout(jPanelFundoLayout);
        jPanelFundoLayout.setHorizontalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldTef1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextFieldCell, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))))
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(jButtonSalvar))
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(jTextFieldBairro, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldComp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNumero, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldLogradouro))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                    .addComponent(jFormattedTextFieldCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldCell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(11, 11, 11)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldTef1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(31, 31, 31)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jTextFieldLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(50, 50, 50)
                .addComponent(jButtonSalvar)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelFundo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private boolean validarCamposObrigatorios(JTextField... campos) {
        for (JTextField campo : campos) {
            if (campo.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        try {
            if (!validarCamposObrigatorios(jTextFieldNome) || Utilitarios.retirarMascara(jFormattedTextFieldCPF.getText()).equals("")) {
                Mensagem.msg10(this);
                return;
            }

            Vendedor esp = new Vendedor();
            NVendedor neg = new NVendedor();

            esp.setId(idAlteracao);
            esp.setNome(jTextFieldNome.getText());
            esp.setCpf(jFormattedTextFieldCPF.getText());
            esp.setCelular(jFormattedTextFieldCell.getText());
            esp.setTelefone(jFormattedTextFieldTef1.getText());
            esp.setEmail(jTextFieldEmail.getText());
            esp.setLogradouro(jTextFieldLogradouro.getText());
            esp.setComplemento(jTextFieldComp.getText());
            esp.setNumero(jTextFieldNumero.getText());
            esp.setBairro(jTextFieldBairro.getText());
            esp.setCep(jFormattedTextFieldCEP.getText());

            neg.salvar(esp);

            if (idAlteracao > 0) {
                aux.atualizar();
                this.dispose();
            } else {
                limparCampos();
            }
            Mensagem.msg01(this);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jFormattedTextFieldCPFKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCPFKeyPressed

    }//GEN-LAST:event_jFormattedTextFieldCPFKeyPressed

    private void jFormattedTextFieldCPFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCPFKeyTyped
        Utilitarios.someteNumeros(evt);
        if (Utilitarios.retirarMascara(jFormattedTextFieldCPF.getText()).length() == 11) {
            if (Utilitarios.validarCPF(Utilitarios.retirarMascara(jFormattedTextFieldCPF.getText()))) {
                //Mensagem.msg06();
                jFormattedTextFieldCPF.setText("");
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldCPFKeyTyped

    private void jFormattedTextFieldTef1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTef1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTef1MouseClicked

    private void jFormattedTextFieldTef1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTef1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldTef1KeyPressed

    private void jFormattedTextFieldTef1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTef1KeyTyped
        Utilitarios.someteNumeros(evt);
    }//GEN-LAST:event_jFormattedTextFieldTef1KeyTyped

    private void jFormattedTextFieldCellMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCellMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCellMouseClicked

    private void jFormattedTextFieldCellKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCellKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCellKeyPressed

    private void jFormattedTextFieldCellKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCellKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCellKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JFormattedTextField jFormattedTextFieldCEP;
    private javax.swing.JFormattedTextField jFormattedTextFieldCPF;
    private javax.swing.JFormattedTextField jFormattedTextFieldCell;
    private javax.swing.JFormattedTextField jFormattedTextFieldTef1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAcao;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldComp;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLogradouro;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    // End of variables declaration//GEN-END:variables
    private javafx.scene.layout.Pane pane;

    public void atualizarAposSalvar(TelaConsultaVendedor aux) {
        this.aux = aux;
    }

    public void alteracao(String acao, int id) throws Exception {
        NVendedor neg = new NVendedor();
        Vendedor objeto = (Vendedor) neg.consultar(id);
        jTextFieldBairro.setText(objeto.getBairro());
        jTextFieldComp.setText(objeto.getComplemento());
        jTextFieldEmail.setText(objeto.getEmail());
        jTextFieldLogradouro.setText(objeto.getLogradouro());
        jTextFieldNome.setText(objeto.getNome());
        jFormattedTextFieldCEP.setText(objeto.getCep());
        jFormattedTextFieldCPF.setText(objeto.getCpf());
        jFormattedTextFieldTef1.setText(objeto.getTelefone());
        jFormattedTextFieldCell.setText(objeto.getCelular());
        jTextFieldNumero.setText(objeto.getNumero());

        jLabelAcao.setText(acao);
        this.idAlteracao = objeto.getId();
    }

    public void limparCampos() {
        jTextFieldBairro.setText("");
        jTextFieldComp.setText("");
        jTextFieldEmail.setText("");
        jTextFieldLogradouro.setText("");
        jTextFieldNome.setText("");
        jTextFieldNumero.setText("");
        jFormattedTextFieldCEP.setText("");
        jFormattedTextFieldCPF.setText("");
        jFormattedTextFieldTef1.setText("");
        jFormattedTextFieldCell.setText("");
    }

}
