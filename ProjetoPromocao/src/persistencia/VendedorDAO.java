package persistencia;

import entidade.*;
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

        String sql2 = "select currval('pessoa_pess_id_seq') as vend_id";

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
        Vendedor vendedor = null;
        try {
            String sql = "SELECT * FROM VENDEDOR WHERE VEND_ID = ? AND EXCLUIDO = FALSE;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setInt(1, id);
            ResultSet rs = prd.executeQuery();

            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setId(rs.getInt("VEND_ID"));
                vendedor.setNome(rs.getString("VEND_NOME"));
                vendedor.setCpf(rs.getString("VEND_CPF"));
                vendedor.setTelefone(rs.getString("VEND_TELEFONE"));
                vendedor.setCelular(rs.getString("VEND_celular"));
                vendedor.setEmail(rs.getString("VEND_EMAIL"));
                vendedor.setCep(rs.getString("VEND_CEP"));
                vendedor.setLogradouro(rs.getString("VEND_LOGRADOURO"));
                vendedor.setNumero(rs.getString("VEND_NUMERO"));
                vendedor.setComplemento(rs.getString("VEND_COMPLEMENTO"));
                vendedor.setBairro(rs.getString("VEND_BAIRRO"));
                vendedor.setMuniId(rs.getInt("VEND_MUNI_ID"));
            }
            prd.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return vendedor;
    }

    public Object consultarByCPF(String cpf) throws Exception {
        Vendedor vendedor = null;
        try {
            String sql = "SELECT * FROM VENDEDOR WHERE VEND_CPF = ? AND EXCLUIDO = FALSE;";
            PreparedStatement prd = cnn.prepareStatement(sql);
            prd.setString(1, cpf);
            ResultSet rs = prd.executeQuery();

            if (rs.next()) {
                vendedor = new Vendedor();
                vendedor.setId(rs.getInt("VEND_ID"));
                vendedor.setNome(rs.getString("VEND_NOME"));
                vendedor.setCpf(rs.getString("VEND_CPF"));
                vendedor.setTelefone(rs.getString("VEND_TELEFONE"));
                vendedor.setCelular(rs.getString("VEND_celular"));
                vendedor.setEmail(rs.getString("VEND_EMAIL"));
                vendedor.setCep(rs.getString("VEND_CEP"));
                vendedor.setLogradouro(rs.getString("VEND_LOGRADOURO"));
                vendedor.setNumero(rs.getString("VEND_NUMERO"));
                vendedor.setComplemento(rs.getString("VEND_COMPLEMENTO"));
                vendedor.setBairro(rs.getString("VEND_BAIRRO"));
                vendedor.setMuniId(rs.getInt("VEND_MUNI_ID"));
            }
            prd.close();
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return vendedor;
    }
    
    public ArrayList<String> todosCpf() throws Exception {
        ArrayList<String> cpfs;
        try {
        String sql = "SELECT vend_cpf FROM VENDEDOR WHERE EXCLUIDO = FALSE;";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

            cpfs = new ArrayList<>();
            while (rs.next()) {
                cpfs.add(rs.getString("VEND_CPF"));
            }
            rs.close();
        } catch (Exception e) {
            throw e;
        }
        return cpfs;
    }

}
