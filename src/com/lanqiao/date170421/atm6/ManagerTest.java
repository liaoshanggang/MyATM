package com.lanqiao.date170421.atm6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ManagerTest {

	public Account account[];

	// ������Ķ�д��
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();
	private Lock readLock = wrlock.readLock();
	private Lock writerLock = wrlock.writeLock();

	/*
	 * ���� n �����Ϊ initBanlance ���˻�
	 */
	public ManagerTest(int n, double initBalance) {

		account = new Account[n];

		for (int i = 0; i < account.length; i++) {
			account[i].setBalance(initBalance);
		}

	}

	//���Լ��˻��۳��󼴿�ת�������˻������˻�������һ���˻���
		public synchronized void tranMoney(Account a,Account b,int tranMoney) {
			
			if(a.getBalance()<tranMoney){
				return;
			}
			//�����߳�
			//new Thread(new AccountThread(a, b, tranMoney)).start();
			//System.out.println("hello");
			
			synchronized (readLock) {
				try {
					a.withdraw(tranMoney);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println(Thread.currentThread() + ":ת�˽��" + tranMoney + ",�� �ʺ�" + a.getBalance() + " ���ʺ� " + b.getBalance());
			}
			synchronized (writerLock) {
				try {
					b.depositMoney(tranMoney);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("�����ʺ��ܽ��:" + getTotalBalance());
			}
		}

		/*
		 * �˻�����
		 */
		public int size() {
			return account.length;
		}
		
		
		/*
		 * �����˻��ܽ��
		 */
		public double getTotalBalance() {
			UserDao u = UserDao.getUserDao();
			double total = 0;
			total += u.getBalance("1111222233334444");
			total += u.getBalance("1111222233335555");
			total += u.getBalance("1111222233336666");
			return total;
		}
		
		
		public List<String> getAllUser(){
			UserDao u = UserDao.getUserDao();
			Map<String, User> map = u.getMap();
			Set<String> set = map.keySet();
			//���ļ���ȡ�����Ȼ��浽list�У����������˻���Ӧ��������������ݡ�
			List<String> list = new ArrayList<String>();
			for (String string : set) {
				list.add(string);
			}
			return list;
		}
}
