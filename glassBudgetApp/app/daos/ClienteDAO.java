package daos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Cliente;

public class ClienteDAO implements DAOModel<Cliente>{
	
	private Cliente c;
	
	public ClienteDAO(Cliente c) {
		this.c = c;
	}
	
	public ClienteDAO() {}

	public void save() throws SQLException {
		String sql = "insert into cliente (nome, endereco, cpf_cnpj, telefone, tipo) values ('" +
				c.getNome() + "', '" +
				c.getEndereco() + "', '" +
				c.getCpf_cnpj() + "', '" +
				c.getTelefone() + "', '" +
				c.getTipo() + "');";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void delete() throws SQLException {
		String sql = "delete from cliente where cpf_cnpj = '" + c.getCpf_cnpj() + "';";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public void update() throws SQLException {
		String sql = "update cliente set nome = " +
				"'" + c.getNome() +"'," +
				" endereco = '" + c.getEndereco() + "'," +
				" telefone = '" + c.getTelefone() + "'," +
				" tipo = '" + c.getTipo() + "' " +
				"where cpf_cnpj = '" + c.getCpf_cnpj() + "'";
		ConnectPostegreSQL.conectar();
		ConnectPostegreSQL.comando.executeUpdate(sql);
	}
	
	public Cliente find(String code) throws SQLException {
		String sql = "select * from cliente where cpf_cnpj = '" + code + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		Cliente c = null;
		if (res.next()) 
			c = new Cliente(res.getString("cod_cliente"), res.getString("nome"), res.getString("endereco"), res.getString("cpf_cnpj"), res.getString("telefone"), res.getString("tipo"));
		return c;
	}
	
	public List<Cliente> findForName(String nome) throws SQLException {
		String sql = "select * from cliente where nome ~* '" + nome + "';";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Cliente> list = new ArrayList<Cliente>();
		
		while (res.next()) {
			Cliente c = new Cliente(res.getString("cod_cliente"), res.getString("nome"), res.getString("endereco"), res.getString("cpf_cnpj"), res.getString("telefone"), res.getString("tipo"));
			list.add(c);
		}
		return list;
	}
	
	public List<Cliente> findAll() throws SQLException {
		String sql = "select * from cliente;";
		ConnectPostegreSQL.conectar();
		ResultSet res = ConnectPostegreSQL.comando.executeQuery(sql);
		List<Cliente> list = new ArrayList<Cliente>();
		
		while (res.next()) {
			Cliente c = new Cliente(res.getString("cod_cliente"), res.getString("nome"), res.getString("endereco"), res.getString("cpf_cnpj"), res.getString("telefone"), res.getString("tipo"));
			list.add(c);
		}
		return list;
	}
}