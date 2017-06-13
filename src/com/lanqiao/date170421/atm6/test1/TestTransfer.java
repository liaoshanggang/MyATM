package com.lanqiao.date170421.atm6.test1;

import com.lanqiao.date170421.atm6.Manager;


public class TestTransfer {
	public static void main(String[] args) {
		Manager m = new Manager();//同一个系统
		
		//建立3个用户,测试相互间的转账同步功能，总金额不变。
		for (int i = 0; i < 4; i++) {
			new Thread(new TransferThread(m,100)).start();;
		}
	}
}
