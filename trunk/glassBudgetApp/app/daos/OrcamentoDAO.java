package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Orcamento;

public class OrcamentoDAO implements DAOModel<Orcamento> {
	
	private Orcamento o;
	
	public OrcamentoDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public OrcamentoDAO(Orcamento o) {
		this.o = o;
	}
	
	public void save() throws SQLException {
		String sql = "insert into orcamento (datapedido, horapedido, cod_cliente, valor, codproduto) values ('" +
				o.getDatapedido() + "', '" +
				o.getHorapedido() + "', '" +
				o.getCod_cliente() + "', '" +
				o.getValor() + "', '" +
				o.getCodproduto() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete() throws SQLException {
		String sql = "delete from orcamento where datapedido = '" + o.getDatapedido() + "' and horapedido = '" + o.getHorapedido() + "' and cod_cliente = '" + o.getCod_cliente() + "' ;";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Orcamento find() throws SQLException {
		String sql = "select * from orcamento where datapedido = '" + o.getDatapedido() + "' and horapedido = '" + o.getHorapedido() + "' and cod_cliente = '" + o.getCod_cliente() + "' ;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Orcamento o = null;
		if (res.next()) 
			o = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
		return o;
	}
	
	public List<Orcamento> searchByData(String data) throws SQLException {
		String sql = "select * from orcamento where datapedido = '" + data + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento o = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
			list.add(o);
		}
		return list;
	}
	
	public List<Orcamento> searchByCliente(String cod_cliente) throws SQLException {
		String sql = "select * from orcamento where cod_cliente = '" + cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento o = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
			list.add(o);
		}
		return list;
	}
	
	public void update() throws SQLException {
		String sql = "update orcamento set valor = " +
				"'" + o.getValor() +"'," +
				" codproduto = '" + o.getCodproduto() + "'," +
				"where datapedido = '" + o.getDatapedido() + "' and +" +
				"horapedido = '" + o.getHorapedido() + "' and +" +
				"cod_cliente = '" + o.getCod_cliente() + "' ;";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Orcamento> findAll() throws SQLException {
		String sql = "select * from orcamento;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento c = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")	), Integer.parseInt(res.getString("codproduto")));
			list.add(c);
		}
		return list;
	}
}