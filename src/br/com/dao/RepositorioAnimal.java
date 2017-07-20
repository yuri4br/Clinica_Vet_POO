package br.com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.model.Animal;

public class RepositorioAnimal implements IRepositorioAnimal {

	private static RepositorioAnimal instance;
	private static final String NOME_TABELA = "animal";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioAnimal getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioAnimal();
		}
		return instance;
	}

	public RepositorioAnimal() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertAnimal(Animal a) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA + "(id_cliente,id_clinica, nome, tipo, raca, nascimento, ativo)"
				+ " values (?,?,?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}

		ps.setInt(1, a.getId_cliente());
		ps.setInt(2, a.getId_clinica());
		ps.setString(3, a.getNome());
		ps.setString(4, a.getTipo());
		ps.setString(5, a.getRaca());
		ps.setString(6, a.getNascimento());
		ps.setString(7, String.valueOf(a.getAtivo()));

		ps.execute();
		ps.close();

	}

	@Override
	public ArrayList<Animal> listAnimal() throws SQLException {
		ArrayList<Animal> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from animal";
		sql += " where id is not null ";
		sql += " order by nome;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs != null) {
			while (rs.next()) {

				Animal a = new Animal(rs.getInt("id"), rs.getInt("id_cliente"), rs.getInt("id_clinica"),
						rs.getString("nome"), rs.getString("tipo"), rs.getString("raca"), rs.getString("nascimento"),
						rs.getString("ativo").charAt(0));

				lista.add(a);
			}
			System.out.println("Consulta concluída.");
		} else {

		}
		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public void updateAnimal(Animal a) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET nome=?, tipo=?, raca=?, nascimento=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setString(1, a.getNome());
		ps.setString(2, a.getTipo());
		ps.setString(3, a.getRaca());
		ps.setString(4, a.getNascimento());
		ps.setInt(5, a.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteAnimal(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='N' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
}
