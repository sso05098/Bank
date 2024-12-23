package bank.account;

import java.util.List;

public class HJAccountService implements AccountService {
	
	private AccountDAO accountDAO;
	
	public HJAccountService(AccountDAO accountDAO) {
		
		// 의존성 주입 : AccountService가 사용할 DB를 Application에서 정해서
		// 파라미터로 주입해 줌
		// 결과적으로 HJAccountService는 연결된 DB가 무엇인지와 관계없이 동작하게 됨
		// 연결된 DB가 바뀌더라도 HJAccountService를 수정할 필요가 없음
		
		this.accountDAO = accountDAO;
		
	}

	@Override
	public boolean createAccount(String owner, int money) {

		int result = accountDAO.insert(owner, money);
		
		return result == 1 ? true : false;
	}

	@Override
	public List<AccountVO> listAllAccount() {

		List<AccountVO> accountList;
		accountList = accountDAO.selectAll();
		if (accountList.isEmpty())
			return null;
		return accountList;
	}

	@Override
	public boolean deposit(int no, int money) {

		// 예금할 계좌 정보 가져오기
		AccountVO account = accountDAO.select(no);
		
		if (account == null)
			return false;
		
		// 잔액에 입금액 더하기
		int balance = account.getBalance() + money;
		
		// 잔액을 update하기
		int result = accountDAO.updateBalance(no, balance);
		
		return result==1 ? true : false;
	}

	@Override
	public boolean withdraw(int no, int money) {
		
		// 예금할 계좌 정보 가져오기
		AccountVO account = accountDAO.select(no);
		
		if (account == null)
			return false;
		
		// 잔액에 출금액 빼기
		int balance = account.getBalance() - money;
		
		// 잔액을 update하기
		int result = accountDAO.updateBalance(no, balance);
		
		return result==1 ? true : false;
		
	}

	@Override
	public boolean removeAccount(int no) {
		
		AccountVO account = accountDAO.select(no);
		
		if (account == null)
			return false;
		
		int result = accountDAO.delete(no);
		
		return result == 1 ? true : false;
	}

}
