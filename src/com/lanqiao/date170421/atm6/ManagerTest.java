package com.lanqiao.date170421.atm6;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ManagerTest {

	public Account account[];

	// 可再入的读写锁
	private ReentrantReadWriteLock wrlock = new ReentrantReadWriteLock();
	private Lock readLock = wrlock.readLock();
	private Lock writerLock = wrlock.writeLock();

	/*
	 * 创建 n 个余额为 initBanlance 的账户
	 */
	public ManagerTest(int n, double initBalance) {

		account = new Account[n];

		for (int i = 0; i < account.length; i++) {
			account[i].setBalance(initBalance);
		}

	}

	//从自己账户扣除后即可转到其他账户。该账户减，另一个账户增
		public synchronized void tranMoney(Account a,Account b,int tranMoney) {
			
			if(a.getBalance()<tranMoney){
				return;
			}
			//开启线程
			//new Thread(new AccountThread(a, b, tranMoney)).start();
			//System.out.println("hello");
			
			synchronized (readLock) {
				try {
					a.withdraw(tranMoney);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println(Thread.currentThread() + ":转账金额" + tranMoney + ",从 帐号" + a.getBalance() + " 到帐号 " + b.getBalance());
			}
			synchronized (writerLock) {
				try {
					b.depositMoney(tranMoney);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				System.out.println("所有帐号总金额:" + getTotalBalance());
			}
		}

		/*
		 * 账户数量
		 */
		public int size() {
			return account.length;
		}
		
		
		/*
		 * 所有账户总金额
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
			//从文件中取款出来然后存到list中，方便设置账户对应的随机数测试数据。
			List<String> list = new ArrayList<String>();
			for (String string : set) {
				list.add(string);
			}
			return list;
		}
}
