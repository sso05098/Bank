package bank.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionFactory {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		final String jdbcDriverClassName = "oracle.jdbc.OracleDriver";
		final String url = "jdbc:oracle:thin:@localhost:1521:xe";
		final String userId = "hr";
		final String userPwd = "hr";
		
		Class.forName(jdbcDriverClassName);
		Connection conn = DriverManager.getConnection(url, userId, userPwd);
		return conn;
		
	}
}
