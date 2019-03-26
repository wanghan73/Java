package com.hp.view;

import java.awt.Menu;
import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.hp.dao.AccountDao;
import com.hp.dao.UsersDao;
import com.hp.daoimpl.AccountDaoImpl;
import com.hp.daoimpl.UsersDaoImpl;
import com.hp.po.Users;
public class AccountUI {
	static Users currentUser = null;
	private static AccountDao accountDao = new AccountDaoImpl();
	/**
	 * ���ܣ�����Ǯ��������
	 */
    public void myPurse(int userId) {
		// TODO Auto-generated method stub
		while(true){
		System.out.println("---------����Ǯ��---------");
		System.out.println("1���鿴��� 2����Ǯ 3��ת�� 4��ͳ�Ʋ�ѯ 0���������˵�");
		System.out.println("��ѡ��ҵ��");
		Scanner scanner = new Scanner(System.in);
		int temp = scanner.nextInt();
		switch (temp) {
		case 1:
			System.out.println("�����˻����Ϊ��"+accountDao.findBalance(userId));
			myPurse(userId);
			break;
		case 2:
			System.out.println("**************��Ǯ****************");
			accountDao.showInfoByid(userId);
			System.out.println("���������");
			try {	
			 double money = scanner.nextDouble();
			 accountDao.saveMoney(money,userId);
			 myPurse(userId);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("�������벻�Ϸ���");
				myPurse(userId);
			}
		
			break;
			
		case 3:
			System.out.println("**************ת��****************");
			accountDao.showInfoByid(userId);
			System.out.println("������Է��˻���");
			int otherAccount= scanner.nextInt();
			System.out.println("������ת�˽�");
			Double money = scanner.nextDouble();
			if(accountDao.accountExist(otherAccount)){
				System.out.println("���˻�����");
				if(accountDao.compareMoney(money,userId)){
					System.out.println("�����ʻ��ɹ�ת����"+money+"Ԫ");
					accountDao.transferMoney(money,userId,otherAccount);
					myPurse(userId);
				}else{
					System.out.println("�����ʻ����㣬�뼰ʱ��ֵ");
					myPurse(userId);
				}
			}else {
				System.out.println("�ǳ���Ǹ�����˻������ڣ���˶Ժ������룡");	
				myPurse(userId);
			}
			break;
		case 4:
			System.out.println("��������ͳ��Ϊ��"+accountDao.findinall(userId));	
			System.out.println("����֧��ͳ��Ϊ��"+accountDao.findoutall(userId));	
			myPurse(userId);
			break;
		case 0:
		   UsersUI usersUI = new UsersUI();
		   usersUI.menu();
			break;
		default:
			break;
		}
	}

}
}