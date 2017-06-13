package com.lanqiao.date170421.atm6;

public class AccountThread implements Runnable {
	Account a = null;
	Account b = null;
	double money = 0;
	int count = 0;

	public AccountThread(Account a, Account b, double money) {
		this.a = a;
		this.b = b;
		this.money = money;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				try {
					a.withdraw(money);
					b.depositMoney(money);
					System.out.println("a成功转" + money + "元给b");
					count++;
					System.out.println("成功" + count + "次");
				} catch (Exception e) {
					System.out.println(e.getMessage());
					//Thread.interrupted();
					break;
				}

			}
		}

	}

}
