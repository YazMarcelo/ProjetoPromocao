package persistencia;

import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import negocio.NProduto;

public class PromocaoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            Promocao objPromocao = (Promocao) (objeto);
            String sql = "INSERT INTO PROMOCAO("
                    + "PROM_DESCRICAO, "
                    + "PROM_TIPO, "
                    + "PROM_DATA_INICIO, "
                    + "PROM_DATA_FIM, ";
            /* 
                PROM_DESCONTO
                PROM_QTD_PAGA
                PROM_QTD_LEVA
                PROM_PAGA_PROD_ID
                PROM_LEVA_PROD_ID
                PROM_VALOR_MINIMO
             */
            String sqlDesconto = sql + "PROM_DESCONTO, PROM_QTD_PAGA, PROM_PAGA_PROD_ID) VALUES (?, ?, ?, ?, ?, ?, ?);";
            String sqlQuantidade = sql + "PROM_QTD_PAGA, PROM_QTD_LEVA, PROM_PAGA_PROD_ID, PROM_LEVA_PROD_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
            String sqlGeral = sql + "PROM_DESCONTO, PROM_VALOR_MINIMO) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement prd;
            switch (objPromocao.getTipo()) {
                case DESCONTO:
                    prd = cnn.prepareStatement(sqlDesconto);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setTimestamp(3, new java.sql.Timestamp(objPromocao.getDataInicio().getTime()));
                    prd.setTimestamp(4, new java.sql.Timestamp(objPromocao.getDataFim().getTime()));
                    prd.setFloat(5, objPromocao.getDesconto());
                    prd.setInt(6, objPromocao.getQtdPaga());
                    prd.setInt(7, objPromocao.getProdPaga().getId());
                    break;
                case QUANTIDADE:
                    prd = cnn.prepareStatement(sqlQuantidade);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setTimestamp(3, new java.sql.Timestamp(objPromocao.getDataInicio().getTime()));
                    prd.setTimestamp(4, new java.sql.Timestamp(objPromocao.getDataFim().getTime()));
                    prd.setInt(5, objPromocao.getQtdPaga());
                    prd.setInt(6, objPromocao.getQtdLeva());
                    prd.setInt(7, objPromocao.getProdPaga().getId());
                    prd.setInt(8, objPromocao.getProdLeva().getId());
                    break;
                default:
                    prd = cnn.prepareStatement(sqlGeral);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setTimestamp(3, new java.sql.Timestamp(objPromocao.getDataInicio().getTime()));
                    prd.setTimestamp(4, new java.sql.Timestamp(objPromocao.getDataFim().getTime()));
                    prd.setFloat(5, objPromocao.getDesconto());
                    prd.setDouble(6, objPromocao.getValorMinimo());
            }
            prd.execute();
            prd.close();
            String sql2 = "SELECT CURRVAL('PROMOCAO_PROM_ID_SEQ') AS PROM_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql2);
            if (rs.next()) {
                objPromocao.setId(rs.getInt("PROM_ID"));
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
            String sql = "UPDATE PROMOCAO SET EXCLUIDO = TRUE WHERE PROM_ID = ?;";
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
            Promocao objPromocao = (Promocao) (objeto);
            String sql = "UPDATE PROMOCAO SET PROM_DESCRICAO = ?, PROM_TIPO = ?, PROM_DATA_INICIO = ?, PROM_DATA_FIM = ?";
            String sqlDesconto = sql + ", PROM_DESCONTO = ?, PROM_QTD_PAGA = ?, PROM_PAGA_PROD_ID = ? WHERE PROM_ID = ?;";
            String sqlQuantidade = sql + ", PROM_QTD_PAGA = ?, PROM_QTD_LEVA = ?, PROM_PAGA_PROD_ID = ?, PROM_LEVA_PROD_ID = ? WHERE PROM_ID = ?;";
            String sqlGeral = sql + ", PROM_DESCONTO = ?, PROM_VALOR_MINIMO = ? WHERE PROM_ID = ?;";
            PreparedStatement prd;
            switch (objPromocao.getTipo()) {
                case DESCONTO:
                    prd = cnn.prepareStatement(sqlDesconto);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setDate(3, new Date(objPromocao.getDataInicio().getTime()));
                    prd.setDate(4, new Date(objPromocao.getDataFim().getTime()));
                    prd.setFloat(5, objPromocao.getDesconto());
                    prd.setInt(6, objPromocao.getQtdPaga());
                    prd.setInt(7, objPromocao.getProdPaga().getId());
                    prd.setInt(8, objPromocao.getId());
                    break;
                case QUANTIDADE:
                    prd = cnn.prepareStatement(sqlQuantidade);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setDate(3, new Date(objPromocao.getDataInicio().getTime()));
                    prd.setDate(4, new Date(objPromocao.getDataFim().getTime()));
                    prd.setInt(5, objPromocao.getQtdPaga());
                    prd.setInt(6, objPromocao.getQtdLeva());
                    prd.setInt(7, objPromocao.getProdPaga().getId());
                    prd.setInt(8, objPromocao.getProdLeva().getId());
                    prd.setInt(9, objPromocao.getId());
                    break;
                default:
                    prd = cnn.prepareStatement(sqlGeral);
                    prd.setString(1, objPromocao.getDescricao());
                    prd.setInt(2, objPromocao.getTipo().getId());
                    prd.setDate(3, new Date(objPromocao.getDataInicio().getTime()));
                    prd.setDate(4, new Date(objPromocao.getDataFim().getTime()));
                    prd.setFloat(5, objPromocao.getDesconto());
                    prd.setDouble(6, objPromocao.getValorMinimo());
                    prd.setInt(7, objPromocao.getId());
            }
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
    public ArrayList<Promocao> listar() throws Exception {
        ArrayList<Promocao> listaPromocao = new ArrayList<>();
        NProduto nProduto = null;
        try {
            String sql = "SELECT * FROM PROMOCAO WHERE EXCLUIDO = FALSE ORDER BY PROM_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql);
            while (rs.next()) {
                if (nProduto == null) {
                    nProduto = new NProduto();
                }
                Promocao objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.valueOf(rs.getInt("PROM_TIPO")));
                objeto.setDesconto(rs.getFloat("PROM_DESCONTO"));
                objeto.setQtdPaga(rs.getInt("PROM_QTD_PAGA"));
                objeto.setQtdLeva(rs.getInt("PROM_QTD_LEVA"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                int prodPaga = rs.getInt("PROM_PAGA_PROD_ID");
                objeto.setProdPaga(prodPaga == 0 ? null : nProduto.consultar(prodPaga));
                int prodLeva = rs.getInt("PROM_LEVA_PROD_ID");
                objeto.setProdLeva(prodLeva == 0 ? null : nProduto.consultar(prodLeva));
                objeto.setValorMinimo(rs.getDouble("PROM_VALOR_MINIMO"));
                listaPromocao.add(objeto);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaPromocao;
    }

    public ArrayList<Promocao> listarByProduto(int codigoProduto) throws Exception {
        ArrayList<Promocao> listaPromocao = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PROMOCAO WHERE EXCLUIDO = FALSE AND PROM_PAGA_PROD_ID = ? AND PROM_DATA_INICIO <= NOW() AND PROM_DATA_FIM > NOW() ORDER BY PROM_ID;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, codigoProduto);
            ResultSet rs = prd.executeQuery();
            NProduto nProduto = null;
            while (rs.next()) {
                if (nProduto == null) {
                    nProduto = new NProduto();
                }
                Promocao objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.valueOf(rs.getInt("PROM_TIPO")));
                objeto.setDesconto(rs.getFloat("PROM_DESCONTO"));
                objeto.setQtdPaga(rs.getInt("PROM_QTD_PAGA"));
                objeto.setQtdLeva(rs.getInt("PROM_QTD_LEVA"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                int prodPaga = rs.getInt("PROM_PAGA_PROD_ID");
                objeto.setProdPaga(prodPaga == 0 ? null : nProduto.consultar(prodPaga));
                int prodLeva = rs.getInt("PROM_LEVA_PROD_ID");
                objeto.setProdLeva(prodLeva == 0 ? null : nProduto.consultar(prodLeva));
                objeto.setValorMinimo(rs.getDouble("PROM_VALOR_MINIMO"));
                listaPromocao.add(objeto);
            }
            prd.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaPromocao;
    }

    
    public ArrayList<Promocao> listarByValorMinimo(double valorMinimo) throws Exception {
        ArrayList<Promocao> listaPromocao = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PROMOCAO WHERE EXCLUIDO = FALSE AND PROM_TIPO = 3 AND PROM_VALOR_MINIMO <= ? AND PROM_DATA_INICIO <= NOW() AND PROM_DATA_FIM > NOW() ORDER BY PROM_DESCONTO;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setDouble(1, valorMinimo);
            ResultSet rs = prd.executeQuery();
            NProduto nProduto = null;
            while (rs.next()) {
                if (nProduto == null) {
                    nProduto = new NProduto();
                }
                Promocao objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.GERAL);
                objeto.setDesconto(rs.getFloat("PROM_DESCONTO"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                objeto.setValorMinimo(rs.getDouble("PROM_VALOR_MINIMO"));
                listaPromocao.add(objeto);
            }
            prd.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaPromocao;
    }

    public Iterator<Promocao> listarIterator() throws Exception {
        return listar().iterator();
    }

    @Override
    public Object consultar(int id) throws Exception {
        Promocao objeto = null;
        try {
            String sql = "SELECT * FROM PROMOCAO WHERE PROM_ID = ? AND EXCLUIDO = FALSE;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();
            if (rs.next()) {
                NProduto nProduto = new NProduto();
                objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.valueOf(rs.getInt("PROM_TIPO")));
                objeto.setDesconto(rs.getFloat("PROM_DESCONTO"));
                objeto.setQtdPaga(rs.getInt("PROM_QTD_PAGA"));
                objeto.setQtdLeva(rs.getInt("PROM_QTD_LEVA"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                int prodPaga = rs.getInt("PROM_PAGA_PROD_ID");
                objeto.setProdPaga(prodPaga == 0 ? null : nProduto.consultar(prodPaga));
                int prodLeva = rs.getInt("PROM_LEVA_PROD_ID");
                objeto.setProdLeva(prodLeva == 0 ? null : nProduto.consultar(prodLeva));
                objeto.setValorMinimo(rs.getDouble("PROM_VALOR_MINIMO"));
            }
            rs.close();
            prd.close();
        } catch (SQLException e) {
            throw e;
        }
        return objeto;
    }

}
