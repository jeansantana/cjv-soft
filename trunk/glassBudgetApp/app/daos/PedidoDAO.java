package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Pedido;

public class PedidoDAO {
	
	public void save(Pedido c) throws SQLException {
		String sql = "insert into pedido values ('" +
				c.getData_pedido() + "', '" +
				c.getHorapedido() + "', '" +
				c.getCod_cliente() + "', '" +
				c.getCodfuncionario() + "', '" +
				c.getStatus() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete(Pedido c) throws SQLException {
		String sql = "delete from pedido where data_pedido = '" + c.getData_pedido() + "' and " +
		"horapedido = '" + c.getHorapedido() + "' and " +
		"cod_cliente = '" + c.getCod_cliente() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Pedido search(String data_pedido, String horapedido, String cod_cliente) throws SQLException {
		String sql = "select * from pedido where data_pedido = '" + data_pedido + "' and horapedido = '" + horapedido + "' and cod_cliente = '" + cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Pedido c = null;
		if (res.next()) {
			char s = res.getString("status").charAt(0);
			c = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")), s);
		}
		return c;
	}
	
	public List<Pedido> searchForFuncionario(String cod_cliente) throws SQLException {
		String sql = "select * from pedido where cod_cliente ~* '" + cod_cliente + "';";
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
	
	public void update(Pedido c) throws SQLException {
		String sql = "update pedido set codfuncionario = " +
				"'" + c.getCodfuncionario() +"'," +
				"where data_pedido = '" + c.getData_pedido() + "' and " +
				"horapedido = '" + c.getHorapedido() + "' and " +
				"cod_cliente = '" + c.getCod_cliente() + "', ";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Pedido> show() throws SQLException {
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
}