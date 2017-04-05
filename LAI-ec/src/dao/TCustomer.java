package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.HashUtil;
import fw.SQLUtil;
import fw.StringUtil;

/**
 * t_customer（顧客テーブル）へのアクセス機能を提供する。
 */
public class TCustomer extends AbstractDAO<TCustomer>
{
	/** 顧客ID */
	private	long	customer_id = 0;
	/** 顧客IDをセットする */
	public	void	setCustomer_id (long customer_id){this.customer_id = customer_id;}
	/** 顧客IDを取得する */
	public	long	getCustomer_id(){return this.customer_id;}

	/** ログイン名 */
	private	String	login_nm = null;
	/** ログイン名をセットする */
	public	void	setLogin_nm (String login_nm){this.login_nm = login_nm;}
	/** ログイン名を取得する */
	public	String	getLogin_nm(){return this.login_nm;}

	/** メールアドレス */
	private	String	email = null;
	/** メールアドレスをセットする */
	public	void	setEmail (String email){this.email = email;}
	/** メールアドレスを取得する */
	public	String	getEmail(){return this.email;}

	/** パスワード */
	private	String	password = null;
	/** パスワードをハッシュ化してセットする */
	public	void	setPassword (String password){this.password = HashUtil.getInstance().generateHash(password);}

	/** 顧客名 */
	private	String	customer_nm = null;
	/** 顧客名をセットする */
	public	void	setCustomer_nm (String customer_nm){this.customer_nm = customer_nm;}
	/** 顧客名を取得する */
	public	String	getCustomer_nm(){return this.customer_nm;}

	/** 住所１ */
	private	String	address_1 = null;
	/** 住所１をセットする */
	public	void	setAddress_1 (String address_1){this.address_1 = address_1;}
	/** 住所１を取得する */
	public	String	getAddress_1(){return this.address_1;}

	/** 住所２ */
	private	String	address_2 = null;
	/** 住所２をセットする */
	public	void	setAddress_2 (String address_2){this.address_2 = address_2;}
	/** 住所２を取得する */
	public	String	getAddress_2(){return this.address_2;}

	/** 住所３ */
	private	String	address_3 = null;
	/** 住所３をセットする */
	public	void	setAddress_3 (String address_3){this.address_3 = address_3;}
	/** 住所３を取得する */
	public	String	getAddress_3(){return this.address_3;}

	/** 住所４ */
	private	String	address_4 = null;
	/** 住所４をセットする */
	public	void	setAddress_4 (String address_4){this.address_4 = address_4;}
	/** 住所４を取得する */
	public	String	getAddress_4(){return this.address_4;}

	@Override
	public	String	toString()
	{
		return "{" +
				customer_id + "," +
				login_nm + "," +
				email + "," +
				password + "," +
				customer_nm + "," +
				address_1 + "," +
				address_2 + "," +
				address_3 + "," +
				address_4 + "}";
	}

	/** 顧客IDを指定して１件取得する */
	public	static	TCustomer	select (DBConnectionHolder ch, long customer_id)
	{
		String condition = "WHERE customer_id = " + customer_id;
		ArrayList<TCustomer> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 認証用	にレコードを取得する */
	public	static	TCustomer	selectForAuth (DBConnectionHolder ch, String login_nm, String password)
	{
		password = HashUtil.getInstance().generateHash(password);
		String condition = "WHERE login_nm = " + SQLUtil.getDBStringExpression(login_nm)
				+ "AND password = " + SQLUtil.getDBStringExpression(password);
		ArrayList<TCustomer> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 既存確認用にレコードを取得する */
	public	static	TCustomer	selectWithLoginNm (DBConnectionHolder ch, String login_nm)
	{
		String condition = "WHERE login_nm = "+ SQLUtil.getDBStringExpression (login_nm);
		ArrayList<TCustomer> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TCustomer>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TCustomer> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TCustomer>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "customer_id,login_nm,email,password,customer_nm," +
				"address_1,address_2,address_3,address_4 "
				+ "\r\nFROM t_customer";
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TCustomer>	selector = new Selector<TCustomer>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TCustomer>
	{
		@Override
		public	TCustomer	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TCustomer d = new TCustomer();

			d.customer_id = rs.getLong ("customer_id");
			d.login_nm = rs.getString ("login_nm");
			d.email = rs.getString ("email");
//			d.password = rs.getString ("password");	パスワードは登録・変更する場合のみ値をセットする想定
			d.customer_nm = rs.getString ("customer_nm");
			d.address_1 = rs.getString ("address_1");
			d.address_2 = rs.getString ("address_2");
			d.address_3 = rs.getString ("address_3");
			d.address_4 = rs.getString ("address_4");

			return d;
		}
	}

	/** 自インスタンスのデータをテーブルにinsertする。 */
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_customer "
				+ "(customer_id,login_nm,email,password,customer_nm,address_1,address_2,address_3,address_4)"
				+ " VALUE (?,?,?,?,?,?,?,?,?)";
		Connection con = ch.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setLong(1, customer_id);
			st.setString(2,login_nm);
			st.setString(3,email);
			st.setString(4,password);
			st.setString(5,customer_nm);
			st.setString(6,address_1);
			st.setString(7,address_2);
			st.setString(8,address_3);
			st.setString(9,address_4);
			return st.executeUpdate();
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}
/*
 * Statementを使用する場合の実装
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_customer " +
				"(customer_id, login_nm, email, password, customer_nm, address_1, address_2, address_3, address_4) " +
				"VALUES (" +
				customer_id + "," +
				SQLUtil.getDBStringExpression (login_nm) + "," +
				SQLUtil.getDBStringExpression (email) + "," +
				SQLUtil.getDBStringExpression (password) + "," +
				SQLUtil.getDBStringExpression (customer_nm) + "," +
				SQLUtil.getDBStringExpression (address_1) + "," +
				SQLUtil.getDBStringExpression (address_2) + "," +
				SQLUtil.getDBStringExpression (address_3) + "," +
				SQLUtil.getDBStringExpression (address_4) +
				")";

		return super.executeUpdate(ch, sql);
	}
*/
	/** 自インスタンスのデータを使用してテーブルをupdateする。 */
	public	int	update (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_customer SET "
				+ "login_nm = ?,"
				+ "email = ?,"
				+ "customer_nm = ?,"
				+ "address_1 = ?,"
				+ "address_2 = ?,"
				+ "address_3 = ?,"
				+ "address_4 = ?"
				+ "\r\nWHERE customer_id =?";
		Connection con = ch.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString(1,login_nm);
			st.setString(2, email);
			st.setString(3, customer_nm);
			st.setString(4, address_1);
			st.setString(5, address_2);
			st.setString(6, address_3);
			st.setString(7, address_4);
			st.setLong(8, customer_id);
			return st.executeUpdate();
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}

/*
 * Statementを使用する場合の実装
	public	int	update (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_customer SET " +
				"login_nm = " + SQLUtil.getDBStringExpression (login_nm) + "," +
				"email = " + SQLUtil.getDBStringExpression (email) + "," +
				// 通常の更新時にはパスワードを更新しない
//				"password = " + SQLUtil.getDBStringExpression (password) + "," +
				"customer_nm = " + SQLUtil.getDBStringExpression (customer_nm) + "," +
				"address_1 = " + SQLUtil.getDBStringExpression (address_1) + "," +
				"address_2 = " + SQLUtil.getDBStringExpression (address_2) + "," +
				"address_3 = " + SQLUtil.getDBStringExpression (address_3) + "," +
				"address_4 = " + SQLUtil.getDBStringExpression (address_4) +
				"\r\nWHERE customer_id = " + customer_id;

		return super.executeUpdate(ch, sql);
	}
*/

	/** パスワードを更新する。 */
	public	int	updatePassword (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_customer SET " +
						"password = ?" +
						"\r\nWHERE customer_id = ?";
		Connection con = ch.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setString (1, password);
			st.setLong(2, customer_id);

			return st.executeUpdate();
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}

/*
 * Statementを使用する場合の実装
	public	int	updatePassword (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_customer SET " +
						"password = " + SQLUtil.getDBStringExpression (password) +
						"\r\nWHERE customer_id = " + customer_id;

		return super.executeUpdate(ch, sql);
	}
*/
}
