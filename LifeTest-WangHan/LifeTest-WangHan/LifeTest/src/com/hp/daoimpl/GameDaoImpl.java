package com.hp.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.hp.dao.GameDao;
import com.hp.po.Memorandum;
import com.hp.po.Ranking;
import com.hp.po.Users;
import com.hp.util.DBUtil;

public class GameDaoImpl implements GameDao{
	private static Connection c=null;
	private static PreparedStatement ps=null;
	private static PreparedStatement ps1=null;
	private static PreparedStatement ps2=null;
	private static ResultSet rs=null;
    private static ResultSet rs1=null;
    Ranking currentRank=null;
	Users currentUser=null;
	/**
	 * 功能：显示游戏排行榜
	 */
	public void ranking(int userId){
		int i = 0;
		try {
			String sql = "SELECT rankingId,integral,time FROM ranking  where userId= ? order by integral desc";
			c = DBUtil.getConnection();
			ps = c.prepareStatement(sql);
			ps.setInt(1, userId);
			rs = ps.executeQuery();// 接收结果
			System.out.println("排名                  姓名                 积分                   时间");
			while (rs.next()) {
				currentRank = new Ranking();
				currentRank.setRankingId(rs.getInt("rankingId"));
				currentRank.setIntegral(rs.getInt("integral"));
				currentRank.setTime(rs.getDate("time"));
                String sql1 = "SELECT userName FROM users where userId=?";
				ps1 = c.prepareStatement(sql1);
				ps1.setInt(1, userId);
				rs1 = ps1.executeQuery();
                while (rs1.next()) {
					currentUser = new Users();
					currentUser.setUserName(rs1.getString("userName"));
					i++;
					int count = 0;
					System.out.println(i + "         " + currentUser.getUserName() + "           "
							+ currentRank.getIntegral() + "           " + currentRank.getTime());
				}
			}
			rs.close();
			ps.close();
			rs1.close();
			ps1.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();// 打印异常位置和原因
		}
	}
	/**
	 * 功能：将游戏记录插入数据库，更新分数
	 */
	public int Scoreinsert(Ranking rank, int userId) {
		int r = 0;
		try {

			String sql2 = "insert into ranking(userId,integral,time) values(?,?,?)";
			c = DBUtil.getConnection();
			ps2 = c.prepareStatement(sql2);
			ps2.setInt(1, userId);
			ps2.setInt(2, rank.getIntegral());
			ps2.setTimestamp(3, new Timestamp(rank.getTime().getTime()));
			r = ps2.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}
}
