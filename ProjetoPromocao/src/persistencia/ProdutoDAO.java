package persistencia;

import entidade.*;
import interfaces.CRUD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo
 */
public class ProdutoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            Produto produto = (Produto) (objeto);
            String sql = "INSERT INTO PRODUTO (PROD_DESCRICAO, PROD_VALOR, PROD_DATA_FABRICACAO, PROD_DATA_VENCIMENTO, PROD_QTD_UNIDADE, PROD_UNME_ID, PROD_SALDO_ESTOQUE, PROD_DESCONTO) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, produto.getDescricao());
            prd.setDouble(2, produto.getValor());
            prd.setDate(3, new Date(produto.getDataFabricacao().getTime()));
            prd.setDate(4, new Date(produto.getDataVencimento().getTime()));
            prd.setInt(5, produto.getQtdUnidade());
            prd.setInt(6, produto.getUnidadeMedida().getId());
            prd.setInt(7, produto.getSaldoEstoque());
            prd.setFloat(8, produto.getDesconto());
            prd.execute();
            prd.close();
            String sql2 = "SELECT CURRVAL('PRODUTO_PROD_ID_SEQ') AS PROD_ID;";
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql2);
            if (rs.next()) {
                int codigo = rs.getInt("PROD_ID");
                produto.setId(codigo);
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
            String sql = "UPDATE PRODUTO SET EXCLUIDO = TRUE WHERE PROD_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            prd.execute();
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
            Produto produto = (Produto) (objeto);
            String sql = "UPDATE PRODUTO SET "
                    + "PROD_DESCRICAO = ?,"
                    + "PROD_VALOR = ?,"
                    + "PROD_DATA_FABRICACAO = ?,"
                    + "PROD_DATA_VENCIMENTO = ?,"
                    + "PROD_QTD_UNIDADE = ?,"
                    + "PROD_UNME_ID = ?,"
                    + "PROD_SALDO_ESTOQUE = ?,"
                    + "PROD_DESCONTO = ? "
                    + "WHERE PROD_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, produto.getDescricao());
            prd.setDouble(2, produto.getValor());
            prd.setDate(3, new Date(produto.getDataFabricacao().getTime()));
            prd.setDate(4, new Date(produto.getDataVencimento().getTime()));
            prd.setInt(5, produto.getQtdUnidade());
            prd.setInt(6, produto.getUnidadeMedida().getId());
            prd.setInt(7, produto.getSaldoEstoque());
            prd.setFloat(8, produto.getDesconto());
            prd.setInt(9, produto.getId());
            prd.execute();
            prd.close();
            cnn.commit();
        } catch (Exception e) {
            cnn.rollback();
            throw e;
        } finally {
            cnn.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<Produto> listar() throws Exception {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PRODUTO WHERE EXCLUIDO = FALSE ORDER BY PROD_ID;";
            Statement stm = cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            Produto produto;
            UnidadeMedidaDAO unidadeMedidaDAO = null;
            while (rs.next()) {
                if (unidadeMedidaDAO == null) {
                    unidadeMedidaDAO = new UnidadeMedidaDAO();
                }
                produto = new Produto();
                produto.setId(rs.getInt("PROD_ID"));
                produto.setDescricao(rs.getString("PROD_DESCRICAO"));
                produto.setValor(rs.getDouble("PROD_VALOR"));
                produto.setDataFabricacao(rs.getDate("PROD_DATA_FABRICACAO"));
                produto.setDataVencimento(rs.getDate("PROD_DATA_VENCIMENTO"));
                produto.setQtdUnidade(rs.getInt("PROD_QTD_UNIDADE"));
                produto.setUnidadeMedida((UnidadeMedida) unidadeMedidaDAO.consultar(rs.getInt("PROD_UNME_ID")));
                produto.setSaldoEstoque(rs.getInt("PROD_SALDO_ESTOQUE"));
                produto.setDesconto(rs.getFloat("PROD_DESCONTO"));
                listaProdutos.add(produto);
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return listaProdutos;
    }

    @Override
    public Object consultar(int id) throws Exception {
        Produto produto = null;
        try {
            String sql = "SELECT * FROM PRODUTO WHERE PROD_ID = ? AND EXCLUIDO = FALSE;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("PROD_ID"));
                produto.setDescricao(rs.getString("PROD_DESCRICAO"));
                produto.setValor(rs.getDouble("PROD_VALOR"));
                produto.setDataFabricacao(rs.getDate("PROD_DATA_FABRICACAO"));
                produto.setDataVencimento(rs.getDate("PROD_DATA_VENCIMENTO"));
                produto.setQtdUnidade(rs.getInt("PROD_QTD_UNIDADE"));
                produto.setUnidadeMedida((UnidadeMedida) (new UnidadeMedidaDAO().consultar(rs.getInt("PROD_UNME_ID"))));
                produto.setSaldoEstoque(rs.getInt("PROD_SALDO_ESTOQUE"));
                produto.setDesconto(rs.getFloat("PROD_DESCONTO"));
            }
            prd.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return produto;
    }

    public static void atualizarSaldoEstoque(Produto produto, Connection cnn) throws SQLException {
        String sql = "UPDATE PRODUTO SET PROD_SALDO_ESTOQUE = ? WHERE PROD_ID = ?;";
        PreparedStatement prd = cnn.prepareStatement(sql);
        prd.setInt(1, produto.getSaldoEstoque());
        prd.setInt(2, produto.getId());
        prd.execute();
        prd.close();
    }
}
