package ec;

import dao.TOdrHeader;
import dao.TPaymentMethod;
import data.CartInfo;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * 支払方法指定ページのアクション
 */
public class OrderPaymentAction extends AbstractAction
{
	private	String	command = null;
	public	void	setCommand(String command){this.command = command;}

	private	int	payment_method_id = 20;
	public	void	setPayment_method_id (int payment_method_id){this.payment_method_id = payment_method_id;}
	public	String	getPayment_method_id_cash_checked(){return payment_method_id == TPaymentMethod.CASH ? "CHECKED" : "";}
	public	String	getPayment_method_id_quest_checked(){return payment_method_id == TPaymentMethod.QUEST ? "CHECKED" : "";}

	private	String	payment_method_sub_cd = null;
	public	void	setPayment_method_sub_cd(String payment_method_sub_cd){this.payment_method_sub_cd = payment_method_sub_cd;}
	public	String	getPayment_method_sub_cd(){return this.payment_method_sub_cd;}

	@Override
	public	String	execute()
	{
		if (StringUtil.isEmpty(command))
		{
			initToDisplay();
			return INPUT;
		}

		accept();

		switch (command.toLowerCase())
		{
			case GO_BACK:
				return GO_BACK;
			case GO_NEXT:
				return GO_NEXT;
			default:
				return INPUT;
		}
	}

	private	void	initToDisplay()
	{
		CartInfo cart = super.getCart();
		if (cart == null)
			return;

		TOdrHeader h = cart.getHeader();
		payment_method_id = h.getPayment_method_id();
		payment_method_sub_cd = h.getPayment_method_sub_cd();
	}

	private	void	accept()
	{
		CartInfo cart = super.getCart();
		TOdrHeader h = cart.getHeader();
		h.setPayment_method_id(payment_method_id);
		if (payment_method_id == TPaymentMethod.QUEST)
			h.setPayment_method_sub_cd(payment_method_sub_cd);
		else
			h.setPayment_method_sub_cd(null);

	}
}
