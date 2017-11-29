package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class NVendedor {

    VendedorDAO dao;

    public NVendedor() {
        dao = new VendedorDAO();
    }

    public void salvar(Vendedor obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Vendedor consultar(int codigo) throws SQLException, Exception {
        return (Vendedor) dao.consultar(codigo);
    }

    public ArrayList<Object> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
