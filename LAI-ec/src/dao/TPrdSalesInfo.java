package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.SQLUtil;
import fw.StringUtil;

/**
 * t_prd_sales_info（商品販売情報テーブル）へのアクセス機能を提供する。
 */
public class TPrdSalesInfo extends AbstractDAO<TPrdSalesInfo>
{
	/** 商品ID */
	private	long	prd_id = 0;
	/** 商品IDを取得する */
	public	long	getPrd_id(){return this.prd_id;}

	/** 商品コード */
	private	String	prd_cd = null;
	/** 商品コードを取得する */
	public	String	getPrd_cd(){return this.prd_cd;}
	/** 商品コードをセットする */
	public	void	setPrd_cd(String prd_cd){this.prd_cd = prd_cd;}

	/** 引当可能数 */
	private	int	allocatable_quantity = 0;
	/** 引当可能数を取得する */
	public	int	getAllocatable_quantity(){return this.allocatable_quantity;}
	/** 引当可能数をセットする */
	public	void	setAllocatable_quantity(int allocatable_quantity){this.allocatable_quantity = allocatable_quantity;}

	/** 初期在庫数 */
	private	int	initial_inv_quantity = 0;
	/** 初期在庫数を取得する */
	public	int	getInitial_inv_quantity(){return this.initial_inv_quantity;}
	/** 引当可能数をセットする */
	public	void	setInitial_inv_quantity(int initial_inv_quantity){this.initial_inv_quantity = initial_inv_quantity;}

	/** 販売済数 */
	private	int	sold_quantity = 0;
	/** 販売済数を取得する */
	public	int	getSold_quantity(){return this.sold_quantity;}
	/** 販売済数をセットする */
	public	void	setSold_quantity(int sold_quantity){this.sold_quantity = sold_quantity;}


	@Override
	public	String	toString()
	{
		return "{" +
				prd_id + "," +
				prd_cd + "," +
				allocatable_quantity + "," +
				initial_inv_quantity + "," +
				sold_quantity + "}";
	}

	/** 商品ID（複数件対応）を指定して該当レコードを"for update"指定付きで取得する */
	public	static	ArrayList<TPrdSalesInfo>	selectForUpdate (DBConnectionHolder ch, long[] prd_id_list)
	{
		if (prd_id_list.length == 0)
			throw new RuntimeException ("商品IDリストが空です。");

		String sql_option = "WHERE " + SQLUtil.createInCondition("prd_id", prd_id_list) + "\r\nFOR UPDATE";
		return _select (ch, sql_option);
	}

	/** 自由な検索条件を指定してヒットするレコードを取得する */
	public	static	ArrayList<TPrdSalesInfo>	selectWithCondition (DBConnectionHolder ch, String condition, String order_by)
	{
		String sql_option = (condition != null ? condition : "");
		if (StringUtil.isNotEmpty(order_by))
			sql_option = sql_option + "\r\n" + order_by;

		ArrayList<TPrdSalesInfo> result = _select (ch, sql_option);
		return result;
	}

	private	static	ArrayList<TPrdSalesInfo>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT prd_id,prd_cd,allocatable_quantity,initial_inv_quantity,sold_quantity"
				+ " \r\nFROM t_prd_sales_info";
		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;


		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TPrdSalesInfo>	selector = new Selector<TPrdSalesInfo>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TPrdSalesInfo>
	{
		@Override
		public	TPrdSalesInfo	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TPrdSalesInfo d = new TPrdSalesInfo();

			d.prd_id = rs.getLong ("prd_id");
			d.prd_cd = rs.getString ("prd_cd");
			d.allocatable_quantity = rs.getInt ("allocatable_quantity");
			d.initial_inv_quantity = rs.getInt ("initial_inv_quantity");
			d.sold_quantity = rs.getInt ("sold_quantity");

			return d;
		}
	}

	/** レコードを更新する */
	public	int	update (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_prd_sales_info SET " +
				"prd_cd = " + SQLUtil.getDBStringExpression (prd_cd) + "," +
				"allocatable_quantity = " + allocatable_quantity + "," +
				"initial_inv_quantity = " + initial_inv_quantity + "," +
				"sold_quantity = " + sold_quantity +
				"\r\nWHERE prd_id = " + prd_id;

		return super.executeUpdate(ch, sql);
	}
}
