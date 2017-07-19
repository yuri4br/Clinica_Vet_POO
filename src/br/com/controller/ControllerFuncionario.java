package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioFuncionario;
import br.com.dao.RepositorioFuncionario;
import br.com.model.Funcionario;

public class ControllerFuncionario {
	
	private IRepositorioFuncionario repositorio;

    public ControllerFuncionario() throws Exception{
        this.repositorio = (IRepositorioFuncionario) new RepositorioFuncionario();
    }
    
    public void insertFuncionario(Funcionario f) throws SQLException{
        repositorio.insertFuncionario(f);
    }
    
    public ArrayList<Funcionario> listFuncionario() throws SQLException{
        return repositorio.listFuncionario();
    }
    public void updateFuncionario(Funcionario f) throws SQLException {
    	this.repositorio.updateFuncionario(f);
    }
    
    public void deleteFuncionario(int index) throws SQLException {
    	this.repositorio.deleteFuncionario(index);
    }

}
