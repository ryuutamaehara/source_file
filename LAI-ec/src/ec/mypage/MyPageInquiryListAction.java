package ec.mypage;

import java.util.ArrayList;

import dao.TCustomer;
import dao.TInquiry;
import fw.AbstractAction;

/** 問合せ一覧のアクション */
public class MyPageInquiryListAction extends AbstractAction
{
	private	ArrayList<TInquiry>	inquiry_list = null;
	public	ArrayList<TInquiry>	getInquiry_list(){return this.inquiry_list;};

	@Override
	public	String execute()	throws Exception
	{
		TCustomer customer = super.getCurrentCustomer();
		inquiry_list = TInquiry.selectForCustomer(this, customer.getCustomer_id());
		return SUCCESS;
	}
}
