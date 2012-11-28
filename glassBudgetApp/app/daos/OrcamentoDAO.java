package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Orcamento;

public class OrcamentoDAO {
	
	public void save(Orcamento c) throws SQLException {
		String sql = "insert into orcamento (datapedido, horapedido, cod_cliente, valor, codproduto) values ('" +
				c.getDatapedido() + "', '" +
				c.getHorapedido() + "', '" +
				c.getCod_cliente() + "', '" +
				c.getValor() + "', '" +
				c.getCodproduto() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete(Orcamento c) throws SQLException {
		String sql = "delete from orcamento where datapedido = '" + c.getDatapedido() + "' and horapedido = '" + c.getHorapedido() + "' and cod_cliente = '" + c.getCod_cliente() + "' ;";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Orcamento search(String cod_cliente) throws SQLException {
		String sql = "select * from orcamento where cod_cliente = '" + cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Orcamento c = null;
		if (res.next()) 
			c = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
		return c;
	}
	
	public List<Orcamento> searchForData(String data) throws SQLException {
		String sql = "select * from orcamento where datapedido = '" + data + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento c = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
			list.add(c);
		}
		return list;
	}
	
	public List<Orcamento> searchForCliente(String cod_cliente) throws SQLException {
		String sql = "select * from orcamento where cod_cliente = '" + cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento c = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
			list.add(c);
		}
		return list;
	}
	
	public void update(Orcamento c) throws SQLException {
		String sql = "update orcamento set valor = " +
				"'" + c.getValor() +"'," +
				" codproduto = '" + c.getCodproduto() + "'," +
				"where datapedido = '" + c.getDatapedido() + "' and +" +
				"horapedido = '" + c.getHorapedido() + "' and +" +
				"cod_cliente = '" + c.getCod_cliente() + "' ;";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Orcamento> show() throws SQLException {
		String sql = "select * from orcamento;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Orcamento> list = new ArrayList<Orcamento>();
		
		while (res.next()) {
			Orcamento c = new Orcamento(res.getString("datapedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Double.parseDouble(res.getString("valor")), Integer.parseInt(res.getString("codproduto")));
			list.add(c);
		}
		return list;
	}
}
