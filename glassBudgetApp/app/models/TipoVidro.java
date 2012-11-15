package models;

public class TipoVidro {
	private String nome;
	private double espessura;
	private double preco;
	private String desc;
	
	public TipoVidro() {}
	
	public TipoVidro(String nome, double espessura, double preco, String desc) {
		super();
		this.nome = nome;
		this.espessura = espessura;
		this.preco = preco;
		this.desc = desc;
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
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}	
}
