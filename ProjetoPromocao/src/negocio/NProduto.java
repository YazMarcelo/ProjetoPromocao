package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class NProduto {

    ProdutoDAO dao;

    public NProduto() {
        dao = new ProdutoDAO();
    }

    public void salvar(Produto obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Produto consultar(int codigo) throws SQLException, Exception {
        return (Produto) dao.consultar(codigo);
    }

    public ArrayList<Produto> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
