package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;


/**
 * t_odr_status（受注ステータステーブル）へのアクセス機能を提供する。
 */
public class TOdrStatus extends AbstractDAO<TOdrStatus>
{
	/** 注文ステータスID : 新規受付 (10) */
	public	final	static	int	NEW = 10;
	/** 注文ステータスID : 出荷待ち (20) */
	public	final	static	int	SHIPPING_REQUESTED = 20;
	/** 注文ステータスID : 出荷済 (30) */
	public	final	static	int	SHIPPED = 30;
	/** 注文ステータスID : お届け済 (35) */
	public	final	static	int	DELIVERED = 35;
	/** 注文ステータスID : キャンセル (80) */
	public	final	static	int	CANCELED = 80;
	/** 注文ステータスID : 出荷取消し (81) */
	public	final	static	int	REVOKED = 81;
	/** 注文ステータスID : 返品 (90) */
	public	final	static	int	RETURNED = 90;

	/** 受注ステータスID */
	private	int	odr_status_id = 10;
	public	int	getOdr_status_id(){return this.odr_status_id;}

	/** 受注ステータス */
	private	String	odr_status_nm = null;
	public	String	getOdr_status_nm(){return this.odr_status_nm;}

	@Override
	public	String	toString()
	{
		return "{" +
				odr_status_id + "," +
				odr_status_nm + "}";
	}

	/** 受注ステータスIDを指定して１件取得する */
	public	static	TOdrStatus	select (DBConnectionHolder ch, int odr_status_id)
	{
		String sql = "SELECT " + "odr_status_id, odr_status_nm"
				+ "\r\nFROM t_odr_status"
				+ "\r\nWHERE odr_status_id = " + odr_status_id;

		ArrayList<TOdrStatus> result = selector.select(ch, fetcher, sql);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	private	static	Selector<TOdrStatus>	selector = new Selector<TOdrStatus>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TOdrStatus>
	{
		@Override
		public	TOdrStatus	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TOdrStatus d = new TOdrStatus();

			d.odr_status_id = rs.getInt ("odr_status_id");
			d.odr_status_nm = rs.getString ("odr_status_nm");

			return d;
		}
	}
}
