package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fw.DBConnectionHolder;
import fw.SQLUtil;

/**
 * ユーザ情報
 * @author tsuhtan
 */
public class UserProfile	extends	AbstractDAO
{
	/** ユーザＩＤ*/
	private	String	user_id = null;
	public	String	getUserId(){return this.user_id;}
	public	void	setUserId (String user_id){this.user_id = user_id;}

	/** パスワード*/
	private	String	password = null;
	public	String	getPassword(){return this.password;}
	public	void	setPassword (String password){this.password = password;}

	/** ユーザ名 */
	private	String	user_name = null;
	public	String	getUserName(){return this.user_name;}
	public	void	setUserName (String user_name){this.user_name = user_name;}

	/** スーパユーザである */
	private	boolean	is_super_user = false;
	public	boolean	getIsSuperUser(){return this.is_super_user;}
	public	void	setIsSuperUser (boolean is_super_user){this.is_super_user = is_super_user;}

	/** 性別： "FEMALE" / "MALE" / "NOT_SPECIFIED" */
	private	String	gender = null;
	public	String	getGender(){return this.gender;}
	public	void	setGender (String gender){this.gender = gender;}
	public	final	static	String	GENDER_FEMALE = "FEMALE";
	public	final	static	String	GENDER_MALE = "MALE";
	public	final	static	String	GENDER_NOT_SPECIFIED = "NOT_SPECIFIED";

	/** 年齢 */
	private	int	age = 0;
	public	int	getAge(){return this.age;}
	public	void	setAge (int age){this.age = age;}

	/**
	 * 認証のための検索を実行する。
	 * @param ch
	 * @param user_id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public	static	UserProfile	selectForAuth (DBConnectionHolder ch, String user_id, String password)	throws	SQLException
	{
		String sql = "select user_id, password, user_name,"
				+ "is_super_user, gender, age"
				+ "\r\nfrom user_profile"
				+ "\r\nwhere user_id =  " + SQLUtil.getDBStringExpression (user_id)
				+ "\r\nand password = " + SQLUtil.getDBStringExpression (password);

		return _select (ch, sql);
	}

	public	static	UserProfile	selectForUpdate (DBConnectionHolder ch, String user_id)	throws	SQLException
	{
		String sql = "select user_id, password, user_name,"
				+ "is_super_user, gender, age"
				+ "\r\nfrom user_profile"
				+ "\r\nwhere user_id =  " + SQLUtil.getDBStringExpression (user_id)
				+" \r\nfor update";

		return _select (ch, sql);
	}

	private	static	UserProfile	_select (DBConnectionHolder ch, String sql)	throws	SQLException
	{
		Connection con = ch.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery (sql);
			if (rs.next()) {
				return fetch (rs, new UserProfile());
			}
			else {
				return null;
			}
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

	private	static	UserProfile	fetch (ResultSet rs, UserProfile d)	throws	SQLException
	{
		d.user_id = rs.getString ("user_id");
		d.password = rs.getString ("password");
		d.user_name = rs.getString ("user_name");
		d.is_super_user = rs.getBoolean ("is_super_user");
		d.gender = rs.getString ("gender");
		d.age = rs.getInt ("age");

		return d;
	}

	/**
	 * パスワードを更新する。
	 * @param ch
	 * @return
	 * @throws SQLException
	 */
	public	int	updatePassword (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "update user_profile set password = " + SQLUtil.getDBStringExpression (password)
					+ "\r\nwhere user_id = " + SQLUtil.getDBStringExpression (user_id);

		return super.executeUpdate (ch, sql);
	}
}
