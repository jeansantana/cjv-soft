package models;

public class Produto {
	private String codproduto;
	private String nomeproduto;
	private double altura;
	private double largura;
	private int codtipovidro;
	
	public Produto(String codproduto, String nomeproduto, double altura, double largura, int codtipovidro) {
		this.setCodproduto(codproduto);
		this.setNomeproduto(nomeproduto);
		this.setAltura(altura);
		this.setLargura(largura);
		this.setCodtipovidro(codtipovidro);
	}
	
	public Produto(){}

	public Produto(String nomeproduto, double altura, double largura, int codtipovidro) {
		super();
		this.nomeproduto = nomeproduto;
		this.altura = altura;
		this.largura = largura;
		this.codtipovidro = codtipovidro;
	}

	public String getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	public String getNomeproduto() {
		return nomeproduto;
	}

	public void setNomeproduto(String nomeproduto) {
		this.nomeproduto = nomeproduto;
	}

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}
	
	public int getCodtipovidro() {
		return codtipovidro;
	}

	public void setCodtipovidro(int codtipovidro) {
		this.codtipovidro = codtipovidro;
	}
	
	@Override
	public String toString() {
		String a = "Produto{\n"+codproduto+"\n"+nomeproduto+"\n"+altura+"\n"+codproduto+"\n"+codtipovidro+"}\n";
		return a;
	}
}
