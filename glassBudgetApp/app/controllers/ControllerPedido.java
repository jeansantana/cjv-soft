package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Cliente;
import models.Funcionario;
import models.ModelBuscaPedidos;
import models.Orcamento;
import models.Pedido;
import models.Produto;
import models.TipoVidro;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import play.db.jpa.Model;
import play.mvc.Controller;
import utils.Utils;
import daos.ConnectPostegreSQL;
import daos.OrcamentoDAO;
import daos.PedidoDAO;
import daos.ProdutoDAO;

public class ControllerPedido extends Controller {
	
	/**
	 * 
	 */
	private static List<TipoVidro> listTipoVidros;
	private static Cliente cliente;
	private static Funcionario f;
	private static Pedido ped;
	private static Produto prodt;
	private static Orcamento orcmnt;
	private static TipoVidro tpVidro;
	private static Statement comando;
	public static Map<String, Object> param;
	public static List<ModelBuscaPedidos> pedidos;
	
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
		listTipoVidros = list;
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
		
		ArrayList<TipoVidro> list = new ArrayList<TipoVidro>();
		
		listTipoVidros = list;
		
		while(res.next()) {
			TipoVidro t = new TipoVidro(Integer.parseInt(res.getString("codtipovidro")), res.getString("nome"), Double.parseDouble(res.getString("espessura")), Double.parseDouble(res.getString("preco")), res.getString("descricao"));
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
	
	public static void listarPedidos(String data, String cpfcnpj) throws SQLException {
		if (data == null || data.equals("")) {
			if (cpfcnpj == null) {
				System.err.println("Caso 1: Erro");
			} else if (cpfcnpj.equals("")){
				validation.required(cpfcnpj).message("Preencha o campo do código do cliente (CPF/CNPJ) para prosseguir com a busca!");
			} else {
				if (validation.hasErrors()) {
					params.flash();
					validation.keep();
					buscarPedidos();
				} else {
					PedidoDAO p = new PedidoDAO();
					pedidos = p.findByCpf(cpfcnpj);
				}
			}
		} else if (cpfcnpj == null || cpfcnpj.equals("")) {
			if (data == null) {
				System.err.println("Caso 2: Erro");
			} else if (data.equals("")) {
				validation.required(data).message("Preencha o campo data do pedido para prosseguir com a busca!");
			} else if (data.equals("____-__-__" )) {
				validation.required(data).message("Preencha o campo data do pedido para prosseguir com a busca!");
			} else {
				if (validation.hasErrors()) {
					params.flash();
					validation.keep();
				} else {
					PedidoDAO p = new PedidoDAO();
					pedidos = p.findByData(Utils.invert(data));
				}
			}
		}
		if(validation.hasErrors()) {
			params.flash();
			validation.keep();
			buscarPedidos();
		} else {
			List<ModelBuscaPedidos> list = pedidos;
		
			listaPedidos();
		}
	}
	
	public static void listaPedidos() {
		List<ModelBuscaPedidos> list = pedidos;
		Funcionario c = f;
		render(c, list);
	}

	public static void fazerPedido(String nomeProd, String largura, String altura, String lstMateriais) throws SQLException {
		Pedido pedido = new Pedido(cliente.getCod_cliente(), f.getCodfuncionario(), 'n');
		ped = pedido;
		
		TipoVidro tipo = null;
		
		for (TipoVidro tp : listTipoVidros) {
			if (lstMateriais.equals(tp.getNome())) {
				tipo = tp;
			}
		}
		
		tpVidro = tipo;
		
		Produto prod = new Produto(nomeProd, Double.parseDouble(altura), Double.parseDouble(largura), tipo.getCodtipovidro());
		prodt = prod;
		
		Orcamento orc = new Orcamento(pedido.getData_pedido(), pedido.getHorapedido(), cliente.getCod_cliente(), prod.getAltura()*prod.getLargura()*tipo.getPreco(), prod.getCodproduto());
		orcmnt = orc;
		
		confirmarPedido();
	}
	
	public static void confirmarcaoPedido() throws SQLException {
		PedidoDAO pdao = new PedidoDAO(ped);
		pdao.save();
		
		ProdutoDAO prodDao = new ProdutoDAO(prodt);
		prodDao.save();
		prodt = prodDao.resgate();
		orcmnt.setCodproduto(prodt.getCodproduto());
		OrcamentoDAO orcDao = new OrcamentoDAO(orcmnt);
		orcDao.save();
		createReport();
		Application.telaPrincipal();
	}
	
	private static void createReport() throws SQLException {		
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/glass_budget", "jeansilva",
					"123");
			String report = "/home/jeansilva/workspace/glassBudgetApp/reports/notafiscal.jrxml";

			Pedido pedido = ped;
			Cliente client = cliente;
			
			param = new HashMap<String, Object>();
			param.put("datapedido", Utils.convertStringtoDate(pedido.getData_pedido()));
			param.put("cpf_cnpj", client.getCpf_cnpj());
			System.err.println(pedido.getData_pedido());
			System.err.println(pedido.getHorapedido());
			param.put("horapedido", Utils.convertStringToTimestamp(pedido.getData_pedido(), pedido.getHorapedido()));
			JasperReport jasperReport = JasperCompileManager.compileReport(report);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, param, con);
			//JasperViewer.viewReport(jasperPrint);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	public static void confirmarPedido() {
		Funcionario c = f;
		Pedido pedido = ped;
		Produto produto = prodt;
		Orcamento orcamento = orcmnt;
		TipoVidro tipoVidro = tpVidro;
		Cliente cl = cliente;
		render(c, cl, pedido, produto, orcamento, tipoVidro);
		
		try {
			render(c, pedido, produto, orcamento, tipoVidro);
		} catch (Exception e) {
			Application.index();
		}
	}
}