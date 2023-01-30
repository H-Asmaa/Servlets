package servletCours;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnection {
	public Connection cn;
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/servletdb","root","");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return cn;
	}
}
