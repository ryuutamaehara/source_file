package ec.mypage;

import java.util.ArrayList;

import dao.TCustomer;
import dao.TOdrHeader;
import dao.TOdrStatus;
import dao.TPaymentMethod;
import dao.TQuest;
import data.CartInfo;
import data.OdrDetailInfo;
import fw.AbstractAction;

public class MyPageOdrDisplayAction extends AbstractAction
{
	private	long	odr_id = 0;
	public	void	setOdr_id(long odr_id){this.odr_id = odr_id;}

	private	TOdrHeader	header = null;
	public	TOdrHeader	getHeader(){return this.header;}

	private	TCustomer	customer = null;
	public	TCustomer	getCustomer(){return this.customer;}

	private	ArrayList<OdrDetailInfo>	detail_list = null;
	public	ArrayList<OdrDetailInfo>	getDetail_list(){return this.detail_list;}

	private	String	odr_status_nm = null;
	public	String	getOdr_status_nm(){return this.odr_status_nm;}

	private	String	payment_method_nm = null;
	public	String	getPayment_method_nm(){return this.payment_method_nm;}

	private	String	payment_method_sub_nm = null;
	public	String	getPayment_method_sub_nm(){return this.payment_method_sub_nm;}

	public	String	getCancel_button_disabled()
	{
		return this.header.getOdr_status_id() != TOdrStatus.NEW ? "disabled" : "";
	}

	@Override
	public	String execute()	throws Exception
	{
		customer = super.getCurrentCustomer();
		CartInfo cart = CartInfo.loadInstance(this, customer, odr_id);
		if (cart == null)
			return super.displayError(super.getText("Message.ODR_NOT_FOUND"));

		header = cart.getHeader();
		customer = cart.getCustomer();
		detail_list = cart.getDetail_list();

		prepareOtherInfo();

		return SUCCESS;
	}

	private	void	prepareOtherInfo()
	{
		TOdrStatus os = TOdrStatus.select (this, header.getOdr_status_id());
		odr_status_nm = os.getOdr_status_nm();

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
