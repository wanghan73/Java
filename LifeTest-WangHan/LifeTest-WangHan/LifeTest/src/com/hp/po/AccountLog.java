package com.hp.po;

import java.sql.Date;

public class AccountLog {
	private int logId;
	private int accountId;
	private int transferAccount;
	private String type;
	private Date logTime;
	private double money;
	public AccountLog(){}
	public AccountLog(int logId,int accountId, int transferAccount, String type, Date logTime, double money) {
		super();
		this.logId = logId;
		this.accountId=accountId;
		this.transferAccount = transferAccount;
		this.type = type;
		this.logTime = logTime;
		this.money = money;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getTransferAccount() {
		return transferAccount;
	}
	public void setTransferAccount(int transferAccount) {
		this.transferAccount = transferAccount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	@Override
	public String toString() {
		return "AccountLog [logId=" + logId + ", accountId=" + accountId + ", transferAccount=" + transferAccount
				+ ", type=" + type + ", logTime=" + logTime + ", money=" + money + "]";
	}
	

}
