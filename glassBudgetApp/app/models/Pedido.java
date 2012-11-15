package models;

public class Pedido {
	private String data_pedido;
	private String horapedido;
	private int cod_cliente;
	private int codfuncionario;
	
	public Pedido() {}

	public Pedido(String data_pedido, String horapedido, int cod_cliente,
			int codfuncionario) {
		super();
		this.data_pedido = data_pedido;
		this.horapedido = horapedido;
		this.cod_cliente = cod_cliente;
		this.codfuncionario = codfuncionario;
	}

	public String getData_pedido() {
		return data_pedido;
	}

	public void setData_pedido(String data_pedido) {
		this.data_pedido = data_pedido;
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

	public int getCodfuncionario() {
		return codfuncionario;
	}

	public void setCodfuncionario(int codfuncionario) {
		this.codfuncionario = codfuncionario;
	}
	
	@Override
	public String toString() {
		String a = "Pedito{\n"+data_pedido+"\n"+horapedido+"\n"+cod_cliente+"\n"+codfuncionario+"\n}\n";
		return a;
	}
}
