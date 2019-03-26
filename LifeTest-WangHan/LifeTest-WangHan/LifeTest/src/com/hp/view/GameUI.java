package com.hp.view;

import java.awt.Menu;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

import javax.xml.crypto.Data;

import com.hp.dao.GameDao;
import com.hp.dao.MemorandumDao;
import com.hp.dao.UsersDao;
import com.hp.daoimpl.GameDaoImpl;
import com.hp.daoimpl.MemorandumDaoImpl;
import com.hp.daoimpl.UsersDaoImpl;
import com.hp.po.Memorandum;
import com.hp.po.Ranking;
import com.hp.po.Users;

public class GameUI {
	static Users currentUser = null;
	Users user=new Users();
	Ranking currentRanking=new Ranking();
	GameDao G=new GameDaoImpl();
	Ranking rank=new Ranking();
	static UsersDao usersDao = new UsersDaoImpl();
	static UsersUI usersUI = new UsersUI();
	public void show(Users users){
	currentUser = usersDao.login(users);
	System.out.println("---------娱乐天地---------");
	System.out.println("****[欢迎您,用户"+currentUser.getUserName()+"]********");
	System.out.println("1.猜拳游戏     2.游戏排行榜    3.返回主菜单");
	System.out.println("---------娱乐天地---------");
	System.out.println("请选择业务:");
	Scanner sc = new Scanner(System.in);
	String s = sc.next();
	
	switch (s) {
	case "1":
		// 猜拳游戏
		caiquan(currentUser.getUserId());
		show(users);
		break;
	case "2":
		// 游戏排行榜
		ranking(currentUser.getUserId());
		show(users);
		break;
	case "3":
		//返回主菜单
		usersUI.menu();
		break;
	default:
		System.out.println("输入错误！请重新输入！出拳规则:1.剪刀  2.石头  3.布");
		break;
	}
	}
	/**
	 * 功能：猜拳游戏
	 */
	public void caiquan(int userId) {
	
		System.out.println("---------娱乐天地---------");
		System.out.println("*********猜拳游戏*********");
		int score =0;
		while(true){
		System.out.println("请出拳: 1.剪刀  2.石头   3.布");
		Scanner sc = new Scanner(System.in);
		int f=sc.nextInt();
		String[] a=new String[]{"剪刀","石头","布"};
		System.out.println(currentUser.getUserName()+a[f-1]);
		Random rd=new Random();
		int x=rd.nextInt(3)+1;
		System.out.println("电脑出:"+a[x-1]);
		if(f==x){
			System.out.println("和局");
			score=score+0;
			System.out.println(score);
		}else if(f==1&&x==2||f==2&&x==3||f==3&&x==1){
			System.out.println("你输了,电脑赢了");
			System.out.println("游戏结束,共计"+score+"分");
			score=score+0;
			System.out.println(score);
			
			break;
		}else if(f==1&&x==3||f==2&&x==1||f==3&&x==2){
			System.out.println("你赢了,电脑输了");
			score=score+1;
			System.out.println(score);
		}
		}
		rank.setTime(new Date());
		rank.setIntegral(score);
		G.Scoreinsert(rank ,userId);
	}
	/**
	 * 功能：游戏排行榜
	 */
	public void ranking(int userId) {
		System.out.println("---------娱乐天地---------");
		System.out.println("*********游戏排行榜*********");
		System.out.println("");
		G.ranking(currentUser.getUserId());
		}
	}

