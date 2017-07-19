package br.com.model;

import java.util.ArrayList;



public class Ficha {

	private int id;
	private Clinica clinica;
	private Cliente cliente;
	private Funcionario funcionario;
	private Animal animal;
	private ArrayList<Servico> servico;
	private String agendamento;
	private String diagnostico;
	private String tipo;
	private String situacao;

	public Ficha(){
		
	}
	
	public Ficha(int id, Clinica clinica, 	Cliente cliente,
			Funcionario funcionario, Animal animal, ArrayList<Servico> servico, String agendamento, String diagnostico,
			String tipo,String situacao) {
		this.id = id;
		this.clinica = clinica;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.animal = animal;
		this.servico = servico;
		this.agendamento = agendamento;
		this.diagnostico = diagnostico;
		this.tipo = tipo;
		this.situacao = situacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public ArrayList<Servico> getServico() {
		return servico;
	}

	public void setServico(ArrayList<Servico> servico) {
		this.servico = servico;
	}

	public String getAgendamento() {
		return agendamento;
	}

	public void setAgendamento(String agendamento) {
		this.agendamento = agendamento;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		return "Ficha [id=" + id + ", clinica=" + clinica + ", cliente=" + cliente + ", funcionario=" + funcionario
				+ ", animal=" + animal + ", servico=" + servico + ", agendamento=" + agendamento + ", diagnostico="
				+ diagnostico + ", tipo=" + tipo + ", situacao=" + situacao + "]";
	}
}
