package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioCargo;
import br.com.dao.RepositorioCargo;
import br.com.exception.NaoEncontradoException;
import br.com.model.Cargo;

public class ControllerCargo {
	
	private IRepositorioCargo repositorio;

    public ControllerCargo() throws Exception{
        this.repositorio = (IRepositorioCargo) new RepositorioCargo();
    }
    
    public void insertCargo(Cargo c) throws SQLException,Exception{
        repositorio.insertCargo(c);
    }
    
    public ArrayList<Cargo> listCargo() throws SQLException,Exception{
        return repositorio.listCargo();
    }
    public void updateAnimal(Cargo c) throws NaoEncontradoException,SQLException,Exception {
    	this.repositorio.updateCargo(c);
    }
    
    public void deleteCargo(int index) throws NaoEncontradoException,SQLException,Exception {
    	this.repositorio.deleteCargo(index);
    }

}
