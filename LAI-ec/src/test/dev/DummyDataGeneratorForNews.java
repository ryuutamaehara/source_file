package test.dev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import fw.MethodNotSupportedException;

public class DummyDataGeneratorForNews	implements	fw.DBConnectionHolder
{
	public	static	void	main (String[] args)
	{
		if (args.length != 1)
		{
			System.out.println ("起動パラメタとして繰り返し数を指定してください。");
			System.exit(1);
		}
		int max_count = Integer.parseInt(args[0]);
		DummyDataGeneratorForNews ddg = new DummyDataGeneratorForNews();
		ddg.init (max_count);
		try {
			ddg.doIt();
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			ddg.wrapup();
		}
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

	private	int	max_count = 0;

	private	void	init (int max_count)
	{
		this.max_count = max_count;
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
		if (con == null)
			return;

		try {
			con.close ();
		} catch (Exception e) {
			throw new RuntimeException (e);
		}
 	}

	private	class	Term
	{
		private	Calendar cs = null;
		private	Calendar cs_base = null;
		private	Calendar ce = null;
		private	Calendar ce_base = null;
		private	Timestamp start = null;
		private	Timestamp end = null;

		private	Term()
		{
			cs_base = Calendar.getInstance();
			truncate (cs_base);
			cs = Calendar.getInstance();

			ce_base = Calendar.getInstance();
			truncate (ce_base);
			ce_base.add(Calendar.SECOND, -1);
			ce = Calendar.getInstance();
			reset();
			build();
		}

		private	void	truncate (Calendar c)
		{
			c.set(Calendar.HOUR_OF_DAY, 0);
			c.set(Calendar.MINUTE, 0);
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.MILLISECOND, 0);
		}

		private	void	reset()
		{
			cs.setTimeInMillis(cs_base.getTimeInMillis());
			ce.setTimeInMillis(ce_base.getTimeInMillis());
		}

		private	void	build()
		{
			start = new Timestamp (cs.getTimeInMillis());
			end = new Timestamp (ce.getTimeInMillis());
		}
	}
	private	void	doIt()	throws	SQLException
	{
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement (
				"INSERT INTO t_news VALUES (?, 2, ?, ?, 1, ?, ?, ?, ?)");
		Term term = new Term();
		for (int i = 0; i < max_count; i++)
		{
			long news_id = 10000 + i;
			String news_ids = "[" + news_id + "]";
			st.setLong (1, news_id);
			term = getTerm (term);
			st.setTimestamp (2, term.start);
			st.setTimestamp (3, term.end);
			st.setInt(4, r.nextInt(2));
			st.setInt(5, r.nextInt(2));
			st.setString (6, "タイトル" + news_ids);
			st.setString (7, "本文" + news_ids);

			st.execute();
		}
		con.commit();
	}

	private	Random	r = new Random();

	private	Term	getTerm (Term term)
	{
		term.reset();
		int start_offset = r.nextInt(500) - 490;
		term.cs.add(Calendar.DATE, start_offset);
		int length = r.nextInt (30) + 10;
		term.ce.setTimeInMillis(term.cs.getTimeInMillis());
		term.ce.add (Calendar.DATE, length);
		term.build();

		return term;
	}
}
