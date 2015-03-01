package pos_mongo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class InsertBaseMongo {

	@SuppressWarnings({ "deprecation", "resource" })
	public static void main(String[] args) throws SQLException, IOException {
		MongoClient mongo = new MongoClient();
		DB db = mongo.getDB("uninorte");
		DBCollection tabelaFilme = db.getCollection("filmes");

		java.sql.Connection con;
		con = ConexaoMySQL.getConexaoMySQL();

		String sql = "SELECT mv.id as id_filme, name, year, rank, director_id, first_name, last_name "
				+ "FROM movies as mv "
				+ "INNER JOIN movies_directors md ON md.director_id = mv.id "
				+ "INNER JOIN directors d  ON d.id = mv.id LIMIT 10";

		Statement stm = con.createStatement();

		ResultSet rs = stm.executeQuery(sql);

		while (rs.next()) {

			BasicDBObject documentFilmes = new BasicDBObject();
			documentFilmes.append("id_filme", rs.getString("id_filme"))
					.append("name", rs.getString("name"))
					.append("year", rs.getString("year"))
					.append("rank", rs.getString("rank"));

			BasicDBObject documentDiretors = new BasicDBObject();
			documentDiretors.append("director_id", rs.getString("director_id"))
					.append("first_name", rs.getString("first_name"))
					.append("last_name", rs.getString("last_name"))
					.append("diretor", documentDiretors);

			//insere o documento na base Mongo
			tabelaFilme.insert(documentFilmes);
			
			//Imprimir JSONs da coleção
			Set<String> colls = db.getCollectionNames();
			for (String s : colls) {
				System.out.println(s);
			}
			
		}
		con.close();
	}
	
}
