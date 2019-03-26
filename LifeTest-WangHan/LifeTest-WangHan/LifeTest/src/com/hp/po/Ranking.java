package com.hp.po;

import java.util.Date;

public class Ranking {
      private int rankingId;
      private int userId;
      private int integral;
      private Date time;
      public Ranking(){}
	public Ranking(int rankingId,int userId, int integral, Date time) {
		super();
		this.rankingId = rankingId;
		this.userId=userId;
		this.integral = integral;
		this.time = time;
	}
	public int getRankingId() {
		return rankingId;
	}
	public void setRankingId(int rankingId) {
		this.rankingId = rankingId;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Ranking [rankingId=" + rankingId + ", userId=" + userId + ", integral=" + integral + ", time=" + time
				+ "]";
	}
	 
      
}
