package br.com.dao;
import java.sql.SQLException;
import java.util.ArrayList;
import br.com.model.Servico;

public interface IRepositorioServico {

	public void insertServico(Servico s) throws SQLException;
	public ArrayList<Servico> listServico() throws SQLException;
	public void updateServico(Servico s) throws SQLException;
	public void deleteServico(int index) throws SQLException;
	public void ativarServico(int index) throws SQLException;
}
