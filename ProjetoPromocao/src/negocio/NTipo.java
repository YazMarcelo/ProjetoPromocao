package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class NTipo {

    TipoDAO dao;

    public NTipo() {
        dao = new TipoDAO();
    }

    public void salvar(Tipo obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(String codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Tipo consultar(String codigo) throws SQLException, Exception {
        return (Tipo) dao.consultar(codigo);
    }

    public ArrayList<Tipo> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
