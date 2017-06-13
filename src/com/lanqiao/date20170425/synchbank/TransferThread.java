package com.lanqiao.date20170425.synchbank;

public class TransferThread extends Thread {

	private Bank bank;
	
	private int from;
	
	private double maxAmount;
	
	private final int DELAY = 10; //延迟
	
	
	public TransferThread(Bank b, int from, double amount) {
		super();
		this.bank = b;
		this.from = from;
		this.maxAmount = amount;
	}

	

	@Override
	public void run() {
		
		try {
			while(true){
					//随机的存款账户
					int toAccount = (int)(bank.size() * Math.random());
					//随机转账金额 ，默认 1000*随机数
					int amount = (int)(maxAmount * Math.random());
					bank.transfer(from, toAccount, amount);
					Thread.sleep((int)(DELAY * Math.random()));
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
