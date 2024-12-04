package bank.member;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.account.AccountVO;
import bank.common.DBConnectionFactory;

public class SYMemberDAO implements MemberDAO {

	@Override
	public int insertMember(MemberVO member) {
		int result = 0;
		// sql문 
		String sql = new StringBuilder()
		.append("insert into acc_members (member_id, member_pwd, member_nm, register_time) ")
		.append("values (?, ?, ?, sysdate)").toString();
		
		// DB 연결
		try (
				// db 연결
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, member.getMember_id());
				pstmt.setString(2,  member.getMember_pwd());
				pstmt.setString(3, member.getMember_nm());
				
				result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 결과 확인
		return result;
	}

	@Override
	public MemberVO selectMember(String loginId, String loginPwd) {
		MemberVO member = null;
		ResultSet rs = null;
		
		String sql = "select member_id, member_pwd, member_nm, to_char(register_time, 'yyyy-mm-dd') as register_time from acc_members where member_id = ? and member_pwd = ?";
		
		try (
				Connection conn = DBConnectionFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
				pstmt.setString(1, loginId);
				pstmt.setString(2, loginPwd);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String member_nm = rs.getString("member_nm");
					String register_time = rs.getString("register_time");
					
					member = new MemberVO(loginId, loginPwd, member_nm, register_time);
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
		return member;
	}
	

}
