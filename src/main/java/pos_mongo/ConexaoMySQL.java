package pos_mongo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL {

	public static void main(String[] args) throws IOException {
		getConexaoMySQL();
	}

	public static String status = "Não conectou...";

	public ConexaoMySQL() {

	}

	public static java.sql.Connection getConexaoMySQL() throws IOException {

		Connection connection = null; // atributo do tipo Connection

		try {
			Class.forName(Util.getProp("driverName"));
			String url = Util.getProp("urlConexao");
			connection = DriverManager.getConnection(url, Util.getProp("user"),
					Util.getProp("key"));

			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
				System.out.println(status);
			} else {
				status = ("STATUS--->Não foi possivel realizar conexão");
				System.out.println(status);
			}

			return connection;

		} catch (ClassNotFoundException e) { // Driver não encontrado
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;

		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;

		}

	}

	public static String statusConection() {
		return status;
	}

	public static boolean FecharConexao() throws IOException {

		try {

			ConexaoMySQL.getConexaoMySQL().close();

			return true;

		} catch (SQLException e) {

			return false;

		}

	}

	public static java.sql.Connection ReiniciarConexao() throws IOException {

		FecharConexao();

		return ConexaoMySQL.getConexaoMySQL();

	}

}