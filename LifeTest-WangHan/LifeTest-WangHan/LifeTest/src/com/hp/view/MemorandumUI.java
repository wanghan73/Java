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
	 * ���ܣ�����¼������
	 */
	public void main(Users users){
		
		currentUser = usersDao.login(users);	
		
		while(true){
		System.out.println("----------�ҵı���¼-----------");
		System.out.println("***��ӭ�����û�-"+currentUser.getUserName()+"***");
		System.out.println("1���鿴����¼   2����ӱ���¼   3���޸ı���¼    4��ɾ������¼   0���������˵�");
		System.out.println("----------���˱���¼--��--------");
		System.out.println("��ѡ��ҵ��:");
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
			System.out.println("����Ƿ�������������");
		}
		}
	}
	/**
	 * ���ܣ���ʾ����¼
	 */
	public void select(int userId){
		System.out.println("----------�ҵı���¼----------");
		System.out.println("**********�鿴����¼**********");
		m.select1(currentUser.getUserId());
   }
	/**
	 * ���ܣ���
	 */
	public void add(int userId){
		System.out.println("-----------�ҵı���¼---------");
		System.out.println("***********��ӱ���¼*********");
		System.out.println("���������:");
		String title=sc.next();
		System.out.println("���������ݣ�");
		String content=sc.next();
		Memorandum mem=new Memorandum();
        mem.setMemorandumTitle(title);
        mem.setContent(content);
        if(m.add1(mem, currentUser.getUserId())!=0){
			System.out.println("���ı���¼��ӳɹ�����ע��鿴");
		}else{
			System.out.println("���ʧ��");
		}
	}
	/**
	 * ���ܣ�����
	 */
	public void update(int userId){
		System.out.println("-----------�ҵı���¼---------");
		System.out.println("***********�޸ı���¼*********");
		System.out.println("������Ҫ�޸ĵı��:");
	    int mid=sc.nextInt();
		System.out.println("���������:");
		String title=sc.next();
		System.out.println("���������ݣ�");
		String content=sc.next();
		Memorandum mem=new Memorandum();
		mem.setMemorandumId(mid);
        mem.setMemorandumTitle(title);
        mem.setContent(content);
        if(m.update1(mem, currentUser.getUserId())!=0){
			System.out.println("���ı���¼�޸ĳɹ�����ע��鿴");
		}else{
			System.out.println("�޸ı���¼���ɹ���");
		}
	}
	/**
	 * ���ܣ�ɾ
	 */
	public void delete(int userId){
		System.out.println("-----------�ҵı���¼---------");
		System.out.println("***********ɾ������¼*********");
		m.select1(currentUser.getUserId());
		System.out.println("������Ҫɾ���ı��:");
		int mi=sc.nextInt();
		Memorandum mem=new Memorandum();
		mem.setMemorandumId(mi);
		if(m.delete1(mem,currentUser.getUserId())!=0){
			System.out.println("���ı���¼ɾ���ɹ�����ע��鿴");
		}else{
			System.out.println("ɾ������¼���ɹ���");
		}
	}
}