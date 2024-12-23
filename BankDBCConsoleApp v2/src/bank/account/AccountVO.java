package bank.account;

import java.util.Date;

public class AccountVO {
	private int no;
	private String owner;
	private int balance;
	private Date regdate;
	
	public AccountVO(int no, String owner, int balance, Date regdate) {
		this.no = no;
		this.owner = owner;
		this.balance = balance;
		this.regdate = regdate;
	}
		
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "AccountVO [no=" + no + ", owner=" + owner + ", balance=" + balance + ", regdate=" + regdate + "]";
	}
}