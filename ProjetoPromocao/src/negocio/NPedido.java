package negocio;

import entidade.*;
import persistencia.*;
import java.sql.SQLException;
import java.util.List;

public class NPedido {

    PedidoDAO dao;

    public NPedido() {
        dao = new PedidoDAO();
    }

    public void salvar(Pedido obj) throws SQLException, Exception {
        if (obj.getId() == 0) {
            dao.incluir(obj);
        } else {
            dao.alterar(obj);
        }
    }

    public void excluir(int codigo) throws SQLException, Exception {
        dao.excluir(codigo);
    }

    public Pedido consultar(int codigo) throws SQLException, Exception {
        return (Pedido) dao.consultar(codigo);
    }

    public List<Pedido> listar() throws SQLException, Exception {
        return dao.listar();
    }
}
