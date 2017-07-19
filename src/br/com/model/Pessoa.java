package br.com.model;

import java.util.ArrayList;

public abstract class Pessoa {

	private int id;
	private String nome;
	private int cpf;
	private char ativo;
	private Endereco endereco;
	private ArrayList<String> telefone;
	
	public Pessoa(){
		
	}
	
	public Pessoa(int id, String nome, int cpf, char ativo, Endereco endereco, ArrayList<String> telefone) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.ativo = ativo;
		this.endereco = endereco;
		this.telefone = telefone;
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

	public int getCpf() {
		return cpf;
	}

	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<String> getTelefone() {
		return telefone;
	}

	public void setTelefone(ArrayList<String> telefone) {
		this.telefone = telefone;
	}

	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", ativo=" + ativo + ", endereco=" + endereco
				+ ", telefone=" + telefone + "]";
	}

}
