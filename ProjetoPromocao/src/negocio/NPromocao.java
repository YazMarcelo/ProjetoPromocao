package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class NPromocao {

    PromocaoDAO dao;

    public NPromocao() {
        dao = new PromocaoDAO();
    }

    public void salvar(Promocao obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Promocao consultar(int codigo) throws SQLException, Exception {
        return (Promocao) dao.consultar(codigo);
    }

    public Iterator<Promocao> listarIterator() throws SQLException, Exception {
        return dao.listarIterator();
    }
    
    public List<Promocao> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
