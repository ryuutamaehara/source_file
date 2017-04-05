package ec;

import java.util.ArrayList;

import dao.TCustomer;
import dao.TOdrHeader;
import dao.TPaymentMethod;
import dao.TQuest;
import data.CartInfo;
import data.OdrDetailInfo;
import fw.AbstractAction;

public class OrderConfirmAction extends AbstractAction
{
	private	TOdrHeader	header = null;
	public	TOdrHeader	getHeader(){return this.header;}

	private	TCustomer	customer = null;
	public	TCustomer	getCustomer(){return this.customer;}

	private	ArrayList<OdrDetailInfo>	detail_list = null;
	public	ArrayList<OdrDetailInfo>	getDetail_list(){return this.detail_list;}

	private	String	payment_method_nm = null;
	public	String	getPayment_method_nm(){return this.payment_method_nm;}

	private	String	payment_method_sub_nm = null;
	public	String	getPayment_method_sub_nm(){return this.payment_method_sub_nm;}

	@Override
	public	String execute()	throws Exception
	{
		CartInfo cart = super.getCart();
		header = cart.getHeader();
		customer = cart.getCustomer();
		detail_list = cart.getDetail_list();

		preparePaymentInfo();

		return SUCCESS;
	}

	private	void	preparePaymentInfo()
	{
		int pm_id = header.getPayment_method_id();
		TPaymentMethod pm = TPaymentMethod.select(this, pm_id);
		payment_method_nm = pm.getPayment_method_nm();

		if (pm_id == TPaymentMethod.QUEST)
		{
			TQuest quest = TQuest.select(this, header.getPayment_method_sub_cd());
			payment_method_sub_nm = quest.getQuest_nm();
		}
	}
}
