package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.model.Clinica;

public interface IRepositorioClinica {

	public ArrayList<Clinica> listClinica() throws SQLException;

}
