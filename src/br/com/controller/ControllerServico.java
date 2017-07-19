package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioServico;
import br.com.dao.RepositorioServico;
import br.com.model.Servico;

public class ControllerServico {
	
	private IRepositorioServico repositorio;

    public ControllerServico() throws Exception{
        this.repositorio = (IRepositorioServico) new RepositorioServico();
    }
    
    public void insertServico(Servico s) throws SQLException{
        repositorio.insertServico(s);
    }
    
    public ArrayList<Servico> listServico() throws SQLException{
        return repositorio.listServico();
    }
    public void updateServico(Servico s) throws SQLException {
    	this.repositorio.updateServico(s);
    }
    
    public void deleteServico(int index) throws SQLException {
    	this.repositorio.deleteServico(index);
    }
}
