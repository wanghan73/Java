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
	 * 功能：个人钱包主界面
	 */
    public void myPurse(int userId) {
		// TODO Auto-generated method stub
		while(true){
		System.out.println("---------个人钱包---------");
		System.out.println("1、查看余额 2、存钱 3、转账 4、统计查询 0、返回主菜单");
		System.out.println("请选择业务：");
		Scanner scanner = new Scanner(System.in);
		int temp = scanner.nextInt();
		switch (temp) {
		case 1:
			System.out.println("您的账户余额为："+accountDao.findBalance(userId));
			myPurse(userId);
			break;
		case 2:
			System.out.println("**************存钱****************");
			accountDao.showInfoByid(userId);
			System.out.println("请输入存款金额：");
			try {	
			 double money = scanner.nextDouble();
			 accountDao.saveMoney(money,userId);
			 myPurse(userId);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("您的输入不合法！");
				myPurse(userId);
			}
		
			break;
			
		case 3:
			System.out.println("**************转账****************");
			accountDao.showInfoByid(userId);
			System.out.println("请输入对方账户：");
			int otherAccount= scanner.nextInt();
			System.out.println("请输入转账金额：");
			Double money = scanner.nextDouble();
			if(accountDao.accountExist(otherAccount)){
				System.out.println("该账户存在");
				if(accountDao.compareMoney(money,userId)){
					System.out.println("您的帐户成功转出："+money+"元");
					accountDao.transferMoney(money,userId,otherAccount);
					myPurse(userId);
				}else{
					System.out.println("您的帐户余额不足，请及时充值");
					myPurse(userId);
				}
			}else {
				System.out.println("非常抱歉，该账户不存在，请核对后再输入！");	
				myPurse(userId);
			}
			break;
		case 4:
			System.out.println("当月收入统计为："+accountDao.findinall(userId));	
			System.out.println("当月支出统计为："+accountDao.findoutall(userId));	
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