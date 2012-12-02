package controllers;

import java.sql.SQLException;

import daos.TipoVidroDAO;
import models.Cliente;
import models.Funcionario;
import models.TipoVidro;
import play.mvc.Controller;

public class ControllerMaterial extends Controller {
	
	private static Cliente cliente;
	private static Funcionario f;
	
	public ControllerMaterial(Funcionario f, Cliente cliente) {
		this.f = f;
		this.cliente = cliente;
	}
	
	public ControllerMaterial() {
	
	}
	
	public static void cadastrarMaterial(String nometipo, String espessura, String preco, String desc) throws SQLException {
		TipoVidro tp = new TipoVidro(nometipo, Double.parseDouble(espessura), Double.parseDouble(preco), desc);
		TipoVidroDAO tpDao = new TipoVidroDAO(tp);
		tpDao.save();
		Application.telaPrincipal();
	}
	
	public static void cadastroMaterial() {
		Funcionario c = f;
		try {
			render(c);
		} catch (Exception e) {
			Application.index();
		}
	}
}
