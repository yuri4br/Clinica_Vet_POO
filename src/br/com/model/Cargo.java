package br.com.model;

public class Cargo {

	private int id;
	private String nome;
	private char ativo;

	public Cargo(){
		
	}
	
	public Cargo(int id, String nome, char ativo) {
		this.id = id;
		this.nome = nome;
		this.setAtivo(ativo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return nome;
	}

}
