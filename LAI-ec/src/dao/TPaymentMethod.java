package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;

/**
 * t_payment_method（支払方法テーブル）へのアクセス機能を提供する。
 */
public class TPaymentMethod extends AbstractDAO<TPaymentMethod>
{
	/** 支払方法ID : 銀行引き落とし (10) */
	public	final	static	int	CASH = 10;
	/** 支払方法ID : クエストで支払う (20) */
	public	final	static	int	QUEST = 20;

	/** 支払方法ID */
	private	int	payment_method_id = 10;
	public	int	getPayment_method_id(){return this.payment_method_id;}

	/** 支払方法名 */
	private	String	payment_method_nm = null;
	public	String	getPayment_method_nm(){return this.payment_method_nm;}

	@Override
	public	String	toString()
	{
		return "{" +
				payment_method_id + "," +
				payment_method_nm + "}";
	}

	/** 支払方法IDを指定して１件取得する */
	public	static	TPaymentMethod	select (DBConnectionHolder ch, int payment_method_id)
	{
		String sql = "SELECT " + "payment_method_id, payment_method_nm"
				+ "\r\nFROM t_payment_method"
				+ "\r\nWHERE payment_method_id = " + payment_method_id;

		ArrayList<TPaymentMethod> result = selector.select(ch, fetcher, sql);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	/** 全件取得する */
	public	static	ArrayList<TPaymentMethod>	selectAll (DBConnectionHolder ch)
	{
		String sql = "SELECT " + "payment_method_id, payment_method_nm"
				+ "\r\nFROM t_payment_method"
				+ "\r\nORDER BY payment_method_id";

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TPaymentMethod>	selector = new Selector<TPaymentMethod>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TPaymentMethod>
	{
		@Override
		public	TPaymentMethod	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TPaymentMethod d = new TPaymentMethod();

			d.payment_method_id = rs.getInt ("payment_method_id");
			d.payment_method_nm = rs.getString ("payment_method_nm");

			return d;
		}
	}
}
