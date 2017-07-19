package br.com.conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class Conexao {

	//variaval para conexao com o banco
	private static Connection connection;

	public static Connection getConnection(int banco) throws Exception {
		// aqui vamos criar as variaves para colocar depois a conexao do banco, usuario e senha 
		String conexao = "";
		String usuario = "";
		String senha = "";
		
		//podemos trabalhar com mais de um banco;
		if (banco == Database.MYSQL) {
			Class.forName("com.mysql.jdbc.Driver");
			conexao = "jdbc:mysql://localhost/projeto";
			usuario = "root";
			senha = "root";
		} else if (banco == Database.ORACLE) {
			conexao = "jdbc:postgresql://totvs11:5432/projeto";
			usuario = "root";
			senha = "root";
		} else {
			throw new IllegalArgumentException("Tipo de banco não suportado");
		}		
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(conexao, usuario,senha);
			} catch (SQLException e) {
				throw new Exception("SQLException => ConnectionManager: "
						+ e.getMessage());
			}
		}
		return connection;
	}
	public static void close() throws Exception {
		connection.close();
	}

}