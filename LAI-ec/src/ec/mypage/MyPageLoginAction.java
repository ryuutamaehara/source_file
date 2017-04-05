package ec.mypage;

import dao.TCustomer;
import fw.AbstractAction;

/**
 * マイページへのログイン
 */
public class MyPageLoginAction extends AbstractAction
{
	private	String	login_nm = null;
	public	void	setLogin_nm (String login_nm){this.login_nm = login_nm;}
	public	String	getLogin_nm(){return this.login_nm;}

	private	String	password = null;
	public	void	setPassword (String password){this.password = password;}

	@Override
	public	String	execute()
	{
		String method = super.getRequestMethod();
		if (method.equalsIgnoreCase("get"))
			return DISPLAY;

		return processLogin();
	}

	private	String	processLogin()
	{
		TCustomer customer = TCustomer.selectForAuth(this, login_nm, password);
		if (customer != null)
		{
			super.setCurrentCustomer(customer);
			return SUCCESS;
		}

		return super.displayError(super.getText("Message.LOGIN_FAILED"));
	}
}
