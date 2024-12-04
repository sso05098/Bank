package bank.test;

import java.sql.Connection;
import java.sql.SQLException;

import bank.common.DBConnectionFactory;

public class DBTest {

	public static void main(String[] args) {
		try (
				Connection conn = DBConnectionFactory.getConnection();
			) {
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

}
