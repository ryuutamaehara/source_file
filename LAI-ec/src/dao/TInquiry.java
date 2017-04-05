package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.SQLUtil;
import fw.StringUtil;

/**
 * t_inquiry（問合せテーブル）へのアクセス機能を提供する。
 */
public class TInquiry extends AbstractDAO<TInquiry>
{
	/** 問合せステータスID : 新規受付 (10) */
	public	final	static	int	NEW = 10;
	/** 問合せステータスID : 保留中 (20) */
	public	final	static	int	PENDING = 20;
	/** 問合せステータスID : 対応中 (30) */
	public	final	static	int	IN_PROCESS = 30;
	/** 問合せステータスID : 対応完了 (40) */
	public	final	static	int	COMPLETED = 40;
	/** 問合せステータスID : キャンセル (80) */
	public	final	static	int	CANCELED = 80;

	/** 問合せID */
	private	long	inq_id = 0;
	/** 問合せIDをセットする */
	public	void	setInq_id (long inq_id){this.inq_id = inq_id;}
	/** 問合せID取得する */
	public	long	getInq_id(){return this.inq_id;}

	/** 顧客ID */
	private	long	customer_id = 0;
	/** 顧客IDをセットする */
	public	void	setCustomer_id (long customer_id){this.customer_id = customer_id;}
	/** 顧客ID取得する */
	public	long	getCustomer_id(){return this.customer_id;}

	/** 問合せ受付日時 */
	private	java.sql.Timestamp	accepted_on = null;
	/** 問合せ受付日時をセットする */
	public	void	setAccepted_on(java.sql.Timestamp accepted_on){this.accepted_on = accepted_on;}
	/** 問合せ受付日時取得する */
	public	java.sql.Timestamp	getAccepted_on(){return this.accepted_on;}

	/** 問合せステータスID */
	private	int	inq_status_id = 0;
	/** 問合せステータスIDをセットする */
	public	void	setInq_status_id (int inq_status_id){this.inq_status_id = inq_status_id;}
	/** 問合せステータスID取得する */
	public	int	getInq_status_id(){return this.inq_status_id;}

	/** 問合せタイトル */
	private	String	inq_subject = null;
	/** 問合せタイトルをセットする */
	public	void	setInq_subject (String inq_subject){this.inq_subject = inq_subject;}
	/** 問合せタイトル取得する */
	public	String	getInq_subject(){return this.inq_subject;}

	/** 問合せ本文 */
	private	String	inq_body = null;
	/** 問合せ本文をセットする */
	public	void	setInq_body (String inq_body){this.inq_body = inq_body;}
	/** 問合せ本文取得する */
	public	String	getInq_body(){return this.inq_body;}

	@Override
	public String toString()
	{
		return "{" +
				inq_id + "," +
				customer_id + "," +
				accepted_on + "," +
				inq_status_id + "," +
				inq_subject + "," +
				inq_body + "}";
	}

	/** 指定された顧客のレコードを新しいものから順に取得する */
	public	static	ArrayList<TInquiry>	selectForCustomer (DBConnectionHolder ch, long customer_id)
	{
		String condition =" WHERE customer_id =" + customer_id;
		String order_by = "ORDER BY accepted_on DESC ";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TInquiry>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TInquiry> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TInquiry>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "inq_id, customer_id, accepted_on, inq_status_id," +
					" inq_subject, inq_body" +
					 " \r\nFROM t_inquiry";
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TInquiry>	selector = new Selector<TInquiry>();
	private	static	MyFetcher	fetcher = new MyFetcher();

	private	static	class	MyFetcher	implements	Fetcher<TInquiry>
	{
		@Override
		public	TInquiry	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TInquiry d = new TInquiry();

			d.inq_id = rs.getLong ("inq_id");
			d.customer_id = rs.getLong ("customer_id");
			d.accepted_on = rs.getTimestamp ("accepted_on");
			d.inq_status_id = rs.getInt ("inq_status_id");
			d.inq_subject = rs.getString ("inq_subject");
			d.inq_body = rs.getString ("inq_body");

			return d;
		}
	}

	/** 自インスタンスのデータをテーブルにinsertする。 */
	public	int	insert (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "INSERT INTO t_inquiry (" +
				"inq_id , customer_id , accepted_on, inq_status_id, inq_subject, inq_body) " +
				"VALUES (" +
				inq_id + "," + customer_id + ",now()," + inq_status_id + "," +
				SQLUtil.getDBStringExpression(inq_subject) + "," +
				SQLUtil.getDBStringExpression(inq_body) + ")";


		return super.executeUpdate(ch, sql);
	}
}
