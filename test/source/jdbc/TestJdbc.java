package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://192.168.99.100:32791/spaceinvaders?useSSL=false";
		String user = "root";
		String pass = "admin";
		try {
			System.out.println("Conectando a base de datos: " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Conecto correctamente");
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
