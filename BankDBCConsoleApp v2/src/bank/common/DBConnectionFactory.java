package bank.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		final String jbdcDriveClassName = "oracle.jdbc.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String userid = "c##java";
		final String passwd = "1234";
		
		Class.forName(jbdcDriveClassName);
		return DriverManager.getConnection(url, userid, passwd);
	}
}