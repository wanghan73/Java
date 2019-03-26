package com.hp.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import com.hp.dao.UsersDao;
import com.hp.po.Users;
import com.hp.util.DBUtil;
public class UsersDaoImpl implements UsersDao{
	private static Connection con = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;
	Users currentUser = null;
	private static Statement st=null;
	private static ResultSet rs=null;
	/**
	 * ���ܣ���¼����
	 */
	public Users login(Users users){
		String sql = "select * from users where userName = ? and userPassword = ?";
		try {
			con = DBUtil.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, users.getUserName());
			preparedStatement.setString(2, users.getUserPwd());
			resultSet = preparedStatement.executeQuery();
		    //��resultset�ڵĽ�����洢������currentUser��
			while(resultSet.next()){
				currentUser = new Users();
				currentUser.setUserId(resultSet.getInt("userId"));
				currentUser.setUserName(resultSet.getString("userName"));
				currentUser.setUserPwd(resultSet.getString("UserPassword"));
				currentUser.setRealName(resultSet.getString("realName"));
				currentUser.setTel(resultSet.getString("tel"));
				currentUser.setAddress(resultSet.getString("address"));
				currentUser.setLoginTime(resultSet.getTimestamp("login_time"));
				//getDate()  getTime()  getTimestamp()
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.releaseDB(resultSet, preparedStatement, con);
		}
		return currentUser;
	}
	/**
	 * ���ܣ����Һ���
	 */
	public boolean checkUserName(String userName){
		boolean flag = false;
		String sql = "select * from users where userName = ?";
		try {
			con = DBUtil.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			rs = preparedStatement.executeQuery();
			if(rs.next()){
				flag = true;   //������ڸ��ʺţ��ͷ���true
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * ���ܣ�ע�Ṧ��
	 */
	public Users register(Users users1){
		boolean flag = false;
		int result=0;
		try {
			//Users users1=new Users();
			String sql = "select * from users where username=?";
			con = DBUtil.getConnection();
			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, users1.getUserName());
			boolean b =checkUserName(users1.getUserName());
			if(b==true){
					System.out.println("�˻�����,ע��ʧ��");
				}else{
					String sql1="insert  into users (userName,userPassword,realName,tel,address,login_time) values(?,?,?,?,?,?)";
					preparedStatement =con.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
					preparedStatement.setString(1,users1.getUserName());
					preparedStatement.setString(2, users1.getUserPwd());
					preparedStatement.setString(3, users1.getRealName());
					preparedStatement.setString(4, users1.getTel());
					preparedStatement.setString(5, users1.getAddress());
					preparedStatement.setTimestamp(6,new Timestamp(users1.getLoginTime().getTime()));
					result=preparedStatement.executeUpdate();
					System.out.println(result);
					System.out.println("ע��ɹ�");
					currentUser = new Users();
					currentUser.setUserName(users1.getUserName());
					currentUser.setUserPwd(users1.getUserPwd());
					currentUser.setRealName(users1.getRealName());
					currentUser.setTel(users1.getTel());
					currentUser.setAddress(users1.getAddress());
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return currentUser;
	}
	/**
	 * ���ܣ���Ϣά��
	 */
	public int Infoupdate(Users users2){
	String sql = "update users set tel=?,address=? where userId=?";
	int result=0;
	try {
		con = DBUtil.getConnection();
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, users2.getTel ());
		//System.out.println(users2.getTel ());
		preparedStatement.setString(2, users2.getAddress());
		preparedStatement.setInt(3, users2.getUserId());
		//System.out.println(users2.getUserId());
		result=preparedStatement.executeUpdate();
		//System.out.println(result);
		System.out.println("���³ɹ�");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
	/**
	 * ���ܣ��޸�����
	 */
	public int Pwdupdate(Users users3){
		String sql = "update users set userPassword=?where userId=?";
	int result1=0;
	try {
		con = DBUtil.getConnection();
		preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, users3.getUserPwd());
		preparedStatement.setInt(2, users3.getUserId());
		//System.out.println(users2.getUserId());
		result1=preparedStatement.executeUpdate();
		//System.out.println(result);
		System.out.println("���³ɹ�");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result1;
	}

}
