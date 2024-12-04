package bank.account;

import java.util.List;

import bank.exception.NoAccountException;

public interface AccountService {
	boolean createAccount(String member_id, int add_money);
	List<AccountVO> listAllAccount(String member_id);
	boolean deposit(int account_no, int add_money) throws NoAccountException;
	boolean withdraw(int account_no, int minus_money) throws NoAccountException;
	boolean removeAccount(int account_no) throws NoAccountException;
	AccountVO searchAccountByNo(int account_no) throws NoAccountException;
	int addAccHistory(AccountHistoryVO accHistory);
	AccountVO searchAccountById(String member_id) throws NoAccountException;
	List<AccountHistoryVO> listAllHistoryByNos(List<AccountVO> accountList) throws NoAccountException;
}
