package com.hp.dao;

import com.hp.po.Memorandum;

public interface MemorandumDao {
	 public abstract void select1(int userId);
	   public abstract int add1(Memorandum mem,int userId);
	   public abstract int update1(Memorandum mem,int userId);
	   public abstract int delete1(Memorandum mem,int userId);
}
