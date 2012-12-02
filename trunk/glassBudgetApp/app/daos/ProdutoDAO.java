package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Produto;

public class ProdutoDAO {
	
	private Produto p;
	
	public ProdutoDAO(Produto p) {
		this.p = p;
	}
	
	public ProdutoDAO() {}
	
	public void save() throws SQLException {
		String sql = "insert into produto (nomeproduto, altura, largura, codtipovidro) values ('" +
				p.getNomeproduto() + "', '" +
				p.getAltura() + "', '" +
				p.getLargura() + "', '" +
				p.getCodtipovidro() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete() throws SQLException {
		String sql = "delete from produto where codproduto = " + p.getCodproduto() + ";";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Produto find(int codproduto) throws SQLException {
		String sql = "select * from produto where codproduto =  " + codproduto + ";";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Produto p = null;
		if (res.next()) 
			p = new Produto(Integer.parseInt(res.getString("codproduto")), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("largura")), Integer.parseInt(res.getString("codtipovidro")));
		return p;
	}
	
	public Produto find() throws SQLException {
		String sql = "select * from produto where codproduto = " + p.getCodproduto() + ";";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Produto p = null;
		if (res.next()) 
			p = new Produto(Integer.parseInt(res.getString("codproduto")), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("largura")), Integer.parseInt(res.getString("codtipovidro")));
		return p;
	}
	
	public void update() throws SQLException {
		String sql = "update produto set nomeproduto = " +
				"'" + p.getNomeproduto() +"'," +
				" altura = '" + p.getAltura() + "'," +
				" largura = '" + p.getLargura() + "'," +
				" codtipovidro = '" + p.getCodtipovidro() + "' " +
				"where codproduto = '" + p.getCodproduto() + "'";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Produto> findAll() throws SQLException {
		String sql = "select * from produto;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Produto> list = new ArrayList<Produto>();
		
		while (res.next()) {
			Produto p = new Produto(Integer.parseInt(res.getString("codproduto")), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("largura")), Integer.parseInt(res.getString("codtipovidro")));
			list.add(p);
		}
		return list;
	}
	
	public Produto resgate() throws SQLException {
		
		String sql = "select * from produto where codproduto = (select max(codproduto) from produto);";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Produto p = null;
		if (res.next()) 
			p = new Produto(Integer.parseInt(res.getString("codproduto")), res.getString("nomeproduto"), Double.parseDouble(res.getString("altura")), Double.parseDouble(res.getString("largura")), Integer.parseInt(res.getString("codtipovidro")));
		return p;
	}
}