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
import br.com.model.Cliente;
import br.com.model.Clinica;
import br.com.model.Funcionario;
import br.com.model.Servico;
import br.com.model.Ficha;

public class RepositorioFicha implements IRepositorioFicha {

	private static RepositorioFicha instance;
	private static final String NOME_TABELA = "ficha";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioFicha getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioFicha();
		}
		return instance;
	}

	public RepositorioFicha() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertFicha(Ficha f) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA
				+ "(id_clinica, id_cliente, id_funcionario, id_animal, agendamento, diagnostico, tipo,situacao)"
				+ " values (?,?,?,?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}

		ps.setInt(1, f.getClinica().getId());
		ps.setInt(2, f.getCliente().getId());
		ps.setInt(3, f.getFuncionario().getId());
		ps.setInt(4, f.getAnimal().getId());
		ps.setString(5, f.getAgendamento());
		ps.setString(6, f.getDiagnostico());
		ps.setString(7, f.getTipo());
		ps.setString(8, f.getSituacao());

		ps.execute();

		rs = ps.getGeneratedKeys();

		if (rs != null) {
			while (rs.next()) {
				f.setId(rs.getInt(1));
			}
		}
		ps.close();

	}
	@Override
	public void adicionarServico(Ficha f) throws SQLException {

		PreparedStatement ps = null;
		String sql = "";
		sql = "insert into ficha_servico (id_ficha, id_servico) values (?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}

		connection.setAutoCommit(false);

		for (Servico s : f.getServico()) {
			ps.setInt(1, f.getId());
			ps.setInt(2, s.getId());
			ps.addBatch();
		}
		ps.executeBatch();
		connection.commit();
		ps.close();

	}

	@Override
	public ArrayList<Ficha> listFicha() throws SQLException {
		ArrayList<Ficha> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from ficha_completa";
		sql += " where id is not null;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs != null) {
			while (rs.next()) {

				Ficha f = new Ficha(rs.getInt("id"), new Clinica(rs.getInt("id_clinica"), rs.getString("nome_clinica")),
						new Cliente(rs.getInt("id_cliente"), rs.getString("nome_cliente"), 0, ' ', null, null, null),
						new Funcionario(rs.getInt("id_funcionario"), rs.getString("nome_funcionario"), 0, ' ', null,
								null),
						new Animal(rs.getInt("id_animal"), 0, 0, rs.getString("nome_animal"), "", "", "", ' '),
						getServico(rs.getInt("id")), rs.getString("agendamento"), rs.getString("diagnostico"),
						rs.getString("tipo"), rs.getString("situacao"));

				lista.add(f);
			}
			System.out.println("consulta completada com sucesso...");
		} else {

		}
		ps.close();
		rs.close();
		return lista;
	}

	private ArrayList<Servico> getServico(int idFicha) throws SQLException {
		ArrayList<Servico> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		System.out.println("id ficha: " + idFicha);
		sql = "select id_ficha, id_servico, servico.nome, servico.tipo, servico.valor " + "from servico,ficha_servico "
				+ "where servico.id = ficha_servico.id_servico and " + "id_ficha= " + idFicha + ";";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();

		while (rs.next()) {
			Servico s = new Servico(rs.getInt("id_servico"), rs.getString("nome"), rs.getString("tipo"),
					rs.getDouble("valor"), 'A');
			lista.add(s);

		}

		ps.close();
		rs.close();
		return lista;
	}

	@Override
	public void updateFicha(Ficha f) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET agendamento=?, diagnostico=?, tipo=?, situacao=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setString(1, f.getAgendamento());
		ps.setString(2, f.getDiagnostico());
		ps.setString(3, f.getTipo());
		ps.setString(4, f.getSituacao());
		ps.setInt(5, f.getId());
		ps.executeUpdate();
		ps.close();
	}
}
