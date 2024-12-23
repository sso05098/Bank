package bank.account;

import java.util.List;

public interface AccountService {
	boolean createAccount(String owner, int money);
	List<AccountVO> listAllAccount();
	boolean deposit(int no, int money);
	boolean withdraw(int no, int money);
	boolean removeAccount(int no);
}
