package br.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.model.Endereco;
import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.model.Cliente;

public class RepositorioCliente implements IRepositorioCliente {

	private static RepositorioCliente instance;
	private static final String NOME_TABELA = "cliente";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioCliente getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioCliente();
		}
		return instance;
	}

	public RepositorioCliente() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertCliente(Cliente c) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA + "(nome, cpf, telefone, celular, ativo) values (?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setString(1, c.getNome());
		ps.setInt(2, c.getCpf());
		ps.setString(3, c.getTelefone().get(0));
		ps.setString(4, c.getTelefone().get(1));
		ps.setString(5, String.valueOf(c.getAtivo()));
		ps.execute();

		rs = ps.getGeneratedKeys();

		if (rs != null) {
			while (rs.next()) {
				c.setId(rs.getInt(1));
			}
		}
		if (c.getId() > 0)
			setEndereco(c);
		ps.close();

	}

	
	private void setEndereco(Cliente c) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";

		sql = "insert into endereco(id_cliente, cep, pais,estado,cidade,bairro,rua,numero,complemento) "
				+ "values (?,?,?,?,?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setInt(1, c.getId());
		ps.setInt(2, c.getEndereco().getCep());
		ps.setString(3, c.getEndereco().getPais());
		ps.setString(4, c.getEndereco().getEstado());
		ps.setString(5, c.getEndereco().getCidade());
		ps.setString(6, c.getEndereco().getBairro());
		ps.setString(7, c.getEndereco().getRua());
		ps.setInt(8, c.getEndereco().getNumero());
		ps.setString(9, c.getEndereco().getComplemento());
		ps.execute();
		ps.close();
		
	}
	
	@Override
	public ArrayList<Cliente> listCliente() throws SQLException {
		ArrayList<Cliente> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from cliente_completo";
		sql += " where id is not null ";
		sql += " order by nome;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		rs = ps.executeQuery();
             
		if(rs !=null){
			while(rs.next()){
				Endereco endereco = new Endereco(rs.getInt("id_endereco"), 
						rs.getInt("cep"),
						rs.getString("pais"), 
						rs.getString("estado"),
						rs.getString("cidade"),
						rs.getString("bairro"),
						rs.getString("rua"),
						rs.getInt("numero"), 
						rs.getString("complemento"));
						ArrayList<String> telefones= new ArrayList<>();
						telefones.add(rs.getString("telefone"));
						telefones.add(rs.getString("celular"));
				
				Cliente c = new Cliente(rs.getInt("id"),
									rs.getString("nome"),
									rs.getInt("cpf"),
									rs.getString("ativo").charAt(0),
									endereco,
									telefones,
									null);
						
				lista.add(c);
			}
			System.out.println("consulta completada com sucesso...");
		}else{
			
		}
		ps.close();
		rs.close();
		return lista;
	}
	
	@Override
	public void updateCliente(Cliente c) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET nome=?,telefone=?,celular=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getTelefone().get(0));
		ps.setString(3, c.getTelefone().get(1));
		ps.setInt(4, c.getId());
		ps.executeUpdate();
		ps.close();
		
		updateEndereco(c);
	}
	
	private void updateEndereco(Cliente c) throws SQLException {			
		PreparedStatement ps = null;
		String sql = "";
		
		sql = "update endereco set cep=?, pais=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, complemento=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, c.getEndereco().getCep());
		ps.setString(2, c.getEndereco().getPais());
		ps.setString(3, c.getEndereco().getEstado());
		ps.setString(4, c.getEndereco().getCidade());
		ps.setString(5, c.getEndereco().getBairro());
		ps.setString(6, c.getEndereco().getRua());
		ps.setInt(7, c.getEndereco().getNumero());
		ps.setString(8, c.getEndereco().getComplemento());
		ps.setInt(9, c.getEndereco().getId());
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public void deleteCliente(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='N' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void ativarCliente(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='A' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
}
