package models;

public class Funcionario {
	
	private String nome;
	private String login;
	private String senha;
	
	public Funcionario(String nome, String login, String senha) {
		this.nome = nome;
		this.login = login;
		this.senha = senha;
	}
	
	public Funcionario(String login, String senha) {
		this.nome = "current";
		this.login = login;
		this.senha = senha;
	}

	public Funcionario() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		return login;
	}
	
}
