package com.hp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.hp.dao.MemorandumDao;
import com.hp.dao.UsersDao;
import com.hp.po.Memorandum;
import com.hp.po.Users;
import com.hp.util.DBUtil;

public class MemorandumDaoImpl implements MemorandumDao {
	private static Connection c=null;
	private static PreparedStatement ps=null;
	private static ResultSet rs=null;
	Memorandum currentMem=null;
	/**
	 * 功能：查找备忘录
	 */
	public void select1(int userId){
		try {
			String sql = "SELECT memorandumId,memorandumTitle,content,time FROM memorandum where userId=?";
            c=DBUtil.getConnection();
  			ps=c.prepareStatement(sql);
			ps.setInt(1,userId);
			rs = ps.executeQuery();//接收结果
	        // 展开结果集数据库
	        while(rs.next()){
	        	currentMem=new Memorandum();
	        	currentMem.setMemorandumId(rs.getInt("memorandumId"));
	        	currentMem.setMemorandumTitle(rs.getString("memorandumTitle"));
	        	currentMem.setContent(rs.getString("content"));
	        	currentMem.setTime(rs.getDate("time"));
	        	System.out.println("1、编号："+currentMem.getMemorandumId()+"   2、标题:"+currentMem.getMemorandumTitle()+"   3、内容："+currentMem.getContent()+"   4、时间"+currentMem.getTime());
	        	}
	        	rs.close();
	        	ps.close();
	        	c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();//打印异常位置和原因
		}
	}
	/**
	 * 功能：增加备忘录
	 */
	public int add1(Memorandum mem,int userId){
		int r=0;
		try {
	    	String sql="insert into memorandum(memorandumTitle,content,userId,time) values(?,?,?,now())";
	    	c=DBUtil.getConnection();
  			ps=c.prepareStatement(sql);
			ps.setString(1,mem.getMemorandumTitle());
			//System.out.println(mem.getMemorandumTitle());
			ps.setString(2, mem.getContent());
			ps.setInt(3,userId);
			r=ps.executeUpdate();
			//System.out.println(r);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	
        	
	/**
	 * 功能：更新备忘录
	 */
	public int update1(Memorandum mem,int userId){
		int r=0;
		String sql1="select * from memorandum where memorandumId=?";
		try {
			c=DBUtil.getConnection();
			ps=c.prepareStatement(sql1);
			ps.setInt(1,mem.getMemorandumId());
			rs = ps.executeQuery();
			if(rs.next()){
				String sql="update memorandum set memorandumTitle=?,content=? where memorandumId=? and userId=?";
	  			ps=c.prepareStatement(sql);
				ps.setString(1,mem.getMemorandumTitle());
				//System.out.println(mem.getMemorandumTitle());
				ps.setString(2, mem.getContent());
				ps.setInt(3, mem.getMemorandumId());
				ps.setInt(4,userId);
				r=ps.executeUpdate();
	        	}else{
	        		System.out.println("备忘录不存在，请重新输入");
	        	}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
	/**
	 * 功能：删除备忘录
	 */
	public int delete1(Memorandum mem,int userId){
		int r=0;
		String sql1="select * from memorandum where memorandumId=?";
		try {
			c=DBUtil.getConnection();
			ps=c.prepareStatement(sql1);
			ps.setInt(1,mem.getMemorandumId());
			rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("Memorandum [memorandumId="+currentMem.getMemorandumId()+", memorandumTitle:"+currentMem.getMemorandumTitle()+", content:"+currentMem.getContent()+", times:"+currentMem.getTime()+", userId="+currentMem.getUserId()+"]确定要删除吗？y/n");
	        	Scanner sc=new Scanner(System.in);
	        	String answer=sc.next();
	        	if(answer.equals("y")){
	        		try {
	        	    	String sql="delete from memorandum where memorandumId=?";
	          			ps=c.prepareStatement(sql);
	        			ps.setInt(1, mem.getMemorandumId());
	        		    r=ps.executeUpdate();
	        		} catch (SQLException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	        	}else{
	        		System.out.println("已取消删除");
	        	}	
	          }else{
	        	System.out.println("备忘录不存在，请重新输入");
	        }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return r;
	}
}
