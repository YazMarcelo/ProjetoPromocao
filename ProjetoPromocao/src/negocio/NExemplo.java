/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import entidade.Exemplo;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import persistencia.ExemploDAO;
import util.Mensagem;

/**
 *
 * @author aluno
 */
public class NExemplo {

    ExemploDAO dao;
    Mensagem msg = new Mensagem();

    public NExemplo() {
        dao = new ExemploDAO();
    }

    public void salvar(Exemplo espe) throws SQLException, Exception {

        if (obrigatoriosPreenchidos(espe)) {
            if (espe.getCodigo() == 0) {

                dao.incluir(espe);

                msg.msg01();
            } else {
                if ((msg.msg02()) == JOptionPane.YES_OPTION) {
                    
                    dao.alterar(espe);
                    
                    msg.msg04();
                }
            }
        } else {
            msg.msg11();
        }

    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Exemplo consultar(int codigo) throws SQLException, Exception {
        return (Exemplo) dao.consultar(codigo);
    }

    public ArrayList<Object> listar() throws SQLException, Exception {
        return dao.listar();
    }

    public boolean obrigatoriosPreenchidos(Exemplo especie) {
        if (especie.getDescricao().equals("")) {
            return false;
        }

        return true;
    }
}
