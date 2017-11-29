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
public class VendedorDAO implements CRUD {
private final Connection cnn = util.Conexao.getConexao();
    @Override
    public void incluir(Object objeto) throws Exception {
        Vendedor obj = (Vendedor) (objeto);

        String sql = "insert into vendedor (vend_nome,vend_cpf,vend_telefone,vend_celular,vend_email,vend_cep,vend_logradouro,vend_numero,vend_complemento,vend_bairro,vend_muni_id) VALUES (?,?,?,?,?,?,?,?,?,?,?);";


        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getNome());
        prd.setString(2, obj.getCpf());
        prd.setString(3, obj.getTelefone());
        prd.setString(4, obj.getCelular());
        prd.setString(5, obj.getEmail());
        prd.setString(6, obj.getCep());
        prd.setString(7, obj.getLogradouro());
        prd.setString(8, obj.getNumero());
        prd.setString(9, obj.getComplemento());
        prd.setString(10, obj.getBairro());
        prd.setInt(11, obj.getMuniId());

        prd.execute();

        String sql2 = "select currval('vendedor_vend_id_seq') as vend_id";

        Statement stm = cnn.createStatement();

        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            int codigo = rs.getInt("vend_id");
            obj.setId(codigo);
        }

        rs.close();
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "UPDATE public.vendedor set excluido = true WHERE vend_id = ?;";


        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setInt(1, id);

        prd.execute();

        prd.close();
    }

    @Override
    public void alterar(Object objeto) throws Exception {

        Vendedor obj = (Vendedor) (objeto);

        String sql = "update public.vendedor set "
                + "vend_nome = ?,"
                + "vend_cpf = ?,"
                + "vend_telefone = ?,"
                + "vend_celular = ?,"
                + "vend_email = ?,"
                + "vend_cep = ?,"
                + "vend_logradouro = ?,"
                + "vend_numero = ?,"
                + "vend_complemento = ?,"
                + "vend_bairro = ?,"
                + "vend_muni_id = ?"
                + " where vend_id = ?;";


        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getNome());
        prd.setString(2, obj.getCpf());
        prd.setString(3, obj.getTelefone());
        prd.setString(4, obj.getCelular());
        prd.setString(5, obj.getEmail());
        prd.setString(6, obj.getCep());
        prd.setString(7, obj.getLogradouro());
        prd.setString(8, obj.getNumero());
        prd.setString(9, obj.getComplemento());
        prd.setString(10, obj.getBairro());
        prd.setInt(11, obj.getMuniId());
        prd.setInt(12, obj.getId());

        prd.execute();

        prd.close();
    }

    @Override
    public ArrayList<Vendedor> listar() throws Exception {

        ArrayList<Vendedor> listaObjs = new ArrayList<>();

        String sql = "select * from public.vendedor where excluido = false order by vend_id ";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        Vendedor objeto;

        while (rs.next()) {
            objeto = new Vendedor();
            objeto.setId(rs.getInt("vend_id"));
            objeto.setNome(rs.getString("vend_nome"));
            objeto.setCpf(rs.getString("vend_cpf"));
            objeto.setTelefone(rs.getString("vend_telefone"));
            objeto.setCelular(rs.getString("vend_celular"));
            objeto.setEmail(rs.getString("vend_email"));
            objeto.setCep(rs.getString("vend_cep"));
            objeto.setLogradouro(rs.getString("vend_logradouro"));
            objeto.setNumero(rs.getString("vend_numero"));
            objeto.setComplemento(rs.getString("vend_complemento"));
            objeto.setBairro(rs.getString("vend_bairro"));
            objeto.setMuniId(rs.getInt("vend_muni_id"));
            listaObjs.add(objeto);
        }
        rs.close();

        return listaObjs;

    }

    @Override
    public Object consultar(int id) throws Exception {
        String sql = "select * from public.vendedor where vend_id = ? and excluido = false;";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setInt(1, id);

        ResultSet rs = prd.executeQuery();

        Vendedor objeto = new Vendedor();

        if (rs.next()) {
            objeto.setId(rs.getInt("vend_id"));
            objeto.setNome(rs.getString("vend_nome"));
            objeto.setCpf(rs.getString("vend_cpf"));
            objeto.setTelefone(rs.getString("vend_telefone"));
            objeto.setCelular(rs.getString("vend_celular"));
            objeto.setEmail(rs.getString("vend_email"));
            objeto.setCep(rs.getString("vend_cep"));
            objeto.setLogradouro(rs.getString("vend_logradouro"));
            objeto.setNumero(rs.getString("vend_numero"));
            objeto.setComplemento(rs.getString("vend_complemento"));
            objeto.setBairro(rs.getString("vend_bairro"));
            objeto.setMuniId(rs.getInt("vend_muni_id"));
        }

        prd.execute();

        prd.close();

        return objeto;
    }

}
