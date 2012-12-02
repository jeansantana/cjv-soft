package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de conecção com o banco de dados
 * */

public class ConnectPostegreSQL {
	
	public static Statement comando;
	
	public static void conectar() throws SQLException, java.sql.SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection con = DriverManager.getConnection(
				"jdbc:postgresql://localhost:5432/glass_budget", "jeansilva",
				"123");
		comando = con.createStatement();
	}	
}
