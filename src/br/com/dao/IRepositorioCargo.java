package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.exception.NaoEncontradoException;
import br.com.model.Cargo;

public interface IRepositorioCargo {

	public void insertCargo(Cargo c) throws SQLException,Exception;
	public ArrayList<Cargo> listCargo() throws SQLException,Exception;
	public void updateCargo(Cargo c) throws NaoEncontradoException,SQLException,Exception;
	public void deleteCargo(int index) throws NaoEncontradoException,SQLException,Exception;
}
