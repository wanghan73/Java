package com.hp.view;

import java.util.Scanner;

public class MainUI {
	public static void main(String[] args) {
		while (true) {
			System.out.println("---------������������ƽ̨---------");
			System.out.println("************1����¼************");
			System.out.println("************2��ע��************");
			System.out.println("************0���˳�************");
			System.out.println("------------------------------");
			System.out.println("��ѡ��");
			Scanner sc = new Scanner(System.in);
			String select = sc.next();
			UsersUI usersUI = new UsersUI();
			switch (select) {
			case "1":
				// �����¼����
				usersUI.login();
				break;
			case "2":
				// ����ע�����
				usersUI.register();
				break;
			case "0":
				System.out.println("ллʹ��!");
				
				System.exit(0);
				break;
			default:
				System.out.println("����Ƿ�������������");
				break;
			}
		}
	}
}
