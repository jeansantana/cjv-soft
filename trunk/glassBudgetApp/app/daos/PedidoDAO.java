package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.ModelBuscaPedidos;
import models.Pedido;

public class PedidoDAO implements DAOModel<Pedido>{
	
	private Pedido p;
	
	public PedidoDAO(Pedido p) {
		this.p = p;
	}
	
	public PedidoDAO() {}
	
	public void save() throws SQLException {
		String sql = "insert into pedido values ('" +
				p.getData_pedido() + "', '" +
				p.getHorapedido() + "', '" +
				p.getCod_cliente() + "', '" +
				p.getCodfuncionario() + "', '" +
				p.getStatus() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete() throws SQLException {
		String sql = "delete from pedido where data_pedido = '" + p.getData_pedido() + "' and " +
		"horapedido = '" + p.getHorapedido() + "' and " +
		"cod_cliente = '" + p.getCod_cliente() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void update() throws SQLException {
		String sql = "update pedido set codfuncionario = " +
				+ p.getCodfuncionario() +", status = '" +
				p.getStatus() + "' " +		
				"where data_pedido = '" + p.getData_pedido() + "' and " +
				"horapedido = '" + p.getHorapedido() + "' and " +
				"cod_cliente = '" + p.getCod_cliente() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Pedido find() throws SQLException {
		String sql = "select * from pedido where data_pedido = '" + 
				p.getData_pedido() + "' and horapedido = '" + 
				p.getHorapedido() + "' and cod_cliente = '" + 
				p.getCod_cliente() + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Pedido p = null;
		if (res.next()) {
			char s = res.getString("status").charAt(0);
			p = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")), s);
		}
		return p;
	}
	
	public Pedido find(String data_pedido, String horapedido, int cod_cliente) throws SQLException {
		String sql = "select * from pedido where data_pedido = '" + 
				data_pedido + "' and horapedido = '" + 
				horapedido + "' and cod_cliente = '" + 
				cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Pedido p = null;
		if (res.next()) {
			char s = res.getString("status").charAt(0);
			p = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")), s);
		}
		return p;
	}
	
	public List<Pedido> findAll() throws SQLException {
		String sql = "select * from pedido;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Pedido> list = new ArrayList<Pedido>();
		
		while (res.next()) {
			char s = res.getString("status").charAt(0);
			Pedido p = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")), s);
			list.add(p);
		}
		return list;
	}
	
	public List<ModelBuscaPedidos> findByCpf(String cpf) throws SQLException {
		String sql = "select cliente.nome, orcamento.datapedido, orcamento.horapedido, produto.nomeproduto," +
				" orcamento.valor from orcamento, cliente, produto, pedido " +
				"where orcamento.datapedido = pedido.data_pedido and " +
				"orcamento.horapedido = pedido.horapedido and " +
				"orcamento.cod_cliente = pedido.cod_cliente and " +
				"cliente.cod_cliente = pedido.cod_cliente and" +
				"  orcamento.codproduto = produto.codproduto and" +
				" cliente.cpf_cnpj='" + cpf + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<ModelBuscaPedidos> list = new ArrayList<ModelBuscaPedidos>();
		
		while (res.next()) {
			
			ModelBuscaPedidos p = new ModelBuscaPedidos(res.getString("nome"), res.getString("datapedido"), res.getString("horapedido"),
					res.getString("nomeproduto"), Double.parseDouble(res.getString("valor")));
			list.add(p);
		}
		return list;
	}
	
	public List<ModelBuscaPedidos> findByData(String data) throws SQLException {
		String sql = "select cliente.nome, orcamento.datapedido, orcamento.horapedido, produto.nomeproduto," +
				" orcamento.valor from orcamento, cliente, produto, pedido " +
				"where orcamento.datapedido = pedido.data_pedido and " +
				"orcamento.horapedido = pedido.horapedido and " +
				"orcamento.cod_cliente = pedido.cod_cliente and " +
				"cliente.cod_cliente = pedido.cod_cliente and" +
				"  orcamento.codproduto = produto.codproduto and" +
				" orcamento.datapedido = '" + data + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<ModelBuscaPedidos> list = new ArrayList<ModelBuscaPedidos>();
		
		while (res.next()) {
			
			ModelBuscaPedidos p = new ModelBuscaPedidos(res.getString("nome"), res.getString("datapedido"), res.getString("horapedido"),
					res.getString("nomeproduto"), Double.parseDouble(res.getString("valor")));
			list.add(p);
		}
		return list;
	}
	
	public List<Pedido> findByDate(String data) throws SQLException {
		String sql = "select * from pedido where data_pedido = '" + data + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Pedido> list = new ArrayList<Pedido>();
		
		while (res.next()) {
			char s = res.getString("status").charAt(0);
			Pedido p = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")), s);
			list.add(p);
		}
		return list;
	}
}