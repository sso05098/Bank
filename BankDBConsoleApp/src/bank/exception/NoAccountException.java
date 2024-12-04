package bank.exception;

import java.util.List;

import bank.account.AccountHistoryVO;

public class NoAccountException extends Exception {
	// 기본 생성자
	public NoAccountException() {
		
	}
	
	// 메시지를 출력할 생성자
	public NoAccountException(int account_no) {
		super("없는 계좌 번호 : " + account_no);
	}

	public NoAccountException(String member_id) {
		super(member_id + " : 의 소유 계좌가 존재하지 않습니다.");
	}

	public NoAccountException(List<AccountHistoryVO> historyList) {
		super("입출금 내역이 존재하지 않습니다.");
	}
}
