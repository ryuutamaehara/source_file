package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TCustomer;
import dao.TIdManager;
import dao.TOdrDetail;
import dao.TOdrHeader;
import dao.TOdrStatus;
import dao.TPaymentMethod;
import dao.TPrd;
import dao.TPrdSalesInfo;
import fw.MethodNotSupportedException;

public class DAOTester	implements	fw.DBConnectionHolder
{
	public	static	void	main (String[] args)
	{
		DAOTester tester = new DAOTester();
		tester.init();
		tester.doTest();
		tester.wrapup();
		System.exit(0);
	}


	private	void	doTest()
	{
		try {
//			doTestIdManager();
//			doTestInvAllocation();
//			doTestCustomerInsert();
//			doTestCustomerUpdate();
//			doTestOdr();
//			doTestCustomerUpdatePassword();
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}


	private	void	doTestIdManager()	throws	SQLException
	{
		long id = TIdManager.getNextIDValue(this, "customer_id");
		getConnection().commit();
		System.out.println (id);
	}

	private	void	doTestInvAllocation()	throws	SQLException
	{
		long[] prd_id_list = {176321, 176322, 176343, 176351};
		int[] quantity_list = {1, 2, 3, 10};
		ArrayList<TPrdSalesInfo> si_list = TPrdSalesInfo.selectForUpdate(this, prd_id_list);
		for (TPrdSalesInfo si : si_list)
		{
			System.out.print (si + " → ");
			int  index = find (prd_id_list, si.getPrd_id());
			int quantity = quantity_list[index];
			si.setAllocatable_quantity (si.getAllocatable_quantity() - quantity);
			si.setSold_quantity (si.getSold_quantity() + quantity);
			si.update(this);
			System.out.println (si);
		}

		getConnection().commit();
	}

	private	int	find (long[] list, long value)
	{
		for (int i = 0; i < list.length; i++)
		{
			if (list[i] == value)
				return i;
		}

		return -1;
	}

	private	final	static	String	test_login_nm = "test01";

	private	void	doTestCustomerInsert()	throws	SQLException
	{
		TCustomer customer = new TCustomer();
		customer.setCustomer_id(TIdManager.getNextIDValue(this, "customer_id"));
		customer.setLogin_nm(test_login_nm);
		customer.setEmail(null);//	とりあえず使わない
		customer.setPassword ("test01_01");
		customer.setCustomer_nm("テストですよ");
		customer.setAddress_1("この村");
		customer.setAddress_2("海辺の草原隣");
		customer.setAddress_3("10962");
		customer.setAddress_4("3");
		customer.insert(this);

		getConnection().commit();
	}

	private	void	doTestCustomerUpdate()	throws	SQLException
	{
		TCustomer c2 = TCustomer.selectWithLoginNm(this, test_login_nm);
		System.out.println (c2);

		c2.setCustomer_nm("テストでした。");
		c2.setAddress_1("この村の");
		c2.setAddress_2("海辺の草原隣の");
		c2.setAddress_3("123");
		c2.setAddress_4("6");
		c2.update(this);

		getConnection().commit();
	}

	private	void	doTestCustomerUpdatePassword()	throws	SQLException
	{
		TCustomer c2 = TCustomer.selectWithLoginNm(this, test_login_nm);
		System.out.println (c2);

		c2.setPassword ("test01_02");
		c2.updatePassword(this);

		getConnection().commit();
	}

	private	void	doTestOdr()	throws	SQLException
	{
		TOdrHeader header = createOdrHeader();

		ArrayList<TOdrDetail> details = doTestOdrDetail (header);
		long product_total_price = 0;
		for (TOdrDetail d : details)
		{
			System.out.println (d);
			product_total_price += d.getTotal_price();
		}

		header.setProduct_total_price (product_total_price);
		header.setTotal_payment (product_total_price);
		header = doTestOdrHeader (header);
		System.out.println (header);

		getConnection().commit();
	}

	private	TOdrHeader	createOdrHeader()	throws	SQLException
	{
		TCustomer customer = TCustomer.selectWithLoginNm(this, test_login_nm);

		TOdrHeader header = new TOdrHeader();
		header.setOdr_id(TIdManager.getNextIDValue(this, "odr_id"));
		header.setCustomer_id(customer.getCustomer_id());
		header.setOdr_status_id (TOdrStatus.NEW);
		header.setPayment_method_id (TPaymentMethod.QUEST);
		header.setPayment_method_sub_cd ("Q0002");
		header.setPayment_status_id (TOdrHeader.PS_PAID);
		header.setProduct_total_price (0);
		header.setDiscounted_value (0);
		header.setTotal_payment (0);
		header.setDeliv_to (TOdrHeader.DT_OTHER);
		header.setCustomer_nm (customer.getCustomer_nm());
		header.setAddress_1 (customer.getAddress_1());
		header.setAddress_2 (customer.getAddress_2());
		header.setAddress_3 (customer.getAddress_3());
		header.setAddress_4 (customer.getAddress_4());
		header.setDt_nm ("平民壱号");
		header.setDt_address_1 ("この村");
		header.setDt_address_2 ("山のふもと");
		header.setDt_address_3 ("10921");
		header.setDt_address_4 ("2");

		return header;
	}

	private	TOdrHeader	doTestOdrHeader (TOdrHeader header)	throws	SQLException
	{

		header.insert(this);
		return header;
	}

	private	ArrayList<TOdrDetail>	doTestOdrDetail (TOdrHeader header)	throws	SQLException
	{
		ArrayList<TOdrDetail> details = new ArrayList<TOdrDetail>();
		details.add (createOdrDetail (header, 0, "10176321", 1));
		details.add (createOdrDetail (header, 1, "10176322", 2));
		details.add (createOdrDetail (header, 2, "10176343", 3));
		details.add (createOdrDetail (header, 3, "10176351", 10));

		return details;
	}

	private	TOdrDetail	createOdrDetail (TOdrHeader header, int detail_num, String prd_cd, int quantity)	throws SQLException
	{
		TPrd p = TPrd.selectWithPrdCd(this, prd_cd);
		TOdrDetail d = new TOdrDetail();
		d.setOdr_id (header.getOdr_id());
		d.setCustomer_id(header.getCustomer_id());
		d.setDetail_num(detail_num);
		d.setPrd_id(p.getPrd_id());
		d.setQuantity(quantity);
		d.setUnit_price(p.getSelling_price());
		d.setTotal_price(d.getUnit_price() * d.getQuantity());
		d.setOdr_status_id(TOdrStatus.NEW);
		d.setOdr_prd_nm(p.getPrd_nm());
		d.setOdr_dd_desc(p.getDd_desc());

		d.insert(this);

		return d;
	}

	private	final	static	String	DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	private	final	static	String	DB_URL = "jdbc:mysql://localhost/ec";
	private	final	static	String	DB_USER = "ec";
	private	final	static	String	DB_PASSWORD = "ecwork";

	private	Connection con = null;
	public	Connection	getConnection(){return this.con;}

	@Override
	public String getText(String key) {throw new MethodNotSupportedException();}
	@Override
	public int getInt(String key) {throw new MethodNotSupportedException();}

	private	void	init()
	{
		openConnection();
	}

	private	void	openConnection ()
	{
		try {
			Class.forName (DB_DRIVER_NAME);
			con = DriverManager.getConnection (DB_URL, DB_USER, DB_PASSWORD);
			con.setAutoCommit (false);
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	private	void	wrapup()
	{
		closeConnection();
	}

	private	void closeConnection()
	{
		try {
			con.close ();
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
 	}
}
