package controllers;

import java.sql.SQLException;

import models.Cliente;
import models.Funcionario;
import daos.ClienteDAO;
import play.mvc.Controller;

public class ControllerCliente extends Controller {
	//criar funções de controle do cliente
	
	private static Cliente cliente;
	private static Funcionario f;
	
	public ControllerCliente(Funcionario f, Cliente cliente) {
		this.f = f;
		this.cliente = cliente;
	}
	
	public ControllerCliente() {
	}

	public static void cadastrarCliente(String nome, String cep, String logra, String cidade, String uf, 
			String bairro, String telefone, String num, String tipo, String cpfcnpj) throws SQLException {
		
		String endereco = logra + " Nº " + num + ". Bairro: " + bairro + ". " + cidade + "-" + uf + ". CEP: " + cep;
		
		if (tipo.equals("Pessoa Física")) {
			tipo = "física";
		} else if (tipo.equals("Pessoa Jurídica")){
			tipo = "jurídica";
		}
		
		validation.required(nome).message("Campo nome é obrigatório");
		validation.required(cep).message("Campo CEP é obrigatório");
		validation.required(logra).message("Campo Logradouro é obrigatório");
		validation.required(cidade).message("Campo Cidade é obrigatório");
		if (uf.equals("-")) {
			//adiciona um erro no flash.
			validation.addError(uf, "Campo UF é obrigatório");
		}
		validation.required(bairro).message("Campo Bairro é obrigatório");
		validation.required(telefone).message("Campo Telefone é obrigatório");
		validation.required(num).message("Campo Nº é obrigatório");
		if (tipo.equals("Selecione"))
			validation.addError(tipo, "Campo Tipo é obrigatório");
		validation.required(cpfcnpj).message("Campo CPF/CNPJ é obrigatório");
		
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			cadastroCliente();
			
		} else {
			Cliente c = new Cliente(nome, endereco, cpfcnpj, telefone, tipo);
			ClienteDAO con = new ClienteDAO(c);
			con.save();
			cliente = c;
			
			ClienteDAO client = new ClienteDAO();
			cliente = client.find(cpfcnpj);
			
			ControllerPedido controlPedido = new ControllerPedido(cliente, f);
			ControllerPedido.realizarPedidoInfo();
		}
	}
	
	public static void cadastroCliente() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			Application.index();
		}
	}
	
	public static void buscarCliente(String cpfcnpj) throws SQLException {
		ClienteDAO client = new ClienteDAO();
		cliente = client.find(cpfcnpj);
		ControllerPedido controlPedido = new ControllerPedido(cliente, f);
		ControllerPedido.realizarPedidoInfo();
	}
}
