package models;

public class Cliente {
	private String cod_cliente;
	private String nome;
	private String endereco;
	private String cpf_cnpj;
	private String telefone;
	private String tipo;
	
	public Cliente(String cod_cliente, String nome, String endereco, String cpf_cnpj, String telefone, String tipo) {
		this.setCod_cliente(cod_cliente);
		this.setNome(nome);
		this.setEndereco(endereco);
		this.setCpf_cnpj(cpf_cnpj);
		this.setTelefone(telefone);
		this.setTipo(tipo);
	}

	public Cliente(String nome, String endereco, String cpf_cnpj,
			String telefone, String tipo) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.cpf_cnpj = cpf_cnpj;
		this.telefone = telefone;
		this.tipo = tipo;
	}
	
	public Cliente() {}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTelefone() {
		return telefone;
	}
  
	public void setTelefone(String telefone) {
		this.telefone = telefone;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCod_cliente() {
		return cod_cliente;
	}

	public void setCod_cliente(String cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
	
	@Override
	public String toString() {
		String a = "Cliente{\n"+cod_cliente+"\n"+nome+"\n"+endereco+"\n"+cpf_cnpj+"\n"+telefone+"\n"+tipo+"\n}\n";
		return a;
	}	
}