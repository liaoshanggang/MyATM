package com.lanqiao.date170422.atm6.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author forward
 * @date 2017��4��22�� ����6:13:27
 * @version V2.0
 */
public class LogsDaoImpl implements LogsDao{
	// ��ȡ���ռ��ļ�·��
	public static final String PATH_TRADE_LOGS = "D:\\WorkSpace\\MyATM\\src\\com\\lanqiao\\date170422\\atm6\\tradelogs.txt";

	// ��Ŵ��ļ���ȥ�����ļ�¼
	List<String> list = null;

	//private static LogsDao logsDao = null;

	{
		list = new ArrayList<String>();
	}

	/*private LogsDaoImpl() {
	}*/

	/**
	 * @Description:��ȡ��������
	 * @param ��
	 * @return LogsDao��������
	 */
	/*public static LogsDao getLogsDao() {
		if (logsDao == null) {
			logsDao = new LogsDaoImpl();
		}
		return logsDao;
	}*/

	/**
	 * @Description:��ʾָ���û����׵���Ϣ
	 * @param inputId
	 * @return ����鵽��¼������ָ���û����е���Ϣ
	 * 
	 */
	public StringBuffer getTradeLogById(String inputId) {
		System.out.println("=====��ʾ��ǰ�˺�" + inputId + "���׵����н�����Ϣ=====");
		StringBuffer str = new StringBuffer();
		for (String string : list) {
			if (string.contains(inputId)) {
				str.append(string + "\n");
			}
		}
		return str;
	}

	/**
	 * @Description:��ʾ�����û������н��׼�¼��Ϣ
	 */
	public void showTradeLogs() {
		for (String string : list) {
			System.out.println(string);
		}
	}

	/**
	 * @Description:���ļ��л�ȡ�û����˺���Ϣ���浽list������ȥ��
	 * @param
	 * @return
	 */
	public void readLogs() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				PATH_TRADE_LOGS));) {

			/*
			 * �ٴΰ��ļ������ݶ�������list�����У�����֮ǰ�ڴ��¼���ϴζ�ȡ�����ģ�
			 * �����ٴζ�ʱҪ��֮ǰ���ڴ��еļ�¼ɾ������Ҫ��֤��ʾ����Ψһ,list��map���ϲ�һ��
			 */
			list.clear();

			String s = "";
			int count = 0;
			while ((s = br.readLine()) != null) {
				s = s.replace("|", ",");
				String[] v = s.split(",");
				count++;
				// ������ͷ��������list����
				if (count == 1) {
					continue;
				}

				s = "�˺ţ�" + v[0] + " " + v[1] + " ���" + v[2] + "Ԫ��ȡ��" + v[3]
						+ "Ԫ�����" + v[4] + "Ԫ";
				// System.out.println(s);
				list.add(s);
			}
			System.out.println("hello" + "��ȡ��¼�ɹ���");
			// System.out.println("�ļ���ȡ�ɹ����浽list�ļ�¼");
		} catch (IOException e) {
			System.out.println("readLogs����");
			e.printStackTrace();
		}
	}

	/**
	 * @Description: ����ָ�������ı��Ӧ�˺ŵ����,����û�����ݿ⣬
	 *               ��ֻ�������°���ȡ���û���Ϣд��ȥֻ�ı��Ӧ�û����˺����,û���ݿ����ϵͳ���ò�����
	 * @param inputId
	 *            ��balance
	 * @return ���д���¼��ȷ������true������ҵ�
	 */
	public boolean writeLogs(String inputId, String date, double inMoney,
			double outMoney, double balance) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				PATH_TRADE_LOGS, true));) {
			String str = inputId + "|" + date + "|" + inMoney + "| " + outMoney
					+ "|" + balance + "\n";
			bw.write(str);
			// "|\t   "
		} catch (IOException e) {
			System.out.println("writeLogs����");
			e.printStackTrace();
		}
		return true;
	}
}
