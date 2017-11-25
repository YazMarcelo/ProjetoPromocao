package persistencia;

import entidade.FormaPagamento;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FormaPagamentoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        try {
            cnn.setAutoCommit(false);
            FormaPagamento objFormaPagamento = (FormaPagamento) (objeto);
            String sql = "INSERT INTO FORMA_PAGAMENTO(FOPA_DESCRICAO) VALUES (?);";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objFormaPagamento.getDescricao());
            prd.execute();
            prd.close();
            String sql2 = "SELECT CURRVAL('FORMA_PAGAMENTO_FOPA_ID_SEQ') AS FOPA_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql2);
            if (rs.next()) {
                objFormaPagamento.setId(rs.getInt("FOPA_ID"));
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
            String sql = "UPDATE FORMA_PAGAMENTO SET EXCLUIDO = TRUE WHERE FOPA_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            prd.executeUpdate();
            prd.close();
            cnn.commit();
        } catch (NumberFormatException | SQLException e) {
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
            FormaPagamento objFormaPagamento = (FormaPagamento) (objeto);
            String sql = "UPDATE FORMA_PAGAMENTO SET FOPA_DESCRICAO = ? WHERE FOPA_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, objFormaPagamento.getDescricao());
            prd.setInt(2, objFormaPagamento.getId());
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
    public ArrayList<FormaPagamento> listar() throws Exception {
        ArrayList<FormaPagamento> listaFormaPagamento = new ArrayList<>();
        try {
            String sql = "SELECT * FROM FORMA_PAGAMENTO ORDER BY FOPA_ID;";
            ResultSet rs = cnn.createStatement().executeQuery(sql);
            while (rs.next()) {
                if (!rs.getBoolean("EXCLUIDO")) {
                    FormaPagamento objeto = new FormaPagamento();
                    objeto.setId(rs.getInt("FOPA_ID"));
                    objeto.setDescricao(rs.getString("FOPA_DESCRICAO"));
                    listaFormaPagamento.add(objeto);
                }
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        }
        return listaFormaPagamento;
    }

    @Override
    public Object consultar(int id) throws Exception {
        FormaPagamento objeto = null;
        try {
            String sql = "SELECT * FROM FORMA_PAGAMENTO WHERE FOPA_ID = ?;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();
            if (rs.next()) {
                objeto = new FormaPagamento();
                objeto.setId(rs.getInt("FOPA_ID"));
                objeto.setDescricao(rs.getString("FOPA_DESCRICAO"));
            }
            rs.close();
            prd.close();
        } catch (SQLException e) {
            throw e;
        }
        return objeto;
    }

}
