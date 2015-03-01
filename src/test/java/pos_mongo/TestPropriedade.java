package pos_mongo;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestPropriedade {

	@Test
	public void testUrlConexao() throws IOException {
		assertEquals("jdbc:mysql://localhost/imdb", Util.getProp("urlConexao"));
	}

}
