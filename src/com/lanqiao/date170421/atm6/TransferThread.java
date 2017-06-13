package com.lanqiao.date170421.atm6;

public class TransferThread extends Thread {

	private ManagerTest m;
	
	private Account from;
	
	private double maxAmount;
	
	private final int DELAY = 10; //延迟
	
	
	public TransferThread(ManagerTest m, Account from, double amount) {
		super();
		this.m = m;
		this.from = from;
		this.maxAmount = amount;
	}

	

	@Override
	public void run() {
		
		try {
			while(true){
					//随机的存款账户
					int toAccount = (int)(m.size() * Math.random());
					//随机转账金额 ，默认 1000*随机数
					int amount = (int)(maxAmount * Math.random());
					m.tranMoney(from, m.account[toAccount], amount);
					Thread.sleep((int)(DELAY * Math.random()));
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	
}