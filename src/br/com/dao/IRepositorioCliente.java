package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.model.Cliente;

public interface IRepositorioCliente {

	public void insertCliente(Cliente c) throws SQLException;
	public ArrayList<Cliente> listCliente() throws SQLException;
	public void updateCliente(Cliente c) throws SQLException;
	public void deleteCliente(int index) throws SQLException;
	public void ativarCliente(int index) throws SQLException;
}
