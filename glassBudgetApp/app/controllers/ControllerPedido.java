package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;
import models.Funcionario;
import models.TipoVidro;
import daos.ConnectPostegreSQL;
import play.mvc.Controller;

public class ControllerPedido extends Controller {
	
	/**
	 * 
	 */
	
	private static Cliente cliente;
	private static Funcionario f;
	
	public ControllerPedido(Cliente cliente, Funcionario f) {
		this.cliente = cliente;
		this.f = f;
	}
	
	public ControllerPedido() {
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
			Application.index();
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

	public static void buscarPedidos() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			Application.index();
		}
	}
}
