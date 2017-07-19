package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioAnimal;
import br.com.dao.RepositorioAnimal;
import br.com.model.Animal;

public class ControllerAnimal {
	
	private IRepositorioAnimal repositorio;

    public ControllerAnimal() throws Exception{
        this.repositorio = (IRepositorioAnimal) new RepositorioAnimal();
    }
    
    public void insertAnimal(Animal a) throws SQLException{
        repositorio.insertAnimal(a);
    }
    
    public ArrayList<Animal> listAnimal() throws SQLException{
        return repositorio.listAnimal();
    }
    public void updateAnimal(Animal a) throws SQLException {
    	this.repositorio.updateAnimal(a);
    }
    
    public void deleteAnimal(int index) throws SQLException {
    	this.repositorio.deleteAnimal(index);
    }
}
