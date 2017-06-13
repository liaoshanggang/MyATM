package com.lanqiao.date20170425.synchbank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank {

	private final double account[];

	private Object lock = new Object();

	// ������Ķ�д��
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();
	private Lock readLock = wrlock.readLock();
	private Lock writerLock = wrlock.writeLock();

	/*
	 * ���� n �����Ϊ initBanlance ���˻�
	 */
	public Bank(int n, double initBalance) {

		account = new double[n];

		for (int i = 0; i < account.length; i++) {
			account[i] = initBalance;
		}

	}

	/*
	 * ת�˲���
	 */
	public synchronized void transfer(int from, int to, int amount) {

		if (account[from] < amount) {
			return; // ����˻��е���� < ת�˽�� ��ôɶҲ����
		}
		synchronized (readLock) {
			
			account[from] -= amount;
			System.out.println(Thread.currentThread() + ":ת�˽��" + amount + ",�� �ʺ�" + from + " ���ʺ� " + to);
		}
		synchronized (writerLock) {
			
			account[to] += amount;
			System.out.println("�����ʺ��ܽ��:" + getTotalBalance());
		}

	}

	/*
	 * �����˻��ܽ��
	 */
	private Object getTotalBalance() {
		
		double total = 0.0;
		for (double d : account) {
			total += d;
		}
		return total;
		
	}

	/*
	 * �˻�����
	 */
	public int size() {
		return account.length;
	}

}
