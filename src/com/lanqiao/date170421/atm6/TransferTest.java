package com.lanqiao.date170421.atm6;

public class TransferTest {
	//创建模拟账户数
		public static final int ACCOUNTS = 3;
		
		public static final double INIT_BALANCE = 1000;
		
		public static void main(String[] args) {
			
			ManagerTest m = new ManagerTest(ACCOUNTS,INIT_BALANCE);
			
			//帐号数比较小的时候，不会出问题
			for (int i = 0; i < ACCOUNTS; i++) {
				TransferThread tt = new TransferThread(m,m.account[i],INIT_BALANCE);
				tt.start();
			}
			
		}
}
