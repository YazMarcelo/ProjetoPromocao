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
public class ProdutoDAO implements CRUD {

    private final Connection cnn = util.Conexao.getConexao();

    @Override
    public void incluir(Object objeto) throws Exception {
        Produto obj = (Produto) (objeto);

        String sql = "insert into produto (prod_descricao,prod_valor,prod_data_fabricacao,prod_data_vencimento,prod_qtd_unidade,prod_unme_id,prod_saldo_estoque,prod_desconto) VALUES (?,?,?,?,?,?,?,?);";

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getDescricao());
        prd.setDouble(2, obj.getValor());
        prd.setDate(3, (Date) obj.getDataFabricacao());
        prd.setDate(4, (Date) obj.getDataVencimento());
        prd.setInt(5, obj.getQtdUnidade());
        prd.setInt(6, obj.getUnidadeMedida().getId());
        prd.setInt(7, obj.getSaldoEstoque());
        prd.setFloat(8, obj.getDesconto());

        prd.execute();

        String sql2 = "select currval('produto_prod_id_seq') as prod_id";

        Statement stm = cnn.createStatement();

        ResultSet rs = stm.executeQuery(sql2);

        if (rs.next()) {
            int codigo = rs.getInt("prod_id");
            obj.setId(codigo);
        }

        rs.close();
    }

    @Override
    public void excluir(int id) throws Exception {
        String sql = "UPDATE public.produto set excluido = true WHERE prod_id = ?;";

        Connection cnn = util.Conexao.getConexao();

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setInt(1, id);

        prd.execute();

        prd.close();
    }

    @Override
    public void alterar(Object objeto) throws Exception {

        Produto obj = (Produto) (objeto);

        String sql = "update public.produto set "
                + "prod_descricao = ?,"
                + "prod_valor = ?,"
                + "prod_data_fabricacao = ?,"
                + "prod_data_vencimento = ?,"
                + "prod_qtd_unidade = ?,"
                + "prod_unme_id = ?,"
                + "prod_saldo_estoque = ?,"
                + "prod_desconto = ?"
                + " where prod_id = ?;";

        PreparedStatement prd = cnn.prepareStatement(sql);

        prd.setString(1, obj.getDescricao());
        prd.setDouble(2, obj.getValor());
        prd.setDate(3, (Date) obj.getDataFabricacao());
        prd.setDate(4, (Date) obj.getDataVencimento());
        prd.setInt(5, obj.getQtdUnidade());
        prd.setInt(6, obj.getUnidadeMedida().getId());
        prd.setInt(7, obj.getSaldoEstoque());
        prd.setFloat(8, obj.getDesconto());
        prd.setInt(9, obj.getId());

        prd.execute();

        prd.close();
    }

    @Override
    public ArrayList<Produto> listar() throws Exception {

        ArrayList<Produto> listaObjs = new ArrayList<>();

        String sql = "select * from public.produto where excluido = false order by prod_id ";

        Statement stm = cnn.createStatement();
        ResultSet rs = stm.executeQuery(sql);

        Produto objeto;

        while (rs.next()) {
            objeto = new Produto();
            objeto.setId(rs.getInt("prod_id"));
            objeto.setDescricao(rs.getString("prod_descricao"));
            objeto.setValor(rs.getDouble("prod_valor"));
            objeto.setDataFabricacao(rs.getDate("prod_data_fabricacao"));
            objeto.setDataVencimento(rs.getDate("prod_data_vencimento"));
            objeto.setQtdUnidade(rs.getInt("prod_qtd_unidade"));
            objeto.setUnidadeMedida(new NUnidadeMedida().consultar(rs.getInt("prod_unme_id")));
            objeto.setSaldoEstoque(rs.getInt("prod_saldo_estoque"));
            objeto.setDesconto(rs.getFloat("prod_desconto"));
            listaObjs.add(objeto);
        }

        rs.close();

        return listaObjs;
    }

    @Override
    public Object consultar(int id) throws Exception {
        String sql = "select * from public.produto where prod_id = ? and excluido = false;";

        PreparedStatement prd = cnn.prepareStatement(sql);

        //Seta os valores para o procedimento
        prd.setInt(1, id);

        ResultSet rs = prd.executeQuery();

        Produto objeto = new Produto();

        if (rs.next()) {
            objeto.setId(rs.getInt("prod_id"));
            objeto.setDescricao(rs.getString("prod_descricao"));
            objeto.setValor(rs.getDouble("prod_valor"));
            objeto.setDataFabricacao(rs.getDate("prod_data_fabricacao"));
            objeto.setDataVencimento(rs.getDate("prod_data_vencimento"));
            objeto.setQtdUnidade(rs.getInt("prod_qtd_unidade"));
            objeto.setUnidadeMedida(new NUnidadeMedida().consultar(rs.getInt("prod_unme_id")));
            objeto.setSaldoEstoque(rs.getInt("prod_saldo_estoque"));
            objeto.setDesconto(rs.getFloat("prod_desconto"));
        }

        prd.execute();

        prd.close();

        return objeto;
    }

}
