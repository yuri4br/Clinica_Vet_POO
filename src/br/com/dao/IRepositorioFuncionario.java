package br.com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import br.com.model.Funcionario;;

public interface IRepositorioFuncionario {

	public void insertFuncionario(Funcionario f) throws SQLException;
	public ArrayList<Funcionario> listFuncionario() throws SQLException;
	public void updateFuncionario(Funcionario f) throws SQLException;
	public void deleteFuncionario(int index) throws SQLException;
	public void ativarFuncionario(int index) throws SQLException;
}
