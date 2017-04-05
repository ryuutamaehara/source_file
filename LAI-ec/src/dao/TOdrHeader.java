package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * t_odr_header（受注伝票テーブル）へのアクセス機能を提供する。
 */
public class TOdrHeader extends AbstractDAO<TOdrHeader>
{
	/** 支払ステータスID : 未入金 (10) */
	public	final	static	int	PS_UNPAID = 10;
	/** 支払ステータスID : 入金済 (30) */
	public	final	static	int	PS_PAID = 30;
	/** 支払ステータスID : 入金期限切れ (70) */
	public	final	static	int	PS_EXPIRED = 70;
	/** 支払ステータスID : キャンセル (80) */
	public	final	static	int	PS_CANCELED = 80;

	/** お届け先種別（注文者自宅・その他） : 自宅へお届け (1) */
	public	final	static	int	DT_MY_HOME = 1;
	/** お届け先種別（注文者自宅・その他） : その他へお届け (2) */
	public	final	static	int	DT_OTHER = 2;

	/** 注文番号 */
	private	long	odr_id = 0;
	/** 注文番号をセットする */
	public	void	setOdr_id (long odr_id){this.odr_id = odr_id;}
	/** 注文番号を取得する */
	public	long	getOdr_id(){return this.odr_id;}

	/** 受注日時 */
	private	java.sql.Timestamp	accepted_on = null;
	/** 受注日時をセットする */
	public	void	setAccepted_on (java.sql.Timestamp	accepted_on){this.accepted_on = accepted_on;}
	/** 受注日時を取得する */
	public	java.sql.Timestamp	getAccepted_on(){return this.accepted_on;}

	/** 顧客ID */
	private	long	customer_id = 0;
	/** 顧客IDをセットする */
	public	void	setCustomer_id (long customer_id){this.customer_id = customer_id;}
	/** 顧客IDを取得する */
	public	long	getCustomer_id(){return this.customer_id;}

	/** 注文ステータスID */
	private	int	odr_status_id = 10;
	/** 注文ステータスIDをセットする */
	public	void	setOdr_status_id (int odr_status_id){this.odr_status_id = odr_status_id;}
	/** 注文ステータスIDを取得する */
	public	int	getOdr_status_id(){return this.odr_status_id;}

	/** 支払方法ID */
	private	int	payment_method_id = 10;
	/** 支払方法IDをセットする */
	public	void	setPayment_method_id (int payment_method_id){this.payment_method_id = payment_method_id;}
	/** 支払方法IDを取得する */
	public	int	getPayment_method_id(){return this.payment_method_id;}

	/** 支払方法補助コード */
	public	String	payment_method_sub_cd = null;
	/** 支払方法補助コードをセットする */
	public	void	setPayment_method_sub_cd(String payment_method_sub_cd){this.payment_method_sub_cd = payment_method_sub_cd;}
	/** 支払方法補助コードを取得する */
	public	String	getPayment_method_sub_cd(){return this.payment_method_sub_cd;};

	/** 支払いステータスID */
	private	int	payment_status_id = 0;
	/** 支払いステータスIDをセットする */
	public	void	setPayment_status_id (int payment_status_id){this.payment_status_id = payment_status_id;}
	/** 支払いステータスIDを取得する */
	public	int	getPayment_status_id(){return this.payment_status_id;}

	/** 商品合計金額 */
	private	long	product_total_price = 0;
	/** 商品合計金額をセットする */
	public	void	setProduct_total_price (long product_total_price){this.product_total_price = product_total_price;}
	/** 商品合計金額を取得する */
	public	long	getProduct_total_price(){return this.product_total_price;}

	/** 獲得ポイント**/
	private long total_points_to_gain = 0;
	/** 獲得ポイントをセットする */
	public void setTotal_points_to_gain(long total_points_to_gain) {this.total_points_to_gain = total_points_to_gain;}
	/** 獲得ポイントを取得する */
	public long getTotal_points_to_gain() {	return total_points_to_gain;}

	/** 値引き額 */
	private	long	discounted_value = 0;
	/** 値引き額をセットする */
	public	void	setDiscounted_value (long discounted_value){this.discounted_value = discounted_value;}
	/** 値引き額を取得する */
	public	long	getDiscounted_value(){return this.discounted_value;}

	/** 総支払額 */
	private	long	total_payment = 0;
	/** 総支払額をセットする */
	public	void	setTotal_payment (long total_payment){this.total_payment = total_payment;}
	/** 総支払額を取得する */
	public	long	getTotal_payment(){return this.total_payment;}

	/** お届け先種別（注文者自宅・その他） */
	private	int	deliv_to = 1;
	/** お届け先種別（注文者自宅・その他）をセットする */
	public	void	setDeliv_to (int deliv_to){this.deliv_to = deliv_to;}
	/** お届け先種別（注文者自宅・その他）を取得する */
	public	int	getDeliv_to(){return this.deliv_to;}

	/** 顧客名 */
	private	String	customer_nm = null;
	/** 顧客名をセットする */
	public	void	setCustomer_nm (String customer_nm){this.customer_nm = customer_nm;}
	/** 顧客名を取得する */
	public	String	getCustomer_nm(){return this.customer_nm;}

	/** 顧客住所１ */
	private	String	address_1 = null;
	/** 顧客住所１をセットする */
	public	void	setAddress_1 (String address_1){this.address_1 = address_1;}
	/** 顧客住所１を取得する */
	public	String	getAddress_1(){return this.address_1;}

	/** 顧客住所２ */
	private	String	address_2 = null;
	/** 顧客住所２をセットする */
	public	void	setAddress_2 (String address_2){this.address_2 = address_2;}
	/** 顧客住所２を取得する */
	public	String	getAddress_2(){return this.address_2;}

	/** 顧客住所３ */
	private	String	address_3 = null;
	/** 顧客住所３をセットする */
	public	void	setAddress_3 (String address_3){this.address_3 = address_3;}
	/** 顧客住所３を取得する */
	public	String	getAddress_3(){return this.address_3;}

	/** 顧客住所４ */
	private	String	address_4 = null;
	/** 顧客住所４をセットする */
	public	void	setAddress_4 (String address_4){this.address_4 = address_4;}
	/** 顧客住所４を取得する */
	public	String	getAddress_4(){return this.address_4;}

	/** お届け先名 */
	private	String	dt_nm = null;
	/** お届け先名をセットする */
	public	void	setDt_nm (String dt_nm){this.dt_nm = dt_nm;}
	/** お届け先名を取得する */
	public	String	getDt_nm(){return this.dt_nm;}

	/** お届け先住所１ */
	private	String	dt_address_1 = null;
	/** お届け先住所１をセットする */
	public	void	setDt_address_1 (String dt_address_1){this.dt_address_1 = dt_address_1;}
	/** お届け先住所１を取得する */
	public	String	getDt_address_1(){return this.dt_address_1;}

	/** お届け先住所２ */
	private	String	dt_address_2 = null;
	/** お届け先住所２をセットする */
	public	void	setDt_address_2 (String dt_address_2){this.dt_address_2 = dt_address_2;}
	/** お届け先住所２を取得する */
	public	String	getDt_address_2(){return this.dt_address_2;}

	/** お届け先住所３ */
	private	String	dt_address_3 = null;
	/** お届け先住所３をセットする */
	public	void	setDt_address_3 (String dt_address_3){this.dt_address_3 = dt_address_3;}
	/** お届け先住所３を取得する */
	public	String	getDt_address_3(){return this.dt_address_3;}

	/** お届け先住所４ */
	private	String	dt_address_4 = null;
	/** お届け先住所４をセットする */
	public	void	setDt_address_4 (String dt_address_4){this.dt_address_4 = dt_address_4;}
	/** お届け先住所４を取得する */
	public	String	getDt_address_4(){return this.dt_address_4;}

	@Override
	public	String	toString()
	{
		return "{" +
				odr_id + "," +
				accepted_on + "," +
				customer_id + "," +
				odr_status_id + "," +
				payment_method_id + "," +
				payment_method_sub_cd + "," +
				payment_status_id + "," +
				product_total_price + "," +
				total_points_to_gain + "," +
				discounted_value + "," +
				total_payment + "," +
				deliv_to + "," +
				customer_nm + "," +
				address_1 + "," +
				address_2 + "," +
				address_3 + "," +
				address_4 + "," +
				dt_nm + "," +
				dt_address_1 + "," +
				dt_address_2 + "," +
				dt_address_3 + "," +
				dt_address_4 + "}";
	}




	/** 注文番号を指定して１件取得する */
	public	static	TOdrHeader	select (DBConnectionHolder ch, long odr_id)
	{
		String condition = "WHERE odr_id = " + odr_id;
		ArrayList<TOdrHeader> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 注文番号を指定して更新用に１件取得する */
	public	static	TOdrHeader	selectForUpdate (DBConnectionHolder ch, long odr_id)
	{
		String condition = "WHERE odr_id = " + odr_id + " FOR UPDATE";
		ArrayList<TOdrHeader> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 指定された顧客のレコードを新しいものから順に取得する */
	public	static	ArrayList<TOdrHeader>	selectForCustomer (DBConnectionHolder ch, long customer_id)
	{
		String condition = "WHERE customer_id = " + customer_id;
		String order_by = "ORDER BY accepted_on DESC";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TOdrHeader>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TOdrHeader> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TOdrHeader>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "odr_id, accepted_on, customer_id, odr_status_id, " +
				"payment_method_id, payment_method_sub_cd, payment_status_id, " +
				"product_total_price, total_points_to_gain, discounted_value, total_payment, deliv_to, customer_nm, " +
				"address_1, address_2, address_3, address_4, dt_nm, " +
				"dt_address_1, dt_address_2, dt_address_3, dt_address_4"
				+ "\r\nFROM t_odr_header";

		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TOdrHeader>	selector = new Selector<TOdrHeader>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TOdrHeader>
	{
		@Override
		public	TOdrHeader	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TOdrHeader d = new TOdrHeader();

			d.odr_id = rs.getLong ("odr_id");
			d.accepted_on = rs.getTimestamp ("accepted_on");
			d.customer_id = rs.getLong ("customer_id");
			d.odr_status_id = rs.getInt ("odr_status_id");
			d.payment_method_id = rs.getInt ("payment_method_id");
			d.payment_method_sub_cd = rs.getString("payment_method_sub_cd");
			d.payment_status_id = rs.getInt ("payment_status_id");
			d.product_total_price = rs.getLong ("product_total_price");
			d.total_points_to_gain = rs.getLong ("total_points_to_gain");
			d.discounted_value = rs.getLong ("discounted_value");
			d.total_payment = rs.getLong ("total_payment");
			d.deliv_to = rs.getInt ("deliv_to");
			d.customer_nm = rs.getString ("customer_nm");
			d.address_1 = rs.getString ("address_1");
			d.address_2 = rs.getString ("address_2");
			d.address_3 = rs.getString ("address_3");
			d.address_4 = rs.getString ("address_4");
			d.dt_nm = rs.getString ("dt_nm");
			d.dt_address_1 = rs.getString ("dt_address_1");
			d.dt_address_2 = rs.getString ("dt_address_2");
			d.dt_address_3 = rs.getString ("dt_address_3");
			d.dt_address_4 = rs.getString ("dt_address_4");

			return d;
		}
	}




	/** 自インスタンスのデータをテーブルにinsertする。 */
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_odr_header " +
				"(odr_id, accepted_on, customer_id, odr_status_id, " +
				"payment_method_id, payment_method_sub_cd, payment_status_id, " +
				"product_total_price, total_points_to_gain, discounted_value, total_payment, deliv_to, customer_nm, " +
				"address_1, address_2, address_3, address_4, " +
				"dt_nm, dt_address_1, dt_address_2, dt_address_3, dt_address_4) " +
				"VALUES (?, now(), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = ch.getConnection();
		PreparedStatement st = null;
		int ret = 0;
		try {
			st = con.prepareStatement(sql);
			st.setLong (1, odr_id);
			st.setLong (2, customer_id);
			st.setInt (3, odr_status_id);
			st.setInt (4, payment_method_id);
			st.setString (5, payment_method_sub_cd);
			st.setInt (6, payment_status_id);
			st.setLong (7, product_total_price);
			st.setLong (8, total_points_to_gain);
			st.setLong (9, discounted_value);
			st.setLong (10, total_payment);
			st.setInt (11, deliv_to);
			st.setString (12, customer_nm);
			st.setString (13, address_1);
			st.setString (14, address_2);
			st.setString (15, address_3);
			st.setString (16, address_4);
			st.setString (17, dt_nm);
			st.setString (18, dt_address_1);
			st.setString (19, dt_address_2);
			st.setString (20, dt_address_3);
			st.setString (21, dt_address_4);

			ret = st.executeUpdate();
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}

		refreshAcceptedOn(ch);
		return ret;
	}
/*
 * Statementを使用する場合の実装
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_odr_header " +
				"(odr_id, accepted_on, customer_id, odr_status_id, " +
				"payment_method_id, payment_method_sub_cd, payment_status_id, " +
				// ポイント付与機能追加：BEGIN
				"product_total_price, total_points_to_gain, discounted_value, total_payment, deliv_to, customer_nm, " +
				// ポイント付与機能追加：END
				"address_1, address_2, address_3, address_4, " +
				"dt_nm, dt_address_1, dt_address_2, dt_address_3, dt_address_4) " +
				"VALUES (" +
				odr_id + ", now()," + customer_id + "," +
				odr_status_id + "," + payment_method_id + "," +
				SQLUtil.getDBStringExpression (payment_method_sub_cd) + "," + payment_status_id + "," +
				// ポイント付与機能追加：BEGIN
				product_total_price + "," + total_points_to_gain + "," + discounted_value + "," + total_payment + "," +
				// ポイント付与機能追加：END
				deliv_to + "," +
				SQLUtil.getDBStringExpression (customer_nm) + "," +
				SQLUtil.getDBStringExpression (address_1) + "," +
				SQLUtil.getDBStringExpression (address_2) + "," +
				SQLUtil.getDBStringExpression (address_3) + "," +
				SQLUtil.getDBStringExpression (address_4) + "," +
				SQLUtil.getDBStringExpression (dt_nm) + "," +
				SQLUtil.getDBStringExpression (dt_address_1) + "," +
				SQLUtil.getDBStringExpression (dt_address_2) + "," +
				SQLUtil.getDBStringExpression (dt_address_3) + "," +
				SQLUtil.getDBStringExpression (dt_address_4) +
				")";

		int ret = super.executeUpdate(ch, sql);
		refreshAcceptedOn(ch);
		return ret;
	}
*/

	private	void	refreshAcceptedOn (DBConnectionHolder ch)	throws SQLException
	{
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			con = ch.getConnection();
			st = con.prepareStatement("SELECT accepted_on FROM t_odr_header WHERE odr_id = ?");
			st.setLong(1, odr_id);
			rs = st.executeQuery();
			rs.next();
			accepted_on = rs.getTimestamp(1);
		} finally {
			if (rs != null)
				try {rs.close();} catch (SQLException e){}
			if (st != null)
				try {st.close();} catch (SQLException e){}
		}
	}

	/** 自インスタンスのデータを使用してテーブルのステータス系項目をupdateする。 */
	public	int	updateStatus (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_odr_header SET " +
				"odr_status_id = " + odr_status_id + "," +
				"payment_status_id = " + payment_status_id +
				"\r\nWHERE odr_id = " + odr_id;

		return super.executeUpdate(ch, sql);
	}
}
