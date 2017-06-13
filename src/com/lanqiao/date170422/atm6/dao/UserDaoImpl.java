package com.lanqiao.date170422.atm6.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.lanqiao.date170422.atm6.bean.Account;
import com.lanqiao.date170422.atm6.bean.User;
/**
 * @Description: TODO
 * @author forward
 * @date 2017年4月22日 下午6:14:32
 * @version V2.0
 */
public class UserDaoImpl implements UserDao{
	//private static UserDao userDao = null;
	public static Map<String, User> map = null;

	// 用户信息文件路径
	public static final String PATH_USER_INFO = "D:\\WorkSpace\\MyATM\\src\\com\\lanqiao\\date170422\\atm6\\account.txt";

	{
		map = new HashMap<String, User>();
	}

	/*private UserDaoImpl() {
		// readUserInfo();
	}*/

	/**
	 * @Description:提供共有静态方法获取UserDao单例对象
	 * @return UserDao对象的引用
	 */
	/*public static UserDao getUserDao() {
		if (userDao == null) {
			userDao = new UserDaoImpl();
		}
		return userDao;
	}*/

	/**
	 * @Description:获取指定账号余额
	 * @param inputId
	 * @return double
	 */
	public double getBalance(String inputId) {
		String id = this.getId(inputId);
		System.out.println("hello" + id);
		return map.get(id).getAccount().getBalance();
	}

	/**
	 * @Description:获取指定账号名字
	 * @param inputId
	 * @return String
	 */
	public String getName(String inputId) {
		String id = this.getId(inputId);
		return map.get(id).getName();
	}

	/**
	 * @Description:获取指定账号密码
	 * @param inputId
	 * @return String
	 */
	public String getPwd(String inputId) {
		String id = this.getId(inputId);
		return String.valueOf(map.get(id).getPwd());
	}

	/**
	 * @Description:获取指定账号用户
	 * @param inputId
	 * @return User
	 */
	public User getUser(String inputId) {
		String id = this.getId(inputId);
		return map.get(id);
	}

	/**
	 * @Description: 通过指定id获取是否存在账号id
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
	 * @Description:从文件中获取用户的账号信息并存到map集合中去。
	 * @param
	 * @return
	 */
	public void readUserInfo() {
		try (BufferedReader br = new BufferedReader(new FileReader(
				PATH_USER_INFO));) {
			String s = "";
			int count = 0;
			System.out.println("============这是从更新文件中取出的用户信息============");
			while ((s = br.readLine()) != null) {
				s = s.replace("|", ",");
				String[] v = s.split(",");
				String id = v[0];
				String name = v[1];
				count++;
				// 跳过表头，不存入map集合
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
			System.out.println("readUserInfo错误！");
			e.printStackTrace();
		}
	}

	/**
	 * @Description: 根据指定参数改变对应账号的余额,由于没有数据库，
	 *               现只能再重新把已取的用户信息写进去只改变账号余额,没数据库该系统不保证信息输入正确。
	 * @param inputId
	 *            ,balance
	 * @return boolean
	 */
	public boolean writeUserInfo(String inputId, double balance) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(
				PATH_USER_INFO));) {

			Iterator<Entry<String, User>> it = map.entrySet().iterator();
			String head = "账号\t\t\t\t|用户|\t密码|\t余额\n";
			bw.write(head);
			while (it.hasNext()) {
				Entry<String, User> entry = it.next();
				User u = entry.getValue();
				String id = entry.getKey();
				String name = u.getName();
				int pwd = u.getPwd();
				double ban2 = u.getAccount().getBalance();
				if (inputId.equals(id)) {
					double ban1 = balance;// 就改变这个数据，其他都不变
					String str = id + "|" + name + "|" + pwd + "|" + ban1
							+ "\n";
					bw.write(str);
					continue;
				}
				String str = id + "|" + name + "|" + pwd + "|" + ban2 + "\n";
				bw.write(str);
			}

		} catch (IOException e) {
			System.out.println("writeUserInfo错误！");
			e.printStackTrace();
		}
		return true;
	}
}
