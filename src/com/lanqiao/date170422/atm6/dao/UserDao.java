package com.lanqiao.date170422.atm6.dao;

import com.lanqiao.date170422.atm6.bean.User;
/**
 * @Description: TODO
 * @author forward
 * @date 2017年4月22日 下午6:14:27
 * @version V2.0
 */
public interface UserDao {
	
	/**
	 * @Description:获取指定账号余额
	 */
	public double getBalance(String inputId);

	/**
	 * @Description:获取指定账号名字
	 */
	public String getName(String inputId);

	/**
	 * @Description:获取指定账号密码
	 */
	public String getPwd(String inputId);

	/**
	 * @Description:获取指定账号用户
	 */
	public User getUser(String inputId);

	/**
	 * @Description: 通过指定id获取是否存在账号id
	 */
	public String getId(String inputId);

	/**
	 * @Description:从文件中获取用户的账号信息并存到map集合中去。
	 */
	public void readUserInfo();

	/**
	 * @Description: 根据指定参数改变对应账号的余额
	 */
	public boolean writeUserInfo(String inputId, double balance);
}
