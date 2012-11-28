package models;

public class Orcamento {
	private String datapedido;
	private String horapedido;
	private int cod_cliente;
	private double valor;
	private int codproduto;

	public Orcamento() {
	}

	public Orcamento(String datapedido, String horapedido, int cod_cliente,
			double valor, int codproduto) {
		this.setDatapedido(datapedido);
		this.setHorapedido(horapedido);
		this.setCod_cliente(cod_cliente);
		this.setValor(valor);
		this.setCodproduto(codproduto);
	}

	public Orcamento(String horapedido, int cod_cliente, double valor,
			int codproduto) {
		super();
		this.horapedido = horapedido;
		this.cod_cliente = cod_cliente;
		this.valor = valor;
		this.codproduto = codproduto;
	}

	public String getDatapedido() {
		return datapedido;
	}

	public void setDatapedido(String datapedido) {
		this.datapedido = datapedido;
	}

	public String getHorapedido() {
		return horapedido;
	}

	public void setHorapedido(String horapedido) {
		this.horapedido = horapedido;
	}

	public int getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(int cod_cliente) {
		this.cod_cliente = cod_cliente;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(int codproduto) {
		this.codproduto = codproduto;
	}

	@Override
	public String toString() {
		String a = "Orcamento{\n" + datapedido + "\n" + horapedido + "\n"
				+ cod_cliente + "\n" + valor + "\n" + codproduto + "\n}\n";
		return a;
	}
}
