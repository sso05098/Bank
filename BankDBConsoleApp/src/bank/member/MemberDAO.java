package bank.member;

public interface MemberDAO {

	int insertMember(MemberVO member);

	MemberVO selectMember(String loginId, String loginPwd);
	
}
