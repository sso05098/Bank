package bank.account;

import java.util.List;

public interface AccountDAO {
	int insert(String owner, int money);
	AccountVO select(int no);
	List<AccountVO> selectAll();
	int updateBalance(int no, int balance);
	int delete(int no);
}
