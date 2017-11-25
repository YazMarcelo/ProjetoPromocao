package persistencia;

import entidade.*;
import negocio.*;
import interfaces.CRUD;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Marcelo
 */
public class TipoDAO implements CRUD {

    @Override
    public void incluir(Object objeto) throws Exception {
        Tipo obj = (Tipo) (objeto);

        String sql = "insert into tipo (tipo_descricao) VALUES (?);";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getDescricao());

        prd.execute();

        String sql2 = "select currval('tipo_tipo_id_seq') as tipo_id";

        Statement stm = cnn.createStatement();

        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            int codigo = rs.getInt("tipo_id");
            obj.setId(codigo);
        }

        rs.close();
        cnn.close();
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "UPDATE public.tipo set excluido = true WHERE tipo_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setInt(1, id);

        prd.execute();

        prd.close();
        cnn.close();
    }

    @Override
    public void alterar(Object objeto) throws Exception {

        Tipo obj = (Tipo) (objeto);

        String sql = "update public.tipo set "
                + "tipo_descricao = ?"
                + " where tipo_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getDescricao());
        prd.setInt(2, obj.getId());

        prd.execute();

        prd.close();
        cnn.close();
    }

    @Override
    public ArrayList<Tipo> listar() throws Exception {

        ArrayList<Tipo> listaObjs = new ArrayList<>();

        String sql = "select * from public.tipo where excluido = false order by tipo_id ";

        Connection cnn = util.Conexao.getConexao();
        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        Tipo objeto;

        while (rs.next()) {
            objeto = new Tipo();
            objeto.setId(rs.getInt("tipo_id"));
            objeto.setDescricao(rs.getString("tipo_descricao"));
            listaObjs.add(objeto);
        }

        rs.close();
        cnn.close();

        return listaObjs;
    }

    @Override
    public Object consultar(int id) throws Exception {
        String sql = "select * from public.tipo where tipo_id = ? and excluido = false;";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setInt(1, id);

        ResultSet rs = prd.executeQuery();

        Tipo objeto = new Tipo();

        if (rs.next()) {
            objeto.setId(rs.getInt("tipo_id"));
            objeto.setDescricao(rs.getString("tipo_descricao"));
        }

        prd.execute();

        prd.close();
        cnn.close();

        return objeto;
    }

}
