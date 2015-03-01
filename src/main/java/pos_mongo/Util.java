package pos_mongo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Util {
	public static final String DADOS = "conexao.properties";

	public static String getProp(String key) throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(DADOS);
		props.load(file);
		return props.getProperty(key);
	}
}
