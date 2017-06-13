package com.lanqiao.date170425.atm6;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: �˺���
 * @author forward
 * @date 2017��4��11�� ����12:29:52
 * @version V2.0
 */
public class Account {
	private String cardNo;// ���п���
	private double balance = 100000.00;// ���
	private List<String> logs;// ȡ����־

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

	// �ӵ�ǰ����м�ȥ��
	public synchronized boolean withdraw(double leftMoney) throws Exception {
		if (leftMoney >= balance) {
			throw new Exception("�����˺�����");
		}
		balance -= leftMoney;
		System.out.println("ȡ��ɹ���");

		// �����ļ�account.txt��Ϣ
		UserDao ud = UserDao.getUserDao();
		ud.writeUserInfo(cardNo, balance);

		// ��¼��־���ڴ�list�б���
		String logStr = Account.currentTime() + "ȡ�� " + leftMoney + "Ԫ�� ��"
				+ balance;
		this.logs.add(logStr);

		// ��¼�ռ�д���ļ�tradelogs.txt��ȥ
		LogsDao ld = LogsDao.getLogsDao();
		ld.writeLogs(cardNo, Account.currentTime(), 0.0, leftMoney, balance);

		return true;
	}

	// ����
	public synchronized boolean depositMoney(double addMoney) throws Exception {
		balance += addMoney;
		System.out.println("���ɹ���");

		// �����ļ�account.txt��Ϣ
		UserDao ud = UserDao.getUserDao();
		ud.writeUserInfo(cardNo, balance);

		// ��¼��־���ڴ�list�б���
		String logStr = Account.currentTime() + "ȡ�� " + addMoney + "Ԫ�� ��"
				+ balance;
		this.logs.add(logStr);

		// ��¼�ռ�д���ļ�tradelogs.txt��ȥ
		LogsDao ld = LogsDao.getLogsDao();
		ld.writeLogs(cardNo, Account.currentTime(), addMoney, 0.0, balance);

		return true;
	}

	/**
	 * ��ǰϵͳ����ʱ��
	 */
	public static String currentTime() {
		// String pattern = "yyyy��MM��dd�� HH:ss:mm";//��ʾʱ����д���
		Date nowTime = new Date();
		String pattern = "G yyyy��MMMd��E HHʱmm��ss��z";
		SimpleDateFormat SDF = new SimpleDateFormat(pattern);
		String timePattern = SDF.format(nowTime);
		return timePattern;
	}

	/**
	 * ���ȡ����־
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