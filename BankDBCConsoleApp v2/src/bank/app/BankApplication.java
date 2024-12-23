package bank.app;

import java.util.List;
import java.util.Scanner;

import bank.account.AccountDAO;
import bank.account.AccountService;
import bank.account.AccountVO;
import bank.account.HJAccountService;
import bank.account.OracleAccountDAO;

public class BankApplication {
	
	private static Scanner sc = new Scanner(System.in);
	private static AccountService as;
	
	public static void main(String[] args) {
		
		// DB 결정
		AccountDAO accountDAO = new OracleAccountDAO();
		
		// DB에 대한 의존성 주입
		as = new HJAccountService(accountDAO);
		
		int menu = 0;
		
		do {
			
			System.out.println("------------------------------------------------");
			System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 계좌삭제 | 6. 종료");
			System.out.println("------------------------------------------------");
			System.out.print("선택> ");
			
			menu = Integer.parseInt(sc.nextLine());
			
			switch (menu) {
				case 1 : // 계좌등록
					createAccount();
					break;
				case 2 : // 계좌목록
					viewAccountList();
					break;
				case 3 :
					deposit();
					break;
				case 4 :
					withdraw();
					break;
				case 5 :
					removeAccount();
					break;
				case 6 :
					exit();
					break;
				default :
					System.out.println("메뉴에 있는 번호를 입력하세요.");
			}
		
		} while (menu != 6);
	}

	private static void createAccount() {
		System.out.print("계좌주 : ");
		String owner = sc.nextLine();
		
		System.out.print("초기 입금액 : ");
		int money = Integer.parseInt(sc.nextLine());
		
		if (as.createAccount(owner, money)) {
			System.out.println(owner + "님의 계좌가 등록되었습니다.");
		}
		
	}

	private static void viewAccountList() {
		
		List<AccountVO> accountList = as.listAllAccount();
		if (accountList != null) { 
			for (AccountVO account : accountList) {
				System.out.println(account.toString());
			}
		} else {
			System.out.println("등록된 계좌가 없습니다.");
		}
	}

	private static void deposit() {
		// 계좌번호, 금액
		System.out.print("계좌번호 : ");
		int accountNo = Integer.parseInt(sc.nextLine());
		
		System.out.print("예금액 : ");
		int money = Integer.parseInt(sc.nextLine());
		
		if (as.deposit(accountNo, money))
			System.out.println("계좌번호 " + accountNo + "에 " + money + "원이 입금되었습니다.");
		
	}

	private static void withdraw() {
		// 계좌번호, 금액
		System.out.print("계좌번호 : ");
		int accountNo = Integer.parseInt(sc.nextLine());
		
		System.out.print("출금액 : ");
		int money = Integer.parseInt(sc.nextLine());
		
		if (as.withdraw(accountNo, money))
			System.out.println("계좌번호 " + accountNo + "에서 " + money + "원이 출금되었습니다.");
		
	}

	private static void removeAccount() {
		System.out.println("계좌번호 : ");
		int accountNo = Integer.parseInt(sc.nextLine());
		as.removeAccount(accountNo);
	}

	private static void exit() {
		System.out.println("HJ 은행 서비스를 종료합니다.");
	
	}
}
