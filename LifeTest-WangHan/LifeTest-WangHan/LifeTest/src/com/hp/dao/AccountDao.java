package com.hp.dao;

public interface AccountDao {
	public abstract double findBalance(int userId);
	public abstract void saveMoney(double money,int userId);
	public abstract void showInfoByid(int userId);
	public abstract int  showAccountIdByUserId(int userId);
	public abstract boolean accountExist(int otherAccount);
	public abstract boolean compareMoney(double money,int userId);
	public abstract void transferMoney(Double money, int userId, int otherAccount);
	public abstract double findinall(int userId);
	public abstract double findoutall(int userId);
}
