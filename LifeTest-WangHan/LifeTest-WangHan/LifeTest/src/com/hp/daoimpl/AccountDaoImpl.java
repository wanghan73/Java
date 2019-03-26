package com.hp.daoimpl;

import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Scanner;

import com.hp.dao.AccountDao;
import com.hp.dao.UsersDao;
import com.hp.po.Account;
import com.hp.util.DBUtil;

public class AccountDaoImpl implements AccountDao {
	private static Connection connection = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	Account currentAcc = null;
	/**
	 * 功能：查找账户余额
	 */
	@Override
	public double findBalance(int userId) {
		// TODO Auto-generated method stub
		double Balance = 0;
		String sql = "select account_money from account where userId =? ";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Balance = resultSet.getDouble(1); // 获取ATM机里的余额
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return Balance;

	}
	/**
	 * 功能：将钱存入账户，并更新账户余额
	 *     更新日志
	 */
	@Override
	public void saveMoney(double money, int userId) {
		// TODO Auto-generated method stub
		String sql = "update account set account_money =account_money + ? where userId = ?";
		String sql1 = "insert into account_log(accountId,transfer_account,type,log_time,money)  values(?,?,?,now(),?)";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, money);
			preparedStatement.setInt(2, userId);
			int r = preparedStatement.executeUpdate();
			if (r > 0) {
				System.out.println("您的帐户成功存入" + money);
			}
			DBUtil.releaseDB(resultSet, preparedStatement, null);

			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setInt(1, showAccountIdByUserId(userId));
			preparedStatement1.setInt(2, showAccountIdByUserId(userId));
			preparedStatement1.setString(3, "转入");
			preparedStatement1.setDouble(4, money);
			int r1 = preparedStatement1.executeUpdate();
			if (r1 > 0) {
				System.out.println("日志更新成功");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 功能：根据userId查找账户信息
	 */
	@Override
	public void showInfoByid(int userId) {
		// TODO Auto-generated method stub
		String sql = "select accountId,account_money,userId from account where userId =? ";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				currentAcc = new Account();
				currentAcc.setAccountId(resultSet.getInt(1));
				currentAcc.setAccount_money(resultSet.getDouble(2));
				currentAcc.setUserId(resultSet.getInt(3));
				System.out.println("Account [accountId =" + currentAcc.getAccountId() + ",  account_money="
						+ currentAcc.getAccount_money() + " , userId=" + currentAcc.getUserId());
			}
		} catch (Exception e) {

		} finally {

		}
	}
	/**
	 * 功能：判断账户是否存在
	 */
	@Override
	public boolean accountExist(int otherAccount) {
		// TODO Auto-generated method stub
		boolean flag = false;
		String sql = "select * from account where accountId = ?";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, otherAccount);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {

		}
		return flag;
	}
	/**
	 * 功能：判断
	 */
	@Override
	public boolean compareMoney(double money, int userId) {
		// TODO Auto-generated method stub
		if (findBalance(userId) > money) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 功能：查找账户
	 */
	@Override
	public int showAccountIdByUserId(int userId) {
		// TODO Auto-generated method stub
		int accountId = 0;
		String sql = "select accountId from account where userId =? ";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				accountId = resultSet.getInt(1); // 获取accountId
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return accountId;
	}
	/**
	 * 功能：转账
	 */
	@Override
	public void transferMoney(Double money, int userId, int otherAccount) {
		// TODO Auto-generated method stub
		String sql = "update account set account_money =account_money + ? where accountId = ?";
		try {
			connection = DBUtil.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, money);
			preparedStatement.setInt(2, otherAccount);
			int r = preparedStatement.executeUpdate();
			if (r > 0) {
				System.out.println(otherAccount + "的帐户成功转入" + money);
			}
			DBUtil.releaseDB(resultSet, preparedStatement, null);

			String sql1 = "update account set account_money =account_money - ? where userId = ?";
			PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
			preparedStatement1.setDouble(1, money);
			preparedStatement1.setInt(2, userId);
			int r1 = preparedStatement1.executeUpdate();
			if (r1 > 0) {
				System.out.println(showAccountIdByUserId(userId) + "的帐户成功转出" + money);
			}
			DBUtil.releaseDB(resultSet, preparedStatement, null);

			String sql2 = "insert into account_log(accountId,transfer_account,type,log_time,money)  values(?,?,?,now(),?)";
			PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
			preparedStatement2.setInt(1, showAccountIdByUserId(userId));
			preparedStatement2.setInt(2, otherAccount);
			preparedStatement2.setString(3, "转出");
			preparedStatement2.setDouble(4, money);
			int r3 = preparedStatement2.executeUpdate();
			/*
			 * if (r3 > 0) { System.out.println("日志更新成功"); }
			 */
			DBUtil.releaseDB(resultSet, preparedStatement, null);

			String sql3 = "insert into account_log(accountId,transfer_account,type,log_time,money)  values(?,?,?,now(),?)";
			PreparedStatement preparedStatement3 = connection.prepareStatement(sql3);
			preparedStatement3.setInt(1, otherAccount);
			preparedStatement3.setInt(2, showAccountIdByUserId(userId));
			preparedStatement3.setString(3, "转入");
			preparedStatement3.setDouble(4, money);
			int r4 = preparedStatement3.executeUpdate();
			DBUtil.releaseDB(resultSet, preparedStatement, null);
			/*
			 * if (r4 > 0) { System.out.println("日志更新成功"); }
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * 功能：统计转入金额
	 */
	@Override
	public double findinall(int userId) {
		// TODO Auto-generated method stub
		double income = 0;
		String sql ="select sum(money) as income from account_log where accountId = ? and type = ? and date_format(log_time,'%Y-%m')=date_format(?,'%Y-%m')";	
		try {
			connection = DBUtil.getConnection();
			PreparedStatement preparedStatement11 = connection.prepareStatement(sql);
			preparedStatement11.setInt(1, showAccountIdByUserId(userId));
			preparedStatement11.setString(2, "转入");
			preparedStatement11.setTimestamp(3, new Timestamp(new Date().getTime()));
			resultSet=preparedStatement11.executeQuery();
			while (resultSet.next()) {
				income=resultSet.getDouble("income");  
			}
         
         	} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		
		return income;
	}
	/**
	 * 功能：统计转出金额
	 */
	@Override
	public double findoutall(int userId) {
		// TODO Auto-generated method stub
		double outcome = 0;
		String sql ="select sum(money) as outcome from account_log where accountId = ? and type = ? and date_format(log_time,'%Y-%m')=date_format(?,'%Y-%m')";	
		try {
			connection = DBUtil.getConnection();
			PreparedStatement preparedStatement111 = connection.prepareStatement(sql);
			preparedStatement111.setInt(1, showAccountIdByUserId(userId));
			preparedStatement111.setString(2, "转出");
			preparedStatement111.setTimestamp(3, new Timestamp(new Date().getTime()));
			resultSet=preparedStatement111.executeQuery();
			while (resultSet.next()) {
				outcome=resultSet.getDouble("outcome");
			}
         
         	} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		
		return outcome;
	}
}
