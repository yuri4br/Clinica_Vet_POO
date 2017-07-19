package br.com.model;

import java.util.ArrayList;

public class Funcionario extends Pessoa {

	private int id_clinica;
	private String login;
	private String senha;
	private double salario;
	private int cargo;
	private int rg;

	public Funcionario(){
		
	}
	public Funcionario(int id, String nome, int cpf, char ativo, Endereco endereco, ArrayList<String> telefone) {
		super(id, nome, cpf, ativo, endereco, telefone);
	}

	public Funcionario(int id, String nome, int cpf, char ativo, Endereco endereco, ArrayList<String> telefone,
			int id_clinica, String senha,String login, double salario, int cargo, int rg) {
		super(id, nome, cpf, ativo, endereco, telefone);
		this.id_clinica = id_clinica;
		this.login = login;
		this.senha = senha;
		this.salario = salario;
		this.cargo = cargo;
		this.rg = rg;
	}

	public int getId_clinica() {
		return id_clinica;
	}

	public void setId_clinica(int id_clinica) {
		this.id_clinica = id_clinica;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

	public int getRg() {
		return rg;
	}

	public void setRg(int rg) {
		this.rg = rg;
	}


	@Override
	public String toString() {
		return "Funcionario [id_clinica=" + id_clinica + ", login=" + login + ", senha=" + senha + ", salario="
				+ salario + ", cargo=" + cargo + ", rg=" + rg + ", getNome()=" + getNome()
				+ ", getCpf()=" + getCpf() + ", getAtivo()=" + getAtivo() + ", getEndereco()=" + getEndereco()
				+ ", getTelefone()=" + getTelefone() + ", toString()=" + super.toString() + "]";
	}

	

}
