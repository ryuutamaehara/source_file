package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import fw.DBConnectionHolder;

/**
 * データアクセスクラスの規定クラス。
 * @author tsuhtan
 */
public class AbstractDAO
{
	/**
	 * 指定された更新系SQLを実行する。
	 * @param ch
	 * @param sql 実行するSQL文字列
	 * @return
	 * @throws SQLException
	 */
	protected	int	executeUpdate (DBConnectionHolder ch, String sql)	throws	SQLException
	{
		Connection con = ch.getConnection();
		Statement st = null;
		try {
			st = con.createStatement ();
			int updated_num = st.executeUpdate (sql);
			return updated_num;
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}
}
