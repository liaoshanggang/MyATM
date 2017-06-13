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

	private final int DELAY = 10; // 延迟

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
		
		//测试3个存款账户相互间转账，转账总次数10次，总钱数不变
		try {
			for (int i = 0; i < 10; i++) {

				// 随机的存款账户
				int toAccount = rd.nextInt(3);
				int fromAccount = rd.nextInt(3);

				amount = (double) (maxAmount * (rd.nextInt(3)+1));
				a = u.getUser(list.get(fromAccount)).getAccount();// 当前的账户
				b = u.getUser(list.get(toAccount)).getAccount();// 转到其他账户
				
				Thread.sleep((int) (DELAY * Math.random()));
				
				//开始转账
				m.tranMoney(a, b, amount);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
