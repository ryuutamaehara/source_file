package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fw.DBConnectionHolder;

/**
 * select SQL文の実行と結果の取得処理。
 *
 * @param <T>	対象テーブルに対応するdaoクラス
 */
class	Selector<T>
{
	/**
	 * select SQL文の実行と結果の取得処理。
	 *
	 * @param ch
	 * @param fetcher
	 * @param sql	実行するselect SQL文を保持する文字列
	 * @return
	 */
	ArrayList<T>	select (DBConnectionHolder ch, Fetcher<T> fetcher, String sql)
	{
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection con = ch.getConnection();
			st = con.createStatement();
			rs = st.executeQuery (sql);
			ArrayList<T> list = new ArrayList<T>();
			while (rs.next()) {
				T d = fetcher.fetch(ch, rs);
				list.add(d);
			}
			return list;
		} catch (SQLException e) {
			throw new RuntimeException (e);
		} finally {
			if (rs != null) {
				try {
					rs.close ();
				} catch (SQLException e2) {
				}
			}
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}
}
