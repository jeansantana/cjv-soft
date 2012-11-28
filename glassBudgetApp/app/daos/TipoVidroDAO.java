package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.constant.SysProperties;

import models.TipoVidro;

public class TipoVidroDAO {
	
	public void save(TipoVidro t) throws SQLException {
		String sql = "insert into tipodevidro (nome, espessura, preco, descricao) values ('" +
				t.getNome() + "', '" +
				t.getEspessura() + "', '" +
				t.getPreco() + "', '" +
				t.getDescricao() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete(int i) throws SQLException {
		String sql = "delete from tipodevidro where codtipovidro = " + i + ";";
		System.out.println(sql);
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public TipoVidro search(int cod) throws SQLException {
		String sql = "select * from tipodevidro where codtipovidro = '" + cod + "';";
		System.out.println(sql);
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		TipoVidro t = null;
		if (res.next()) 
			t = new TipoVidro(Integer.parseInt(res.getString("codtipovidro")), 
					res.getString("nome"), 
					Double.parseDouble(res.getString("espessura")), 
					Double.parseDouble(res.getString("preco")), 
					res.getString("descricao"));
		return t;
	}
	
	public List<TipoVidro> searchForName(String nome) throws SQLException {
		String sql = "select * from tipodevidro where nome ~* '" + nome + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		
		while (res.next()) {
			TipoVidro t = new TipoVidro(Integer.parseInt(res.getString("codtipovidro")), 
					res.getString("nome"), 
					Double.parseDouble(res.getString("espessura")), 
					Double.parseDouble(res.getString("preco")), 
					res.getString("descricao"));
			list.add(t);
		}
		return list;
	}
	//nome, espessura, preco, descricao
	public void update(TipoVidro t) throws SQLException {
		String sql = "update cliente set nome = " +
				"'" + t.getNome() +"'," +
				" endereco = '" + t.getEspessura() + "'," +
				" telefone = '" + t.getPreco() + "'," +
				" tipo = '" + t.getDescricao() + "' " +
				"where cpf_cnpj = '" + t.getCodtipovidro() + "'";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<TipoVidro> show() throws SQLException {
		String sql = "select * from tipodevidro;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<TipoVidro> list = new ArrayList<TipoVidro>();
		
		while (res.next()) {
			TipoVidro t = new TipoVidro(Integer.parseInt(res.getString("codtipovidro")), 
					res.getString("nome"), 
					Double.parseDouble(res.getString("espessura")), 
					Double.parseDouble(res.getString("preco")), 
					res.getString("descricao"));
			list.add(t);
		}
		return list;
	}
}