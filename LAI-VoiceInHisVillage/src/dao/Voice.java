package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.SQLUtil;

/**
 * 村人の声
 * @author tsuhtan
 */
public class Voice	extends	AbstractDAO
{
	/** 声ID */
	private	String	voice_id = null;
	public	String	getVoiceId(){return this.voice_id;}
	public	void	setVoiceId (String voice_id){this.voice_id = voice_id;}

	/** 投稿者のuser_id */
	private	String	posted_by = null;
	public	String	getPostedBy(){return this.posted_by;}
	public	void	setPostedBy (String posted_by){this.posted_by = posted_by;}

	/** 投稿日時 */
	private	java.sql.Timestamp	posted_on = null;
	public	java.sql.Timestamp	getPostedOn(){return this.posted_on;}
	public	void	setPostedOn (java.sql.Timestamp posted_on){this.posted_on = posted_on;}

	/** 題 */
	private	String	subject	= null;
	public	String	getSubject(){return this.subject;}
	public	void	setSubject (String subject){this.subject = subject;}

	/** 本文 */
	private	String	voice_body = null;
	public	String	getVoiceBody(){return this.voice_body;}
	public	void	setVoiceBody (String voice_body){this.voice_body = voice_body;}



	// 以下 user_profileレコードの情報
	/** ユーザ名 */
	private	String	user_name = null;
	public	String	getUserName(){return this.user_name;}
	public	void	setUserName (String user_name){this.user_name = user_name;}

	/** 性別： "FEMALE" / "MALE" / "NOT_SPECIFIED" */
	private	String	gender = null;
	public	String	getGender(){return this.gender;}
	public	void	setGender (String gender){this.gender = gender;}

	/** 年齢 */
	private	int	age = 0;
	public	int	getAge(){return this.age;}
	public	void	setAge (int age){this.age = age;}

	/**
	 * 一覧表示用検索。
	 * @param ch
	 * @param condition "where"を含まない検索条件句。
	 * @return
	 * @throws SQLException
	 */
	public	static	Voice[]	selectVoicesForList (DBConnectionHolder ch, String condition)	throws	SQLException
	{
		ArrayList<Voice> list = new ArrayList<Voice>();

		String sql = "select voice_id, posted_by, posted_on, subject, voice_body,"
				+ " user_name, gender, age"
				+ "  from voice, user_profile"
				+ "\r\nwhere posted_by = user_id";
		if (condition != null) {
			sql += " AND " + condition;
		}
		
		sql += "\r\norder by posted_on desc";

		Connection con = ch.getConnection();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery (sql);
			while (rs.next()) {
				list.add (fetch (rs, new Voice ()));
			}

			return list.toArray (new Voice[0]);
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

	private	static	Voice	fetch (ResultSet rs, Voice d)	throws	SQLException
	{
		d.voice_id = rs.getString ("voice_id");
		d.posted_by = rs.getString ("posted_by");
		d.posted_on = rs.getTimestamp ("posted_on");
		d.subject = rs.getString ("subject");
		d.voice_body = rs.getString ("voice_body");
		d.user_name = rs.getString ("user_name");
		d.gender = rs.getString ("gender");
		d.age = rs.getInt ("age");

		return d;
	}

	/**
	 * 削除対象を検索する。対象は指定ユーザの投稿分のみ。
	 * @param ch
	 * @param voice_id
	 * @param user_id
	 * @return
	 * @throws SQLException
	 */
	public	static	Voice	selectToDelete (DBConnectionHolder ch, String voice_id, String user_id)	throws	SQLException
	{
		String condition = "voice_id = " + SQLUtil.getDBStringExpression (voice_id)
					+ " AND user_id = " + SQLUtil.getDBStringExpression (user_id);
		Voice[] list = selectVoicesForList (ch, condition);
		if (list.length == 0) {
			return null;
		}
		else {
			return list[0];
		}
	}

	/**
	 * テーブルへの新規レコードの挿入。
	 * @param ch
	 * @return
	 * @throws SQLException
	 */
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "insert into voice values ("
				+ SQLUtil.getDBStringExpression (voice_id)
				+ ", " + SQLUtil.getDBStringExpression (posted_by)
				+ ", now()"
				+ ", " + SQLUtil.getDBStringExpression (subject)
				+ ", " + SQLUtil.getDBStringExpression (voice_body)
				+ ")";

		return super.executeUpdate (ch, sql);
	}

	/**
	 * テーブルから削除する。
	 * @param ch
	 * @return
	 * @throws SQLException
	 */
	public	int	delete (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "delete from voice where voice_id = " + SQLUtil.getDBStringExpression (voice_id);

		return super.executeUpdate (ch, sql);
	}
}
