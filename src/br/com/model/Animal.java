package br.com.model;


public class Animal {

	private int id;
	private int id_cliente;
	private int id_clinica;
	private String nome;
	private String tipo;
	private String raca;
	private String nascimento;
	private char ativo;

	public Animal(){
		
	}
	public Animal(int id, int id_cliente, int id_clinica, String nome, String tipo, String raca, String nascimento,
			char ativo) {
		this.id = id;
		this.id_cliente = id_cliente;
		this.id_clinica = id_clinica;
		this.nome = nome;
		this.tipo = tipo;
		this.raca = raca;
		this.nascimento = nascimento;
		this.ativo = ativo;
	}

	public Animal(int id_cliente, int id_clinica, String nome, String tipo, String raca, String nascimento) {
		this.id_cliente = id_cliente;
		this.id_clinica = id_clinica;
		this.nome = nome;
		this.tipo = tipo;
		this.raca = raca;
		this.setNascimento(nascimento);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public int getId_clinica() {
		return id_clinica;
	}

	public void setId_clinica(int id_clinica) {
		this.id_clinica = id_clinica;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	@Override
	public String toString() {
		return nome;
	}

}
