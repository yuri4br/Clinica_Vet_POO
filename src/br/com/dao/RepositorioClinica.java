package br.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.model.Clinica;

public class RepositorioClinica implements IRepositorioClinica {

	private static RepositorioClinica instance;
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioClinica getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioClinica();
		}
		return instance;
	}

	public RepositorioClinica() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}


	@Override
	public ArrayList<Clinica> listClinica() throws SQLException {
		ArrayList<Clinica> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from clinica";
		sql += " where id is not null ";
		sql += " order by nome;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs != null) {
			;
			while (rs.next()) {

				Clinica c = new Clinica(rs.getInt("id"),rs.getString("nome"));
				lista.add(c);
			}
			System.out.println("consulta completada com sucesso...");
		} else {

		}
		ps.close();
		rs.close();
		return lista;
	}


}
