package com.lanqiao.date170421.atm6.test1;

import com.lanqiao.date170421.atm6.Manager;


public class TestTransfer {
	public static void main(String[] args) {
		Manager m = new Manager();//ͬһ��ϵͳ
		
		//����3���û�,�����໥���ת��ͬ�����ܣ��ܽ��䡣
		for (int i = 0; i < 4; i++) {
			new Thread(new TransferThread(m,100)).start();;
		}
	}
}
