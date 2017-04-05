package ec.mypage;

import java.sql.Connection;
import java.sql.SQLException;

import dao.TCustomer;
import fw.AbstractAction;

/** パスワード変更アクション */
public class MyPagePasswordChangeAction extends AbstractAction
{
	private	String	current_password = null;
	public	void	setCurrent_password (String current_password){this.current_password = current_password;}
	public	String	getCurrent_password(){return this.current_password;}

	private	String	new_password1 = null;
	public	void	setNew_password1 (String new_password1){this.new_password1 = new_password1;}
	public	String	getNew_password1(){return this.new_password1;}

	private	String	new_password2 = null;
	public	void	setNew_password2 (String new_password2){this.new_password2 = new_password2;}
	public	String	getNew_password2(){return this.new_password2;}

	@Override
	public	String execute()	throws Exception
	{
		String method = super.getRequestMethod();
		if (method.equalsIgnoreCase("get"))
			return DISPLAY;

		return processPasswordChange();
	}

	private	String	processPasswordChange()	throws	SQLException
	{
		TCustomer current_customer = super.getCurrentCustomer();
		TCustomer customer = TCustomer.selectForAuth(this, current_customer.getLogin_nm(), current_password);
		if (customer == null)
			return super.displayError(super.getText("Message.WRONG_CURRENT_PASSWORD"));

		if (! new_password1.equals(new_password2))
			return super.displayError(super.getText("Message.NEW_PASSWORD_MISMATCH"));

		customer.setPassword(new_password1);
		Connection con = null;
		try {
			customer.updatePassword (this);
			con = super.getConnection();
			con.commit();
		} catch (SQLException e) {
			try {if (con != null) con.rollback();} catch (SQLException e2){}
			throw e;
		}

		return SUCCESS;
	}
}
