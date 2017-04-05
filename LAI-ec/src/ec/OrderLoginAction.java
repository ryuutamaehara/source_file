package ec;

import dao.TCustomer;
import data.CartInfo;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * 注文ログインページ（order_1）のアクション。
 */
public class OrderLoginAction extends AbstractAction
{
	private	boolean	is_new = false;
	public	void	setIs_new(boolean is_new){this.is_new = is_new;}
	public	String	getIs_new_true_checked(){return this.is_new ? CHECKED : "";}
	public	String	getIs_new_false_checked(){return this.is_new ? "" : CHECKED;}

	private	String	login_nm = null;
	public	void	setLogin_nm (String login_nm){this.login_nm = login_nm;}
	public	String	getLogin_nm(){return this.login_nm;}

	private	String	password = null;
	public	void	setPassword (String password){this.password = password;}

	private	String	password2 = null;
	public	void	setPassword2 (String password2){this.password2 = password2;}

	private	CartInfo	cart = null;

	@Override
	public	String	execute()
	{
		String method = super.getRequestMethod();
		if (method.equalsIgnoreCase("get"))
			return DISPLAY;

		cart = super.getCart();
		if (is_new)
			return processNewCustomer();
		else
			return processLogin();
	}

	private	String	processLogin()
	{
		TCustomer customer = TCustomer.selectForAuth(this, login_nm, password);
		if (customer != null)
		{
			cart.setCustomer(customer);
			super.setCurrentCustomer(customer);
			return SUCCESS;
		}

		return super.displayError(super.getText("Message.LOGIN_FAILED"));
	}

	private	String	processNewCustomer()
	{
		if (StringUtil.isEmpty(login_nm) || StringUtil.isEmpty(password))
			return super.displayError(super.getText("Message.MISSING_PARAMS_FOR_NEW_CUSTOMER"));

		TCustomer customer = TCustomer.selectWithLoginNm(this, login_nm);
		if (customer != null)
			return super.displayError(super.getText("Message.LOGIN_NM_ALREADY_REGISTERED"));

		if (! password.equals(password2))
			return super.displayError(super.getText("Message.PASSWORD_MISMATCH"));

		customer = new TCustomer();
		customer.setLogin_nm(login_nm);
		customer.setPassword(password);
		cart.setnewCustomer(customer);

		return SUCCESS;
	}
}
