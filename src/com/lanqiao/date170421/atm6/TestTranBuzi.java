package com.lanqiao.date170421.atm6;

public class TestTranBuzi {
	public static void main(String[] args) {
		UserDao u = UserDao.getUserDao();
		Account a = u.getUser("1111222233334444").getAccount();//��ǰ���˻�
		Account b = u.getUser("1111222233335555").getAccount();//ת�������˻�
		//����ÿ��ת100
		new Thread(new AccountThread(a, b, 100)).start();
	}
}
