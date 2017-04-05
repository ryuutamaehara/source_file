package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * t_prd（商品テーブル）へのアクセス機能を提供する。
 */
public class TPrd extends AbstractDAO<TPrd>
{
	/** 販売状況ID : 販売中 (10) */
	public	final	static	int	SS_ON_SALE = 10;
	/** 販売状況ID : 在庫限り (50) */
	public	final	static	int	SS_ONLY_STOCK = 50;
	/** 販売状況ID : 販売中止中 (70) */
	public	final	static	int	SS_SUSPENDED = 70;
	/** 販売状況ID : 販売終了 (80) */
	public	final	static	int	SS_TERMINATED = 80;

	/** 商品ID */
	private	long	prd_id = 0;
	/** 商品IDを取得する */
	public	long	getPrd_id(){return this.prd_id;}

	/** 商品コード */
	private	String	prd_cd = null;
	/** 商品コードを取得する */
	public	String	getPrd_cd(){return this.prd_cd;}

	/** 商品名 */
	private	String	prd_nm = null;
	/** 商品名を取得する */
	public	String	getPrd_nm(){return this.prd_nm;}

	/** 商品名カナ */
	private	String	prd_nm_kn = null;
	/** 商品名カナを取得する */
	public	String	getPrd_nm_kn(){return this.prd_nm_kn;}

	/** URL用商品モデル名 */
	private	String	prd_nm_for_url = null;
	/** URL用商品モデル名を取得する */
	public	String	getPrd_nm_for_url(){return this.prd_nm_for_url;}

	/** 納期目安 */
	private	String	dd_desc = null;
	/** 納期目安を取得する */
	public	String	getDd_desc(){return this.dd_desc;}

	/** 販売状況ID */
	private	int	selling_situ_id = 40;
	/** 販売状況IDを取得する */
	public	int	getSelling_situ_id(){return this.selling_situ_id;}

	/** 公開中である */
	private	boolean	is_on_view = true;
	/** 公開中であるを取得する */
	public	boolean	getIs_on_view(){return this.is_on_view;}

	/** 有効である */
	private	boolean	is_active = true;
	/** 有効であるを取得する */
	public	boolean	getIs_active(){return this.is_active;}

	/** 定価 */
	private	long	list_price = 0;
	/** 定価を取得する */
	public	long	getList_price(){return this.list_price;}

	/** 販売価格 */
	private	long	selling_price = 0;
	/** 販売価格を取得する */
	public	long	getSelling_price(){return this.selling_price;}

	/** 仕入単価 */
	private	long	purchase_price = 0;
	/** 仕入単価を取得する */
	public	long	getPurchase_price(){return this.purchase_price;}

	/** 表示開始日時 */
	private	java.sql.Timestamp	display_start_dttm = null;
	/** 表示開始日時を取得する */
	public	java.sql.Timestamp	getDisplay_start_dttm(){return this.display_start_dttm;}

	/** 表示終了日時 */
	private	java.sql.Timestamp	display_end_dttm = null;
	/** 表示終了日時を取得する */
	public	java.sql.Timestamp	getDisplay_end_dttm(){return this.display_end_dttm;}

	/** 販売開始日時 */
	private	java.sql.Timestamp	selling_start_dttm = null;
	/** 販売開始日時を取得する */
	public	java.sql.Timestamp	getSelling_start_dttm(){return this.selling_start_dttm;}

	/** 販売終了日時 */
	private	java.sql.Timestamp	selling_end_dttm = null;
	/** 販売終了日時を取得する */
	public	java.sql.Timestamp	getSelling_end_dttm(){return this.selling_end_dttm;}

	/** 取り寄せが必要 */
	private	boolean	is_on_back_order = false;
	/** 取り寄せが必要を取得する */
	public	boolean	getIs_on_back_order(){return this.is_on_back_order;}

	/** 主カテゴリコード */
	private	String	leading_category_cd = null;
	/** 主カテゴリコードを取得する */
	public	String	getLeading_category_cd(){return this.leading_category_cd;}

	/** 説明 */
	private	String	description = null;
	/** 説明を取得する */
	public	String	getDescription(){return this.description;}

	/** レビュー対象である */
	private	boolean	is_review_target = false;
	/** レビュー対象であるを取得する */
	public	boolean	getIs_review_target(){return this.is_review_target;}

	/** メモ（備考） */
	private	String	memo = null;
	/** メモ（備考）を取得する */
	public	String	getMemo(){return this.memo;}


	@Override
	public	String	toString()
	{
		return "{" +
				prd_id + "," +
				prd_cd + "," +
				prd_nm + "," +
				prd_nm_kn + "," +
				prd_nm_for_url + "," +
				dd_desc + "," +
				selling_situ_id + "," +
				is_on_view + "," +
				is_active + "," +
				list_price + "," +
				selling_price + "," +
				purchase_price + "," +
				display_start_dttm + "," +
				display_end_dttm + "," +
				selling_start_dttm + "," +
				selling_end_dttm + "," +
				is_on_back_order + "," +
				leading_category_cd + "," +
				description + "," +
				is_review_target + "," +
				memo + "}";
	}

	/** 商品IDを指定して１件取得する */
	public	static	TPrd	select (DBConnectionHolder ch, long prd_id)
	{
		String condition = "WHERE prd_id = " + prd_id;
		ArrayList<TPrd> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 商品コードを指定して１件取得する */
	public	static	TPrd	selectWithPrdCd (DBConnectionHolder ch, String prd_cd)
	{
		String condition = "WHERE prd_cd =" + fw.SQLUtil.getDBStringExpression(prd_cd);
		ArrayList<TPrd> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TPrd>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TPrd> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TPrd>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT prd_id,prd_cd,prd_nm,prd_nm_kn,prd_nm_for_url, "
				+ "dd_desc, "
				+ "selling_situ_id,is_on_view,is_active, "
				+ "list_price,selling_price,purchase_price, "
				+ "display_start_dttm,display_end_dttm,selling_start_dttm,selling_end_dttm, "
				+ "is_on_back_order,leading_category_cd, "
				+ "description,is_review_target,memo"
				+ "\r\nFROM t_prd";



		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;


		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TPrd>	selector = new Selector<TPrd>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TPrd>
	{
		@Override
		public	TPrd	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TPrd d = new TPrd();

			d.prd_id = rs.getLong ("prd_id");
			d.prd_cd = rs.getString ("prd_cd");
			d.prd_nm = rs.getString ("prd_nm");
			d.prd_nm_kn = rs.getString ("prd_nm_kn");
			d.prd_nm_for_url = rs.getString ("prd_nm_for_url");
			d.dd_desc = rs.getString ("dd_desc");
			d.selling_situ_id = rs.getInt ("selling_situ_id");
			d.is_on_view = rs.getBoolean ("is_on_view");
			d.is_active = rs.getBoolean ("is_active");
			d.list_price = rs.getLong ("list_price");
			d.selling_price = rs.getLong ("selling_price");
			d.purchase_price = rs.getLong ("purchase_price");
			d.display_start_dttm = rs.getTimestamp ("display_start_dttm");
			d.display_end_dttm = rs.getTimestamp ("display_end_dttm");
			d.selling_start_dttm = rs.getTimestamp ("selling_start_dttm");
			d.selling_end_dttm = rs.getTimestamp ("selling_end_dttm");
			d.is_on_back_order = rs.getBoolean ("is_on_back_order");
			d.leading_category_cd = rs.getString ("leading_category_cd");
			d.description = rs.getString ("description");
			d.is_review_target = rs.getBoolean ("is_review_target");
			d.memo = rs.getString ("memo");

			return d;
		}
	}
}
