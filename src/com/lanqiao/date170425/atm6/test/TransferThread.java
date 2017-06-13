package com.lanqiao.date170425.atm6.test;

import java.util.List;

import com.lanqiao.date170425.atm6.Manager;

public class TransferThread implements Runnable{
	Manager m = null;
	List<String> list = null;
	public TransferThread() {
		this.m = new Manager();
		this.list = m.getAllUser();
	}
	
	@Override
	public void run() {
		
	}

}
