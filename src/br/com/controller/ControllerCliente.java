package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioCliente;
import br.com.dao.RepositorioCliente;
import br.com.model.Cliente;

public class ControllerCliente {

	private IRepositorioCliente repositorio;

    public ControllerCliente() throws Exception{
        this.repositorio = (IRepositorioCliente) new RepositorioCliente();
    }
	
	public ArrayList<Cliente> listar(){
		try {
			return RepositorioCliente.getInstance().listCliente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}	
	
	public void insert(Cliente c){
		try {
			this.repositorio.insertCliente(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(int index){
		try {
			this.repositorio.deleteCliente(index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void ativar(int index){
		try {
			this.repositorio.ativarCliente(index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update(Cliente c){
		try {
			this.repositorio.updateCliente(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
