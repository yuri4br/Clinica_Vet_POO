package br.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.model.Servico;

public class RepositorioServico implements IRepositorioServico {

	private static RepositorioServico instance;
	private static final String NOME_TABELA = "servico";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioServico getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioServico();
		}
		return instance;
	}

	public RepositorioServico() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertServico(Servico s) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA + "(nome, tipo, valor, ativo)"
				+ " values (?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setString(1, s.getNome());
		ps.setString(2, s.getTipo());
		ps.setDouble(3, s.getValor());
		ps.setString(4, String.valueOf(s.getAtivo()));

		ps.execute();
		ps.close();

	}

	@Override
	public ArrayList<Servico> listServico() throws SQLException {
		ArrayList<Servico> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from "+NOME_TABELA+";";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
             
		if(rs !=null){
                 ;
			while(rs.next()){
                              
				Servico s = new Servico(rs.getInt("id"),rs.getString("nome"),rs.getString("tipo")
						,rs.getDouble("valor"),rs.getString("ativo").charAt(0));
						
				lista.add(s);
			}
			System.out.println("Consulta concluída.");
		}else{
			
		}
		ps.close();
		rs.close();
		return lista;
	}
	
	@Override
	public void updateServico(Servico s) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET nome=?, tipo=?, valor=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setString(1, s.getNome());
		ps.setString(2, s.getTipo());
		ps.setDouble(3, s.getValor());
		ps.setInt(4, s.getId());
		ps.executeUpdate();
		ps.close();
	}

	@Override
	public void deleteServico(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='N' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void ativarServico(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='A' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
}
