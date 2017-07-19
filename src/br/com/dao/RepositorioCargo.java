package br.com.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.exception.NaoEncontradoException;
import br.com.model.Cargo;
public class RepositorioCargo implements IRepositorioCargo {

	private static RepositorioCargo instance;
	private static final String NOME_TABELA = "cargos";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioCargo getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioCargo();
		}
		return instance;
	}

	public RepositorioCargo() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertCargo(Cargo c) throws SQLException,Exception {
		PreparedStatement ps = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA + "(cargo, ativo)"
				+ " values (?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setString(1, c.getNome());
		ps.setString(2, String.valueOf(c.getAtivo()));

		ps.execute();
		ps.close();

	}

	@Override
	public ArrayList<Cargo> listCargo() throws SQLException,Exception {
		ArrayList<Cargo> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from "+NOME_TABELA+" where ativo = 'A';";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
             
		if(rs !=null){
			while(rs.next()){
				Cargo s = new Cargo(rs.getInt("id"),rs.getString("cargo"),rs.getString("ativo").charAt(0));
				lista.add(s);
			}
		}else{
			
		}
		ps.close();
		rs.close();
		return lista;
	}
	
	@Override
	public void updateCargo(Cargo c) throws NaoEncontradoException,SQLException,Exception {
		if(existe(c.getId())){
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET cargo=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setInt(2, c.getId());
		ps.executeUpdate();
		ps.close();
		}else{
			throw new NaoEncontradoException("Cargo não encontrado");
		}
	}

	@Override
	public void deleteCargo(int index) throws NaoEncontradoException,SQLException,Exception {
		if(existe(index)){
			PreparedStatement ps = null;
			String sql = "";
			sql = "UPDATE " + NOME_TABELA+ " SET ativo ='N' WHERE id=?;";
			ps = (PreparedStatement) this.connection.prepareStatement(sql);
			ps.setInt(1, index);
			ps.executeUpdate();
			ps.close();
		}else{
			throw new NaoEncontradoException("Cargo não encontrado");
		}
	}
	
	
	private boolean existe(int index) throws SQLException,Exception {
		boolean teste = false;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select * from "+NOME_TABELA+" where id = "+index +";";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();       
		if(rs !=null){
			while(rs.next()){
				teste = true;
			}
		}
		
		ps.close();
		rs.close();
		return teste;
	}
}
