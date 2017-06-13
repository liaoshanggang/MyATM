package com.lanqiao.date20170425.synchbank;

public class TransferThread extends Thread {

	private Bank bank;
	
	private int from;
	
	private double maxAmount;
	
	private final int DELAY = 10; //�ӳ�
	
	
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
					//����Ĵ���˻�
					int toAccount = (int)(bank.size() * Math.random());
					//���ת�˽�� ��Ĭ�� 1000*�����
					int amount = (int)(maxAmount * Math.random());
					bank.transfer(from, toAccount, amount);
					Thread.sleep((int)(DELAY * Math.random()));
				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	
	
}
