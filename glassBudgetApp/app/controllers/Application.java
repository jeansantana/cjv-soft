package controllers;

import play.*;
import play.mvc.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import models.*;

import models.*;

public class Application extends Controller {

	private static Connection con;
	private static Statement comando;
	private static Funcionario f;

	// conecta ao banco
	private static void conectar() throws SQLException, java.sql.SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
		}

		con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/glassBudget", "jeansilva",
				"123");
		comando = con.createStatement();
	}

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
		conectar();
		ResultSet res = comando.executeQuery("select * from funcionario where login = '" + login + "' and senha='" + senha + "'");
		System.out.println("Aqui: " + login);
		if (res.next()) {
			String nome = res.getString("nomefuncionario");
			f = new Funcionario(nome, login, senha);
			session.put("login", f);// o segundo parametro pode ser um objeto
									// para por exe. saber quem está logado.
									// Cria uma classe Usuário
			telaPrincipal();
		} else {
			index();
		}
	}
	
	public static void cadastrarCliente(String nome, String cidade, String uf, String bairro, String telefone, 
			String num, String tipo, String cpfcnpj) throws SQLException {
		conectar();
		String endereco = cidade + "-" + uf + " " + bairro + " n"+ num;
		if (tipo.equals("Pessoa Física")) {
			tipo = "física";
		} else {
			tipo = "jurídica";
		}
		
		comando.executeUpdate("insert into cliente (nome, endereco, cpf_cnpj, telefone, tipo) " +
				"values ('"+ nome +"', '"+ endereco +"', '"+ cpfcnpj +"', '"+ telefone +"', '"+ tipo +"')");
		telaPrincipal();
	}

	public static void logout() {
		session.remove("login");
		index();
	}

	public static void cadastroCliente() {
		Funcionario c = f;
		render(c);
	}

	public static void cadastroProduto() {
		Funcionario c = f;
		render(c);
	}

	public static void telaPrincipal() {
		Funcionario c = f;
		render(c);
	}
	
	public static void realizarPedido() throws SQLException {
		Funcionario c = f;
		
		conectar();
		ResultSet res = comando.executeQuery("select * from tipodevidro");
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		while(res.next()) {
			TipoVidro t = new TipoVidro(res.getString("nome"), Double.parseDouble(res.getString("espessura")), Double.parseDouble(res.getString("preco")), res.getString("descricao"));
			list.add(t);
		}
		
		render(c, list);
	}
}