package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.model.Ficha;


public interface IRepositorioFicha {

	public void insertFicha(Ficha f) throws SQLException;
	public ArrayList<Ficha> listFicha() throws SQLException;
	public void updateFicha(Ficha f) throws SQLException;
	public void adicionarServico(Ficha f) throws SQLException;

}
