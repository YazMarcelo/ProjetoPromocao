package apresentacao.Consulta;

import apresentacao.Cadastro.CadastroProduto;
import apresentacao.Cadastro.CadastroPromocao;
import entidade.Produto;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import negocio.NProduto;
import util.Mensagem;
import util.Utilitarios;

public class TelaConsultaProduto extends javax.swing.JInternalFrame {

    DefaultTableModel model = null;
    TableRowSorter trs;
    int esc;
    CadastroProduto telaCadastroProduto;
    NProduto nProduto = new NProduto();

    public TelaConsultaProduto() {
        initComponents();
        atualizar();
    }

    CadastroPromocao telaCadastroPromocao = null; //para o 2° comportamento desta tela, em CadastroPromocao
    int qualProd; //para saber para qual produto é a consulta em CadastroPromocao (ProdPaga ou ProdLeva)

    public TelaConsultaProduto(CadastroPromocao telaCadastroPromocao, int qualProd) {
        this();
        this.telaCadastroPromocao = telaCadastroPromocao;
        jButtonNovo.setVisible(false);
        jButtonAlterar.setVisible(false);
        jButtonAtualizar.setVisible(false);
        jButtonExcluir.setVisible(false);
        this.qualProd = qualProd;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonNovo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonAtualizar = new javax.swing.JButton();
        jTextFieldPesquisar1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setClosable(true);
        setAlignmentX(100.0F);
        setAlignmentY(100.0F);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Consulta de Produto");

        jButtonNovo.setBackground(new java.awt.Color(0, 136, 204));
        jButtonNovo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButtonNovo.setForeground(new java.awt.Color(255, 255, 255));
        jButtonNovo.setText("+ Novo Produto");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(932, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNovo, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Valor", "Fabricação", "Vencimento", "Conteúdo", "Em estoque"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableProdutoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableProdutoMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableProduto);
        if (jTableProduto.getColumnModel().getColumnCount() > 0) {
            jTableProduto.getColumnModel().getColumn(0).setResizable(false);
            jTableProduto.getColumnModel().getColumn(0).setPreferredWidth(70);
            jTableProduto.getColumnModel().getColumn(1).setResizable(false);
            jTableProduto.getColumnModel().getColumn(1).setPreferredWidth(430);
            jTableProduto.getColumnModel().getColumn(2).setResizable(false);
            jTableProduto.getColumnModel().getColumn(3).setResizable(false);
            jTableProduto.getColumnModel().getColumn(5).setResizable(false);
        }

        jButtonAlterar.setBackground(new java.awt.Color(0, 136, 204));
        jButtonAlterar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonAlterar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setBackground(new java.awt.Color(210, 50, 45));
        jButtonExcluir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonExcluir.setForeground(new java.awt.Color(255, 255, 255));
        jButtonExcluir.setText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonAtualizar.setBackground(new java.awt.Color(4, 165, 30));
        jButtonAtualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonAtualizar.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        jTextFieldPesquisar1.setForeground(new java.awt.Color(204, 204, 204));
        jTextFieldPesquisar1.setText("Procurar...");
        jTextFieldPesquisar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldPesquisar1MouseClicked(evt);
            }
        });
        jTextFieldPesquisar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisar1ActionPerformed(evt);
            }
        });
        jTextFieldPesquisar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisar1KeyTyped(evt);
            }
        });

        jLabel2.setText("Filtro");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione...", "Código", "Nome" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButtonAlterar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonExcluir)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonAtualizar)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(56, 56, 56))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonAtualizar)
                    .addComponent(jTextFieldPesquisar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addGap(41, 41, 41))
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

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        telaCadastroProduto = new CadastroProduto(this);
        telaCadastroProduto.setVisible(true);
        atualizaAposFechar();
    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        atualizar();
    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        if (jTableProduto.getSelectedRow() >= 0) {
            int resposta = Mensagem.msg03(this);
            if (resposta == JOptionPane.YES_OPTION) {
                try {

                    int id = Integer.valueOf(jTableProduto.getValueAt(jTableProduto.getSelectedRow(), 0).toString());

                    nProduto.excluir(id);

                    model.removeRow(jTableProduto.getSelectedRow());
                    jTableProduto.setModel(model);

                    Mensagem.msg05(this);
                } catch (Exception ex) {
                    Logger.getLogger(TelaConsultaProduto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Mensagem.msg23(this);
        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        if (jTableProduto.getSelectedRow() >= 0) {
            try {
                telaCadastroProduto = new CadastroProduto(this);
                telaCadastroProduto.alteracao("Alterar Produto", Integer.valueOf(jTableProduto.getValueAt(jTableProduto.getSelectedRow(), 0).toString()));
                telaCadastroProduto.setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(TelaConsultaProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Mensagem.msg23(this);
        }
        atualizaAposFechar();
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jTextFieldPesquisar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPesquisar1MouseClicked
        jTextFieldPesquisar1.setText("");
    }//GEN-LAST:event_jTextFieldPesquisar1MouseClicked

    private void jTextFieldPesquisar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPesquisar1ActionPerformed

    private void jTextFieldPesquisar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisar1KeyReleased

    }//GEN-LAST:event_jTextFieldPesquisar1KeyReleased

    private void jTextFieldPesquisar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisar1KeyTyped
        jTextFieldPesquisar1.setForeground(new java.awt.Color(0, 0, 0));
        if (jComboBox1.getSelectedItem().equals("Descrição") || jComboBox1.getSelectedItem().equals("Selecione...")) {
            esc = 1;
        } else {
            esc = 0;
        }

        jTextFieldPesquisar1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {

                trs.setRowFilter(RowFilter.regexFilter("(?i)" + jTextFieldPesquisar1.getText(), esc));
            }
        });
        trs = new TableRowSorter(model);
        jTableProduto.setRowSorter(trs);
    }//GEN-LAST:event_jTextFieldPesquisar1KeyTyped

    private void jTableProdutoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutoMouseClicked

    }//GEN-LAST:event_jTableProdutoMouseClicked

    private void jTableProdutoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableProdutoMousePressed
        try {
            if (telaCadastroPromocao != null) {
                int linha = jTableProduto.getSelectedRow();
                if (linha >= 0 && evt.getClickCount() == 2) {
                    telaCadastroPromocao.selecionarProduto(nProduto.consultar(Integer.parseInt(jTableProduto.getValueAt(linha, 0).toString())), qualProd);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex);
        }
    }//GEN-LAST:event_jTableProdutoMousePressed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextFieldPesquisar1;
    // End of variables declaration//GEN-END:variables

    public void atualizar() {
        try {
            ArrayList<Produto> listaDeEspecialidades;

            listaDeEspecialidades = nProduto.listar();
            model = (DefaultTableModel) jTableProduto.getModel();

            model.setNumRows(0);
            for (int pos = 0; pos < listaDeEspecialidades.size(); pos++) {
                String[] saida = new String[7];
                Produto aux = (Produto) listaDeEspecialidades.get(pos);

                saida[0] = String.valueOf(aux.getId());
                saida[1] = aux.getDescricao();
                saida[2] = String.valueOf(aux.getValor());
                saida[3] = Utilitarios.dateBRFormat(String.valueOf(aux.getDataFabricacao()));
                saida[4] = Utilitarios.dateBRFormat(String.valueOf(aux.getDataVencimento()));
                saida[5] = (aux.getQtdUnidade() + " " + aux.getUnidadeMedida().getSigla());
                saida[6] = String.valueOf(aux.getSaldoEstoque());

                model.addRow(saida);
            }
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    public void atualizaAposFechar() {
        telaCadastroProduto.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                atualizar();
            }
        });
    }

    public JPanel getMainPanel() {
        return jPanel1;
    }
}
