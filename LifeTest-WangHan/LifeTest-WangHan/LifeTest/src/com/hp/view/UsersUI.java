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
	 * ���ܣ��û�����ĵ�¼���棬ʵ���û��ĵ�¼����
	 */
	public void login() {
		while(true){
		System.out.println("---------������������ƽ̨---------");
		System.out.println("************�û���¼************");
		Scanner sc = new Scanner(System.in);
		System.out.println("�������û�����");
		String name = sc.next();
		System.out.println("���������룺");
		String password = sc.next();
        // �������󣬸�ֵ��Ա����name��password
		//Users users = new Users();
		users.setUserName(name);
		users.setUserPwd(password);
        // ����ѯ���currentUser�����û�����
		//UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
        // �Դ��ص�currentUser�����ж�
		if (currentUser != null) {
			System.out.println("��¼�ɹ�");
			menu();
			return;
		} else {
			if (count++> 3) {
				System.out.println("�����������������ȷ�Ϻ����µ�¼��");
				MainUI mUI = new MainUI();
				mUI.main(null);
				//menu();
				//return;
			} else {
				System.out.println("�û����������������������");
				login();
			}
		}
		}
	}
	/**
	 * ���ܣ����˵�����
	 */
    public void menu(){
    	MemorandumUI mi=new MemorandumUI();
    	GameUI gameUI = new GameUI();
    	AccountUI accountUI = new AccountUI();
    	boolean i=true;
    	currentUser = usersDao.login(users);
    	while(true){
		System.out.println("--------------------������������ƽ̨-----------------------");
		System.out.println("**********[��ӭ�����û�-" + currentUser.getUserName() + " �ϴε�¼ʱ�䣺" + currentUser.getLoginTime() + "]**********");
		System.out.println("1����Ϣά�� 2���޸����� 3������Ǯ�� 4������¼ 5��������� 0�����ص�¼���˵�");
		System.out.println("--------------------������������ƽ̨-----------------------");
		System.out.println("��ѡ��ҵ��");
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		switch (s) {
		case "1":
			//��Ϣά��
			 InfoUpdate();
			break;
		case "2":
			//�޸�����
			PwdUpdate();
			break;
		case "3":
			//����Ǯ��
			accountUI.myPurse(currentUser.getUserId());
			break;
		case "4":
			//����¼
			mi.main(users);
			break;
		case "5":
			//�������
			gameUI.show(users);
			break;
		case "0":
			//���ص�¼���˵�
			login();
			break;
		default:
			System.out.println("����Ƿ�������������");
			break;
		}
    	}
	}
    /**
	 * ���ܣ���Ϣά������
	 */
	public void InfoUpdate(){
		System.out.println("---------������������ƽ̨----------");
		System.out.println("**********��Ϣά�� ***************");
		//UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
		System.out.println("users"+"["+"userId="+currentUser.getUserId()+" "+"userName="+currentUser.getUserName()+" "+"userPassword="+currentUser.getUserPwd()+" "+"realName="+currentUser.getRealName()+" "+"tel="+currentUser.getTel()+" "+"address="+currentUser.getAddress());
	    Scanner sc=new Scanner(System.in);
	    System.out.println("�������ֻ���:");
	    String tel=sc.next();
	    System.out.println("�������ַ");
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
	 * ���ܣ��޸��������
	 */
	public void PwdUpdate(){
		
		System.out.println("---------������������ƽ̨----------");
		System.out.println("**********�޸����� ***************");
	    //UsersDao usersDao = new UsersDaoImpl();
		currentUser = usersDao.login(users);
		Scanner sc=new Scanner(System.in);
		System.out.println("������ԭ����:");
		String pwd1=sc.next();
		String a=currentUser.getUserPwd();
		//System.out.println(currentUser.getUserPwd());
		//System.out.println(pwd1);
		boolean b1 =pwd1.equals(a);
	    if(b1==false){
	    	System.out.println("��ԭ���벻һ��");
	    }else {
		System.out.println("������������");
		String pwd2=sc.next();
		System.out.println("���ٴ���������");
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
	    	System.out.println("������������벻һ��");
	    }
	}
	}
	
	/**
	 * ���ܣ��û�ע�����
	 */
	public void register(){
		System.out.println("---------������������ƽ̨----------");
		System.out.println("**********�û�ע�� ***************");
		Scanner sc=new Scanner(System.in);
		System.out.println("�������û���:");
		String name=sc.next();
		System.out.println("����������:");
		String password=sc.next();
		System.out.println("��������ʵ����:");
		String realname=sc.next();
		System.out.println("������绰����:");
		String tel=sc.next();
		System.out.println("��������ϵ��ַ:");
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
			System.out.println("ע��ɹ�");
		    menu();
			return;
		} else{
			
			System.out.println("ע��ʧ��");
			 menu();
		}
	}
	
}

