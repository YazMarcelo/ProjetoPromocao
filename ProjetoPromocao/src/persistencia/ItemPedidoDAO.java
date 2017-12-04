package persistencia;

import entidade.ItemPedido;
import entidade.Pedido;
import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ItemPedidoDAO {

    public static void incluir(ItemPedido itemPedido, Connection cnn) throws Exception {
        if (itemPedido.getPromocao() != null) {
            if (itemPedido.getPromocao().getTipo() == TipoPromocao.GERAL) {
                incluirDescontoGeral(itemPedido, cnn);
            } else {
                incluirComPromocao(itemPedido, cnn);
            }
        } else {
            incluirSemPromocao(itemPedido, cnn);
        }
    }

    private static void incluirComPromocao(ItemPedido itemPedido, Connection cnn) throws Exception {
        String sql = "INSERT INTO ITEM_PEDIDO(ITPE_PEDI_ID, ITPE_PROD_ID, ITPE_PROM_ID, ITPE_QTD, ITPE_QTD_BRINDE, ITPE_VALOR_UNITARIO, ITPE_DESCONTO) VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, itemPedido.getPedido().getId());
        prd.setInt(2, itemPedido.getProduto().getId());
        prd.setInt(3, itemPedido.getPromocao().getId());
        prd.setInt(4, itemPedido.getQtd());
        prd.setInt(5, itemPedido.getQtdBrinde());
        prd.setDouble(6, itemPedido.getValorUnitario());
        prd.setFloat(7, itemPedido.getDesconto());
        prd.execute();
        prd.close();
        String sql2 = "SELECT CURRVAL('ITEM_PEDIDO_ITPE_ID_SEQ') AS ITPE_ID;";
        ResultSet rs = cnn.createStatement().executeQuery(sql2);
        if (rs.next()) {
            itemPedido.setId(rs.getInt("ITPE_ID"));
        }
        rs.close();
    }

    private static void incluirSemPromocao(ItemPedido itemPedido, Connection cnn) throws Exception {
        String sql = "INSERT INTO ITEM_PEDIDO(ITPE_PEDI_ID, ITPE_PROD_ID, ITPE_QTD, ITPE_VALOR_UNITARIO, ITPE_DESCONTO) VALUES (?, ?, ?, ?, ?);";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, itemPedido.getPedido().getId());
        prd.setInt(2, itemPedido.getProduto().getId());
        prd.setInt(3, itemPedido.getQtd());
        prd.setDouble(4, itemPedido.getValorUnitario());
        prd.setFloat(5, itemPedido.getDesconto());
        prd.execute();
        prd.close();
        String sql2 = "SELECT CURRVAL('ITEM_PEDIDO_ITPE_ID_SEQ') AS ITPE_ID;";
        ResultSet rs = cnn.createStatement().executeQuery(sql2);
        if (rs.next()) {
            itemPedido.setId(rs.getInt("ITPE_ID"));
        }
        rs.close();
    }

    private static void incluirDescontoGeral(ItemPedido itemPedido, Connection cnn) throws Exception {
        String sql = "INSERT INTO ITEM_PEDIDO(ITPE_PEDI_ID, ITPE_DESCONTO, ITPE_PROM_ID) VALUES (?, ?, ?);";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, itemPedido.getPedido().getId());
        prd.setFloat(2, itemPedido.getDesconto());
        prd.setInt(3, itemPedido.getPromocao().getId());
        prd.execute();
        prd.close();
        String sql2 = "SELECT CURRVAL('ITEM_PEDIDO_ITPE_ID_SEQ') AS ITPE_ID;";
        ResultSet rs = cnn.createStatement().executeQuery(sql2);
        if (rs.next()) {
            itemPedido.setId(rs.getInt("ITPE_ID"));
        }
        rs.close();
    }

    public static void excluirByPedido(int pedidoId, Connection cnn) throws Exception {
        String sql = "UPDATE ITEM_PEDIDO SET EXCLUIDO = TRUE WHERE ITPE_PEDI_ID = ?;";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, pedidoId);
        prd.executeUpdate();
        prd.close();
    }

    public static ArrayList<ItemPedido> listarByPedido(int pedidoId, Connection cnn) throws Exception {
        ArrayList<ItemPedido> listaItemPedido = new ArrayList<>();
        String sql = "SELECT * FROM ITEM_PEDIDO WHERE EXCLUIDO = FALSE AND ITPE_PEDI_ID = ? ORDER BY ITPE_ID;";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, pedidoId);
        ResultSet rs = prd.executeQuery();
        ProdutoDAO produtoDAO = null;
        PromocaoDAO promocaoDAO = null;
        while (rs.next()) {
            if (produtoDAO == null) {
                produtoDAO = new ProdutoDAO();
            }
            if (promocaoDAO == null) {
                promocaoDAO = new PromocaoDAO();
            }
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setId(rs.getInt("ITPE_ID"));
            itemPedido.setPedido(new Pedido(rs.getInt("ITPE_PEDI_ID")));
            itemPedido.setProduto((Produto) produtoDAO.consultar(rs.getInt("ITPE_PROD_ID")));
            itemPedido.setPromocao((Promocao) promocaoDAO.consultar(rs.getInt("ITPE_PROM_ID")));
            itemPedido.setQtd(rs.getInt("ITPE_QTD"));
            itemPedido.setDesconto(rs.getFloat("ITPE_DESCONTO"));
            itemPedido.setValorUnitario(rs.getDouble("ITPE_VALOR_UNITARIO"));
            listaItemPedido.add(itemPedido);
        }
        rs.close();
        return listaItemPedido;
    }
}
