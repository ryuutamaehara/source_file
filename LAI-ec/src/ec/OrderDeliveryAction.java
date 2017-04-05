package ec;

import dao.TCustomer;
import dao.TOdrHeader;
import data.CartInfo;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * お届け先指定ページのアクション
 */
public class OrderDeliveryAction extends AbstractAction
{
	private	String	command = null;
	public	void	setCommand(String command){this.command = command;}

	private	boolean	deliv_to_my_home = true;
	public	void	setDeliv_to(String deliv_to){this.deliv_to_my_home = deliv_to.equalsIgnoreCase("my_home");}
	public	String	getDeliv_to_my_home_checked(){return deliv_to_my_home ? "CHECKED" : "";}
	public	String	getDeliv_to_other_checked(){return deliv_to_my_home ? "" : "CHECKED";}

	private	String	dt_nm = null;
	public	void	setDt_nm(String dt_nm){this.dt_nm = dt_nm;}
	public	String	getDt_nm(){return this.dt_nm;}

	private	String	dt_address_1 = null;
	public	void	setDt_address_1(String dt_address_1){this.dt_address_1 = dt_address_1;}
	public	String	getDt_address_1(){return this.dt_address_1;}

	private	String	dt_address_2 = null;
	public	void	setDt_address_2(String dt_address_2){this.dt_address_2 = dt_address_2;}
	public	String	getDt_address_2(){return this.dt_address_2;}

	private	String	dt_address_3 = null;
	public	void	setDt_address_3(String dt_address_3){this.dt_address_3 = dt_address_3;}
	public	String	getDt_address_3(){return this.dt_address_3;}

	private	String	dt_address_4 = null;
	public	void	setDt_address_4(String dt_address_4){this.dt_address_4 = dt_address_4;}
	public	String	getDt_address_4(){return this.dt_address_4;}

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
		deliv_to_my_home = (h.getDeliv_to() == TOdrHeader.DT_MY_HOME);
		if (! deliv_to_my_home)
		{
			dt_nm = h.getDt_nm();
			dt_address_1 = h.getDt_address_1();
			dt_address_2 = h.getDt_address_2();
			dt_address_3 = h.getDt_address_3();
			dt_address_4 = h.getDt_address_4();
		}
	}

	private	void	accept()
	{
		CartInfo cart = super.getCart();
		TOdrHeader h = cart.getHeader();
		h.setDeliv_to(this.deliv_to_my_home ? TOdrHeader.DT_MY_HOME : TOdrHeader.DT_OTHER);
		if (this.deliv_to_my_home)
		{
			TCustomer c = cart.getCustomer();
			h.setDt_nm(c.getCustomer_nm());
			h.setDt_address_1(c.getAddress_1());
			h.setDt_address_2(c.getAddress_2());
			h.setDt_address_3(c.getAddress_3());
			h.setDt_address_4(c.getAddress_4());
		}
		else
		{
			h.setDt_nm(dt_nm);
			h.setDt_address_1(dt_address_1);
			h.setDt_address_2(dt_address_2);
			h.setDt_address_3(dt_address_3);
			h.setDt_address_4(dt_address_4);
		}
	}
}
