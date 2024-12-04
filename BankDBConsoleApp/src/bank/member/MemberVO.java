package bank.member;

public class MemberVO {
	private String member_id;
	private String member_pwd;
	private String member_nm;
	private String register_time;
	
	public MemberVO() {
	}

	public MemberVO(String member_id, String member_pwd, String member_nm, String register_time) {
		super();
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_nm = member_nm;
		this.register_time = register_time;
	}

	

	public MemberVO(String member_id, String member_pwd, String member_nm) {
		super();
		this.member_id = member_id;
		this.member_pwd = member_pwd;
		this.member_nm = member_nm;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMember_pwd() {
		return member_pwd;
	}

	public void setMember_pwd(String member_pwd) {
		this.member_pwd = member_pwd;
	}

	public String getMember_nm() {
		return member_nm;
	}

	public void setMember_nm(String member_nm) {
		this.member_nm = member_nm;
	}

	public String getRegister_time() {
		return register_time;
	}

	public void setRegister_time(String register_time) {
		this.register_time = register_time;
	}

	@Override
	public String toString() {
		return "MemberVO [member_id=" + member_id + ", member_pwd=" + member_pwd + ", member_nm=" + member_nm
				+ ", register_time=" + register_time + "]";
	}
	
}
