package bank.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bank.common.DBConnectionFactory;


public class OracleAccountDAO implements AccountDAO {

	@Override
	public int insert(String owner, int money) {
		
		int result = 0;
		
		// sql문
		String sql = new StringBuilder()
			.append("insert into bank_account ")
			.append("(no, owner, balance, regdate)")
			.append("values (seq_bank_account_no.nextval, ? , ? , sysdate)")
			.toString();
		
		try (
			// db 연결
			Connection conn = DBConnectionFactory.getConnection();
			// 실행 객체 만들고
			PreparedStatement pstmt = conn.prepareStatement(sql);){
		
			// sql문 파라미터 값 setting
			pstmt.setString(1, owner);
			pstmt.setInt(2, money);
			
			// 실행 요청
			result = pstmt.executeUpdate();
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}

	@Override
	public AccountVO select(int no) {
		
		AccountVO account = null;
		
		//String sql = "select * from bank_account where no = '" + no + "'";
		String sql = "select * from bank_account where no = ?";
		try (Connection conn = DBConnectionFactory.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, no);
			
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					String owner = rs.getString("owner");
					int balance = rs.getInt("balance");
					Date regdate = rs.getDate("regdate");
					account = new AccountVO(no, owner, balance, regdate);
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return account;
	}

	@Override
	public List<AccountVO> selectAll() {
		// 결과 저장 장소
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		
		// sql 문
		String sql = "select * from bank_account";

		try (
				// DB 연결
				Connection conn = DBConnectionFactory.getConnection();
				// 실행 객체 
				PreparedStatement pstmt = conn.prepareStatement(sql);
				// 실행
				ResultSet rs = pstmt.executeQuery();
		) {
			// 결과 처리
			while (rs.next()) {
				int no = rs.getInt("no");
				String owner = rs.getString("owner");
				int balance = rs.getInt("balance");
				Date regdate = rs.getDate("regdate");
				accountList.add(new AccountVO(no, owner, balance, regdate));
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return accountList;
	}

	@Override
	public int updateBalance(int no, int balance) {
		// 결과 저장소
		int result = 0;
		
		// sql 문
		String sql = new StringBuilder()
				.append("update bank_account ")
				.append("set balance=?")
				.append("where no=?")
				.toString();
		
		try( Connection conn = DBConnectionFactory.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql);
		) {
			pstmt.setInt(1, balance);
			pstmt.setInt(2, no);
			
			// 실행
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// 결과처리
		return result;
	}

	@Override
	public int delete(int no) {
		
		int result = 0;
		// sql문
		String sql = "delete from bank_account where no = ?";
		
		try (Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
		
		// DB 연결
		// 실행문 생성
		
		// 파라미터 set
		// 실행
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		// 결과처리
		return result;
	}

}
