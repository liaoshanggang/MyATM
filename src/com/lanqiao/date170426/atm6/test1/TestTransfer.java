package com.lanqiao.date170426.atm6.test1;

import com.lanqiao.date170426.atm6.Manager;

public class TestTransfer {
	public static void main(String[] args) {
		Manager m = new Manager();

		// �����໥���ת��ͬ�����ܣ��ܽ��䡣
		for (int i = 0; i < 3; i++) {
			new Thread(new TransferThread(m, 100)).start();
		}
	}
}
