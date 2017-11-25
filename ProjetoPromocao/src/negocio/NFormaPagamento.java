package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class NFormaPagamento {

    FormaPagamentoDAO dao;

    public NFormaPagamento() {
        dao = new FormaPagamentoDAO();
    }

    public void salvar(FormaPagamento obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public FormaPagamento consultar(int codigo) throws SQLException, Exception {
        return (FormaPagamento) dao.consultar(codigo);
    }

    public ArrayList<FormaPagamento> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
