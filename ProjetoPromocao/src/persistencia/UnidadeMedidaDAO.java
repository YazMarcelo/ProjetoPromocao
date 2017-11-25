package persistencia;

import entidade.UnidadeMedida;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UnidadeMedidaDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            UnidadeMedida objUnidadeMedida = (UnidadeMedida) (objeto);
            String sql = "INSERT INTO UNIDADE_MEDIDA(UNME_DESCRICAO, UNME_SIGLA) VALUES (?);";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objUnidadeMedida.getDescricao());
            prd.execute();
            prd.close();
            String sql2 = "SELECT CURRVAL('UNIDADE_MEDIDA_UNME_ID_SEQ') AS UNME_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql2);
            if (rs.next()) {
                objUnidadeMedida.setId(rs.getInt("UNME_ID"));
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
            String sql = "UPDATE UNIDADE_MEDIDA SET EXCLUIDO = TRUE WHERE UNME_ID = ?;";
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
            UnidadeMedida objUnidadeMedida = (UnidadeMedida) (objeto);
            String sql = "UPDATE UNIDADE_MEDIDA SET UNME_DESCRICAO = ?, UNME_SIGLA = ? WHERE UNME_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objUnidadeMedida.getDescricao());
            prd.setString(2, objUnidadeMedida.getSigla());
            prd.setInt(3, objUnidadeMedida.getId());
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
    public ArrayList<UnidadeMedida> listar() throws Exception {
        ArrayList<UnidadeMedida> listaUnidadeMedida = new ArrayList<>();
        try {
            String sql = "SELECT * FROM UNIDADE_MEDIDA ORDER BY UNME_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean("EXCLUIDO")) {
                    UnidadeMedida objeto = new UnidadeMedida();
                    objeto.setId(rs.getInt("UNME_ID"));
                    objeto.setDescricao(rs.getString("UNME_DESCRICAO"));
                    objeto.setSigla(rs.getString("UNME_SIGLA"));
                    listaUnidadeMedida.add(objeto);
                }
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaUnidadeMedida;
    }

    @Override
    public Object consultar(int id) throws Exception {
        UnidadeMedida objeto = null;
        try {
            String sql = "SELECT * FROM UNIDADE_MEDIDA WHERE UNME_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();
            if (rs.next()) {
                objeto = new UnidadeMedida();
                objeto.setId(rs.getInt("UNME_ID"));
                objeto.setDescricao(rs.getString("UNME_DESCRICAO"));
                objeto.setSigla(rs.getString("UNME_SIGLA"));
            }
            rs.close();
            prd.close();
        } catch (SQLException e) {
            throw e;
        }
        return objeto;
    }

}
