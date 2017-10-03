package com.internousdev.solare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBconnector MySQLとのコネクションを獲得するクラス
 *
 * @author 岩井晋太郎
 * @since 2015/04/09
 * @version 1.0
 */
public class DBConnector {

	/**
	 * ドライバー名
	 *
	 * @author 岩井晋太郎
	 * @since 2015/04/09
	 * @version 1.0
	 */
	private static String driverName = "com.mysql.jdbc.Driver";

	/**
	 * URL
	 *
	 * @author 岩井晋太郎
	 * @since 2015/04/09
	 * @version 1.0
	 */
	private static String url = "jdbc:mysql://localhost/solare";

	/**
	 * ユーザー名
	 *
	 * @author 岩井晋太郎
	 * @since 2015/04/09
	 * @version 1.0
	 */
	private static String user = "root";

	/**
	 * パスワード
	 *
	 * @author 岩井晋太郎
	 * @since 2015/04/09
	 * @version 1.0
	 */
	private static String pass = "mysql";

	/**
	 * 接続メソッド
	 *
	 * @author 岩井晋太郎
	 * @since 2015/04/09
	 * @return con jdbcドライバ
	 */
	public Connection getConnection() {
		Connection con = null;
		try {
			Class.forName(driverName);
			con = DriverManager.getConnection(url, user, pass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}