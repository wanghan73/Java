package com.hp.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 类描述：数据库操作工具类
 * 作者： 陈向阳
 * 创建日期：2017年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class DBUtil {

	// JDBC驱动程序
	private static String driver;
	
	// 连接数据库的路径
	private static String url;
	
	// 用户名
	private static String user;
	
	// 密码
	private static String password;

	static {
		try {
			// 读取配置文件,加载JDBC参数
			Properties properties = new Properties();
			// 使用类路径的读取方式 /:表示classpath的根目录
			InputStream inStream = DBUtil.class.getResourceAsStream("/db.properties");
			// 加载文件
			properties.load(inStream);
			
			// 读取JDBC参数信息
			driver = properties.getProperty("jdbc.driver");
			url = properties.getProperty("jdbc.url");
			user = properties.getProperty("jdbc.user");
			password = properties.getProperty("jdbc.password");
			//注册驱动程序
			Class.forName(driver);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 方法描述： 获取数据库连接
	 * @return 返回连接
	 */
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 方法描述：释放资源
	 * @param resultSet 结果集
	 * @param statement 命令对象
	 * @param connection 连接对象
	 */
	public static void releaseDB(ResultSet resultSet, Statement statement, Connection connection) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
