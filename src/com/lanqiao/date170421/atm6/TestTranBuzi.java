package com.lanqiao.date170421.atm6;

public class TestTranBuzi {
	public static void main(String[] args) {
		UserDao u = UserDao.getUserDao();
		Account a = u.getUser("1111222233334444").getAccount();//当前的账户
		Account b = u.getUser("1111222233335555").getAccount();//转到其他账户
		//测试每次转100
		new Thread(new AccountThread(a, b, 100)).start();
	}
}
