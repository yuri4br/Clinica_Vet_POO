package br.com.model;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Cliente extends Pessoa{

	private ArrayList<Animal> animal;
	
	public Cliente(){
	}
	
	public Cliente(int id, String nome, int cpf, char ativo, Endereco endereco, ArrayList<String> telefone,
			ArrayList<Animal> animal) {
		super(id, nome, cpf, ativo, endereco, telefone);
		this.animal = animal;
	}

	public ArrayList<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(ArrayList<Animal> animal) {
		this.animal = animal;
	}

	@Override
	public String toString() {
		return "Cliente [animal=" + animal + ", getId()=" + getId() + ", getNome()=" + getNome() + ", getCpf()="
				+ getCpf() + ", getAtivo()=" + getAtivo() + ", getEndereco()=" + getEndereco() + ", getTelefone()="
				+ getTelefone() + "]";
	}

	
}
