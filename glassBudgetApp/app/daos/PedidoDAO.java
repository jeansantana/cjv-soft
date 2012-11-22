package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Pedido;

public class PedidoDAO {
	
	public void save(Pedido c) throws SQLException {
		String sql = "insert into pedido (data_pedido, horapedido, cod_cliente, codfuncionario) values ('" +
				c.getData_pedido() + "', '" +
				c.getHorapedido() + "', '" +
				c.getCod_cliente() + "', '" +
				c.getCodfuncionario() + "');";
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
		if (res.next()) 
			c = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")));
		return c;
	}
	
	public List<Pedido> searchForCliente(String cod_cliente) throws SQLException {
		String sql = "select * from pedido where cod_cliente ~* '" + cod_cliente + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Pedido> list = new ArrayList<Pedido>();
		
		while (res.next()) {
			Pedido c = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")));
			list.add(c);
		}
		return list;
	}
	
	public void update(Pedido c) throws SQLException {
		String sql = "update pedido set codfuncionario = " +
				"'" + c.getCodfuncionario() +"'," +
				"where data_pedido = '" + c.getData_pedido() + "' and " +
				"horapedido = '" + c.getHorapedido() + "' and " +
				"cod_cliente = '" + c.getCod_cliente() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Pedido> show() throws SQLException {
		String sql = "select * from cliente;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Pedido> list = new ArrayList<Pedido>();
		
		while (res.next()) {
			Pedido c = new Pedido(res.getString("data_pedido"), res.getString("horapedido"), Integer.parseInt(res.getString("cod_cliente")), Integer.parseInt(res.getString("codfuncionario")));
			list.add(c);
		}
		return list;
	}
}
