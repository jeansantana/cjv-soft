package models;

public class TipoVidro {
	private int codtipovidro;
	private String nome;
	private double espessura;
	private double preco;
	private String descricao;
	
	public TipoVidro() {}
	
	public TipoVidro(String nome, double espessura, double preco,
			String descricao) {
		this.nome = nome;
		this.espessura = espessura;
		this.preco = preco;
		this.descricao = descricao;
	}

	public TipoVidro(int codtipovidro, String nome, double espessura, double preco, String descricao) {
		this.setCodtipovidro(codtipovidro);
		this.setNome(nome);
		this.setEspessura(espessura);
		this.setPreco(preco);
		this.setDescricao(descricao);
	}

	public int getCodtipovidro() {
		return codtipovidro;
	}

	public void setCodtipovidro(int codtipovidro) {
		this.codtipovidro = codtipovidro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getEspessura() {
		return espessura;
	}

	public void setEspessura(double espessura) {
		this.espessura = espessura;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		String a = "TipoDeVidro{\n"+codtipovidro+"\n"+nome+"\n"+espessura+"\n"+preco+"\n"+descricao+"\n}\n";
		return a;
	}
}