package br.com.dao;
import br.com.model.Animal;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IRepositorioAnimal {

	public void insertAnimal(Animal a) throws SQLException;
	public ArrayList<Animal> listAnimal() throws SQLException;
	public void updateAnimal(Animal a) throws SQLException;
	public void deleteAnimal(int index) throws SQLException;

}
