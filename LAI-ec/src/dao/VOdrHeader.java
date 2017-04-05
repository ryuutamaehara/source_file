package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * v_odr_header（受注伝票ビュー）へのアクセス機能を提供する。
 */
public class VOdrHeader extends AbstractDAO<VOdrHeader>
{
	/** 注文番号 */
	private	long	odr_id = 0;
	/** 注文番号を取得する */
	public	long	getOdr_id(){return this.odr_id;}

	/** 受注日時 */
	private	java.sql.Timestamp	accepted_on = null;
	/** 受注日時を取得する */
	public	java.sql.Timestamp	getAccepted_on(){return this.accepted_on;}

	/** 顧客ID */
	private	long	customer_id = 0;
	/** 顧客IDを取得する */
	public	long	getCustomer_id(){return this.customer_id;}

	/** 注文ステータスID */
	private	int	odr_status_id = 10;
	/** 注文ステータスIDを取得する */
	public	int	getOdr_status_id(){return this.odr_status_id;}

	/** 受注ステータス名 */
	private	String	odr_status_nm = null;
	/** 受注ステータス名を取得する */
	public	String	getOdr_status_nm(){return this.odr_status_nm;}

	@Override
	public	String	toString()
	{
		return "{" +
				odr_id + "," +
				accepted_on + "," +
				customer_id + "," +
				odr_status_id + "," +
				odr_status_nm + "}";
	}

	/** 注文番号を指定して１件取得する */
	public	static	VOdrHeader	select (DBConnectionHolder ch, long odr_id)
	{
		String condition = "WHERE odr_id = " + odr_id;
		ArrayList<VOdrHeader> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 指定された顧客のレコードを新しいものから順に取得する */
	public	static	ArrayList<VOdrHeader>	selectForCustomer (DBConnectionHolder ch, long customer_id)
	{
		String condition = "WHERE customer_id = " + customer_id;
		String order_by = "ORDER BY accepted_on DESC";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<VOdrHeader>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<VOdrHeader> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<VOdrHeader>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "odr_id, accepted_on, customer_id, odr_status_id, odr_status_nm"
				+ "\r\nFROM v_odr_header";
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<VOdrHeader>	selector = new Selector<VOdrHeader>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<VOdrHeader>
	{
		@Override
		public	VOdrHeader	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			VOdrHeader d = new VOdrHeader();

			d.odr_id = rs.getLong ("odr_id");
			d.accepted_on = rs.getTimestamp ("accepted_on");
			d.customer_id = rs.getLong ("customer_id");
			d.odr_status_id = rs.getInt ("odr_status_id");
			d.odr_status_nm = rs.getString ("odr_status_nm");

			return d;
		}
	}
}
