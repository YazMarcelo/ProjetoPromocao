package entidade;

public enum TipoPromocao {
	DESCONTO("Desconto", 1),
	QUANTIDADE("Quantidade", 2),
	GERAL("Geral", 3);

	private final String nome;
	private final int id;

	private TipoPromocao(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}

	@Override
	public String toString() {
		return nome;
	}

	public int getId() {
		return id;
	}
	
	public static TipoPromocao valueOf(int id) {
		switch(id) {
			case 1: return DESCONTO;
			case 2: return QUANTIDADE;
		}
		return GERAL;
	}
}
