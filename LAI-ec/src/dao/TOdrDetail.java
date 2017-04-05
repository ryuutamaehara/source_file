package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * t_odr_detail（受注明細テーブル）へのアクセス機能を提供する。
 */
public class TOdrDetail extends AbstractDAO<TOdrDetail>
{
	/** 注文番号 */
	private	long	odr_id = 0;
	/** 注文番号をセットする */
	public	void	setOdr_id (long odr_id){this.odr_id = odr_id;}
	/** 注文番号を取得する */
	public	long	getOdr_id(){return this.odr_id;}

	/** 顧客ID */
	private	long	customer_id = 0;
	/** 顧客IDをセットする */
	public	void	setCustomer_id (long customer_id){this.customer_id = customer_id;}
	/** 顧客IDを取得する */
	public	long	getCustomer_id(){return this.customer_id;}

	/** 明細番号 */
	private	int	detail_num = 0;
	/** 明細番号をセットする */
	public	void	setDetail_num (int detail_num){this.detail_num = detail_num;}
	/** 明細番号を取得する */
	public	int	getDetail_num(){return this.detail_num;}

	/** 商品ID */
	private	long	prd_id = 0;
	/** 商品IDをセットする */
	public	void	setPrd_id (long prd_id){this.prd_id = prd_id;}
	/** 商品IDを取得する */
	public	long	getPrd_id(){return this.prd_id;}

	/** 数量 */
	private	int	quantity = 0;
	/** 数量をセットする */
	public	void	setQuantity (int quantity){this.quantity = quantity;}
	/** 数量を取得する */
	public	int	getQuantity(){return this.quantity;}

	/** 単価 */
	private	long	unit_price = 0;
	/** 単価をセットする */
	public	void	setUnit_price (long unit_price){this.unit_price = unit_price;}
	/** 単価を取得する */
	public	long	getUnit_price(){return this.unit_price;}

	/** 小計 */
	private	long	total_price = 0;
	/** 小計をセットする */
	public	void	setTotal_price (long total_price){this.total_price = total_price;}
	/** 小計を取得する */
	public	long	getTotal_price(){return this.total_price;}

	/** 獲得予定ポイント**/
	private long points_to_gain = 0;
	/** 獲得予定ポイントをセットする */
	public void setPoints_to_gain(long points_to_gain) {this.points_to_gain = points_to_gain;}
	/** 獲得予定ポイントを取得する */
	public long getPoints_to_gain() {return points_to_gain;}


	/** 注文ステータスID */
	private	int	odr_status_id = 0;
	/** 注文ステータスIDをセットする */
	public	void	setOdr_status_id (int odr_status_id){this.odr_status_id = odr_status_id;}
	/** 注文ステータスIDを取得する */
	public	int	getOdr_status_id(){return this.odr_status_id;}

	/** 受注時商品名 */
	private	String	odr_prd_nm = null;
	/** 受注時商品名をセットする */
	public	void	setOdr_prd_nm (String odr_prd_nm){this.odr_prd_nm = odr_prd_nm;}
	/** 受注時商品名を取得する */
	public	String	getOdr_prd_nm(){return this.odr_prd_nm;}

	/** 受注時納期目安 */
	private	String	odr_dd_desc = null;
	/** 受注時納期目安をセットする */
	public	void	setOdr_dd_desc (String odr_dd_desc){this.odr_dd_desc = odr_dd_desc;}
	/** 受注時納期目安を取得する */
	public	String	getOdr_dd_desc(){return this.odr_dd_desc;}

	@Override
	public	String	toString()
	{
		return "{" +
				+ odr_id + ","
				+ customer_id + ","
				+ detail_num + ","
				+ prd_id + ","
				+ quantity + ","
				+ unit_price + ","
				+ total_price + ","
				+ points_to_gain + ","
				+ odr_status_id + ","
				+ odr_prd_nm + ","
				+ odr_dd_desc + "}";
	}




	/** 注文番号と明細番号を指定して１件取得する */
	public	static	TOdrDetail	select (DBConnectionHolder ch, long odr_id, int detail_num)
	{
		String condition = "WHERE odr_id = " + odr_id + " AND detail_num = " + detail_num;
		ArrayList<TOdrDetail> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 指定された注文番号のレコードを明細番号の若いものから順に取得する */
	public	static	ArrayList<TOdrDetail>	selectForOdr (DBConnectionHolder ch, long odr_id)
	{
		String condition = "WHERE odr_id = " + odr_id;
		String order_by = "ORDER BY detail_num ASC";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TOdrDetail>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TOdrDetail> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TOdrDetail>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "odr_id, customer_id, detail_num, prd_id, quantity, unit_price, " +
				"total_price, points_to_gain, odr_status_id, odr_prd_nm, odr_dd_desc"
				+ "\r\nFROM t_odr_detail";
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TOdrDetail>	selector = new Selector<TOdrDetail>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TOdrDetail>
	{
		@Override
		public	TOdrDetail	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TOdrDetail d = new TOdrDetail();

			d.odr_id = rs.getLong ("odr_id");
			d.customer_id = rs.getLong ("customer_id");
			d.detail_num = rs.getInt ("detail_num");
			d.prd_id = rs.getLong ("prd_id");
			d.quantity = rs.getInt ("quantity");
			d.unit_price = rs.getLong ("unit_price");
			d.total_price = rs.getLong ("total_price");
			d.points_to_gain = rs.getLong ("points_to_gain");
			d.odr_status_id = rs.getInt ("odr_status_id");
			d.odr_prd_nm = rs.getString("odr_prd_nm");
			d.odr_dd_desc = rs.getString("odr_dd_desc");

			return d;
		}
	}

	/** 自インスタンスのデータをテーブルにinsertする。 */
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_odr_detail " +
				"(odr_id, customer_id, detail_num, prd_id, " +
				"quantity, unit_price, total_price, points_to_gain, odr_status_id, " +
				"odr_prd_nm, odr_dd_desc) " +
				"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		Connection con = ch.getConnection();
		PreparedStatement st = null;
		try {
			st = con.prepareStatement(sql);
			st.setLong (1, odr_id);
			st.setLong (2, customer_id);
			st.setInt (3, detail_num);
			st.setLong (4, prd_id);
			st.setInt (5, quantity);
			st.setLong (6, unit_price);
			st.setLong (7, total_price);
			st.setLong (8, points_to_gain);
			st.setInt (9, odr_status_id);
			st.setString (10, odr_prd_nm);
			st.setString (11, odr_dd_desc);

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
		String sql = "INSERT INTO t_odr_detail " +
				"(odr_id, customer_id, detail_num, prd_id, " +
				// ポイント付与機能追加：BEGIN
				"quantity, unit_price, total_price, points_to_gain, odr_status_id, " +
				// ポイント付与機能追加：END
				"odr_prd_nm, odr_dd_desc) " +
				"VALUES (" +
				odr_id + "," +
				customer_id + "," +
				detail_num + "," +
				prd_id + "," +
				quantity + "," +
				unit_price + "," +
				total_price + "," +
				// ポイント付与機能追加：BEGIN
				points_to_gain + "," +
				// ポイント付与機能追加：END
				odr_status_id + "," +
				SQLUtil.getDBStringExpression (odr_prd_nm) + "," +
				SQLUtil.getDBStringExpression (odr_dd_desc) +
				")";

		return super.executeUpdate(ch, sql);
	}
*/
}
