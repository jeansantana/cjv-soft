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
		
		validation.required(nometipo).message("Campo Nome é obrigatório");
		validation.required(espessura).message("Campo Espessura é obrigatório");
		validation.required(preco).message("Campo Preço é obrigatório");
		validation.required(desc).message("Campo Descrição é obrigatório");
		double esp = 0;
		double pr = 0;
		
		if (!espessura.equals("")) {
			try {
				esp = Double.parseDouble(espessura);
			} catch (Exception e) {
				validation.addError(espessura, "O valor do campo Espessura deve ser preenchido somente com números");
				e.printStackTrace();
			}
		}
		
		if (!preco.equals("")) {
			try {
				pr = Double.parseDouble(preco);
			} catch (Exception e) {
				validation.addError(preco, "O valor do campo Preço deve ser preenchido somente com números");
				e.printStackTrace();
			}
		}
		
		if (validation.hasErrors()) {
			params.flash();
			validation.keep();
			cadastroMaterial();
		} else {
			TipoVidro tp = new TipoVidro(nometipo, esp, pr, desc);
			TipoVidroDAO tpDao = new TipoVidroDAO(tp);
			tpDao.save();
			Application.telaPrincipal();
		}
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
