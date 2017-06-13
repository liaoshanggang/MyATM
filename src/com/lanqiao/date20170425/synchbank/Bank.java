package com.lanqiao.date20170425.synchbank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank {

	private final double account[];

	private Object lock = new Object();

	// 可再入的读写锁
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();
	private Lock readLock = wrlock.readLock();
	private Lock writerLock = wrlock.writeLock();

	/*
	 * 创建 n 个余额为 initBanlance 的账户
	 */
	public Bank(int n, double initBalance) {

		account = new double[n];

		for (int i = 0; i < account.length; i++) {
			account[i] = initBalance;
		}

	}

	/*
	 * 转账操作
	 */
	public synchronized void transfer(int from, int to, int amount) {

		if (account[from] < amount) {
			return; // 如果账户中的余额 < 转账金额 那么啥也不做
		}
		synchronized (readLock) {
			
			account[from] -= amount;
			System.out.println(Thread.currentThread() + ":转账金额" + amount + ",从 帐号" + from + " 到帐号 " + to);
		}
		synchronized (writerLock) {
			
			account[to] += amount;
			System.out.println("所有帐号总金额:" + getTotalBalance());
		}

	}

	/*
	 * 所有账户总金额
	 */
	private Object getTotalBalance() {
		
		double total = 0.0;
		for (double d : account) {
			total += d;
		}
		return total;
		
	}

	/*
	 * 账户数量
	 */
	public int size() {
		return account.length;
	}

}
