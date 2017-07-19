package br.com.model;

public class Servico {

	private int id;
	private String nome;
	private String tipo;
	private double valor;
	private char ativo;

	public Servico(){
		
	}
	
	public Servico(int id, String nome, String tipo, double valor, char ativo) {
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
		this.valor = valor;
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

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public char getAtivo() {
		return ativo;
	}

	public void setAtivo(char ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Servico [id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", valor=" + valor + ", ativo=" + ativo
				+ "]";
	}

}
