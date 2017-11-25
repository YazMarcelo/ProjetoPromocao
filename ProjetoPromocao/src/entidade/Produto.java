package entidade;

import java.util.Date;

public class Produto {

private int id;
private String descricao;
private double valor;
private Date dataFabricacao;
private Date dataVencimento;
private int qtdUnidade;
private UnidadeMedida unidadeMedida;
private int saldoEstoque;
private int porcentagemDesconto;

public int getId() {
return id;
}

public void setId(int id) {
this.id = id;
}

public String getDescricao() {
return descricao;
}

public void setDescricao(String descricao) {
this.descricao = descricao;
}

public double getValor() {
return valor;
}

public void setValor(double valor) {
this.valor = valor;
}

public Date getDataFabricacao() {
return dataFabricacao;
}

public void setDataFabricacao(Date dataFabricacao) {
this.dataFabricacao = dataFabricacao;
}

public Date getDataVencimento() {
return dataVencimento;
}

public void setDataVencimento(Date dataVencimento) {
this.dataVencimento = dataVencimento;
}

public int getQtdUnidade() {
return qtdUnidade;
}

public void setQtdUnidade(int qtdUnidade) {
this.qtdUnidade = qtdUnidade;
}

public UnidadeMedida getUnidadeMedida() {
return unidadeMedida;
}

public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
this.unidadeMedida = unidadeMedida;
}

public int getSaldoEstoque() {
return saldoEstoque;
}

public void setSaldoEstoque(int saldoEstoque) {
this.saldoEstoque = saldoEstoque;
}

public int getPorcentagemDesconto() {
return porcentagemDesconto;
}

public void setPorcentagemDesconto(int porcentagemDesconto) {
this.porcentagemDesconto = porcentagemDesconto;
}


}