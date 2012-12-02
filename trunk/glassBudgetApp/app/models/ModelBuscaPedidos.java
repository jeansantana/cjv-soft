package models;

import utils.Utils;

public class ModelBuscaPedidos {

	private String nomeCliente;
	private String dataPedido;
	private String horaPedido;
	private String nomeProduto;
	private double valor;

	public ModelBuscaPedidos(String nomeCliente, String dataPedido,
			String horaPedido, String nomeProduto, double valor) {
		super();
		this.nomeCliente = nomeCliente;
		this.dataPedido = dataPedido;
		this.horaPedido = horaPedido;
		this.nomeProduto = nomeProduto;
		this.valor = valor;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getDataPedido() {
		return dataPedido;
	}

	public void setDataPedido(String dataPedido) {
		this.dataPedido = dataPedido;
	}

	public String getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(String horaPedido) {
		this.horaPedido = horaPedido;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public double getValor() {
		return valor;
	}
	
	public String getValorFormated() {
		return Utils.formatarMoeda(valor);
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

}
