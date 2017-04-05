package ec.mypage;

import java.util.ArrayList;

import dao.TCustomer;
import dao.VOdrHeader;
import fw.AbstractAction;

/** マイページトップ */
public class MyPageTopAction extends AbstractAction
{
	private	TCustomer	customer = null;
	public	TCustomer	getCustomer(){return this.customer;}

	private	ArrayList<VOdrHeader>	order_list = null;
	public	ArrayList<VOdrHeader>	getOrder_list(){return this.order_list;}

	@Override
	public	String	execute()
	{
		customer = super.getCurrentCustomer();
		order_list = VOdrHeader.selectForCustomer(this, customer.getCustomer_id());
		return SUCCESS;
	}
}
