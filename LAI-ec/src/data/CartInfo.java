package data;

import java.util.ArrayList;
import java.util.HashMap;

import dao.TCustomer;
import dao.TOdrDetail;
import dao.TOdrHeader;
import fw.DBConnectionHolder;

/**
 * 買物カゴ管理。
 */
public class CartInfo
{
	/** 新規インスタンスを生成する */
	public	static	CartInfo	newInstance (DBConnectionHolder ch)
	{
		CartInfo cart = new CartInfo();
		cart.init(ch);
		return cart;
	}

	/** インスタンスを読み込む（マイページ用） */
	public	static	CartInfo	loadInstance (DBConnectionHolder ch, TCustomer customer, long odr_id)
	{
		TOdrHeader header = TOdrHeader.select(ch, odr_id);
		if (header == null || customer.getCustomer_id() != header.getCustomer_id())
			return null;

		CartInfo cart = new CartInfo();
		cart.customer = customer;
		cart.header = header;
		cart.init(ch);
		return cart;
	}

	private	CartInfo(){}

	/** 新規顧客である */
	private	boolean	is_new_customer = false;
	/** 新規顧客である */
	public	boolean	getIs_new_customer(){return this.is_new_customer;}
	/** 顧客 */
	private	TCustomer	customer = null;
	/** 顧客をセットする */
	public	void	setCustomer(TCustomer customer){this.customer = customer;}
	/** 新規扱いの顧客をセットする */
	public	void	setnewCustomer(TCustomer customer)
	{
		this.customer = customer;
		this.is_new_customer = true;
	}
	/** 顧客を取得する */
	public	TCustomer	getCustomer(){return this.customer;}

	/** 受注伝票 */
	TOdrHeader	header = null;
	/** 受注伝票を取得する */
	public	TOdrHeader	getHeader(){return this.header;}

	/** 受注明細情報リスト */
	ArrayList<OdrDetailInfo>	detail_list = new ArrayList<OdrDetailInfo>();
	/** 受注明細情報リストを取得する */
	public	ArrayList<OdrDetailInfo>	getDetail_list(){return this.detail_list;}

	/** 受注明細マップ */
	HashMap<String, OdrDetailInfo>	detail_map = new HashMap<String, OdrDetailInfo>();

	/** ポイント付与率 */	// ポイント付与機能追加
	private double point_rate = 0.02;

	public	String	toString()
	{
		// ポイント付与機能追加：BEGIN
		return "{" + is_new_customer + "," + customer + "," + header + "," + detail_list + "," + detail_map
				  + "," + point_rate + "}";
		// ポイント付与機能追加：END
	}

	private	void	init (DBConnectionHolder ch)
	{
		this.point_rate = ((double)ch.getInt("CartInfo.Point.Rate"))/100;
		if (header == null)	//	新規受注としてのカート初期化
			initForNew (ch);
		else
			initForLoadedOdr (ch);
	}

	private	void	initForNew (DBConnectionHolder ch)
	{
		header = new TOdrHeader();
	}

	private	void	initForLoadedOdr (DBConnectionHolder ch)
	{
		detail_list = OdrDetailInfo.loadList(ch, header.getOdr_id());
		for (OdrDetailInfo odi : detail_list)
			detail_map.put(odi.prd.getPrd_cd(), odi);
	}

	/**
	 * 商品コードに対応する明細行を取得する。
	 */
	public	OdrDetailInfo	getDetail (DBConnectionHolder ch,  String prd_cd)
	{
		return detail_map.get(prd_cd);
	}

	/**
	 * 買物カゴに商品を追加する。実行後には別途recalculate(DBConnectionHolder ch)を呼び出す必要がある。
	 * @see recalculate(DBConnectionHolder ch)
	 */
	public	OdrDetailInfo	addProduct (DBConnectionHolder ch,  String prd_cd)	throws BadCodeException
	{
		OdrDetailInfo odi = detail_map.get(prd_cd);
		if (odi != null)
			return odi;

		odi = OdrDetailInfo.newInstance(ch, prd_cd);
		detail_list.add(odi);
		detail_map.put(prd_cd, odi);
		return odi;
	}

	/**
	 * 買物カゴから商品を削除する。実行後には別途recalculate(DBConnectionHolder ch)を呼び出す必要がある。
	 * @see recalculate(DBConnectionHolder ch)
	 */
	public	OdrDetailInfo	removeProduct (DBConnectionHolder ch,  String prd_cd)	throws BadCodeException
	{
		OdrDetailInfo odi = detail_map.get(prd_cd);
		if (odi == null)
			throw new BadCodeException ("prd_cd", prd_cd);

		detail_list.remove(odi);
		detail_map.remove(prd_cd);

		return odi;
	}

	/**
	 * 現在の買物カゴの内容で再計算する。
	 */
	public	void	recalculate (DBConnectionHolder ch)
	{
		// ポイント付与機能追加：BEGIN
		long product_total_price = 0;
		long total_points_to_gain = 0;
		for (OdrDetailInfo odi : detail_list)
		{
			TOdrDetail d = odi.detail;
			product_total_price += d.getTotal_price();
			long points_to_gain = (long)(d.getTotal_price() * this.point_rate);
			d.setPoints_to_gain(points_to_gain);;
			total_points_to_gain += points_to_gain;
		}

		header.setProduct_total_price(product_total_price);
		header.setTotal_payment(product_total_price);
		header.setTotal_points_to_gain(total_points_to_gain);

		}
}
