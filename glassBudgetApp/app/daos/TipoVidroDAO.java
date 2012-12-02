package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.h2.constant.SysProperties;

import models.TipoVidro;

public class TipoVidroDAO implements DAOModel<TipoVidro> {
	
	private TipoVidro t;
	
	public TipoVidroDAO(TipoVidro t) {
		this.t = t;
	}
	
	public void save() throws SQLException {
		String sql = "insert into tipodevidro (nome, espessura, preco, descricao) values ('" +
				t.getNome() + "', '" +
				t.getEspessura() + "', '" +
				t.getPreco() + "', '" +
				t.getDescricao() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete() throws SQLException {
		String sql = "delete from tipodevidro where codtipovidro = " + t.getCodtipovidro() + ";";
		System.out.println(sql);
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public TipoVidro find(int cod) throws SQLException {
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
	
	public TipoVidro find() throws SQLException {
		String sql = "select * from tipodevidro where codtipovidro = '" + t.getCodtipovidro() + "';";
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
	
	public void update() throws SQLException {
		
		String sql = "update tipodevidro set nome='"+ t.getNome() +"', " +
				"espessura=" + t.getEspessura() +", preco=" + t.getPreco() + ", " +
						"descricao = '"+ t.getDescricao() +"' " +
								"where codtipovidro="+ t.getCodtipovidro() +";";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<TipoVidro> findAll() throws SQLException {
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