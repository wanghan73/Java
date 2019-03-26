package com.hp.view;

import java.util.Scanner;

public class MainUI {
	public static void main(String[] args) {
		while (true) {
			System.out.println("---------个人生活助手平台---------");
			System.out.println("************1、登录************");
			System.out.println("************2、注册************");
			System.out.println("************0、退出************");
			System.out.println("------------------------------");
			System.out.println("请选择：");
			Scanner sc = new Scanner(System.in);
			String select = sc.next();
			UsersUI usersUI = new UsersUI();
			switch (select) {
			case "1":
				// 进入登录界面
				usersUI.login();
				break;
			case "2":
				// 进入注册界面
				usersUI.register();
				break;
			case "0":
				System.out.println("谢谢使用!");
				
				System.exit(0);
				break;
			default:
				System.out.println("输入非法，请重新输入");
				break;
			}
		}
	}
}
