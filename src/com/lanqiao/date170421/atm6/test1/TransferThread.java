package com.lanqiao.date170421.atm6.test1;

import java.util.List;
import java.util.Random;

import com.lanqiao.date170421.atm6.*;

public class TransferThread implements Runnable {

	private Manager m;
	List<String> list;

	private double maxAmount;

	private final int DELAY = 10; // �ӳ�

	public TransferThread(Manager m,double amount) {
		super();
		this.m = m;
		this.maxAmount = amount;
		list = m.getAllUser();
	}

	@Override
	public void run() {
		testTran();
	}

	private void testTran() {
		UserDao u = UserDao.getUserDao();
		Random rd = new Random();

		Account a = null;
		Account b = null;
		double amount = 0;
		
		//����3������˻��໥��ת�ˣ�ת���ܴ���5�Σ���Ǯ������
		try {
			for (int i = 0; i < 5; i++) {

				// ����Ĵ���˻�
				int toAccount = rd.nextInt(3);
				int fromAccount = rd.nextInt(3);

				amount = (double) (maxAmount * (rd.nextInt(3)+1));
				a = u.getUser(list.get(fromAccount)).getAccount();// ��ǰ���˻�
				b = u.getUser(list.get(toAccount)).getAccount();// ת�������˻�
				
				Thread.sleep((int) (DELAY * Math.random()));
				
				//��ʼת��
				m.tranMoney(a, b, amount);
				
				//תһ�δ��ļ�����ʾ��������
				//u.readUserInfo();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
