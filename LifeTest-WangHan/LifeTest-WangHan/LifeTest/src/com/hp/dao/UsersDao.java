package com.hp.dao;

import com.hp.po.Users;

public interface UsersDao {
	public abstract Users login(Users users);
    public abstract Users register(Users users1);
    public abstract int Infoupdate(Users users2 );
    public abstract int Pwdupdate(Users users3 );
    
}
