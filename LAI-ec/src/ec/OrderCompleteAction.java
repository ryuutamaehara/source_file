package ec;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dao.TCustomer;
import dao.TIdManager;
import dao.TOdrDetail;
import dao.TOdrHeader;
import dao.TPrdSalesInfo;
import data.CartInfo;
import data.OdrDetailInfo;
import fw.AbstractAction;

/**
 * 注文確定処理
 */
public class OrderCompleteAction extends AbstractAction
{
	private	CartInfo	cart = null;

	private	TCustomer	customer = null;

	private	TOdrHeader	header = null;
	public	TOdrHeader	getHeader(){return this.header;}

	private	ArrayList<OdrDetailInfo>	detail_list = null;

	@Override
	public	String execute()	throws Exception
	{
		init();
		try {
			processComplete();
			super.removeCart();
			return SUCCESS;
		} catch (Exception e) {
			return super.displayError(e);
		}
	}

	private	void	init()
	{
		cart = super.getCart();
		customer = cart.getCustomer();
		header = cart.getHeader();
		detail_list = cart.getDetail_list();
	}

	private	void	processComplete()	throws	Exception
	{
		prepareToSave();
		doSave();
	}

	private	void	prepareToSave()	throws	Exception
	{
		assignIDs();	// この中で個々にコミットする
		allocateInventory();	// ここではコミットしない
	}

	private	void	assignIDs()	throws	SQLException
	{
		Connection con = super.getConnection();
		try {
			if (cart.getIs_new_customer())
			{
				customer.setCustomer_id(TIdManager.getNextIDValue(this, "customer_id"));
			}
			con.commit();
			header.setCustomer_id(customer.getCustomer_id());
			header.setOdr_id(TIdManager.getNextIDValue(this, "odr_id"));
			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			throw e;
		}
	}

	private	void	allocateInventory()	throws	Exception
	{
		long[] prd_id_list = new long[detail_list.size()];
		for (int i = 0; i < prd_id_list.length; i++)
			prd_id_list[i] = detail_list.get(i).prd.getPrd_id();
		ArrayList<TPrdSalesInfo> si_list = TPrdSalesInfo.selectForUpdate(this, prd_id_list);
		HashMap<Long, TPrdSalesInfo> si_map = new HashMap<Long, TPrdSalesInfo>();
		for (TPrdSalesInfo si : si_list)
			si_map.put(si.getPrd_id(), si);

		try {
			for (OdrDetailInfo odi : detail_list)
			{
				TPrdSalesInfo si = si_map.get(odi.prd.getPrd_id());
				int quantity = odi.detail.getQuantity();
				int allocatable_quantity = si.getAllocatable_quantity();
				if (allocatable_quantity < quantity)
					throw new InventoryShortageException (odi, si);

				si.setAllocatable_quantity(allocatable_quantity - quantity);
				si.setSold_quantity(si.getSold_quantity() + quantity);
				si.update(this);
			}
		} catch (Exception e) {
			Connection con = super.getConnection();
			try {
				con.rollback();
			} catch (SQLException e2) {
				e.printStackTrace();
			}
			throw e;
		}
	}

	private	void	doSave()	throws	SQLException
	{
		Connection con = super.getConnection();
		try {
			if (cart.getIs_new_customer())
				customer.insert(this);
			else
				customer.update(this);

			header.insert(this);
			long customer_id = customer.getCustomer_id();
			long odr_id = header.getOdr_id();
			int odr_status_id = header.getOdr_status_id();
			int detail_num = 0;
			for (OdrDetailInfo odi : detail_list)
			{
				TOdrDetail d = odi.detail;
				d.setCustomer_id(customer_id);
				d.setOdr_id(odr_id);
				d.setDetail_num(detail_num++);
				d.setOdr_status_id(odr_status_id);
				d.insert(this);
			}

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e2) {
				e.printStackTrace();
			}
			throw e;
		}
	}
}
