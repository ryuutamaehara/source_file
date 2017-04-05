package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * t_news（ニューステーブル）へのアクセス機能を提供する。
 */
public class TNews extends AbstractDAO<TNews>
{
	/** ニュース種別ID : 新着情報 (1) */
	public	final	static	long	NEWS_ARRIVAL = 1;
	/** ニュース種別ID : お知らせ (2) */
	public	final	static	long	NEWS_NOTICE = 2;

	/** ニュースID */
	private	long	news_id = 0;
	/** ニュースIDをセットする */
	public	void	setNews_id (long news_id){this.news_id = news_id;}
	/** ニュースIDを取得する */
	public	long	getNews_id(){return this.news_id;}

	/** ニュース種別ID */
	private	long	news_category_id = 0;
	/** ニュース種別IDをセットする */
	public	void	setNews_category_id (long news_category_id){this.news_category_id = news_category_id;}
	/** ニュース種別IDを取得する */
	public	long	getNews_category_id(){return this.news_category_id;}

	/** 有効開始日時 */
	private	java.sql.Timestamp	valid_start_dttm = null;
	/** 有効開始日時をセットする */
	public	void	setValid_start_dttm (java.sql.Timestamp valid_start_dttm){this.valid_start_dttm = valid_start_dttm;}
	/** 有効開始日時を取得する */
	public	java.sql.Timestamp	getValid_start_dttm(){return this.valid_start_dttm;}

	/** 有効終了日時 */
	private	java.sql.Timestamp	valid_end_dttm = null;
	/** 有効終了日時をセットする */
	public	void	setValid_end_dttm (java.sql.Timestamp valid_end_dttm){this.valid_end_dttm = valid_end_dttm;}
	/** 有効終了日時を取得する */
	public	java.sql.Timestamp	getValid_end_dttm(){return this.valid_end_dttm;}

	/** 表示順 */
	private	long	display_order = 10;
	/** 表示順をセットする */
	public	void	setDisplay_order (long display_order){this.display_order = display_order;}
	/** 表示順を取得する */
	public	long	getDisplay_order(){return this.display_order;}

	/** 有効である */
	private	boolean	is_active = false;
	/** 有効であるをセットする */
	public	void	setIs_active (boolean is_active){this.is_active = is_active;}
	/** 有効であるを取得する */
	public	boolean	getIs_active(){return this.is_active;}

	/** 公開中である */
	private	boolean	is_on_view = false;
	/** 公開中であるをセットする */
	public	void	setIs_on_view (boolean is_on_view){this.is_on_view = is_on_view;}
	/** 公開中であるを取得する */
	public	boolean	getIs_on_view(){return this.is_on_view;}

	/** タイトル */
	private	String	title = null;
	/** タイトルをセットする */
	public	void	setTitle (String title){this.title = title;}
	/** タイトルを取得する */
	public	String	getTitle(){return this.title;}

	/** 本文 */
	private	String	body = null;
	/** 本文をセットする */
	public	void	setBody (String body){this.body = body;}
	/** 本文を取得する */
	public	String	getBody(){return this.body;}

	@Override
	public	String	toString()
	{
		return "{" +
				news_id + "," +
				news_category_id + "," +
				valid_start_dttm + "," +
				valid_end_dttm + "," +
				display_order + "," +
				is_active + "," +
				is_on_view + "," +
				title + "," +
				body + "}";
	}

	/** ニュースIDを指定して１件取得する */
	public	static	TNews	select (DBConnectionHolder ch, long news_id)
	{
		String condition = "WHERE news_id = " + news_id;
		ArrayList<TNews> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 現在表示すべきレコードを新しい順に取得する */
	public	static	ArrayList<TNews>	selectForNow (DBConnectionHolder ch)
	{
		String condition = "WHERE  NOW() BETWEEN valid_start_dttm AND valid_end_dttm "
				+ "AND is_active = TRUE AND is_on_view = TRUE";
		String order_by = "ORDER BY valid_start_dttm DESC,display_order DESC";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TNews>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TNews> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TNews>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "news_id,news_category_id, "+
					"valid_start_dttm,valid_end_dttm, "+
					"display_order,is_active,is_on_view, "+
					"title,body "
					+"\r\nFROM t_news";

		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TNews>	selector = new Selector<TNews>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TNews>
	{
		@Override
		public	TNews	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TNews d = new TNews();

			d.news_id = rs.getLong ("news_id");
			d.news_category_id = rs.getLong ("news_category_id");
			d.valid_start_dttm = rs.getTimestamp ("valid_start_dttm");
			d.valid_end_dttm = rs.getTimestamp ("valid_end_dttm");
			d.display_order = rs.getLong ("display_order");
			d.is_active = rs.getBoolean ("is_active");
			d.is_on_view = rs.getBoolean ("is_on_view");
			d.title = rs.getString ("title");
			d.body = rs.getString ("body");

			return d;
		}
	}
}
