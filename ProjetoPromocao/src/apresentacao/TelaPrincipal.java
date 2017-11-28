package apresentacao;

import apresentacao.Consulta.TelaConsultaExemplo;
import apresentacao.Consulta.TelaConsultaFormaPagamento;
import apresentacao.Consulta.TelaConsultaProduto;
import apresentacao.Consulta.TelaConsultaPromocao;
import apresentacao.Consulta.TelaConsultaTipo;
import apresentacao.Consulta.TelaConsultaUnidadeMedida;
import javax.swing.JInternalFrame;

public class TelaPrincipal extends javax.swing.JFrame {

	TelaConsultaExemplo tce = new TelaConsultaExemplo();
	TelaConsultaTipo tct = new TelaConsultaTipo();
	TelaConsultaFormaPagamento tcfp = new TelaConsultaFormaPagamento();
	TelaConsultaUnidadeMedida tcum = new TelaConsultaUnidadeMedida();
	TelaConsultaProduto tcprod = new TelaConsultaProduto();
	TelaConsultaPromocao tcprom = new TelaConsultaPromocao();
	JInternalFrame telas[] = {tce, tct, tcfp, tcum, tcprod, tcprom};

	public TelaPrincipal() {
		initComponents();
		setExtendedState(MAXIMIZED_BOTH);
	}

	private void abrirTela(JInternalFrame tela) {
		tela.setLocation(182, 36);
		add(tela);
		tela.show();
		fecharTelas(tela);
		jLabelBemVindo.setText("");
	}

	private void fecharTelas(JInternalFrame telaIgnorada) {
		for (JInternalFrame tela : telas) {
			if (!tela.equals(telaIgnorada)) {
				tela.dispose();
			}
		}
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuBar3 = new javax.swing.JMenuBar();
        jMenu8 = new javax.swing.JMenu();
        jMenu9 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu10 = new javax.swing.JMenu();
        jMenu11 = new javax.swing.JMenu();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jButtonTipo = new javax.swing.JButton();
        jButtonFormaPagamento = new javax.swing.JButton();
        jButtonUnidadeMedida = new javax.swing.JButton();
        jButtonProduto = new javax.swing.JButton();
        jButtonPromocao = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabelSair = new javax.swing.JLabel();
        jLabelBemVindo = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        jMenu5.setText("File");
        jMenuBar2.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar2.add(jMenu6);

        jMenuItem3.setText("jMenuItem3");

        jMenu7.setText("jMenu7");

        jMenu8.setText("File");
        jMenuBar3.add(jMenu8);

        jMenu9.setText("Edit");
        jMenuBar3.add(jMenu9);

        jMenu10.setText("File");
        jMenuBar4.add(jMenu10);

        jMenu11.setText("Edit");
        jMenuBar4.add(jMenu11);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MGM Comercial");
        setBackground(new java.awt.Color(51, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MENU");

        jButtonTipo.setBackground(new java.awt.Color(51, 51, 51));
        jButtonTipo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonTipo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonTipo.setText("Tipo");
        jButtonTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTipoActionPerformed(evt);
            }
        });

        jButtonFormaPagamento.setBackground(new java.awt.Color(51, 51, 51));
        jButtonFormaPagamento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonFormaPagamento.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFormaPagamento.setText("Forma de Pagamento");
        jButtonFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFormaPagamentoActionPerformed(evt);
            }
        });

        jButtonUnidadeMedida.setBackground(new java.awt.Color(51, 51, 51));
        jButtonUnidadeMedida.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonUnidadeMedida.setForeground(new java.awt.Color(255, 255, 255));
        jButtonUnidadeMedida.setText("Unidade de Medida");
        jButtonUnidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUnidadeMedidaActionPerformed(evt);
            }
        });

        jButtonProduto.setBackground(new java.awt.Color(51, 51, 51));
        jButtonProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonProduto.setForeground(new java.awt.Color(255, 255, 255));
        jButtonProduto.setText("Produto");
        jButtonProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoActionPerformed(evt);
            }
        });

        jButtonPromocao.setBackground(new java.awt.Color(51, 51, 51));
        jButtonPromocao.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonPromocao.setForeground(new java.awt.Color(255, 255, 255));
        jButtonPromocao.setText("Promoção");
        jButtonPromocao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPromocaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButtonTipo, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                        .addComponent(jButtonFormaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonUnidadeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPromocao, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButtonPromocao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelSair.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabelSair.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSair.setText("Sair");
        jLabelSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSairMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSair)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelSair)
                .addContainerGap())
        );

        jLabelBemVindo.setFont(new java.awt.Font("Tahoma", 1, 60)); // NOI18N
        jLabelBemVindo.setForeground(new java.awt.Color(102, 102, 102));
        jLabelBemVindo.setText("Bem Vindo!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 1358, Short.MAX_VALUE)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jLabelBemVindo)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203)
                .addComponent(jLabelBemVindo)
                .addGap(50, 50, 50)
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSairMouseClicked
		this.dispose();
    }//GEN-LAST:event_jLabelSairMouseClicked

    private void jButtonTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTipoActionPerformed
		abrirTela(tct);
    }//GEN-LAST:event_jButtonTipoActionPerformed

    private void jButtonFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFormaPagamentoActionPerformed
		abrirTela(tcfp);
    }//GEN-LAST:event_jButtonFormaPagamentoActionPerformed

    private void jButtonUnidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUnidadeMedidaActionPerformed
		abrirTela(tcum);
    }//GEN-LAST:event_jButtonUnidadeMedidaActionPerformed

    private void jButtonProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoActionPerformed
		abrirTela(tcprod);
    }//GEN-LAST:event_jButtonProdutoActionPerformed

    private void jButtonPromocaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPromocaoActionPerformed
		abrirTela(tcprom);
    }//GEN-LAST:event_jButtonPromocaoActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TelaPrincipal().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButtonFormaPagamento;
    private javax.swing.JButton jButtonProduto;
    private javax.swing.JButton jButtonPromocao;
    private javax.swing.JButton jButtonTipo;
    private javax.swing.JButton jButtonUnidadeMedida;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelBemVindo;
    private javax.swing.JLabel jLabelSair;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu11;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
//public void FechaInternalFrames(){
//        JInternalFrame[] frames = DesktopPaneUI.;
//        int i = 0;
//        int j = frames.length;
//          while ( i < j ){
//               frames[i].dispose();
//               i++;
//          }
//    }

}
