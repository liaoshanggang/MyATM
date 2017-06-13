package com.lanqiao.date20170425.synchbank;

/*
 * 1.100账户   每个账户1000  A 转22到  B  total = 100000
 * 2.100账户   每个账户1000  A 转22到  B  total = 100000
 * N
 */
public class UnSynchBankTest {

	//创建模拟账户数
	public static final int ACCOUNTS = 3;
	
	public static final double INIT_BALANCE = 1000;
	
	public static void main(String[] args) {
		
		Bank bank = new Bank(ACCOUNTS,INIT_BALANCE);
		
		//帐号数比较小的时候，不会出问题
		for (int i = 0; i < ACCOUNTS; i++) {
			TransferThread tt = new TransferThread(bank,i,INIT_BALANCE);
			tt.start();
		}
		
	}

}
