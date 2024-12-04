package bank.account;

public class AccountHistoryVO {
	private int account_no;
	private int history_no;
	private String task;
	private int task_money;
	private int account_money;
	private String task_time;
	
	public AccountHistoryVO() {
		super();
	}

	public AccountHistoryVO(int account_no, int history_no, String task, int task_money, int account_money,
			String task_time) {
		super();
		this.account_no = account_no;
		this.history_no = history_no;
		this.task = task;
		this.task_money = task_money;
		this.account_money = account_money;
		this.task_time = task_time;
	}

	public AccountHistoryVO(int account_no, String task, int task_money, int account_money) {
		super();
		this.account_no = account_no;
		this.task = task;
		this.task_money = task_money;
		this.account_money = account_money;
	}

	public int getAccount_no() {
		return account_no;
	}

	public void setAccount_no(int account_no) {
		this.account_no = account_no;
	}

	public int getHistory_no() {
		return history_no;
	}

	public void setHistory_no(int history_no) {
		this.history_no = history_no;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getTask_money() {
		return task_money;
	}

	public void setTask_money(int task_money) {
		this.task_money = task_money;
	}

	public int getAccount_money() {
		return account_money;
	}

	public void setAccount_money(int account_money) {
		this.account_money = account_money;
	}

	public String getTask_time() {
		return task_time;
	}

	public void setTask_time(String task_time) {
		this.task_time = task_time;
	}

	@Override
	public String toString() {
		return "AccountHistoryVO [account_no=" + account_no + ", history_no=" + history_no + ", task=" + task
				+ ", task_money=" + task_money + ", account_money=" + account_money + ", task_time=" + task_time + "]";
	}
	
}
