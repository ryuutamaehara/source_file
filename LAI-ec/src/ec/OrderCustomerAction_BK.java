package ec;

import dao.TCustomer;
import dao.TOdrHeader;
import data.CartInfo;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * 注文：顧客情報入力ページのアクション。
 */
public class OrderCustomerAction_BK extends AbstractAction
{
	private	String	command = null;
	public	void	setCommand(String command){this.command = command;}

	private	String	customer_nm = null;
	public	void	setCustomer_nm(String customer_nm){this.customer_nm = customer_nm;}
	public	String	getCustomer_nm(){return this.customer_nm;}

	private	String	address_1 = null;
	public	void	setAddress_1(String address_1){this.address_1 = address_1;}
	public	String	getAddress_1(){return this.address_1;}

	private	String	address_2 = null;
	public	void	setAddress_2(String address_2){this.address_2 = address_2;}
	public	String	getAddress_2(){return this.address_2;}

	private	String	address_3 = null;
	public	void	setAddress_3(String address_3){this.address_3 = address_3;}
	public	String	getAddress_3(){return this.address_3;}

	private	String	address_4 = null;
	public	void	setAddress_4(String address_4){this.address_4 = address_4;}
	public	String	getAddress_4(){return this.address_4;}

	@Override
	public	String	execute() throws Exception
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

		TCustomer c = cart.getCustomer();
		customer_nm = c.getCustomer_nm();
		address_1 = c.getAddress_1();
		address_2 = c.getAddress_2();
		address_3 = c.getAddress_3();
		address_4 = c.getAddress_4();
	}

	private	void	accept()
	{
		CartInfo cart = super.getCart();

		TCustomer c = cart.getCustomer();
		c.setCustomer_nm(customer_nm);
		c.setAddress_1(address_1);
		c.setAddress_2(address_2);
		c.setAddress_3(address_3);
		c.setAddress_4(address_4);

		TOdrHeader h = cart.getHeader();
		h.setCustomer_nm(customer_nm);
		h.setAddress_1(address_1);
		h.setAddress_2(address_2);
		h.setAddress_3(address_3);
		h.setAddress_4(address_4);
	}
}
