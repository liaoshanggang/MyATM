package com.lanqiao.date170426.atm6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


/**
 * @Description: �����������������ļ�,����Ϊ����ģʽ
 * @author forwardLiao
 * @date 2017��4��9�� ����9:31:29
 * @version V1.0
 */
public class UserDao {
	private static UserDao userDao = null;
	public static Map<String, User> map = null;

	// �û���Ϣ�ļ�·��
	public static final String PATH_USER_INFO = "D:\\WorkSpace\\MyATM\\src\\com\\lanqiao\\date170426\\atm6\\account.txt";

	{
		map = new HashMap<String, User>();
	}

	private UserDao() {
		//readUserInfo();
	}

	/**
	 * @Description:�ṩ���о�̬������ȡUserDao��������
	 * @return UserDao���������
	 */
	public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
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
	
	public Map<String, User> getMap(){
		readUserInfo();
		return map;
	}
	/**
	 * @Description:��ȡָ���˺����
	 * @param inputId
	 * @return double
	 */
	public double getBalance(String inputId) {
		String id = this.getId(inputId);
		//System.out.println("hello" + id);
		return map.get(id).getAccount().getBalance();
	}

	/**
	 * @Description:��ȡָ���˺�����
	 * @param inputId
	 * @return String
	 */
	public String getName(String inputId) {
		String id = this.getId(inputId);
		return map.get(id).getName();
	}

	/**
	 * @Description:��ȡָ���˺�����
	 * @param inputId
	 * @return String
	 */
	public String getPwd(String inputId) {
		String id = this.getId(inputId);
		return String.valueOf(map.get(id).getPwd());
	}

	/**
	 * @Description:��ȡָ���˺��û�
	 * @param inputId
	 * @return User
	 */
	public User getUser(String inputId) {
		String id = this.getId(inputId);
		return map.get(id);
	}
	
	/**
	 * @Description: ͨ��ָ��id��ȡid
	 * @param inputId
	 * @return String
	 */
	public String getId(String inputId) {
		Iterator<Entry<String, User>> it = map.entrySet().iterator();
		String id = "";
		while (it.hasNext()) {
			Entry<String, User> entry = it.next();
			id = entry.getKey();
			if (id.equals(inputId)) {
				break;
			}
		}
		return id;
	}
	
	/**
	 * @Description: ͨ��ָ��id��ȡ�Ƿ�����˺�id
	 * @param inputId
	 * @return String
	 */
	public boolean hasId(String inputId) {
		boolean flag = false;
		Iterator<Entry<String, User>> it = map.entrySet().iterator();
		if(inputId.equals("")){
			return flag;
		}
		String id = "";
		while (it.hasNext()) {
			Entry<String, User> entry = it.next();
			id = entry.getKey();
			if (id.equals(inputId)) {
				return true;//�鵽������
			}
		}
		return flag;//��ʾ��ѯ�궼û����
	}

	/**
	 * @Description:���ļ��л�ȡ�û����˺���Ϣ���浽map������ȥ��
	 * @param
	 * @return
	 */
	public void readUserInfo() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				PATH_USER_INFO));) {
			String s = "";
			int count = 0;
			System.out.println("============���ǴӸ����ļ���ȡ�����û���Ϣ============");
			while ((s = br.readLine()) != null) {
				s = s.replace("|", ",");
				String[] v = s.split(",");
				String id = v[0];
				String name = v[1];
				count++;
				// ������ͷ��������map����
				if (count == 1) {
					String pwd = v[2];
					String balance = v[3];
					String a = id.concat(name).concat(pwd).concat(balance);
					a.replace(" ", "");
					System.out.println(a);
					continue;
				}

				int pwd = Integer.parseInt(v[2]);
				double balance = Double.parseDouble(v[3]);
				User user = new User(name, new Account(id, balance), pwd);
				System.out.println(id + "\t\t" + name + "\t" + pwd + "\t"
						+ balance);
				map.put(id, user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description: ����ָ�������ı��Ӧ�˺ŵ����,����û�����ݿ⣬
	 *               ��ֻ�������°���ȡ���û���Ϣд��ȥֻ�ı��˺����,û���ݿ��ϵͳ����֤��Ϣ������ȷ��
	 * @param inputId,balance
	 * @return boolean
	 */
	public boolean writeUserInfo(String inputId, double balance) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				PATH_USER_INFO));) {

			Iterator<Entry<String, User>> it = map.entrySet().iterator();
			String head = "�˺�\t\t\t\t|�û�|\t����|\t���\n";
			bw.write(head);
			while (it.hasNext()) {
				Entry<String, User> entry = it.next();
				User u = entry.getValue();
				String id = entry.getKey();
				String name = u.getName();
				int pwd = u.getPwd();
				double ban2 = u.getAccount().getBalance();
				if (inputId.equals(id)) {
					double ban1 = balance;// �͸ı�������ݣ�����������
					String str = id + "|" + name + "|" + pwd + "|" + ban1
							+ "\n";
					bw.write(str);
					continue;
				}
				String str = id + "|" + name + "|" + pwd + "|" + ban2 + "\n";
				bw.write(str);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}
