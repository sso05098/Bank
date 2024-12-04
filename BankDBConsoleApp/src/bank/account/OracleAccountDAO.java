package bank.account;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.common.DBConnectionFactory;

public class OracleAccountDAO implements AccountDAO {

	@Override
	public int insert(String member_id, int add_money) {
		int result = 0;
		// sql문 
		String sql = new StringBuilder()
		.append("insert into accounts (account_no, member_id, account_money, register_time) ")
		.append("values (seq_accounts_no.nextval, ?, ?, sysdate)").toString();
		
		// DB 연결
		try (
				// db 연결
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, member_id);
				pstmt.setInt(2,  add_money);
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 실행객체 만들고
		
		// 실행 요청
		
		// 결과 확인
		return result;
	}

	@Override
	public AccountVO select(int account_no) {
		
		AccountVO account = null;
		ResultSet rs = null;
		
		String sql = "select * from accounts where account_no = ?";
		
		try (
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setInt(1, account_no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String owner = rs.getString("member_id");
					int account_money = rs.getInt("account_money");
					Date register_time = rs.getDate("register_time");
					
					account = new AccountVO(account_no, owner, account_money, register_time);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;
	}

	@Override
	public List<AccountVO> selectAll(String member_id) {
		List<AccountVO> accountList = new ArrayList<AccountVO>();
		
		String sql = "select * from accounts where member_id = ?";
		
		try (
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, member_id);
				ResultSet rs = pstmt.executeQuery();
			
				while(rs.next()) {
					int account_no = rs.getInt("account_no");
					member_id = rs.getString("member_id");
					int account_money = rs.getInt("account_money");
					Date register_time = rs.getDate("register_time");
					
					accountList.add(new AccountVO(account_no, member_id, account_money, register_time));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accountList;
	}

	@Override
	public int updateBalance(int account_no, int account_money) {
		int result = 0;
		// sql문 
		String sql = new StringBuilder()
		.append("update accounts set account_money = ? ")
		.append("where account_no = ?").toString();
		
		// DB 연결
		try (
				// db 연결
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setInt(1, account_money);
				pstmt.setInt(2,  account_no);
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 실행객체 만들고
		
		// 실행 요청
		
		// 결과 확인
		return result;
	}

	@Override
	public int delete(int account_no) {
		int result = 0;
		
		// sql문
		String sql = "delete accounts where account_no = ?";
		
		try (
				// DB 연결
				Connection conn = DBConnectionFactory.getConnection();
				// 실행문 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			// 파라미터 세팅
			pstmt.setInt(1, account_no);
			// 실행
			result = pstmt.executeUpdate();
		}  catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public int insertHistory(AccountHistoryVO accHistory) {
		int result = 0;
		// sql문 
		String sql = new StringBuilder()
		.append("insert into accounts_history (account_no, history_no, task, task_money, account_money, task_time) ")
		.append("values (?, seq_history_no.nextval, ?, ?, ?, sysdate)").toString();
		
		// DB 연결
		try (
				// db 연결
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setInt(1, accHistory.getAccount_no());
				pstmt.setString(2,  accHistory.getTask());
				pstmt.setInt(3,  accHistory.getTask_money());
				pstmt.setInt(4,  accHistory.getAccount_money());
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 실행객체 만들고
		
		// 실행 요청
		
		// 결과 확인
		return result;
	}

	@Override
	public AccountVO selectAccById(String member_id) {
		AccountVO account = null;
		ResultSet rs = null;
		
		String sql = "select * from (select * from accounts where member_id = ?  order by register_time desc) where rownum = 1";
		
		try (
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, member_id);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int account_no = rs.getInt("account_no");
					int account_money = rs.getInt("account_money");
					Date register_time = rs.getDate("register_time");
					
					account = new AccountVO(account_no, member_id, account_money, register_time);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;
	}

	@Override
	public List<AccountHistoryVO> selectHistoryByAcc(List<AccountVO> accountList) {
		List<AccountHistoryVO> historyList = new ArrayList<>();
		
		String sql = "select history_no, task, task_money, account_money, to_char(task_time, 'yyyy-mm-dd') as task_time from accounts_history where account_no = ?";

		try (
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				for (AccountVO account : accountList) {
					pstmt.setInt(1, account.getAccount_no());
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) {
						int history_no = rs.getInt("history_no");
						String task = rs.getString("task");
						int task_money = rs.getInt("task_money");
						int account_money = rs.getInt("account_money");
						String task_time = rs.getString("task_time");
						
						AccountHistoryVO history = new AccountHistoryVO(account.getAccount_no(), history_no, task, task_money, account_money, task_time);
						historyList.add(history);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return historyList;
	}
	
}
