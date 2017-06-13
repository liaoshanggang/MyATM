package com.lanqiao.date170422.atm6.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lanqiao.date170422.atm6.dao.*;

/**
 * @Description: �˺���
 * @author forward
 * @date 2017��4��22�� ����5:41:54
 * @version V2.0
 */
public class Account {

	private String cardNo;// ���п���
	private double balance = 100000.00;// ���
	//private List<String> logs;// ȡ����־

	public Account(String cardNo, double balance) {
		super();
		this.cardNo = cardNo;
		this.balance = balance;
		//logs = new LinkedList<>();
	}

	// �ӵ�ǰ����м�ȥ��
	public boolean withdraw(double leftMoney) {
		if (leftMoney >= balance) {
			//throw new Exception("�����˺�����");
			try {
				throw new Exception("�����˺�����");
			} catch (Exception e) {
				System.out.println("withdraw�����˺��������");
				e.printStackTrace();
			}
		}
		balance -= leftMoney;
		System.out.println("ȡ��ɹ���");

		// �����ļ�account.txt��Ϣ
		//UserDao ud = UserDao.getUserDao();
		UserDao ud = new UserDaoImpl();
		ud.writeUserInfo(cardNo, balance);

		// ��¼��־���ڴ�list�б���
		String logStr = Account.currentTime() + "ȡ�� " + leftMoney + "Ԫ�� ��"
				+ balance;
		//this.logs.add(logStr);

		// ��¼�ռ�д���ļ�tradelogs.txt��ȥ
		LogsDao ld = new LogsDaoImpl();
		ld.writeLogs(cardNo, Account.currentTime(), 0.0, leftMoney, balance);

		return true;
	}

	/**
	 * ��ǰϵͳ����ʱ��
	 */
	public static String currentTime() {
		//String pattern = "yyyy��MM��dd�� HH:ss:mm";//��ʾʱ����д���
		Date nowTime = new Date();
		String pattern = "G yyyy��MMMd��E HHʱmm��ss��z";
		SimpleDateFormat SDF = new SimpleDateFormat(pattern);
		String timePattern = SDF.format(nowTime);
		return timePattern;
	}

	/**
	 * ���ȡ����־
	 */
	/*public String showLog() {

		StringBuffer sbf = new StringBuffer();

		for (String str : logs) {
			sbf.append(str);
		}

		return sbf.toString();
	}*/

	public String getCardNo() {
		return cardNo;
	}

	public double getBalance() {
		return balance;
	}

//	public List<String> getLogs() {
//		return logs;
//	}
//
//	public void setLogs(List<String> logs) {
//		this.logs = logs;
//	}
//
//	@Override
//	public String toString() {
//		return "Account [cardNo=" + cardNo + ", balance=" + balance + ", logs="
//				+ logs + "]";
//	}

}
