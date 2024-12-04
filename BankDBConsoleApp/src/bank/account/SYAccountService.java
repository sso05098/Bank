package bank.account;

import java.util.List;

import bank.exception.NoAccountException;

public class SYAccountService implements AccountService {
	
	private AccountDAO accountDao;

	public SYAccountService(AccountDAO accountDao) {
		super();
		this.accountDao = accountDao;
	}

	@Override
	public boolean createAccount(String member_id, int add_money) {
		int result = accountDao.insert(member_id, add_money);
		
		return result == 1 ? true : false;
	}

	@Override
	public List<AccountVO> listAllAccount(String member_id) {
		
		List<AccountVO> accountList = accountDao.selectAll(member_id);
		if (accountList.isEmpty()) {
			return null;
		}
		return accountList;
		 
	}

	@Override
	public boolean deposit(int account_no, int add_money) throws NoAccountException {
		// 예금할 계좌 정보 가져오기
		AccountVO account = accountDao.select(account_no);
		
		if (account == null) throw new NoAccountException(account_no);
	
		// 잔액에 입금액 더하기
		int account_money = account.getAccount_money() + add_money;
		
		// 잔액을 update 하기
		account.setAccount_money(account_money);
		int result = accountDao.updateBalance(account_no, account_money);
		
		return result == 1 ? true : false;

	}

	@Override
	public boolean withdraw(int account_no, int minus_money) throws NoAccountException {
		// 인출할 계좌 정보 가져오기
		AccountVO account = accountDao.select(account_no);
				
		if (account == null) throw new NoAccountException(account_no);
			
		// 잔액에 출금액 빼기
		int account_money = account.getAccount_money() - minus_money;
				
		// 잔액을 update 하기
		account.setAccount_money(account_money);
		int result = accountDao.updateBalance(account_no, account_money);
				
		return result == 1 ? true : false;

	}

	@Override
	public boolean removeAccount(int account_no) throws NoAccountException {
		
		AccountVO account = accountDao.select(account_no);
		
		if (account == null) {
			throw new NoAccountException(account_no);
		}
		
		int result = accountDao.delete(account_no);
		
		return result == 1 ? true : false;

	}

	@Override
	public AccountVO searchAccountByNo(int account_no) throws NoAccountException {
		AccountVO account = accountDao.select(account_no);
		if (account == null) {
			throw new NoAccountException(account_no);
		}
		return account;
	}

	@Override
	public int addAccHistory(AccountHistoryVO accHistory) {
		int result = accountDao.insertHistory(accHistory);
		return result;
	}

	@Override
	public AccountVO searchAccountById(String member_id) throws NoAccountException {
		AccountVO account = accountDao.selectAccById(member_id);
		if (account == null) {
			throw new NoAccountException(member_id);
		}
		return account;
	}

	@Override
	public List<AccountHistoryVO> listAllHistoryByNos(List<AccountVO> accountList) throws NoAccountException {
		List<AccountHistoryVO> historyList = accountDao.selectHistoryByAcc(accountList);
		if (historyList == null) {
			throw new NoAccountException(historyList);
		}
		return historyList;
	}

}
