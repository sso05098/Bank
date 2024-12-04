package bank.account;

import java.util.List;

public interface AccountDAO {
	int insert(String member_id, int add_money);
	AccountVO select(int account_no);
	List<AccountVO> selectAll(String member_id);
	int updateBalance(int account_no, int account_money);
	int delete(int account_no);
	int insertHistory(AccountHistoryVO accHistory);
	AccountVO selectAccById(String member_id);
	List<AccountHistoryVO> selectHistoryByAcc(List<AccountVO> accountList);
}
