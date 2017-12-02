package entidade;

import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private Double valorTotal;
    private Vendedor vendedor = null;
    private FormaPagamento formaPagamento;
    private List<ItemPedido> itens;

    public Pedido() {
        itens = new ArrayList();
    }

    public Pedido(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public void addValorTotal(double valor) {
        this.valorTotal += valor;
    }
}
