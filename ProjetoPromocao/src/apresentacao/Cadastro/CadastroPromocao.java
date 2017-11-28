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
        jComboBoxTipo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

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
                .addContainerGap(205, Short.MAX_VALUE))
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

        jLabel4.setText("Tipo*");

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
                        .addGap(78, 78, 78)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(4, 4, 4)
                        .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))))
                .addGap(0, 79, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(23, 23, 23)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<TipoPromocao> jComboBoxTipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAcao;
    private javax.swing.JPanel jPanelFundo;
    private javax.swing.JPanel jPanelSuperior;
    private javax.swing.JTextField jTextFieldDescricao;
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

}
