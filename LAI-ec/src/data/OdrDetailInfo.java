package data;

import java.util.ArrayList;
import java.util.HashMap;

import dao.TOdrDetail;
import dao.TPrd;
import ec.ECConst;
import fw.DBConnectionHolder;

/**
 * 受注明細関連情報。
 */
public class OdrDetailInfo
{
	/** 新規インスタンスを生成する */
	public	static	OdrDetailInfo	newInstance (DBConnectionHolder ch, String prd_cd)	throws BadCodeException
	{
		TPrd prd = TPrd.selectWithPrdCd(ch, prd_cd);
		if (prd == null)
			throw new BadCodeException ("prd_cd", prd_cd);

		OdrDetailInfo odi = new OdrDetailInfo();
		odi.init(ch, prd);
		return odi;
	}

	/** 指定された受注番号に対応するレコードを読み込む */
	public	static	ArrayList<OdrDetailInfo>	loadList (DBConnectionHolder ch, long odr_id)
	{
		ArrayList<TOdrDetail>	dlist = TOdrDetail.selectForOdr(ch, odr_id);
		ArrayList<OdrDetailInfo> result = new ArrayList<OdrDetailInfo>();
		for (TOdrDetail d : dlist)
		{
			OdrDetailInfo odi = new OdrDetailInfo();
			odi.init(ch, d);
			result.add(odi);
		}

		return result;
	}

	private	OdrDetailInfo(){}

	/** 商品 */
	public	TPrd	prd = null;
	/** 商品を取得する */
	public	TPrd	getPrd(){return this.prd;}

	/** 受注明細 */
	public	TOdrDetail	detail = null;
	/** 受注明細を取得する */
	public	TOdrDetail	getDetail(){return this.detail;}

	/** 個数選択selectの要素 */
	private	HashMap<Integer, Integer> quantity_sel_elements = new HashMap<Integer, Integer>();
	/** 個数選択selectの要素を取得する。 */
	public	HashMap<Integer, Integer> getQuantity_sel_elements(){return this.quantity_sel_elements;}

	public	String	toString()
	{
		return "{" + prd + "," + detail + "," + quantity_sel_elements + "}";
	}

	private	void	init (DBConnectionHolder ch, TPrd prd)
	{
		this.prd = prd;
		detail = new TOdrDetail();
		detail.setPrd_id(prd.getPrd_id());
		detail.setUnit_price(prd.getSelling_price());
		detail.setOdr_prd_nm(prd.getPrd_nm());
		detail.setOdr_dd_desc(prd.getDd_desc());

		createQuantitySelectElements();
		setQuantity(1);	//	初期値として個数を1とする
	}

	private	void	init (DBConnectionHolder ch, TOdrDetail detail)
	{
		this.detail = detail;
		this.prd = TPrd.select(ch, detail.getPrd_id());

		createQuantitySelectElements();
	}

	private	void	createQuantitySelectElements()
	{
		for (int i = 1; i <= ECConst.Order.MAX_ITEM_QUANTITY; i++)
			quantity_sel_elements.put(i, i);
	}

	public	void	setQuantity (int quantity)
	{
		detail.setQuantity(quantity);
		detail.setTotal_price(detail.getUnit_price() * quantity);
	}
}
