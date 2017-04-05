package ec;

import dao.TPrdSalesInfo;
import data.OdrDetailInfo;

/** 在庫が足りななくなってしまったことを通知する例外 */
public class InventoryShortageException	extends	OrderProcessException
{
	private	OdrDetailInfo	di = null;
	public	OdrDetailInfo	getOdrDetailInfo(){return this.di;}

	private	TPrdSalesInfo	si = null;
	public	TPrdSalesInfo	getPrdSalesInfo(){return this.si;}

	public	InventoryShortageException (OdrDetailInfo di, TPrdSalesInfo si)
	{
		super ("商品「" + di.prd.getPrd_nm() + "」の在庫が足りなくなりました。確保可能な在庫数は "
				+ si.getAllocatable_quantity() + " 個です。");
		this.di = di;
		this.si = si;
	}
}
