/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidade.Exemplo;
import interfaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author aluno
 */
public class ExemploDAO implements CRUD{

    @Override
    public void incluir(Object objeto) throws Exception {
        Exemplo objExemplo = (Exemplo)(objeto);
        
        String sql = "insert into exemplo (exem_nome, exem_descricao) VALUES (?,?);";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setString(1, objExemplo.getNome());
        prd.setString(2, objExemplo.getDescricao());


        //Executa o comando no banco de dados
        prd.execute();

        //cria o sql para recuperar o codigo gerado
        String sql2 = "select currval('exemplo_exem_id_seq') as exem_id";

        Statement stm = cnn.createStatement();

        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            int codigo = rs.getInt("exem_id");
            objExemplo.setCodigo(codigo);
        }

        rs.close();
        cnn.close();
    }

    @Override
    public void excluir(int id) throws Exception {
        //Cria a instrução SQL para a inserção no banco
        String sql = "delete from exemplo where exem_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        //Cria o procedimento armazenado a partir da conexão
        //e string sql
        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setInt(1, id);

        prd.execute();

        prd.close();
        cnn.close();
    }

    @Override
    public void alterar(Object objeto) throws Exception {
        
        Exemplo objExem =  (Exemplo) (objeto);
        
        //Cria a instrução SQL para a inserção no banco
        String sql = "update exemplo set exem_nome = ?, exem_descricao = ? where exem_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        //Cria o procedimento armazenado a partir da conexão
        //e string sql
        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setString(1, objExem.getNome());
        prd.setString(2, objExem.getDescricao());
        prd.setInt(3, objExem.getCodigo());

        prd.execute();

        prd.close();
        cnn.close(); 
    }

    @Override
    public ArrayList<Object> listar() throws Exception {
        
        ArrayList<Object> listaExemplo = new ArrayList<>();

        String sql = "select * from exemplo order by exem_id";

        Connection cnn = util.Conexao.getConexao();
        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        Exemplo objeto;

        while (rs.next()) {
            objeto = new Exemplo();
            objeto.setCodigo(rs.getInt("exem_id"));
            objeto.setNome(rs.getString("exem_nome"));
            objeto.setDescricao(rs.getString("exem_descricao"));
            listaExemplo.add(objeto);
        }

        rs.close();
        cnn.close();

        return listaExemplo;
    }

    @Override
    public Object consultar(int id) throws Exception {
        String sql = "select * from exemplo where exem_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        //Cria o procedimento armazenado a partir da conexão
        //e string sql
        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setInt(1, id);

        ResultSet rs = prd.executeQuery();

        Exemplo objeto = new Exemplo();

        if (rs.next()) {
            objeto.setCodigo(rs.getInt("exem_id"));
            objeto.setDescricao(rs.getString("exem_nome"));
            objeto.setDescricao(rs.getString("exem_descricao"));
        }

        prd.execute();

        prd.close();
        cnn.close();

        return objeto;
    }
    
}
