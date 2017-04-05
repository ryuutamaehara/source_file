package fw;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserProfile;

/**
 * プロジェクト共通アクションクラスの基底。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public abstract class AbstractDBAction extends ActionSupport implements
		SessionAware, ServletRequestAware, DBConnectionHolder
{
	/** ログインユーザがスーパユーザであることを示すresult文字列 */
	public	final	static	String	SUPERUSER = "superuser";

	private	Map<String, Object>	session = null;
	private	HttpServletRequest	request = null;
	private	String	errorMessage = null;
	public	String	getErrorMessage(){return this.errorMessage;}

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

	/**
	 * ログイン中のユーザプロファイルを取得する。
	 * @return
	 */
	public	UserProfile	getCurrentUserProfile(){return (UserProfile)getSessionObject (Const.Session.CURRENT_USER);}

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
	 * ログイン中のユーザプロファイルをセットする。
	 * @param user_profile
	 */
	protected	void	setCurrentUserProfile (UserProfile user_profile){setSessionObject (Const.Session.CURRENT_USER, user_profile);}

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
	 * @param errorMessage
	 * @return
	 */
	protected	String	displayError (String errorMessage)
	{
		this.errorMessage = errorMessage;
		return ERROR;
	}

	/**
	 * エラーメッセージをセットして、ERROR終了する。
	 * @param errorMessage
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
		public abstract	void releaseConnecction (Connection con) throws SQLException;
	}

	/**
	 * JDBCドライバを直接使用して接続するコネクタ。
	 * @author tsuhtan
	 */
	protected	class	BasicJdbcConnector	extends	JdbcConnector
	{
		private	final	static	String	DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
		private	final	static	String	DB_URL = "jdbc:mysql://localhost/vv";
		private	final	static	String	DB_USER = "vv";
		private	final	static	String	DB_PASSWORD = "vvwork";

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
		public void releaseConnecction (Connection con) throws SQLException
		{
			con.close ();
		}		
	}

	/**
	 * JNDIを介してDataSourceを取得後、接続するコネクタ。
	 * @author tsuhtan
	 */
	protected	class	JNDIJdbcConnector	extends	JdbcConnector
	{
		private	DataSource	ds = null;

		@Override
		public Connection getConnection () throws SQLException
		{
			try {
				InitialContext ctx = new InitialContext();
				ds = (DataSource)ctx.lookup ("java:/comp/env/jdbc/vvdb");
				con = ds.getConnection();
				return con;
			} catch (NamingException e) {
				throw new RuntimeException (e);
			}
		}

		@Override
		public void releaseConnecction (Connection con) throws SQLException
		{
			con.close ();
		}		
	}

	/**
	 * Resource Injectionを介して取得されたDataSourceにより接続するコネクタ。
	 * @author tsuhtan
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
		public void releaseConnecction (Connection con) throws SQLException
		{
			con.close ();
		}		
	}

	@Override
	public	void	finalize()	throws	Throwable
	{
		if (con != null) {
			jc.releaseConnecction (con);
		}
	}
}
