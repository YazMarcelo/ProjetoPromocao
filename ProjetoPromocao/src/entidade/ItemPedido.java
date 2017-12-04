package entidade;

public class ItemPedido {

    private int id;
    private Pedido pedido;
    private Produto produto;
    private Promocao promocao;
    private int qtd = 0;
    private int qtdBrinde = 0;
    private double valorUnitario;
    private float desconto = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public void addQtd(int qtd) {
        this.qtd += qtd;
    }

    public void recalcularDesconto() {
        double valorTotal = qtd * valorUnitario;
        double valorSubtraido = promocao.getTipo() == TipoPromocao.QUANTIDADE ? valorUnitario * getQtdBrinde() 
                : valorUnitario * (promocao.getDesconto() * ((qtd+1) - promocao.getQtdPaga())/100);
        desconto = (float) (valorSubtraido / valorTotal * 100);
    }

//    public int getQtdBrinde() {
//        int i = 0;
//        int qtdBrinde = 0;
//        while (i < qtd) {
//            if (i > qtdBrinde && (i - qtdBrinde) % promocao.getQtdPaga() == 0) {
//                qtdBrinde += promocao.getQtdLeva();
//                i += qtdBrinde;
//            }
//            i++;
//        }
//        return qtdBrinde;
//    }

    public int getQtdBrinde() {
        return qtdBrinde;
    }

    public void setQtdBrinde(int qtdBrinde) {
        this.qtdBrinde = qtdBrinde;
    }
    
    public void addQtdBrinde(int qtd) {
        this.qtdBrinde += qtd;
    }
    
    public Double getValorTotal() {
        double valorSemDesconto = valorUnitario * qtd;
        return valorSemDesconto - valorSemDesconto * desconto / 100;
    }
}
