package com.hp.po;

import java.sql.Date;

public class Memorandum {
     private int memorandumId;
     private String memorandumTitle;
     private Date time;
     private String content;
     private int userId;
     public Memorandum(){}
	public Memorandum(int memorandumId, String memorandumTitle, Date time, String content,int userId) {
		super();
		this.memorandumId = memorandumId;
		this.memorandumTitle = memorandumTitle;
		this.time = time;
		this.content = content;
		this.userId=userId;
	}
	public int getMemorandumId() {
		return memorandumId;
	}
	public void setMemorandumId(int memorandumId) {
		this.memorandumId = memorandumId;
	}
	public String getMemorandumTitle() {
		return memorandumTitle;
	}
	public void setMemorandumTitle(String memorandumTitle) {
		this.memorandumTitle = memorandumTitle;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Memorandum [memorandumId=" + memorandumId + ", memorandumTitle=" + memorandumTitle + ", time=" + time
				+ ", content=" + content + ", userId=" + userId + "]";
	}
     
}
