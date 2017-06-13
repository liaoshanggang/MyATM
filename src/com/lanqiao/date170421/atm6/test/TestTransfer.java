package com.lanqiao.date170421.atm6.test;

public class TestTransfer {
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new TransferThread(100)).start();;
		}
	}
}
