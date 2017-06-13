package com.lanqiao.date170411.atm5;

import java.util.Scanner;

//需求2： ATM 初始要求：卡默认100000
//录入：银行卡号，取多少钱，密码 
//异常：如果卡号不对 1234-123456-1234AB 
//钱数：100 - 30000 
//密码：6 
//打印出来：当前取多少 还剩多少
//----------- 
//取款次数 金额
//1     150.00 
//2     1000.00

/**
 * @Description: 系统管理类
 * @author 廖尚岗
 * @date 2017年4月11日 下午12:18:30
 * @version V1.0
 */
public class Manager {
	static Scanner in = new Scanner(System.in);
	private String cardNo = "";// 记录卡号

	UserDao userDao = null;
	LogsDao logsDao = null;
	
	{		
		userDao = UserDao.getUserDao();
	}
	public Manager() {
	}

	public void mainMenu() {
		userDao.readUserInfo();
		System.out.println("==========欢迎进入ATM==========");
		cardInputMenu();// 卡号输入界面
		passwordInputMenu();// 密码输入界面
		buzMenu();// ATM主业务界面
	}

	private void buzMenu() {

		while (true) {
			System.out.println("==========ATM业务页面==========");
			System.out.println("1.取款\t2.存款\t3.查询记录");
			System.out.println("4.打印凭条\t0.退出系统");
			System.out.println("请选择您要进行的业务：");
			int sel = in.nextInt();
			switch (sel) {
			case 0:
				endMenu();// 结束选择界面
				break;
			case 1:
				withdrawMenu();// 取款数界面
				break;
			case 2:
				depositMenu();// 存款界面
				break;
			case 3:
				readFileLogs();// 显示所有存取款记录界面
				break;
			case 4:
				print();// 打印当前用户本次所有取款记录
				break;
			default:
				System.out.println("该模块未实现！");
				break;
			}
		}

	}

	// 用户下次登录时，选择查询，能在控制台显示之前的取款信息
	private void readFileLogs() {
		logsDao = LogsDao.getLogsDao();

		logsDao.readLogs();//该句是每次把日记从文件中取出，存到内存中，性能可能不好，但可实现更新记录。

		System.out.println("==========管理员专用功能：显示所有用户所有存取款记录界面==========");
		logsDao.showTradeLogs();

		System.out.println("========普通用户专用功能：显示当前账号交易的所有交易信息========");
		StringBuffer s = logsDao.getTradeLogById(cardNo);
		System.out.println(s);
	}

	private void print() {
		System.out.println("==========打印凭条界面==========");
		System.out.println("该模块未实现！");
	}

	private void endMenu() {
		System.out.println("==========结束界面==========");
		String userSel = "";

		do {
			System.out.println("是否继续交易:(y|n)");
			userSel = in.next();
			if (userSel.equals("n")) {
				showRecord();
				mainMenu();
				break;
			} else {
				buzMenu();
			}
		} while (true);

	}

	private void depositMenu() {
		System.out.println("==========存款界面==========");
		System.out.println("该模块未实现！");
	}

	private void withdrawMenu() {
		System.out.println("==========取款界面==========");

		int money;
		String userSel = "";
		do {
			System.out.println("请输入您的取款数：");
			money = in.nextInt();
			if (verifyMoney(money)) {
				System.out.println("是否继续取款：(y|n)");
				userSel = in.next();
				if (userSel.equals("n")) {
					break;
				}
			}
		} while (true);

	}

	private void passwordInputMenu() {
		System.out.println("===密码输入界面===");

		int pwd;
		do {
			System.out.println("请输入您的密码：");
			pwd = in.nextInt();
		} while (!verifyPwd(pwd));

	}

	private void cardInputMenu() {
		System.out.println("===卡号输入界面===");

		do {
			System.out.println("请输入您的卡号：");
			cardNo = in.next();
		} while (!verifyCardNO(cardNo));

	}

	// 验证取钱数
	private boolean verifyMoney(int money) {
		User u = userDao.getUser(cardNo);
		boolean flag = false;
		try {
			if (money < 100 || money > 30000) {// 验证取钱额度
				flag = false;
				throw new Exception("取钱数不合理！");
			} else {
				if (money % 100 == 0) {
					u.getAccount().withdraw(money);
					flag = true;
				} else {
					flag = false;
					throw new Exception("请整取钱数！");
				}
			}
		} catch (Exception e) {
			flag = false;
			System.out.println(e.getMessage());
		}
		return flag;
	}

	// 验证密码
	private boolean verifyPwd(int pwd) {
		boolean flag = false;
		String s = "";
		s = String.valueOf(pwd);

		try {
			if (s.length() != 6) {// 验证密码长度
				flag = false;
				throw new Exception("密码长度不合理！");
			} else {
				if (userDao.getPwd(cardNo).equals(s)) {
					flag = true;// 卡号位数正确并且存在该银行卡号
					System.out.println("密码正确！");
				} else {
					flag = false;
					throw new Exception("密码不正确！");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	// 验证卡号
	private boolean verifyCardNO(String cardNo) {
		boolean flag = false;

		try {
			if (cardNo.length() != 16) {// 验证卡号长度
				flag = false;
				throw new Exception("卡号长度不合理！");
			} else {
				if (userDao.getId(cardNo) != "") {
					flag = true;
					System.out.println("银行卡号正确！");// 卡号位数正确并且存在该银行卡号
				} else {
					flag = false;
					throw new Exception("卡号" + cardNo + "不存在！");
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return flag;
	}

	// 显示剩余余额记录
	public void showRecord() {
		User u = userDao.getUser(cardNo);
		System.out.println("本次交易成功，请收回你的银行卡，剩余余额 "
				+ u.getAccount().getBalance());
		System.out.println();
	}
}
