package com.lanqiao.date170425.atm6;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 账号类
 * @author forward
 * @date 2017年4月11日 下午12:29:52
 * @version V2.0
 */
public class Account {
	private String cardNo;// 银行卡号
	private double balance = 100000.00;// 余额
	private List<String> logs;// 取款日志

	public Account(String cardNo, double balance) {
		super();
		this.cardNo = cardNo;
		this.balance = balance;
		logs = new LinkedList<>();
	}

	/*private double getTotalBalance() {
		UserDao u = UserDao.getUserDao();
		double total = 0;
		total += u.getBalance("1111222233334444");
		total += u.getBalance("1111222233335555");
		total += u.getBalance("1111222233336666");
		return total;
	}*/

	// 从当前余额中减去金额。
	public synchronized boolean withdraw(double leftMoney) throws Exception {
		if (leftMoney >= balance) {
			throw new Exception("您的账号余额不足");
		}
		balance -= leftMoney;
		System.out.println("取款成功！");

		// 更新文件account.txt信息
		UserDao ud = UserDao.getUserDao();
		ud.writeUserInfo(cardNo, balance);

		// 记录日志到内存list列表中
		String logStr = Account.currentTime() + "取款 " + leftMoney + "元， 余额："
				+ balance;
		this.logs.add(logStr);

		// 记录日记写到文件tradelogs.txt中去
		LogsDao ld = LogsDao.getLogsDao();
		ld.writeLogs(cardNo, Account.currentTime(), 0.0, leftMoney, balance);

		return true;
	}

	// 存款金额。
	public synchronized boolean depositMoney(double addMoney) throws Exception {
		balance += addMoney;
		System.out.println("存款成功！");

		// 更新文件account.txt信息
		UserDao ud = UserDao.getUserDao();
		ud.writeUserInfo(cardNo, balance);

		// 记录日志到内存list列表中
		String logStr = Account.currentTime() + "取款 " + addMoney + "元， 余额："
				+ balance;
		this.logs.add(logStr);

		// 记录日记写到文件tradelogs.txt中去
		LogsDao ld = LogsDao.getLogsDao();
		ld.writeLogs(cardNo, Account.currentTime(), addMoney, 0.0, balance);

		return true;
	}

	/**
	 * 当前系统操作时间
	 */
	public static String currentTime() {
		// String pattern = "yyyy年MM月dd日 HH:ss:mm";//显示时间会有错误！
		Date nowTime = new Date();
		String pattern = "G yyyy年MMMd日E HH时mm分ss秒z";
		SimpleDateFormat SDF = new SimpleDateFormat(pattern);
		String timePattern = SDF.format(nowTime);
		return timePattern;
	}

	/**
	 * 输出取款日志
	 */
	public String showLog() {

		StringBuffer sbf = new StringBuffer();

		for (String str : logs) {
			sbf.append(str);
		}

		return sbf.toString();
	}

	public String getCardNo() {
		return cardNo;
	}

	public double getBalance() {
		return balance;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [cardNo=" + cardNo + ", balance=" + balance + ", logs="
				+ logs + "]";
	}
}
