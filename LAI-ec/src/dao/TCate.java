package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * t_cate（カテゴリテーブル）へのアクセス機能を提供する。
 */
public class TCate extends AbstractDAO<TCate>
{
	/** カテゴリコード */
	private	String	cate_cd = null;
	/** カテゴリコードを取得する */
	public	String	getCate_cd(){return cate_cd;}

	/** マスターカテゴリコード */
	private	String	master_cate_cd = null;
	/** マスターカテゴリコードを取得する */
	public	String	getMaster_cate_cd(){return master_cate_cd;}

	/** カテゴリ名 */
	private	String	cate_nm = null;
	/** カテゴリ名を取得する */
	public	String	getCate_nm(){return cate_nm;}

	/** カテゴリ名（カナ） */
	private	String	kana_cate_nm = null;
	/** カテゴリ名（カナ）を取得する */
	public	String	getKana_cate_nm(){return kana_cate_nm;}

	/** URL用カテゴリ名 */
	private	String	cate_nm_for_url = null;
	/** URL用カテゴリ名を取得する */
	public	String	getCate_nm_for_url(){return cate_nm_for_url;}

	/** 表示順 */
	private	long	display_order = 0;
	/** 表示順を取得する */
	public	long	getDisplay_order(){return display_order;}

	@Override
	public	String	toString()
	{
		return "{" +
				cate_cd + "," +
				master_cate_cd + "," +
				cate_nm + "," +
				kana_cate_nm + "," +
				cate_nm_for_url + "," +
				display_order + "}";
	}

	/**
	 * 指定されたカテゴリコードにマッチするレコードを取得する。
	 * @param ch
	 * @param cate_cd
	 * @return
	 */
	public	static	TCate	select (DBConnectionHolder ch, String cate_cd)
	{
		String condition = "WHERE cate_cd = " + fw.SQLUtil.getDBStringExpression(cate_cd);
		ArrayList<TCate> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/**
	 * 全件取得する。
	 * @param ch
	 * @return
	 */
	public	static	ArrayList<TCate>	selectAll (DBConnectionHolder ch)
	{
		return _select (ch, "ORDER BY master_cate_cd,display_order");
	}

	private	static	ArrayList<TCate>	_select (DBConnectionHolder ch, String sql_option)
	{
		//SELECT文をsqlにセット
		String sql = "SELECT cate_cd,master_cate_cd,cate_nm, "
					+ "kana_cate_nm,cate_nm_for_url, "
					+ "display_order "
					+ "\r\nFROM t_cate";

		//sql_optionから引数を受け取り、sqlに改行コードと一緒に結合する。
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TCate>	selector = new Selector<TCate>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TCate>
	{
		@Override
		public	TCate	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TCate d = new TCate();

			d.cate_cd = rs.getString ("cate_cd");
			d.master_cate_cd = rs.getString ("master_cate_cd");
			d.cate_nm = rs.getString ("cate_nm");
			d.kana_cate_nm = rs.getString ("kana_cate_nm");
			d.cate_nm_for_url = rs.getString ("cate_nm_for_url");
			d.display_order = rs.getInt ("display_order");

			return d;
		}
	}
}
