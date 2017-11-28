package apresentacao.Cadastro;

import apresentacao.Consulta.TelaConsultaPromocao;
import entidade.Promocao;
import entidade.TipoPromocao;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.NPromocao;

public class CadastroPromocao extends javax.swing.JFrame {

	int idAlteracao = 0;
	String descricao;
	TelaConsultaPromocao aux;

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
        jTextFieldPorcentagem = new javax.swing.JTextField();
        jLabelPorcentagem = new javax.swing.JLabel();
        jLabelQtdPaga = new javax.swing.JLabel();
        jTextFieldQtdPaga = new javax.swing.JTextField();
        jTextFieldQtdLeva = new javax.swing.JTextField();
        jLabelQtdLeva = new javax.swing.JLabel();
        jDatePickerInicio = new org.jdatepicker.JDatePicker();
        jDatePickerFim = new org.jdatepicker.JDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldProdPaga = new javax.swing.JTextField();
        jButtonPesquisarProdPaga = new javax.swing.JButton();
        jLabelProdPaga = new javax.swing.JLabel();
        jLabelProdLeva = new javax.swing.JLabel();
        jTextFieldProdLeva = new javax.swing.JTextField();
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

        jLabelPorcentagem.setText("Porcentagem");

        jLabelQtdPaga.setText("Qtd. Paga*");

        jTextFieldQtdPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQtdPagaActionPerformed(evt);
            }
        });

        jLabelQtdLeva.setText("Qtd. Leva");

        jLabel7.setText("Inicio*");

        jLabel8.setText("Fim*");

        jTextFieldProdPaga.setEditable(false);
        jTextFieldProdPaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdPagaActionPerformed(evt);
            }
        });

        jButtonPesquisarProdPaga.setText("Pesquisar");

        jLabelProdPaga.setText("Produto Paga*");

        jLabelProdLeva.setText("Produto Leva*");

        jTextFieldProdLeva.setEditable(false);
        jTextFieldProdLeva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdLevaActionPerformed(evt);
            }
        });

        jButtonPesquisarProdLeva.setText("Pesquisar");

        javax.swing.GroupLayout jPanelFundoLayout = new javax.swing.GroupLayout(jPanelFundo);
        jPanelFundo.setLayout(jPanelFundoLayout);
        jPanelFundoLayout.setHorizontalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelFundoLayout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabelProdPaga)
                                    .addComponent(jLabelProdLeva)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelQtdPaga, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelPorcentagem, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelQtdLeva, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                                .addComponent(jTextFieldProdPaga)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPesquisarProdPaga))
                            .addComponent(jDatePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jDatePickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jComboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescricao)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                                .addComponent(jTextFieldProdLeva, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonPesquisarProdLeva))
                            .addComponent(jTextFieldQtdLeva)
                            .addComponent(jTextFieldQtdPaga)
                            .addComponent(jTextFieldPorcentagem))))
                .addGap(80, 80, 80))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSalvar)
                .addGap(168, 168, 168))
        );
        jPanelFundoLayout.setVerticalGroup(
            jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoLayout.createSequentialGroup()
                .addComponent(jPanelSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFundoLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDatePickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDatePickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPorcentagem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQtdPaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelQtdPaga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldQtdLeva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelQtdLeva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldProdPaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisarProdPaga)
                    .addComponent(jLabelProdPaga))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldProdLeva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisarProdLeva)
                    .addComponent(jLabelProdLeva))
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

			Promocao prom = new Promocao();
			NPromocao neg = new NPromocao();

			prom.setId(idAlteracao);
			prom.setDescricao(jTextFieldDescricao.getText());

			neg.salvar(prom);

			if (idAlteracao > 0) {
				aux.atualizar();
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPesquisarProdLeva;
    private javax.swing.JButton jButtonPesquisarProdPaga;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<TipoPromocao> jComboBoxTipo;
    private org.jdatepicker.JDatePicker jDatePickerFim;
    private org.jdatepicker.JDatePicker jDatePickerInicio;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAcao;
    private javax.swing.JLabel jLabelPorcentagem;
    private javax.swing.JLabel jLabelProdLeva;
    private javax.swing.JLabel jLabelProdPaga;
    private javax.swing.JLabel jLabelQtdLeva;
    private javax.swing.JLabel jLabelQtdPaga;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldPorcentagem;
    private javax.swing.JTextField jTextFieldProdLeva;
    private javax.swing.JTextField jTextFieldProdPaga;
    private javax.swing.JTextField jTextFieldQtdLeva;
    private javax.swing.JTextField jTextFieldQtdPaga;
    // End of variables declaration//GEN-END:variables
    private javafx.scene.layout.Pane pane;

	public void atualizarAposSalvar(TelaConsultaPromocao aux) {
		this.aux = aux;
	}

	public void alteracao(String acao, int id) throws Exception {
		NPromocao neg = new NPromocao();
		Promocao objeto = (Promocao) neg.consultar(id);

		jLabelAcao.setText(acao);
		this.idAlteracao = objeto.getId();
		jTextFieldDescricao.setText(objeto.getDescricao());

	}

	public void limparCampos() {
		jTextFieldDescricao.setText("");

	}

	private void atualizarCampos(TipoPromocao tipoPromocao) {
		switch (tipoPromocao) {
			case DESCONTO:
				jLabelPorcentagem.setVisible(true);
				jTextFieldPorcentagem.setVisible(true);
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
				break;
			case QUANTIDADE:
				jLabelPorcentagem.setVisible(false);
				jTextFieldPorcentagem.setVisible(false);
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
				break;
			default:
				jLabelPorcentagem.setVisible(false);
				jTextFieldPorcentagem.setVisible(false);
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
					
		}
	}

}
