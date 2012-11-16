package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Funcionario;
import models.TipoVidro;
import play.mvc.Before;
import play.mvc.Controller;
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
		ResultSet res = ConnectPostegreSQL.comando.executeQuery("select * from funcionario where login = '" + login + "' and senha='" + senha + "'");
		if (res.next()) {
			String nome = res.getString("nomefuncionario");
			f = new Funcionario(login, nome, senha);
			session.put("login", f);// o segundo parametro pode ser um objeto
									// para por exe. saber quem está logado.
									// Cria uma classe Usuário
			telaPrincipal();
		} else {
			index();
		}
	}
	
	public static void cadastrarCliente(String nome, String cep, String logra, String cidade, String uf, 
			String bairro, String telefone, String num, String tipo, String cpfcnpj) throws SQLException {
		String endereco = logra + " Nº " + num + ". Bairro: " + bairro + ". " + cidade + "-" + uf + ". CEP: " + cep;
		if (tipo.equals("Pessoa Física")) {
			tipo = "física";
		} else {
			tipo = "jurídica";
		}
		Cliente cliente = new Cliente(nome, endereco, cpfcnpj, telefone, tipo);
		ClienteDAO con = new ClienteDAO();
		con.save(cliente);
		telaPrincipal();
	}
	
	public static Cliente buscarCliente(String cpfcnpj) throws SQLException {
		ClienteDAO client = new ClienteDAO();
		System.out.println("Aqui:");
		System.out.println(client.search(cpfcnpj));
		return client.search(cpfcnpj);
	}

	public static void criarContaFuncionario() {
		Funcionario c = f;
		render(c);
	}
	
	public static void gerenciamento() {
		Funcionario c = f;
		render(c);
	}
	
	public static void buscarPedidos() {
		Funcionario c = f;
		render(c);
	}
	
	public static void logout() {
		session.remove("login");
		index();
	}

	public static void cadastroCliente() {
		Funcionario c = f;
		render(c);
	}

	public static void telaPrincipal() {
		Funcionario c = f;
		render(c);
	}
	
	public static void realizarPedido() throws SQLException {
		Funcionario c = f;
		Cliente cl = cliente;
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery("select * from tipodevidro");
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		while(res.next()) {
			TipoVidro t = new TipoVidro(res.getString("nome"), Double.parseDouble(res.getString("espessura")), Double.parseDouble(res.getString("preco")), res.getString("descricao"));
			list.add(t);
		}
		
		render(c, list, cl);
	}
}