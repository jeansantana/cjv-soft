package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.constant.SysProperties;

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
				int id = Integer.parseInt(res.getString("codfuncionario"));
				String nome = res.getString("nomefuncionario");
				f = new Funcionario(id, login, nome, senha);
				ControllerCliente controlCliente = new ControllerCliente(f, cliente);
				ControllerPedido controlPedido = new ControllerPedido(cliente, f);
				ControllerMaterial controlMaterial = new ControllerMaterial(f, cliente);
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

	public static void telaPrincipal() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			index();
		}
	}
}