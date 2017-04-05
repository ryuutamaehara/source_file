package fw;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.TCustomer;
import data.CartInfo;
import data.MasterCategoryWithSub;

/**
 * プロジェクト共通アクションクラスの基底。
 */
public abstract class AbstractAction extends ActionSupport implements
		SessionAware, ServletRequestAware, DBConnectionHolder
{
	protected	final	static	String	DISPLAY = "display";
	protected	final	static	String	GO_BACK = "go_back";
	protected	final	static	String	GO_NEXT = "go_next";

	protected	final	static	String	CHECKED = "CHECKED";

	private	Map<String, Object>	session = null;
	private	HttpServletRequest	request = null;
	private	String	error_message = null;
	public	void	setError_message (String error_message){this.error_message = error_message;}
	public	String	getError_message(){return this.error_message;}

	protected	HashMap<String, String[]>	getParameters()
	{
		Map<String, Object> _params = ActionContext.getContext().getParameters();
		HashMap<String, String[]> params = new HashMap<String, String[]>();
		for (Map.Entry<String, Object> entry : _params.entrySet())
		{
			Object value = entry.getValue();
			params.put(entry.getKey(),
					value instanceof String[] ? (String[])value : new String[]{(String)value});
		}
		return params;
	}

	// for SessionAware
	@Override
	public void setSession (Map<String, Object> session)
	{
		this.session = session;
	}

	// for ServletRequestAware
	@Override
	public void setServletRequest (HttpServletRequest request)
	{
		this.request = request;
	}

	/** リクエストのhttpメソッドを取得する。 */
	public	String	getRequestMethod()
	{
		return request.getMethod();
	}

	/** コンテキストパスを取得する。 */
	public	String	getContextPath()
	{
		return request.getContextPath();
	}

	/**
	 * リクエストのURIを取得する。
	 * @return
	 */
	public	String	getRequestURI()
	{
		return request.getRequestURI();
	}

	/**
	 * リクエストのURIを取得する。
	 * @return
	 */
	public	String	getRequestContextLocalURI()
	{
		String cp = getContextPath();
		String uri = getRequestURI();
		uri = uri.substring(cp.length());
		return uri;
	}

	/**
	 * リクエストのURLを取得する。
	 * @return
	 */
	public	String	getRequestURL()
	{
		return new String (request.getRequestURL());
	}

	/**
	 * URIの末尾パス名をコードとみなして抽出する。
	 * /<i>context path</i>/..../<i>code</i>.html 形式を想定。
	 * @return
	 */
	public	String	getRequestedCdFromURI()
	{
		String uri = getRequestURI();
		int x1 = uri.lastIndexOf('/');
		int x2 = uri.lastIndexOf('.');
		String cd = uri.substring(x1 + 1, x2);
		return cd;
	}

	/**
	 * 指定されたキーで定義された整数のプロパティ値を取得する。
	 * @param key
	 * @return
	 */
	public	int	getInt (String key)
	{
		String s = super.getText(key);
		if (s == null)
			throw new PropertyNotFoundException(key);

		try {
			int value = Integer.parseInt(s);
			return value;
		} catch (RuntimeException e) {
			throw new BadPropertyValueException(key, s, e);
		}
	}

	/**
	 * ログイン中の顧客情報を取得する。
	 * @return
	 */
	public	TCustomer	getCurrentCustomer(){return (TCustomer)getSessionObject (Const.Session.CURRENT_CUSTOMER);}

	/**
	 * セッションに保持されている買物カゴ情報を取得する。
	 * @return
	 */
	public	CartInfo	getCart(){return (CartInfo)getSessionObject (Const.Session.CART);}

	/**
	 * セッションに格納されているオブジェクトを取得する。
	 * @param name
	 * @return
	 */
	protected	Object	getSessionObject (String name)
	{
		return session.get (name);
	}

	/**
	 * ログイン中の顧客情報をセットする。
	 * @param customer
	 */
	protected	void	setCurrentCustomer (TCustomer customer){setSessionObject (Const.Session.CURRENT_CUSTOMER, customer);}

	/**
	 * 買物カゴ情報をセッションに格納する。
	 * @param cart
	 */
	protected	void	setCart (CartInfo cart){setSessionObject (Const.Session.CART, cart);}

	/**
	 * セッションにオブジェクトを格納する。
	 * @param name
	 * @param object
	 */
	protected	void	setSessionObject (String name, Object object)
	{
		session.put (name, object);
	}

	/**
	 * セッションから買物カゴ情報を除去する。
	 */
	protected	void	removeCart()
	{
		removeSessionObject (Const.Session.CART);
	}

	/**
	 * セッションからオブジェクトを除去する。
	 * @param name
	 */
	protected	void	removeSessionObject (String name)
	{
		session.remove(name);
	}

	/**
	 * セッションを無効化する。
	 */
	protected	void	invalidateSession()
	{
		HttpSession session = request.getSession (false);
		if (session != null) {
			session.invalidate();
		}
	}

	/**
	 * エラーメッセージをセットして、ERROR終了する。
	 * @param error_message
	 * @return
	 */
	protected	String	displayError (String error_message)
	{
		this.error_message = error_message;
		return ERROR;
	}

	/**
	 * エラーメッセージをセットして、ERROR終了する。
	 * @param error_message
	 * @return
	 */
	protected	String	displayError (Exception e)
	{
		e.printStackTrace();
		return displayError (e.getClass().getName() + " : " + e.getMessage());
	}

	private	JdbcConnector	jc = new JNDIJdbcConnector();
	private	Connection	con = null;

	/**
	 * DB接続を取得する。
	 */
	@Override
	public Connection getConnection ()	throws	SQLException
	{
		if (con != null) {
			return con;
		}

		con = jc.getConnection();
		return con;
	}

	private	abstract	class	JdbcConnector
	{
		public abstract	Connection getConnection ()	throws	SQLException;
		public abstract	void releaseConnection (Connection con) throws SQLException;
	}

	/**
	 * JDBCドライバを直接使用して接続するコネクタ。
	 */
	protected	class	BasicJdbcConnector	extends	JdbcConnector
	{
		private	final	static	String	DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
		private	final	static	String	DB_URL = "jdbc:mysql://localhost/ec";
		private	final	static	String	DB_USER = "ec";
		private	final	static	String	DB_PASSWORD = "ecwork";

		@Override
		public Connection getConnection () throws SQLException
		{
			try {
				Class.forName (DB_DRIVER_NAME);
				con = DriverManager.getConnection (DB_URL, DB_USER, DB_PASSWORD);
				con.setAutoCommit (false);
				return con;
			} catch (ClassNotFoundException e) {
				throw new RuntimeException (e);
			}
		}

		@Override
		public void releaseConnection (Connection con) throws SQLException
		{
			con.close ();
		}
	}

	/**
	 * JNDIを介してDataSourceを取得後、接続するコネクタ。
	 */
	protected	class	JNDIJdbcConnector	extends	JdbcConnector
	{
		private	DataSource	ds = null;

		@Override
		public Connection getConnection () throws SQLException
		{
			try {
				InitialContext ctx = new InitialContext();
				ds = (DataSource)ctx.lookup ("java:/comp/env/jdbc/ecdb");
				con = ds.getConnection();
				return con;
			} catch (NamingException e) {
				throw new RuntimeException (e);
			}
		}

		@Override
		public void releaseConnection (Connection con) throws SQLException
		{
			con.close ();
		}
	}

	/**
	 * Resource Injectionを介して取得されたDataSourceにより接続するコネクタ。
	 */
	protected	class	RIJdbcConnector	extends	JdbcConnector
	{
		@Resource(name="jdbc/vvdb")
		private	DataSource	ds;

		@Override
		public Connection getConnection () throws SQLException
		{
			con = ds.getConnection();
			return con;
		}

		@Override
		public void releaseConnection (Connection con) throws SQLException
		{
			con.close ();
		}
	}

	/** コネクションを持っている */
	boolean	hasConnection(){return this.con != null;}
	/** コネクションを開放する */
	void	releaseConnection()
	{
		try {
			jc.releaseConnection(con);
			con = null;
		} catch (SQLException e) {}
	}

	@Override
	public	void	finalize()	throws	Throwable
	{
		if (con != null) {
			releaseConnection();
		}
	}

	//
	// 以下、業務寄りの共通機能
	//

	protected	String	search_keyword = null;
	public	String	getSearch_keyword(){return this.search_keyword;}
	public	void	setSearch_keyword (String search_keyword){this.search_keyword = search_keyword;}


	private	ArrayList<MasterCategoryWithSub>	master_cate_list = null;
	/** カテゴリーツリーリストを取得する */
	public	ArrayList<MasterCategoryWithSub>	getMaster_cate_list(){return master_cate_list;}

	protected	void	loadMasterCateList()
	{
		master_cate_list = MasterCategoryWithSub.loadCategoryTree (this);
	}

	/** パラメタなどから取得したカテゴリコード。現在表示しているページのカテゴリを示す。 */
	protected	String	cate_cd = null;
	public	void	setCate_cd (String cate_cd){this.cate_cd = cate_cd;}
	public	String	getCate_cd(){return this.cate_cd;}
}
