package persistencia;

import entidade.FormaPagamento;
import entidade.ItemPedido;
import entidade.Pedido;
import entidade.Vendedor;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PedidoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            Pedido pedido = (Pedido) (objeto);
            String sql = "INSERT INTO PEDIDO(PEDI_VALOR_TOTAL, PEDI_FOPA_ID, PEDI_VENDEDOR_VEND_ID) VALUES (?, ?, ?);";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setDouble(1, pedido.getValorTotal());
            prd.setInt(2, pedido.getFormaPagamento().getId());
            prd.setInt(3, pedido.getVendedor().getId());
            prd.execute();
            for (ItemPedido itemPedido : pedido.getItens()) {
                ItemPedidoDAO.incluir(itemPedido, cnn);
            }
            prd.close();
            String sql2 = "SELECT CURRVAL('PEDIDO_PEDI_ID_SEQ') AS FOPA_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql2);
            if (rs.next()) {
                pedido.setId(rs.getInt("PEDI_ID"));
            }
            rs.close();
            cnn.commit();
        } catch (SQLException e) {
            cnn.rollback();
            throw e;
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            cnn.setAutoCommit(false);
            ItemPedidoDAO.excluirByPedido(id, cnn);
            String sql = "UPDATE PEDIDO SET EXCLUIDO = TRUE WHERE PEDI_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            prd.executeUpdate();
            prd.close();
            cnn.commit();
        } catch (SQLException e) {
            cnn.rollback();
            throw e;
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    @Override
    public void alterar(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            Pedido pedido = (Pedido) (objeto);
            String sql = "UPDATE PEDIDO SET PEDI_VALOR_TOTAL = ?, PEDI_FOPA_ID = ?, PEDI_VENDEDOR_VEND_ID = ? WHERE PEDI_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setDouble(1, pedido.getValorTotal());
            prd.setInt(2, pedido.getFormaPagamento().getId());
            prd.setInt(3, pedido.getVendedor().getId());
            prd.setInt(4, pedido.getId());
            prd.executeUpdate();
            prd.close();
            cnn.commit();
        } catch (SQLException e) {
            cnn.rollback();
            throw e;
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<Pedido> listar() throws Exception {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PEDIDO WHERE EXCLUIDO = FALSE ORDER BY PEDI_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql);
            FormaPagamentoDAO formaPagamentoDAO = null;
            VendedorDAO vendedorDAO = null;
            while (rs.next()) {
                if (formaPagamentoDAO == null) {
                    formaPagamentoDAO = new FormaPagamentoDAO();
                }
                if (vendedorDAO == null) {
                    vendedorDAO = new VendedorDAO();
                }
                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("PEDI_ID"));
                pedido.setFormaPagamento((FormaPagamento) formaPagamentoDAO.consultar(rs.getInt("PEDI_FOPA_ID")));
                pedido.setVendedor((Vendedor) vendedorDAO.consultar(rs.getInt("PEDI_VENDEDOR_VEND_ID")));
                pedido.setValorTotal(rs.getDouble("PEDI_VALOR_TOTAL"));
                pedido.setItens(ItemPedidoDAO.listarByPedido(pedido.getId(), cnn));
                lista.add(pedido);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return lista;
    }

    @Override
    public Object consultar(int id) throws Exception {
        Pedido pedido = null;
        try {
            String sql = "SELECT * FROM PEDIDO WHERE PEDI_ID = ? AND EXCLUIDO = FALSE;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();
            if (rs.next()) {
                FormaPagamentoDAO formaPagamentoDAO = new FormaPagamentoDAO();;
                VendedorDAO vendedorDAO = new VendedorDAO();
                pedido = new Pedido();
                pedido.setId(rs.getInt("PEDI_ID"));
                pedido.setFormaPagamento((FormaPagamento) formaPagamentoDAO.consultar(rs.getInt("PEDI_FOPA_ID")));
                pedido.setVendedor((Vendedor) vendedorDAO.consultar(rs.getInt("PEDI_VENDEDOR_VEND_ID")));
                pedido.setValorTotal(rs.getDouble("PEDI_VALOR_TOTAL"));
                pedido.setItens(ItemPedidoDAO.listarByPedido(pedido.getId(), cnn));
            }
            rs.close();
            prd.close();
        } catch (SQLException e) {
            throw e;
        }
        return pedido;
    }

}
