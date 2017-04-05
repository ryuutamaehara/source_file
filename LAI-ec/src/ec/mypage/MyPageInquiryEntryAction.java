package ec.mypage;

import java.sql.Connection;
import java.sql.SQLException;

import dao.TCustomer;
import dao.TIdManager;
import dao.TInquiry;
import fw.AbstractAction;

/** 問合せ登録アクション */
public class MyPageInquiryEntryAction extends AbstractAction
{
	private	String	inq_subject = null;
	public	void	setInq_subject(String inq_subject){this.inq_subject = inq_subject;}
	public	String	getInq_subject(){return this.inq_subject;}

	private	String	inq_body = null;
	public	void	setInq_body(String inq_body){this.inq_body = inq_body;}
	public	String 	getInq_body(){return this.inq_body;}

	@Override
	public	String execute()	throws Exception
	{
		String method = super.getRequestMethod();
		if (method.equalsIgnoreCase("get"))
			return DISPLAY;

		return processInquiryEntry();
	}

	private	String	processInquiryEntry()	throws	SQLException
	{
		Connection con = super.getConnection();
		TCustomer customer = super.getCurrentCustomer();
		try {
			TInquiry inq = new TInquiry();
			inq.setInq_id (TIdManager.getNextIDValue(this, "inq_id"));
			inq.setCustomer_id(customer.getCustomer_id());
			inq.setInq_status_id(TInquiry.NEW);
			inq.setInq_subject(inq_subject);
			inq.setInq_body(inq_body);
			inq.insert(this);

			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			throw e;
		}

		return SUCCESS;
	}
}
