package models;

public class Cliente {
	private String nome;
	private String endereco;
	private String cpf_cnpj;
	private String telefone;
	private String tipo;
	
	public Cliente(String nome, String endereco, String cpf_cnpj,
			String telefone, String tipo) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf_cnpj = cpf_cnpj;
		this.telefone = telefone;
		this.tipo = tipo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getCpf_cnpj() {
		return cpf_cnpj;
	}
	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
