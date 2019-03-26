package com.hp.po;

public class Account {
	private int accountId;
	private double account_money;
	private int userId;
	public Account(){}
	public Account(int accountId, double account_money,int userId) {
		super();
		this.accountId = accountId;
		this.account_money = account_money;
		this.userId=userId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public double getAccount_money() {
		return account_money;
	}
	public void setAccount_money(double account_money) {
		this.account_money = account_money;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", account_money=" + account_money + ", userId=" + userId + "]";
	}

}
