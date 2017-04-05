package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.StringUtil;

/**
 * v_prd_rec（商品レコメンドビュー）へのアクセス機能を提供する。
 */
public class VPrdRec extends AbstractDAO<VPrdRec>
{
	/** 商品レコメンドID */
	private	long	prd_rec_id = 0;
	/** 商品レコメンドIDを取得 */
	public	long	getPrd_rec_id(){return this.prd_rec_id;}

	/** 商品ID */
	private	long	prd_id = 0;
	/** 商品IDを取得 */
	public	long	getPrd_id(){return this.prd_id;}

	/** 有効開始日時 */
	private	java.sql.Timestamp	valid_start_dttm = null;
	/** 有効開始日時を取得 */
	public	java.sql.Timestamp	getValid_start_dttm(){return this.valid_start_dttm;}

	/** 有効終了日時 */
	private	java.sql.Timestamp	valid_end_dttm = null;
	/** 有効終了日時を取得 */
	public	java.sql.Timestamp	getValid_end_dttm(){return this.valid_end_dttm;}

	/** 有効である（レコメンド） */
	private	boolean	r_is_active = true;
	/** 有効である（レコメンド）を取得 */
	public	boolean	getR_is_active(){return this.r_is_active;}

	/** 公開中である（レコメンド） */
	private	boolean	r_is_on_view = true;
	/** 公開中である（レコメンド）を取得 */
	public	boolean	getR_is_on_view(){return this.r_is_on_view;}

	/** 表示順 */
	private	long	display_order = 10;
	/** 表示順を取得 */
	public	long	getDisplay_order(){return this.display_order;}

	/** 説明（レコメンド） */
	private	String	r_description = null;
	/** 説明（レコメンド）を取得 */
	public	String	getR_description(){return this.r_description;}

	/** 商品コード */
	private	String	prd_cd = null;
	/** 商品コードを取得 */
	public	String	getPrd_cd(){return this.prd_cd;}

	/** 商品名 */
	private	String	prd_nm = null;
	/** 商品名を取得 */
	public	String	getPrd_nm(){return this.prd_nm;}

	/** 商品名カナ */
	private	String	prd_nm_kn = null;
	/** 商品名カナを取得 */
	public	String	getPrd_nm_kn(){return this.prd_nm_kn;}

	/** URL用商品名 */
	private	String	prd_nm_for_url = null;
	/** URL用商品名を取得 */
	public	String	getPrd_nm_for_url(){return this.prd_nm_for_url;}

	/** 納期目安 */
	private	String	dd_desc = null;
	/** 納期目安を取得 */
	public	String	getDd_desc(){return this.dd_desc;}

	/** 販売状況ID */
	private	int	selling_situ_id = 40;
	/** 販売状況IDを取得 */
	public	int	getSelling_situ_id(){return this.selling_situ_id;}

	/** 公開中である（商品） */
	private	boolean	p_is_on_view = true;
	/** 公開中である（商品）を取得 */
	public	boolean	getP_is_on_view(){return this.p_is_on_view;}

	/** 有効である（商品） */
	private	boolean	p_is_active = true;
	/** 有効である（商品）を取得 */
	public	boolean	getP_is_active(){return this.p_is_active;}

	/** 定価 */
	private	long	list_price = 0;
	/** 定価を取得 */
	public	long	getList_price(){return this.list_price;}

	/** 販売価格 */
	private	long	selling_price = 0;
	/** 販売価格を取得 */
	public	long	getSelling_price(){return this.selling_price;}

	/** 仕入単価 */
	private	long	purchase_price = 0;
	/** 仕入単価を取得 */
	public	long	getPurchase_price(){return this.purchase_price;}

	/** 表示開始日時 */
	private	java.sql.Timestamp	display_start_dttm = null;
	/** 表示開始日時を取得 */
	public	java.sql.Timestamp	getDisplay_start_dttm(){return this.display_start_dttm;}

	/** 表示終了日時 */
	private	java.sql.Timestamp	display_end_dttm = null;
	/** 表示終了日時を取得 */
	public	java.sql.Timestamp	getDisplay_end_dttm(){return this.display_end_dttm;}

	/** 販売開始日時 */
	private	java.sql.Timestamp	selling_start_dttm = null;
	/** 販売開始日時を取得 */
	public	java.sql.Timestamp	getSelling_start_dttm(){return this.selling_start_dttm;}

	/** 販売終了日時 */
	private	java.sql.Timestamp	selling_end_dttm = null;
	/** 販売終了日時を取得 */
	public	java.sql.Timestamp	getSelling_end_dttm(){return this.selling_end_dttm;}

	/** 取り寄せが必要 */
	private	boolean	is_on_back_order = false;
	/** 取り寄せが必要を取得 */
	public	boolean	getIs_on_back_order(){return this.is_on_back_order;}

	/** 主カテゴリコード */
	private	String	leading_category_cd = null;
	/** 主カテゴリコードを取得 */
	public	String	getLeading_category_cd(){return this.leading_category_cd;}

	/** 説明（商品） */
	private	String	p_description = null;
	/** 説明（商品）を取得 */
	public	String	getP_description(){return this.p_description;}

	/** レビュー対象である */
	private	boolean	is_review_target = false;
	/** レビュー対象であるを取得 */
	public	boolean	getIs_review_target(){return this.is_review_target;}

	/** メモ（備考） */
	private	String	memo = null;
	/** メモ（備考）を取得 */
	public	String	getMemo(){return this.memo;}

	@Override
	public	String	toString()
	{
		return "{" +
				prd_rec_id + "," +
				prd_id + "," +
				valid_start_dttm + "," +
				valid_end_dttm + "," +
				r_is_active + "," +
				r_is_on_view + "," +
				display_order + "," +
				r_description + "," +
				prd_cd + "," +
				prd_nm + "," +
				prd_nm_kn + "," +
				prd_nm_for_url + "," +
				dd_desc + "," +
				selling_situ_id + "," +
				p_is_on_view + "," +
				p_is_active + "," +
				list_price + "," +
				selling_price + "," +
				purchase_price + "," +
				display_start_dttm + "," +
				display_end_dttm + "," +
				selling_start_dttm + "," +
				selling_end_dttm + "," +
				is_on_back_order + "," +
				leading_category_cd + "," +
				p_description + "," +
				is_review_target + "," +
				memo + "}";
	}

	/** 現在表示すべきレコードを表示順で指定された順に取得する */
	public	static	ArrayList<VPrdRec>	selectForNow (DBConnectionHolder ch)
	{
		String condition = "WHERE NOW() BETWEEN valid_start_dttm AND valid_end_dttm "
							+ "AND r_is_active = TRUE AND r_is_on_view = TRUE ";
		String order_by = "ORDER BY display_order ASC";
		return selectWithCondition (ch, condition, order_by);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<VPrdRec>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<VPrdRec> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<VPrdRec>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT prd_rec_id, prd_id, valid_start_dttm, valid_end_dttm, "
				+ "r_is_active, r_is_on_view, display_order, r_description, "
				+ "prd_cd, prd_nm, prd_nm_kn, prd_nm_for_url, dd_desc, "
				+ "selling_situ_id, p_is_on_view, p_is_active, "
				+ "list_price, selling_price, purchase_price, "
				+ "display_start_dttm, display_end_dttm, selling_start_dttm, selling_end_dttm, "
				+ "is_on_back_order, leading_category_cd, p_description, is_review_target, "
				+ "memo"
				+ "\r\nFROM v_prd_rec";

		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<VPrdRec>	selector = new Selector<VPrdRec>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<VPrdRec>
	{
		@Override
		public	VPrdRec	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			VPrdRec d = new VPrdRec();

			d.prd_rec_id = rs.getLong ("prd_rec_id");
			d.prd_id = rs.getLong ("prd_id");
			d.valid_start_dttm = rs.getTimestamp ("valid_start_dttm");
			d.valid_end_dttm = rs.getTimestamp ("valid_end_dttm");
			d.r_is_active = rs.getBoolean ("r_is_active");
			d.r_is_on_view = rs.getBoolean ("r_is_on_view");
			d.display_order = rs.getLong ("display_order");
			d.r_description = rs.getString ("r_description");
			d.prd_cd = rs.getString ("prd_cd");
			d.prd_nm = rs.getString ("prd_nm");
			d.prd_nm_kn = rs.getString ("prd_nm_kn");
			d.prd_nm_for_url = rs.getString ("prd_nm_for_url");
			d.dd_desc = rs.getString ("dd_desc");
			d.selling_situ_id = rs.getInt ("selling_situ_id");
			d.p_is_on_view = rs.getBoolean ("p_is_on_view");
			d.p_is_active = rs.getBoolean ("p_is_active");
			d.list_price = rs.getLong ("list_price");
			d.selling_price = rs.getLong ("selling_price");
			d.purchase_price = rs.getLong ("purchase_price");
			d.display_start_dttm = rs.getTimestamp ("display_start_dttm");
			d.display_end_dttm = rs.getTimestamp ("display_end_dttm");
			d.selling_start_dttm = rs.getTimestamp ("selling_start_dttm");
			d.selling_end_dttm = rs.getTimestamp ("selling_end_dttm");
			d.is_on_back_order = rs.getBoolean ("is_on_back_order");
			d.leading_category_cd = rs.getString ("leading_category_cd");
			d.p_description = rs.getString ("p_description");
			d.is_review_target = rs.getBoolean ("is_review_target");
			d.memo = rs.getString ("memo");

			return d;
		}
	}
}
