package ec;

import dao.TCustomer;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * 顧客情報入力ページの汎用アクション。
 */
public abstract	class AbstractCustomerEditAction extends AbstractAction
{
	protected	String	command = null;
	public	void	setCommand(String command){this.command = command;}

	protected	String	customer_nm = null;
	public	void	setCustomer_nm(String customer_nm){this.customer_nm = customer_nm;}
	public	String	getCustomer_nm(){return this.customer_nm;}

	protected	String	address_1 = null;
	public	void	setAddress_1(String address_1){this.address_1 = address_1;}
	public	String	getAddress_1(){return this.address_1;}

	protected	String	address_2 = null;
	public	void	setAddress_2(String address_2){this.address_2 = address_2;}
	public	String	getAddress_2(){return this.address_2;}

	protected	String	address_3 = null;
	public	void	setAddress_3(String address_3){this.address_3 = address_3;}
	public	String	getAddress_3(){return this.address_3;}

	protected	String	address_4 = null;
	public	void	setAddress_4(String address_4){this.address_4 = address_4;}
	public	String	getAddress_4(){return this.address_4;}

	@Override
	public	final	String	execute() throws Exception
	{
		if (StringUtil.isEmpty(command))
		{
			initToDisplay();
			return INPUT;
		}

		try {
			accept();
		} catch (Exception e) {
			return super.displayError(e);
		}

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

	protected	abstract	TCustomer	getCustomer();
	protected	abstract	void	saveCustomer (TCustomer customer)	throws Exception;

	private	void	initToDisplay()
	{
		TCustomer c = getCustomer();
		customer_nm = c.getCustomer_nm();
		address_1 = c.getAddress_1();
		address_2 = c.getAddress_2();
		address_3 = c.getAddress_3();
		address_4 = c.getAddress_4();
	}

	private	void	accept()	throws Exception
	{
		TCustomer c = getCustomer();
		c.setCustomer_nm(customer_nm);
		c.setAddress_1(address_1);
		c.setAddress_2(address_2);
		c.setAddress_3(address_3);
		c.setAddress_4(address_4);

		saveCustomer (c);
	}
}
