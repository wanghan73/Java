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
	System.out.println("---------�������---------");
	System.out.println("****[��ӭ��,�û�"+currentUser.getUserName()+"]********");
	System.out.println("1.��ȭ��Ϸ     2.��Ϸ���а�    3.�������˵�");
	System.out.println("---------�������---------");
	System.out.println("��ѡ��ҵ��:");
	Scanner sc = new Scanner(System.in);
	String s = sc.next();
	
	switch (s) {
	case "1":
		// ��ȭ��Ϸ
		caiquan(currentUser.getUserId());
		show(users);
		break;
	case "2":
		// ��Ϸ���а�
		ranking(currentUser.getUserId());
		show(users);
		break;
	case "3":
		//�������˵�
		usersUI.menu();
		break;
	default:
		System.out.println("����������������룡��ȭ����:1.����  2.ʯͷ  3.��");
		break;
	}
	}
	/**
	 * ���ܣ���ȭ��Ϸ
	 */
	public void caiquan(int userId) {
	
		System.out.println("---------�������---------");
		System.out.println("*********��ȭ��Ϸ*********");
		int score =0;
		while(true){
		System.out.println("���ȭ: 1.����  2.ʯͷ   3.��");
		Scanner sc = new Scanner(System.in);
		int f=sc.nextInt();
		String[] a=new String[]{"����","ʯͷ","��"};
		System.out.println(currentUser.getUserName()+a[f-1]);
		Random rd=new Random();
		int x=rd.nextInt(3)+1;
		System.out.println("���Գ�:"+a[x-1]);
		if(f==x){
			System.out.println("�;�");
			score=score+0;
			System.out.println(score);
		}else if(f==1&&x==2||f==2&&x==3||f==3&&x==1){
			System.out.println("������,����Ӯ��");
			System.out.println("��Ϸ����,����"+score+"��");
			score=score+0;
			System.out.println(score);
			
			break;
		}else if(f==1&&x==3||f==2&&x==1||f==3&&x==2){
			System.out.println("��Ӯ��,��������");
			score=score+1;
			System.out.println(score);
		}
		}
		rank.setTime(new Date());
		rank.setIntegral(score);
		G.Scoreinsert(rank ,userId);
	}
	/**
	 * ���ܣ���Ϸ���а�
	 */
	public void ranking(int userId) {
		System.out.println("---------�������---------");
		System.out.println("*********��Ϸ���а�*********");
		System.out.println("");
		G.ranking(currentUser.getUserId());
		}
	}

