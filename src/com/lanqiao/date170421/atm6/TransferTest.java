package com.lanqiao.date170421.atm6;

public class TransferTest {
	//����ģ���˻���
		public static final int ACCOUNTS = 3;
		
		public static final double INIT_BALANCE = 1000;
		
		public static void main(String[] args) {
			
			ManagerTest m = new ManagerTest(ACCOUNTS,INIT_BALANCE);
			
			//�ʺ����Ƚ�С��ʱ�򣬲��������
			for (int i = 0; i < ACCOUNTS; i++) {
				TransferThread tt = new TransferThread(m,m.account[i],INIT_BALANCE);
				tt.start();
			}
			
		}
}
