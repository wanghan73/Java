package com.hp.view;

import java.util.Scanner;

import com.hp.dao.MemorandumDao;
import com.hp.dao.UsersDao;
import com.hp.daoimpl.MemorandumDaoImpl;
import com.hp.daoimpl.UsersDaoImpl;
import com.hp.po.Memorandum;
import com.hp.po.Users;

public class MemorandumUI {
	static Users user=new Users();
    static Users currentUser = null;
	static UsersDao usersDao = new UsersDaoImpl();
	
	Memorandum currentMem=new Memorandum();
	MemorandumDao m=new MemorandumDaoImpl();
	Scanner sc=new Scanner(System.in);
	/**
	 * 功能：备忘录主界面
	 */
	public void main(Users users){
		
		currentUser = usersDao.login(users);	
		
		while(true){
		System.out.println("----------我的备忘录-----------");
		System.out.println("***欢迎您，用户-"+currentUser.getUserName()+"***");
		System.out.println("1、查看备忘录   2、添加备忘录   3、修改备忘录    4、删除备忘录   0、返回主菜单");
		System.out.println("----------个人备忘录--—--------");
		System.out.println("请选择业务:");
		int select=sc.nextInt();
		switch(select){
		case 1:
			select(currentUser.getUserId());
			main(users);
			break;
		case 2:
			add(currentUser.getUserId());
			select(currentUser.getUserId());
			main(users);
			break;
		case 3:
			select(currentUser.getUserId());
			update(currentUser.getUserId());
			select(currentUser.getUserId());
			main(users);
			break;
		case 4:
			select(currentUser.getUserId());
			delete(currentUser.getUserId());
			select(currentUser.getUserId());
			main(users);
			break;
		case 0:
		    UsersUI ui=new UsersUI();
		    ui.menu();
			break;
		default:
			System.out.println("输入非法，请重新输入");
		}
		}
	}
	/**
	 * 功能：显示备忘录
	 */
	public void select(int userId){
		System.out.println("----------我的备忘录----------");
		System.out.println("**********查看备忘录**********");
		m.select1(currentUser.getUserId());
   }
	/**
	 * 功能：增
	 */
	public void add(int userId){
		System.out.println("-----------我的备忘录---------");
		System.out.println("***********添加备忘录*********");
		System.out.println("请输入标题:");
		String title=sc.next();
		System.out.println("请输入内容：");
		String content=sc.next();
		Memorandum mem=new Memorandum();
        mem.setMemorandumTitle(title);
        mem.setContent(content);
        if(m.add1(mem, currentUser.getUserId())!=0){
			System.out.println("您的备忘录添加成功，请注意查看");
		}else{
			System.out.println("添加失败");
		}
	}
	/**
	 * 功能：更新
	 */
	public void update(int userId){
		System.out.println("-----------我的备忘录---------");
		System.out.println("***********修改备忘录*********");
		System.out.println("请输入要修改的编号:");
	    int mid=sc.nextInt();
		System.out.println("请输入标题:");
		String title=sc.next();
		System.out.println("请输入内容：");
		String content=sc.next();
		Memorandum mem=new Memorandum();
		mem.setMemorandumId(mid);
        mem.setMemorandumTitle(title);
        mem.setContent(content);
        if(m.update1(mem, currentUser.getUserId())!=0){
			System.out.println("您的备忘录修改成功，请注意查看");
		}else{
			System.out.println("修改备忘录不成功！");
		}
	}
	/**
	 * 功能：删
	 */
	public void delete(int userId){
		System.out.println("-----------我的备忘录---------");
		System.out.println("***********删除备忘录*********");
		m.select1(currentUser.getUserId());
		System.out.println("请输入要删除的编号:");
		int mi=sc.nextInt();
		Memorandum mem=new Memorandum();
		mem.setMemorandumId(mi);
		if(m.delete1(mem,currentUser.getUserId())!=0){
			System.out.println("您的备忘录删除成功，请注意查看");
		}else{
			System.out.println("删除备忘录不成功！");
		}
	}
}