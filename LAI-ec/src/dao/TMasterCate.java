package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;

/**
 * t_master_cate（マスタカテゴリテーブル）へのアクセス機能を提供する。
 */
public class TMasterCate extends AbstractDAO<TMasterCate>
{
	/** マスターカテゴリコード */
	private	String	master_cate_cd = null;
	/** マスターカテゴリコードを取得する */
	public	String	getMaster_cate_cd(){return this.master_cate_cd;}

	/** マスターカテゴリ名 */
	private	String	master_cate_nm = null;
	/** マスターカテゴリ名を取得する */
	public	String	getMaster_cate_nm(){return this.master_cate_nm;}

	/** マスターカテゴリ名（カナ） */
	private	String	kana_master_cate_nm = null;
	/** マスターカテゴリ名（カナ）を取得する */
	public	String	getKana_master_cate_nm(){return this.kana_master_cate_nm;}

	/** URL用マスターカテゴリ名 */
	private	String	master_cate_nm_for_url = null;
	/** URL用マスターカテゴリ名を取得する */
	public	String	getMaster_cate_nm_for_url(){return this.master_cate_nm_for_url;}

	/** 表示順 */
	private	long	display_order = 0;
	/** 表示順を取得する */
	public	long	getDisplay_order(){return this.display_order;}

	@Override
	public	String	toString()
	{
		return "{" +
				master_cate_cd + "," +
				master_cate_nm + "," +
				kana_master_cate_nm + "," +
				master_cate_nm_for_url + "," +
				display_order + "}";
	}

	public	static	ArrayList<TMasterCate>	selectAll (DBConnectionHolder ch)
	{
		String sql = "SELECT " + "master_cate_cd, master_cate_nm,"
		+ "kana_master_cate_nm, master_cate_nm_for_url,"
		+ "display_order"
		+ "\r\nFROM t_master_cate"
		+ "\r\nORDER BY display_order";

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TMasterCate>	selector = new Selector<TMasterCate>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TMasterCate>
	{
		@Override
		public	TMasterCate	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TMasterCate d = new TMasterCate();

			d.master_cate_cd = rs.getString ("master_cate_cd");
			d.master_cate_nm = rs.getString ("master_cate_nm");
			d.kana_master_cate_nm = rs.getString ("kana_master_cate_nm");
			d.master_cate_nm_for_url = rs.getString ("master_cate_nm_for_url");
			d.display_order = rs.getInt ("display_order");

			return d;
		}
	}
}
