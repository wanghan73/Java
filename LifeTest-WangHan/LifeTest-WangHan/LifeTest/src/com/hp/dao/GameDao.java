package com.hp.dao;



import com.hp.po.Ranking;

public interface GameDao {
  //public abstract Ranking ranking(Ranking ranking);
	public abstract void ranking(int userId);
	public abstract int Scoreinsert(Ranking rank ,int userId);
}
