package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jj.play.ns.com.jhlabs.image.ErodeFilter;

import models.Cliente;
import models.Funcionario;
import models.TipoVidro;
import play.mvc.Before;
import play.mvc.Controller;
//import play.data.validation.*;
import play.data.validation.Error;
import play.data.validation.Validation;
import daos.ClienteDAO;
import daos.ConnectPostegreSQL;

public class Application extends Controller {

	private static Funcionario f;	
	private static Cliente cliente;
	
	// uso do sessions play framework!
	@Before(unless = { "index", "logar", "telaLogin" })
	// nenhuma outra página além dessas poderia ser aberta enquanto não abrisse
	// a sessão login
	public static void checkAutenticacao() throws SQLException {
		if (!session.contains("login")) {
			index();
		}
	}

	public static void index() {
		render();
	}

	public static void logar(String login, String senha) throws SQLException {
		ConnectPostegreSQL.conectar();
		validation.required(login).message("Digite o login!");
		validation.required(senha).message("Digite a senha!");
		
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			index();
		} else {
		
			ResultSet res = ConnectPostegreSQL.comando.executeQuery("select * from funcionario where login = '" + login + "' and senha='" + senha + "'");
			if (res.next()) {
				String nome = res.getString("nomefuncionario");
				f = new Funcionario(login, nome, senha);
				session.put("login", f);// o segundo parametro pode ser um objeto
										// para por exe. saber quem está logado.
										// Cria uma classe Usuário
				telaPrincipal();
			} else {
				params.flash();
				validation.keep();
				validation.addError(login, "Login ou senha incorreto!");
				index();
			}
		}
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
			ClienteDAO con = new ClienteDAO();
			con.save(c);
			cliente = c;
			realizarPedidoInfo();
		}
	}
	
	public static void buscarCliente(String cpfcnpj) throws SQLException {
		ClienteDAO client = new ClienteDAO();
		cliente = client.search(cpfcnpj);
		System.out.println(cliente);
		realizarPedidoInfo();
	}

	public static void criarContaFuncionario() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}
	
	public static void gerenciamento() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}
	
	public static void buscarPedidos() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}
	
	public static void logout() {
		session.remove("login");
		index();
	}

	public static void cadastroCliente() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}

	public static void telaPrincipal() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}
	
	public static void realizarPedidoInfo() throws SQLException {
		Funcionario c = f;
		Cliente cl = null;
		if (cliente == null) {
			params.flash();
			validation.keep();
			validation.addError(null, "Cliente não encontrado!");
			realizarPedido();
		} else {
			cl = cliente;
		}
		
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery("select * from tipodevidro");
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		while(res.next()) {
			TipoVidro t = new TipoVidro(res.getString("nome"), Double.parseDouble(res.getString("espessura")), Double.parseDouble(res.getString("preco")), res.getString("descricao"));
			list.add(t);
		}
		
		try {
			render(c, list, cl);
		} catch (Exception e) {
			realizarPedido();
		}
	}
	
	public static void realizarPedido() throws SQLException {
		Funcionario c = f;
		//Cliente cl = cliente;
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery("select * from tipodevidro");
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		while(res.next()) {
			TipoVidro t = new TipoVidro(res.getString("nome"), Double.parseDouble(res.getString("espessura")), Double.parseDouble(res.getString("preco")), res.getString("descricao"));
			list.add(t);
		}
		
		try {
			render(c, list);
		} catch (Exception e) {
			index();
		}
	}
}