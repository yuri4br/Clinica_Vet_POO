package br.com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import br.com.conexao.Conexao;
import br.com.conexao.Database;
import br.com.model.Endereco;
import br.com.model.Funcionario;;

public class RepositorioFuncionario implements IRepositorioFuncionario {

	private static RepositorioFuncionario instance;
	private static final String NOME_TABELA = "funcionario";
	private Connection connection;
	private int dataBase = Database.MYSQL;

	public static RepositorioFuncionario getInstance() throws Exception {
		if (instance == null) {
			instance = new RepositorioFuncionario();
		}
		return instance;
	}

	public RepositorioFuncionario() throws Exception {
		this.connection = (Connection) Conexao.getConnection(dataBase);
	}

	@Override
	public void insertFuncionario(Funcionario f) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";

		sql = "insert into " + NOME_TABELA + "(id_clinica, id_cargo, nome,senha,login, cpf, rg, salario, telefone,celular, ativo)"
				+ " values (?,?,?,?,?,?,?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setInt(1, f.getId_clinica());
		ps.setInt(2, f.getCargo());
		ps.setString(3, f.getNome());
		ps.setString(4, f.getLogin());
		ps.setString(5, f.getSenha());		
		ps.setInt(6, f.getCpf());
		ps.setInt(7, f.getRg());
		ps.setDouble(8, f.getSalario());
		ps.setString(9, f.getTelefone().get(0));
		ps.setString(10, f.getTelefone().get(1));
		ps.setString(11, String.valueOf(f.getAtivo()));
		ps.execute();

		rs = ps.getGeneratedKeys();

		if (rs != null) {
			while (rs.next()) {
				f.setId(rs.getInt(1));
			}
		}
		if (f.getId() > 0)
			setEndereco(f);
		ps.close();

	}
	
	private void setEndereco(Funcionario f) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";

		sql = "insert into endereco(id_funcionario, cep, pais,estado,cidade,bairro,rua,numero,complemento) "
				+ "values (?,?,?,?,?,?,?,?,?);";

		if (this.dataBase == Database.ORACLE) {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, new String[] { "id" });
		} else {
			ps = (PreparedStatement) this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		}
		
		ps.setInt(1, f.getId());
		ps.setInt(2, f.getEndereco().getCep());
		ps.setString(3, f.getEndereco().getPais());
		ps.setString(4, f.getEndereco().getEstado());
		ps.setString(5, f.getEndereco().getCidade());
		ps.setString(6, f.getEndereco().getBairro());
		ps.setString(7, f.getEndereco().getRua());
		ps.setInt(8, f.getEndereco().getNumero());
		ps.setString(9, f.getEndereco().getComplemento());
		ps.execute();
		ps.close();
		
	}

	@Override
	public ArrayList<Funcionario> listFuncionario() throws SQLException {
		ArrayList<Funcionario> lista = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		sql = "select *from funcionario_completo";
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
				
				Funcionario c = new Funcionario(rs.getInt("id"),
											rs.getString("nome"),
											rs.getInt("cpf"),
											rs.getString("ativo").charAt(0),
											endereco,
											telefones,
											rs.getInt("id_clinica"),
											rs.getString("senha"),
											rs.getString("login"),
											rs.getDouble("salario"),
											rs.getInt("id_cargo"),
											rs.getInt("rg"));
						
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
	public void updateFuncionario(Funcionario f) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA + " SET id_clinica=? ,id_cargo=?, nome=?, cpf=?, rg=?, salario=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, f.getId_clinica());
		ps.setInt(2, f.getCargo());
		ps.setString(3, f.getNome());
		ps.setInt(4, f.getCpf());
		ps.setInt(5, f.getRg());
		ps.setDouble(6, f.getSalario());
		ps.setInt(7, f.getId());
		ps.executeUpdate();
		ps.close();
		
		updateEndereco(f);
	}

	private void updateEndereco(Funcionario f) throws SQLException {		
		PreparedStatement ps = null;
		String sql = "";
		
		sql = "update endereco set cep=?, pais=?, estado=?, cidade=?, bairro=?, rua=?, numero=?, complemento=? WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, f.getEndereco().getCep());
		ps.setString(2, f.getEndereco().getPais());
		ps.setString(3, f.getEndereco().getEstado());
		ps.setString(4, f.getEndereco().getCidade());
		ps.setString(5, f.getEndereco().getBairro());
		ps.setString(6, f.getEndereco().getRua());
		ps.setInt(7, f.getEndereco().getNumero());
		ps.setString(8, f.getEndereco().getComplemento());
		ps.setInt(9, f.getEndereco().getId());
		ps.executeUpdate();
		ps.close();		
	}

	@Override
	public void deleteFuncionario(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='N' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
	
	@Override
	public void ativarFuncionario(int index) throws SQLException {
		PreparedStatement ps = null;
		String sql = "";
		sql = "UPDATE " + NOME_TABELA+ " SET ativo ='A' WHERE id=?;";
		ps = (PreparedStatement) this.connection.prepareStatement(sql);
		ps.setInt(1, index);
		ps.executeUpdate();
		ps.close();
	}
}
