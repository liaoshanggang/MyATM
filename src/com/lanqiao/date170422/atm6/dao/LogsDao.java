package com.lanqiao.date170422.atm6.dao;
/**
 * @Description: 
 * @author forward
 * @date 2017��4��22�� ����6:13:07
 * @version V2.0
 */
public interface LogsDao {

	/**
	 * @Description:��ʾָ���û����׵���Ϣ,����鵽��¼������ָ���û����е���Ϣ
	 */
	public StringBuffer getTradeLogById(String inputId);
	
	/**
	 * @Description:��ʾ�����û������н��׼�¼��Ϣ
	 */
	public void showTradeLogs();
	
	/**
	 * @Description:���ļ��л�ȡ�û����˺���Ϣ���浽list������ȥ��
	 */
	public void readLogs();
	
	/**
	 * @Description: ����ָ�������ı��Ӧ�˺ŵ����,���д���¼��ȷ������true�����������
	 */
	public boolean writeLogs(String inputId, String date, double inMoney,
			double outMoney, double balance);
}
