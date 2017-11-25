package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class NUnidadeMedida {

    UnidadeMedidaDAO dao;

    public NUnidadeMedida() {
        dao = new UnidadeMedidaDAO();
    }

    public void salvar(UnidadeMedida obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public UnidadeMedida consultar(int codigo) throws SQLException, Exception {
        return (UnidadeMedida) dao.consultar(codigo);
    }

    public ArrayList<UnidadeMedida> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
