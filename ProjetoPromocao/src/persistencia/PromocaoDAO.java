package persistencia;

import entidade.Produto;
import entidade.Promocao;
import entidade.TipoPromocao;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PromocaoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            Promocao objPromocao = (Promocao) (objeto);
            String sql = "INSERT INTO PROMOCAO(PROM_DESCRICAO, PROM_TIPO, PROM_PORCENTAGEM, "
                    + "PROM_QTD_PAGA, PROM_QTD_LEVA, PROM_DATA_INICIO, PROM_DATA_FIM,"
                    + "PROM_PAGA_PROD_ID, PROM_LEVA_PROD_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objPromocao.getDescricao());
            prd.setInt(2, objPromocao.getTipo().getId());
            prd.setFloat(3, objPromocao.getDesconto());
            prd.setInt(4, objPromocao.getQtdPaga());
            prd.setInt(5, objPromocao.getQtdLeva());
            prd.setTimestamp(6, new java.sql.Timestamp(objPromocao.getDataInicio().getTime()));
            prd.setTimestamp(7, new java.sql.Timestamp(objPromocao.getDataFim().getTime()));
            prd.setInt(8, objPromocao.getProdPaga() == null ? 0 : objPromocao.getProdPaga().getId());
            prd.setInt(9, objPromocao.getProdLeva() == null ? 0 : objPromocao.getProdLeva().getId());
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
            String sql = "UPDATE PROMOCAO SET PROM_DESCRICAO = ?, PROM_TIPO = ?, PROM_PORCENTAGEM = ?, "
                    + "PROM_QTD_PAGA = ?, PROM_QTD_LEVA = ?, PROM_DATA_INICIO = ?, PROM_DATA_FIM = ?,"
                    + "PROM_PAGA_PROD_ID = ?, PROM_LEVA_PROD_ID = ? WHERE PROM_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objPromocao.getDescricao());
            prd.setInt(2, objPromocao.getTipo().getId());
            prd.setFloat(3, objPromocao.getDesconto());
            prd.setInt(4, objPromocao.getQtdPaga());
            prd.setInt(5, objPromocao.getQtdLeva());
            prd.setTimestamp(6, new java.sql.Timestamp(objPromocao.getDataInicio().getTime()));
            prd.setTimestamp(7, new java.sql.Timestamp(objPromocao.getDataFim().getTime()));
            prd.setInt(8, objPromocao.getProdPaga() == null ? 0 : objPromocao.getProdPaga().getId());
            prd.setInt(9, objPromocao.getProdLeva() == null ? 0 : objPromocao.getProdLeva().getId());
            prd.setInt(10, objPromocao.getId());
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
        try {
            String sql = "SELECT * FROM PROMOCAO WHERE EXCLUIDO = FALSE ORDER BY PROM_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql);
            while (rs.next()) {
                Promocao objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.valueOf(rs.getInt("PROM_TIPO")));
                objeto.setDesconto(rs.getFloat("PROM_PORCENTAGEM"));
                objeto.setQtdPaga(rs.getInt("PROM_QTD_PAGA"));
                objeto.setQtdLeva(rs.getInt("PROM_QTD_LEVA"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                int prodPaga = rs.getInt("PROM_PAGA_PROD_ID");
                objeto.setProdPaga(prodPaga == 0 ? null : new Produto(prodPaga));
                int prodLeva = rs.getInt("PROM_LEVA_PROD_ID");
                objeto.setProdLeva(prodLeva == 0 ? null : new Produto(prodLeva));
                listaPromocao.add(objeto);
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaPromocao;
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
                objeto = new Promocao();
                objeto.setId(rs.getInt("PROM_ID"));
                objeto.setDescricao(rs.getString("PROM_DESCRICAO"));
                objeto.setTipo(TipoPromocao.valueOf(rs.getInt("PROM_TIPO")));
                objeto.setDesconto(rs.getFloat("PROM_PORCENTAGEM"));
                objeto.setQtdPaga(rs.getInt("PROM_QTD_PAGA"));
                objeto.setQtdLeva(rs.getInt("PROM_QTD_LEVA"));
                objeto.setDataInicio(rs.getTimestamp("PROM_DATA_INICIO"));
                objeto.setDataFim(rs.getTimestamp("PROM_DATA_FIM"));
                int prodPaga = rs.getInt("PROM_PAGA_PROD_ID");
                objeto.setProdPaga(prodPaga == 0 ? null : new Produto(prodPaga));
                int prodLeva = rs.getInt("PROM_LEVA_PROD_ID");
                objeto.setProdLeva(prodLeva == 0 ? null : new Produto(prodLeva));
            }
            rs.close();
            prd.close();
        } catch (SQLException e) {
            throw e;
        }
        return objeto;
    }

}
