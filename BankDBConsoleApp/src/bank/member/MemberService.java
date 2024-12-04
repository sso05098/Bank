package bank.member;

public interface MemberService {

	int registMemberIsOk(MemberVO member);

	MemberVO searchMember(String loginId, String loginPwd);
	
}
