package entidade;

import java.util.Date;

public class Promocao {

	private int id;
	private String descricao;
	private TipoPromocao tipo;
	private float porcentagem = 0;
	private int qtdPaga;
	private int qtdLeva = 0;
	private Date dataInicio;
	private Date dataFim;
	private Produto prodPaga = null;
	private Produto prodLeva = null;

	public Integer getId() {
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

	public TipoPromocao getTipo() {
		return tipo;
	}

	public void setTipo(TipoPromocao tipo) {
		this.tipo = tipo;
	}

	public Float getPorcentagem() {
		return porcentagem;
	}

	public void setPorcentagem(float porcentagem) {
		this.porcentagem = porcentagem;
	}

	public Integer getQtdPaga() {
		return qtdPaga;
	}

	public void setQtdPaga(int qtdPaga) {
		this.qtdPaga = qtdPaga;
	}

	public Integer getQtdLeva() {
		return qtdLeva;
	}

	public void setQtdLeva(int qtdLeva) {
		this.qtdLeva = qtdLeva;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Produto getProdPaga() {
		return prodPaga;
	}

	public void setProdPaga(Produto prodPaga) {
		this.prodPaga = prodPaga;
	}

	public Produto getProdLeva() {
		return prodLeva;
	}

	public void setProdLeva(Produto prodLeva) {
		this.prodLeva = prodLeva;
	}

}
