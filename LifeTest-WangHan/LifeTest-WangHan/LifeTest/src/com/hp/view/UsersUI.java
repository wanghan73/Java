package com.hp.view;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import com.hp.dao.UsersDao;
import com.hp.daoimpl.UsersDaoImpl;
import com.hp.po.Users;
public class UsersUI {
    static Users currentUser = null;
	static Users users = new Users();
    static  UsersDao usersDao = new UsersDaoImpl();
	Date date1=new Date();
    SimpleDateFormat sdf=new  SimpleDateFormat("yyyy-MM-dd");
    String d=sdf.format(date1);
    int count = 1;
	/**
	 * 功能：用户界面的登录界面，实现用户的登录功能
	 */
	public void login() {
		while(true){
		System.out.println("---------个人生活助手平台---------");
		System.out.println("************用户登录************");
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入用户名：");
		String name = sc.next();
		System.out.println("请输入密码：");
		String password = sc.next();
        // 创建对象，赋值成员变量name和password
		//Users users = new Users();
		users.setUserName(name);
		users.setUserPwd(password);
        // 将查询后的currentUser传回用户界面
		//UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
        // 对传回的currentUser进行判断
		if (currentUser != null) {
			System.out.println("登录成功");
			menu();
			return;
		} else {
			if (count++> 3) {
				System.out.println("连续三次输入错误，请确认后重新登录！");
				MainUI mUI = new MainUI();
				mUI.main(null);
				//menu();
				//return;
			} else {
				System.out.println("用户名或密码错误，请重新输入");
				login();
			}
		}
		}
	}
	/**
	 * 功能：主菜单界面
	 */
    public void menu(){
    	MemorandumUI mi=new MemorandumUI();
    	GameUI gameUI = new GameUI();
    	AccountUI accountUI = new AccountUI();
    	boolean i=true;
    	currentUser = usersDao.login(users);
    	while(true){
		System.out.println("--------------------个人生活助手平台-----------------------");
		System.out.println("**********[欢迎您，用户-" + currentUser.getUserName() + " 上次登录时间：" + currentUser.getLoginTime() + "]**********");
		System.out.println("1、信息维护 2、修改密码 3、个人钱包 4、备忘录 5、娱乐天地 0、返回登录主菜单");
		System.out.println("--------------------个人生活助手平台-----------------------");
		System.out.println("请选择业务：");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		switch (s) {
		case "1":
			//信息维护
			 InfoUpdate();
			break;
		case "2":
			//修改密码
			PwdUpdate();
			break;
		case "3":
			//个人钱包
			accountUI.myPurse(currentUser.getUserId());
			break;
		case "4":
			//备忘录
			mi.main(users);
			break;
		case "5":
			//娱乐天地
			gameUI.show(users);
			break;
		case "0":
			//返回登录主菜单
			login();
			break;
		default:
			System.out.println("输入非法，请重新输入");
			break;
		}
    	}
	}
    /**
	 * 功能：信息维护界面
	 */
	public void InfoUpdate(){
		System.out.println("---------个人生活助手平台----------");
		System.out.println("**********信息维护 ***************");
		//UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
		System.out.println("users"+"["+"userId="+currentUser.getUserId()+" "+"userName="+currentUser.getUserName()+" "+"userPassword="+currentUser.getUserPwd()+" "+"realName="+currentUser.getRealName()+" "+"tel="+currentUser.getTel()+" "+"address="+currentUser.getAddress());
	    Scanner sc=new Scanner(System.in);
	    System.out.println("请输入手机号:");
	    String tel=sc.next();
	    System.out.println("请输入地址");
	    String address=sc.next();
	    Users users2=new Users();
	    users2.setUserId(currentUser.getUserId());
	    users2.setUserName(currentUser.getUserName());
	    users2.setRealName(currentUser.getUserPwd());
	    users2.setUserPwd(currentUser.getRealName());
	    users2.setTel(tel);
	    users2.setAddress(address);
		usersDao.Infoupdate(users2);
	}
	/**
	 * 功能：修改密码界面
	 */
	public void PwdUpdate(){
		
		System.out.println("---------个人生活助手平台----------");
		System.out.println("**********修改密码 ***************");
	    //UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入原密码:");
		String pwd1=sc.next();
		String a=currentUser.getUserPwd();
		//System.out.println(currentUser.getUserPwd());
		//System.out.println(pwd1);
		boolean b1 =pwd1.equals(a);
	    if(b1==false){
	    	System.out.println("与原密码不一致");
	    }else {
		System.out.println("请输入新密码");
		String pwd2=sc.next();
		System.out.println("请再次输入密码");
		String pwd3=sc.next();
		boolean b2=pwd2.equals(pwd3);
		if(b2==true){
		Users users3=new Users();
		users3.setUserId(currentUser.getUserId());  
		users3.setUserName(currentUser.getUserName());
	    users3.setRealName(currentUser.getUserPwd());
	    users3.setTel(currentUser.getTel());
	    users3.setAddress(currentUser.getAddress());
	    users3.setUserPwd(pwd2);
	    usersDao.Pwdupdate(users3);
	    }else{
	    	System.out.println("两次输入的密码不一致");
	    }
	}
	}
	
	/**
	 * 功能：用户注册界面
	 */
	public void register(){
		System.out.println("---------个人生活助手平台----------");
		System.out.println("**********用户注册 ***************");
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入用户名:");
		String name=sc.next();
		System.out.println("请输入密码:");
		String password=sc.next();
		System.out.println("请输入真实姓名:");
		String realname=sc.next();
		System.out.println("请输入电话号码:");
		String tel=sc.next();
		System.out.println("请输入联系地址:");
		String address=sc.next();
		Users users1=new Users();
		users1.setUserName(name);
		users1.setUserPwd(password);
		users1.setRealName(realname);
		users1.setTel(tel);
		users1.setAddress(address);
		users1.setLoginTime(new Date());
		//users1.setLoginTime(d);
		//UsersDao usersDao=new UsersDaoImpl();
		currentUser=usersDao.register(users1);
		if(currentUser!=null){
			System.out.println("注册成功");
		    menu();
			return;
		} else{
			
			System.out.println("注册失败");
			 menu();
		}
	}
	
}

