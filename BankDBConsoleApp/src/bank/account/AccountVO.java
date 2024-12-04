package bank.account;

import java.sql.Date;

public class AccountVO {
	private int account_no;
	private String member_id;
	private int account_money;
	private Date register_time;
	
	public AccountVO() {
		super();
	}

	public AccountVO(int account_no, String member_id, int account_money, Date register_time) {
		super();
		this.account_no = account_no;
		this.member_id = member_id;
		this.account_money = account_money;
		this.register_time = register_time;
	}

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public int getAccount_money() {
		return account_money;
	}

	public void setAccount_money(int account_money) {
		this.account_money = account_money;
	}

	public Date getRegister_time() {
		return register_time;
	}

	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}

	@Override
	public String toString() {
		return "AccountVO [account_no=" + account_no + ", member_id=" + member_id + ", account_money=" + account_money
				+ ", register_time=" + register_time + "]";
	}
	
	
}
