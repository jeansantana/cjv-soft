package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Funcionario;

public class FuncionarioDAO {
	
	public void save(Funcionario c) throws SQLException {
		String sql = "insert into funcionario (login, nomefuncionario, senha) values ('" +
				c.getLogin() + "', '" +
				c.getNome() + "', '" +
				c.getSenha() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete(Funcionario c) throws SQLException {
		String sql = "delete from funcionario where codfoncionario = '" + c.getCodfuncionario() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Funcionario search(String login, String senha) throws SQLException {
		String sql = "select * from funcionario where login = '" + login + "' and senha = '" + senha + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Funcionario c = null;
		if (res.next()) 
			c = new Funcionario(res.getString("codfuncionario"), res.getString("login"), res.getString("nomefuncionario"), res.getString("senha"));
		return c;
	}
	
	public List<Funcionario> searchForName(String nome) throws SQLException {
		String sql = "select * from funcionario where nomefuncionario ~* '" + nome + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Funcionario> list = new ArrayList<Funcionario>();
		
		while (res.next()) {
			Funcionario c = new Funcionario(res.getString("codfuncionario"), res.getString("login"), res.getString("nomefuncionario"), res.getString("senha"));
			list.add(c);
		}
		return list;
	}
	
	public void update(Funcionario c) throws SQLException {
		String sql = "update funcionario set nomefuncionario = " +
				"'" + c.getNome() +"'," +
				" login = '" + c.getLogin() + "'," +
				" senha = '" + c.getSenha() + "'," +
				"where codfuncionario = '" + c.getCodfuncionario() + "'";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public List<Funcionario> show() throws SQLException {
		String sql = "select * from funcionario;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Funcionario> list = new ArrayList<Funcionario>();
		
		while (res.next()) {
			Funcionario c = new Funcionario(res.getString("codfuncionario"), res.getString("login"), res.getString("nomefuncionario"), res.getString("senha"));
			list.add(c);
		}
		return list;
	}
}