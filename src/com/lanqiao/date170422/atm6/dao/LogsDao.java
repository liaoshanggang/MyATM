package com.lanqiao.date170422.atm6.dao;
/**
 * @Description: 
 * @author forward
 * @date 2017年4月22日 下午6:13:07
 * @version V2.0
 */
public interface LogsDao {

	/**
	 * @Description:显示指定用户交易的信息,如果查到记录，返回指定用户所有的信息
	 */
	public StringBuffer getTradeLogById(String inputId);
	
	/**
	 * @Description:显示所有用户的所有交易记录信息
	 */
	public void showTradeLogs();
	
	/**
	 * @Description:从文件中获取用户的账号信息并存到list集合中去。
	 */
	public void readLogs();
	
	/**
	 * @Description: 根据指定参数改变对应账号的余额,如果写入记录正确，返回true，否则出问题
	 */
	public boolean writeLogs(String inputId, String date, double inMoney,
			double outMoney, double balance);
}
