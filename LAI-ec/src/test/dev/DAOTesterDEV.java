package test.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TCustomer;
import dao.TIdManager;
import dao.TNews;
import dao.TOdrDetail;
import dao.TOdrHeader;
import dao.TOdrStatus;
import dao.TPaymentMethod;
import dao.TPrd;
import dao.TQuest;
import dao.VPrdRec;
import data.MasterCategoryWithSub;
import fw.MethodNotSupportedException;

public class DAOTesterDEV	implements	fw.DBConnectionHolder
{
	public	static	void	main (String[] args)
	{
		DAOTesterDEV tester = new DAOTesterDEV();
		tester.init();
		tester.doTest();
		tester.wrapup();
		System.exit(0);
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

	private	void	doTest()
	{
		try {
//			doTestCate();
//			doTestPrd();
//			doTestNews();
//			doTestPrdRec();
//			doTestIdManager();
			doTestCustomer();
//			doTestOdr();
//		doTestQuest();
//		doTestPaymentMethod();
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
	}

	private	void	doTestCate()
	{
		ArrayList<MasterCategoryWithSub> master_cate_list = MasterCategoryWithSub.loadCategoryTree (this);
		for (MasterCategoryWithSub mc : master_cate_list)
			System.out.println(mc);
	}

	private	void	doTestPrd()
	{
		TPrd prd = TPrd.selectWithPrdCd(this, "10176331");
		System.out.println (prd.toString());

		String condition = "WHERE prd_nm LIKE '%やくそう%'";
		ArrayList<TPrd> list = TPrd.selectWithCondition(this, condition, null);
		displayPrd (list);

		list = TPrd.selectWithCondition(this, condition, "ORDER BY prd_cd desc");
		displayPrd (list);
	}

	private	void	displayPrd (ArrayList<TPrd> list)
	{
		int i = 1;
		for (TPrd p : list)
			System.out.println ((i++) + " : " + p.toString());
	}

	private	void	doTestNews()
	{
		TNews news = TNews.select(this, 1);
		System.out.println(news);

		ArrayList<TNews> news_list = TNews.selectForNow(this);
		for (TNews n : news_list)
			System.out.println(n);
	}

	private	void	doTestPrdRec()
	{
		ArrayList<VPrdRec> rec_list = VPrdRec.selectForNow(this);
		for (VPrdRec rec : rec_list)
			System.out.println(rec);
	}

	private	void	doTestIdManager()	throws	SQLException
	{
		long id = TIdManager.getNextIDValue(this, "customer_id");
		getConnection().commit();
		System.out.println (id);
	}

	private	void	doTestCustomer()	throws	SQLException
	{
		TCustomer customer = new TCustomer();
		customer.setCustomer_id(TIdManager.getNextIDValue(this, "customer_id"));
		customer.setLogin_nm("test2");
		customer.setEmail(null);//	とりあえず使わない
		customer.setPassword ("poipoi");
		customer.setCustomer_nm("テストですよ");
		customer.setAddress_1("この村");
		customer.setAddress_2("海辺の草原隣");
		customer.setAddress_3("10962");
		customer.setAddress_4("3");
		customer.insert(this);

		getConnection().commit();

		TCustomer c2 = TCustomer.select(this, customer.getCustomer_id());
		System.out.println (c2);

		c2.setCustomer_nm("テストでした。");
		c2.setAddress_1("この村の");
		c2.setAddress_2("海辺の草原隣の");
		c2.setAddress_3("123");
		c2.setAddress_4("6");
		c2.update(this);

		getConnection().commit();

		c2.setPassword ("hoge");
		c2.updatePassword(this);
		getConnection().commit();

	}

	private	void	doTestOdr()	throws	SQLException
	{
		TOdrHeader header = doTestOdrHeader();
		System.out.println (header);

		ArrayList<TOdrDetail> details = doTestOdrDetail (header);
		for (TOdrDetail d : details)
			System.out.println (d);

		getConnection().commit();
	}

	private	TOdrHeader	doTestOdrHeader()	throws	SQLException
	{
		TCustomer customer = TCustomer.select(this, 123464);

		TOdrHeader header = new TOdrHeader();
		header.setOdr_id(TIdManager.getNextIDValue(this, "odr_id"));
		header.setCustomer_id(customer.getCustomer_id());
		header.setOdr_status_id (TOdrStatus.NEW);
		header.setPayment_method_id (TPaymentMethod.CASH);
		header.setPayment_status_id (TOdrHeader.PS_PAID);
		header.setProduct_total_price (24850);
		header.setDiscounted_value (0);
		header.setTotal_payment (24000);
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

		header.insert(this);

		header = TOdrHeader.select(this, header.getOdr_id());

		return header;
	}

	private	ArrayList<TOdrDetail>	doTestOdrDetail (TOdrHeader header)	throws	SQLException
	{
		createOdrDetail (header, 0, "10176321", 99);
		createOdrDetail (header, 1, "10176322", 99);
		createOdrDetail (header, 2, "10176343", 1);
		createOdrDetail (header, 3, "10176351", 10);

		ArrayList<TOdrDetail> details = TOdrDetail.selectForOdr(this, header.getOdr_id());
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

	private	void	doTestQuest()
	{
		ArrayList<TQuest> quest_list = TQuest.selectAll(this);
		for (TQuest q : quest_list)
			System.out.println(q);
	}

	private	void	doTestPaymentMethod()
	{
		ArrayList<TPaymentMethod> pm_list = TPaymentMethod.selectAll(this);
		for (TPaymentMethod pm : pm_list)
			System.out.println(pm);
	}
}
