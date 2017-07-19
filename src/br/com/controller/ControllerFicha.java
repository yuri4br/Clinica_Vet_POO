package br.com.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.dao.IRepositorioFicha;
import br.com.dao.RepositorioFicha;
import br.com.model.Ficha;

public class ControllerFicha {
	private IRepositorioFicha repositorio;

    public ControllerFicha() throws Exception{
        this.repositorio = (IRepositorioFicha) new RepositorioFicha();
    }
    
    public void insertFicha(Ficha f) throws SQLException{
        repositorio.insertFicha(f);
    }
    
    public ArrayList<Ficha> listFicha() throws SQLException{
        return repositorio.listFicha();
    }
    public void updateFicha(Ficha f) throws SQLException {
    	this.repositorio.updateFicha(f);
    }
    
    public void adicionarServico(Ficha f) throws SQLException {
    	this.repositorio.updateFicha(f);
    }    
}
