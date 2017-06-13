package com.lanqiao.date170421.atm6.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.lanqiao.date170421.atm6.Manager;
import com.lanqiao.date170421.atm6.*;

public class TransferThread implements Runnable {

	private Manager m;

	private double maxAmount;

	private final int DELAY = 10; // �ӳ�

	public TransferThread(double amount) {
		super();
		this.m = new Manager();
		this.maxAmount = amount;
	}

	@Override
	public void run() {
		testTran(m.getAllUser());
	}

	private void testTran(List<String> list) {
		UserDao u = UserDao.getUserDao();
		Random rd = new Random();

		Account a = null;
		Account b = null;
		double amount = 0;
		
		//����3������˻��໥��ת�ˣ�ת���ܴ���10�Σ���Ǯ������
		try {
			for (int i = 0; i < 10; i++) {

				// ����Ĵ���˻�
				int toAccount = rd.nextInt(3);
				int fromAccount = rd.nextInt(3);

				amount = (double) (maxAmount * (rd.nextInt(3)+1));
				a = u.getUser(list.get(fromAccount)).getAccount();// ��ǰ���˻�
				b = u.getUser(list.get(toAccount)).getAccount();// ת�������˻�
				
				Thread.sleep((int) (DELAY * Math.random()));
				
				//��ʼת��
				m.tranMoney(a, b, amount);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
