package com.lanqiao.date170422.atm6.dao;

import com.lanqiao.date170422.atm6.bean.User;
/**
 * @Description: TODO
 * @author forward
 * @date 2017��4��22�� ����6:14:27
 * @version V2.0
 */
public interface UserDao {
	
	/**
	 * @Description:��ȡָ���˺����
	 */
	public double getBalance(String inputId);

	/**
	 * @Description:��ȡָ���˺�����
	 */
	public String getName(String inputId);

	/**
	 * @Description:��ȡָ���˺�����
	 */
	public String getPwd(String inputId);

	/**
	 * @Description:��ȡָ���˺��û�
	 */
	public User getUser(String inputId);

	/**
	 * @Description: ͨ��ָ��id��ȡ�Ƿ�����˺�id
	 */
	public String getId(String inputId);

	/**
	 * @Description:���ļ��л�ȡ�û����˺���Ϣ���浽map������ȥ��
	 */
	public void readUserInfo();

	/**
	 * @Description: ����ָ�������ı��Ӧ�˺ŵ����
	 */
	public boolean writeUserInfo(String inputId, double balance);
}
