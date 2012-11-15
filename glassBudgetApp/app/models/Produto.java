package models;

public class Produto {
	private String codproduto;
	private String nomeproduto;
	private double altura;
	private double largura;
	
	public Produto(String codproduto, String nomeproduto, double altura, double largura) {
		this.setCodproduto(codproduto);
		this.setNomeproduto(nomeproduto);
		this.setAltura(altura);
		this.setLargura(largura);
	}
	
	public Produto(){}

	public Produto(String nomeproduto, double altura, double largura) {
		super();
		this.nomeproduto = nomeproduto;
		this.altura = altura;
		this.largura = largura;
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
	
	@Override
	public String toString() {
		String a = "Produto{\n"+codproduto+"\n"+nomeproduto+"\n"+altura+"\n"+codproduto+"\n}\n";
		return a;
	}
}
