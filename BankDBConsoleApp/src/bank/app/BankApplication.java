package bank.app;

import java.util.List;
import java.util.Scanner;

import bank.account.AccountDAO;
import bank.account.AccountHistoryVO;
import bank.account.AccountService;
import bank.account.AccountVO;
import bank.account.OracleAccountDAO;
import bank.account.SYAccountService;
import bank.exception.NoAccountException;
import bank.member.MemberDAO;
import bank.member.MemberService;
import bank.member.MemberVO;
import bank.member.SYMemberDAO;
import bank.member.SYMemberService;

public class BankApplication {
	
	private static Scanner sc = new Scanner(System.in);
	private static AccountService as;
	private static MemberService ms;
	
	public static void main(String[] args) {
		// DB 결정
		AccountDAO accountDao = new OracleAccountDAO();
		MemberDAO memberDao = new SYMemberDAO();
		
		// DB에 대한 의존성 주입
		as = new SYAccountService(accountDao);
		ms = new SYMemberService(memberDao);
		
		int menu = 0;
		
		String member_id = null;
		
			do {
				if (member_id != null) {
					System.out.println("1.계좌목록 | 2.입금 | 3.출금 | 4.입출금내역 | 5.계좌개설 | 6.계좌해지 | 7. 로그아웃 | 8.종료");
					System.out.print("선택 : ");
					
					menu = Integer.parseInt(sc.nextLine());
					
					switch (menu) {
						case 1:
							viewAccountList(member_id);
							break;
						case 2:
							deposit();
							break;
						case 3:
							withdraw();
							break;
						case 4:
							accountTaskList(member_id);
							break;
						case 5:
							createAccount();
							break;
						case 6:
							removeAccount();
							break;
						case 7:
							member_id = null;
							break;
						case 8:
							exit();
							break;
					}
				} else {
					
					System.out.println("1.회원가입 | 2.로그인 | 3.종료");
					System.out.print("선택 : ");
					
					menu = Integer.parseInt(sc.nextLine());
					
					switch (menu) {
					case 1:
						registerMember();
						break;
					case 2:
						member_id = login();
						break;
					case 3:
						exit();
						break;
					}
				} 
			
			}while(true);
	}

	private static String login() {
		System.out.print("ID 입력 : ");
		String loginId = sc.nextLine();
		System.out.print("PWD 입력 : ");
		String loginPwd = sc.nextLine();
		MemberVO member = null;
		member = ms.searchMember(loginId, loginPwd);
		if (member != null) {
			System.out.println("로그인에 성공하였습니다.");
			return loginId;
		} else {
			System.out.println("로그인에 실패하였습니다.");
			return null;
		}
	}

	private static void registerMember() {
		System.out.print("등록할 ID 입력 : ");
		String reg_id = sc.nextLine();
		System.out.print("등록할 PWD 입력 : ");
		String reg_pwd = sc.nextLine();
		System.out.print("성명 입력 : ");
		String member_nm = sc.nextLine();
		MemberVO registerMember = new MemberVO(reg_id, reg_pwd, member_nm);
		
		int isOk = ms.registMemberIsOk(registerMember);
		if (isOk > 0) {
			System.out.println("회원가입이 성공적으로 이루어졌습니다.");
		} else {
			System.out.println("회원가입에 실패 하였습니다.");
		}
	}

	private static void accountTaskList(String member_id) {
		List<AccountVO> accountList = as.listAllAccount(member_id);
		if (accountList == null) {
			System.out.println("입출금 내역이 존재하지 않습니다.");
		} else {
			List<AccountHistoryVO> historyList = null;
			try {
				historyList = as.listAllHistoryByNos(accountList);
			} catch (NoAccountException e) {
				e.printStackTrace();
			}
			
			for (AccountHistoryVO history : historyList) {
				System.out.println(history.toString());
			}
		}
	}

	private static void exit() {
		System.out.println("프로그램을 종료합니다.");
		System.exit(1);
	}

	private static void removeAccount() {
		System.out.print("계좌번호 : ");
		int account_no = Integer.parseInt(sc.nextLine());
		try {
			as.removeAccount(account_no);
		} catch (NoAccountException e) {
			e.printStackTrace();
		}
	}

	private static void withdraw() {
		// 계좌번호, 금액
		System.out.print("계좌번호 : ");
		int account_no = Integer.parseInt(sc.nextLine());
		
		System.out.print("출금액 : ");
		int minus_money = Integer.parseInt(sc.nextLine());
		
		AccountVO account = null;
		try {
			account = as.searchAccountByNo(account_no);
		} catch (NoAccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			if (minus_money > account.getAccount_money()) {
				System.out.println("잔액 부족 : " + (account.getAccount_money() - minus_money) + "원");
			} else
				try {
					if (as.withdraw(account_no, minus_money)) {
						account = as.searchAccountByNo(account_no);
						System.out.println(minus_money + "원이 출금되었습니다.");
						
						AccountHistoryVO accHistory = new AccountHistoryVO(account_no, "출금", minus_money, account.getAccount_money());
						
						int historyInsert = as.addAccHistory(accHistory);
						if (historyInsert <= 0) {
							System.out.println("입출금 내역 업데이트에 실패하였습니다.");
						}
					} else {
						System.out.println("출금에 실패하였습니다.");
					}
				} catch (NoAccountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	

	private static void deposit() {
		// 계좌번호, 금액
		System.out.print("계좌번호 : ");
		int account_no = Integer.parseInt(sc.nextLine());
		System.out.print("예금액 : ");
		int add_money = Integer.parseInt(sc.nextLine());
		
		try {
			as.deposit(account_no, add_money);
			System.out.println(add_money + "원이 입금되었습니다.");
			AccountVO account = as.searchAccountByNo(account_no);
			System.out.println("총 잔액은 " + account.getAccount_money() + "원 입니다.");
			
			AccountHistoryVO accHistory = new AccountHistoryVO(account_no, "입금", add_money, account.getAccount_money());
			
			int historyInsert = as.addAccHistory(accHistory);
			if (historyInsert <= 0) {
				System.out.println("입출금 내역 업데이트에 실패하였습니다.");
			}
		} catch (NoAccountException e) {
			System.out.println(e.toString());
		}
		
	}

	private static void viewAccountList(String member_id) {
		List<AccountVO> accountList = as.listAllAccount(member_id);
		if (!accountList.isEmpty()) {
			for (AccountVO account : accountList) {
				System.out.println(account.toString());
			}
		}
	}

	private static void createAccount() {
		System.out.print("아이디 입력 : ");
		String member_id = sc.nextLine();
		
		System.out.print("초기 입금액 : ");
		int add_money = Integer.parseInt(sc.nextLine());
		
		if (as.createAccount(member_id, add_money)) {
			System.out.println(member_id + "님의 계좌가 등록되었습니다.");
			
			AccountVO account = null;
			try {
				account = as.searchAccountById(member_id);
			} catch (NoAccountException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			AccountHistoryVO accHistory = new AccountHistoryVO(account.getAccount_no(), "개설", add_money, account.getAccount_money());
			
			int historyInsert = as.addAccHistory(accHistory);
			if (historyInsert <= 0) {
				System.out.println("입출금 내역 업데이트에 실패하였습니다.");
			}
		} else {
			System.out.println(member_id + "님의 계좌가 등록 실패되었습니다.");
		}
	}
}