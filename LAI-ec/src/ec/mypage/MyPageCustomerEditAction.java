package ec.mypage;

import java.sql.Connection;
import java.sql.SQLException;

import dao.TCustomer;
import ec.AbstractCustomerEditAction;

/** マイページ：お客様情報更新 */
public class MyPageCustomerEditAction extends AbstractCustomerEditAction
{
	@Override
	protected TCustomer getCustomer()
	{
		return super.getCurrentCustomer();
	}

	@Override
	protected void saveCustomer(TCustomer customer)	throws	SQLException
	{
		Connection con = null;
		try {
			customer.update(this);
			con = super.getConnection();
			con.commit();
		} catch (SQLException e) {
			try {if (con != null) con.rollback();} catch (SQLException e2){}
			throw e;
		}
	}
}
