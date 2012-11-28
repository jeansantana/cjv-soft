package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class ProdutoDAO {
	
	public void save(Produto c) throws SQLException {
		String sql = "insert into produto (nomeproduto, altura, comprimento, codtipovidro) values ('" +
				c.getNomeproduto() + "', '" +
				c.getAltura() + "', '" +
				c.getLargura() + "', '" +
				c.getCodtipovidro() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete(Produto c) throws SQLException {
		String sql = "delete from produto where codproduto = '" + c.getCodproduto() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Produto search(String codproduto) throws SQLException {
		String sql = "select * from produto where codproduto = '" + codproduto + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Produto c = null;
		if (res.next()) 
			c = new Produto(res.getString("codproduto"), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("comprimento")), Integer.parseInt(res.getString("codtipovidro")));
		return c;
	}
	
	public List<Produto> searchForName(String nome) throws SQLException {
		String sql = "select * from cliente where nomeproduto ~* '" + nome + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Produto> list = new ArrayList<Produto>();
		
		while (res.next()) {
			Produto c = new Produto(res.getString("codproduto"), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("comprimento")), Integer.parseInt(res.getString("codtipovidro")));
			list.add(c);
		}
		return list;
	}
	
	public void update(Produto c) throws SQLException {
		String sql = "update produto set nomeproduto = " +
				"'" + c.getNomeproduto() +"'," +
				" altura = '" + c.getAltura() + "'," +
				" comprimento = '" + c.getLargura() + "'," +
				" codtipovidro = '" + c.getCodtipovidro() + "' " +
				"where codproduto = '" + c.getCodproduto() + "'";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Produto> show() throws SQLException {
		String sql = "select * from produto;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Produto> list = new ArrayList<Produto>();
		
		while (res.next()) {
			Produto c = new Produto(res.getString("codproduto"), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("comprimento")), Integer.parseInt(res.getString("codtipovidro")));
			list.add(c);
		}
		return list;
	}
}
