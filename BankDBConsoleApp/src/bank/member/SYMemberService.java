package bank.member;

public class SYMemberService implements MemberService {

	private MemberDAO memberDao;
	
	public SYMemberService(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}


	@Override
	public int registMemberIsOk(MemberVO member) {
		int isOk = memberDao.insertMember(member);
		return isOk;
	}


	@Override
	public MemberVO searchMember(String loginId, String loginPwd) {
		MemberVO member = memberDao.selectMember(loginId, loginPwd);
		return member;
	}

}
