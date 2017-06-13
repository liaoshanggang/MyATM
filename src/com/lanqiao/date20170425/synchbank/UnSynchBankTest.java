package com.lanqiao.date20170425.synchbank;

/*
 * 1.100�˻�   ÿ���˻�1000  A ת22��  B  total = 100000
 * 2.100�˻�   ÿ���˻�1000  A ת22��  B  total = 100000
 * N
 */
public class UnSynchBankTest {

	//����ģ���˻���
	public static final int ACCOUNTS = 3;
	
	public static final double INIT_BALANCE = 1000;
	
	public static void main(String[] args) {
		
		Bank bank = new Bank(ACCOUNTS,INIT_BALANCE);
		
		//�ʺ����Ƚ�С��ʱ�򣬲��������
		for (int i = 0; i < ACCOUNTS; i++) {
			TransferThread tt = new TransferThread(bank,i,INIT_BALANCE);
			tt.start();
		}
		
	}

}
