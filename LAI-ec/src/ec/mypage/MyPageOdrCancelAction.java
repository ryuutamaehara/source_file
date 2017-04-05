package ec.mypage;

import java.sql.Connection;
import java.sql.SQLException;

import dao.TCustomer;
import dao.TOdrHeader;
import dao.TOdrStatus;
import fw.AbstractAction;

/** 注文キャンセルアクション */
public class MyPageOdrCancelAction extends AbstractAction
{
	private	long	odr_id = 0;
	public	void	setOdr_id(long odr_id){this.odr_id = odr_id;}

	@Override
	public	String execute()	throws Exception
	{
		TCustomer customer = super.getCurrentCustomer();
		TOdrHeader header = TOdrHeader.selectForUpdate(this, odr_id);
		if (header == null || header.getCustomer_id() != customer.getCustomer_id())
		{
			try {getConnection().rollback();} catch (SQLException e) {e.printStackTrace();}
			return super.displayError(super.getText("Message.ODR_NOT_FOUND"));
		}

		if (header.getOdr_status_id() != TOdrStatus.NEW)
		{
			try {getConnection().rollback();} catch (SQLException e) {e.printStackTrace();}
			return super.displayError(super.getText("Message.ODR_CANNOT_BE_CANCELED"));
		}

		Connection con = null;
		try {
			header.setOdr_status_id(TOdrStatus.CANCELED);
			header.updateStatus(this);
			con = super.getConnection();
			con.commit();
			return SUCCESS;
		} catch (SQLException e) {
			try {if (con != null) con.rollback();} catch (SQLException e2){}
			throw e;
		}
	}
}
