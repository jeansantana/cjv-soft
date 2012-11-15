package models;

public class Funcionario {
	private String codfuncionario;
	private String login;
	private String nomefuncionario;
	private String senha;
	
	public Funcionario(String codfuncionario, String login, String nomefuncionario, String senha) {
		this.setCodfuncionario(codfuncionario);
		this.setLogin(login);
		this.setNomefuncionario(nomefuncionario);
		this.setSenha(senha);
	}


	public Funcionario(String login, String nomefuncionario, String senha) {
		super();
		this.login = login;
		this.nomefuncionario = nomefuncionario;
		this.senha = senha;
	}



	public String getCodfuncionario() {
		return codfuncionario;
	}

	public void setCodfuncionario(String codfuncionario) {
		this.codfuncionario = codfuncionario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNomefuncionario() {
		return nomefuncionario;
	}

	public void setNomefuncionario(String nomefuncionario) {
		this.nomefuncionario = nomefuncionario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public String toString() {
		String a = "Funcionario{\n"+codfuncionario+"\n"+nomefuncionario+"\n"+login+"\n"+senha+"\n}\n";
		return a;
	}	
}