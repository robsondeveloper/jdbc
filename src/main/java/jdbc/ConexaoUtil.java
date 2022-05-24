package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {

	private static final String url = "jdbc:postgresql://localhost:5432/produtos";
	private static final String usuario = "demo";
	private static final String senha = "demo123";

	public Connection obterConexao() {
		try {
			Connection conexao = DriverManager.getConnection(url, usuario, senha);
			return conexao;
		} catch (SQLException e) {
			throw new RuntimeException("Não foi estabelecer uma conexão com o banco de dados");
		}
	}
}
