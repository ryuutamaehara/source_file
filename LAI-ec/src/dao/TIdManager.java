package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.SQLUtil;
import fw.StringUtil;

/**
 * t_id_manager（ID採番管理テーブル）へのアクセス機能を提供する。
 */
public class TIdManager extends AbstractDAO<TIdManager>
{
	/** ID名 */
	private	String	id_nm = null;
	/** ID名をセットする */
	public	void	setId_nm (String id_nm){this.id_nm = id_nm;}
	/** ID名を取得する */
	public	String	getId_nm(){return this.id_nm;}

	/** 最後に使われたID値 */
	private	long	id_value = 0;
	/** 最後に使われたID値をセットする */
	public	void	setId_value (long id_value){this.id_value = id_value;}
	/** 最後に使われたID値を取得する */
	public	long	getId_value(){return this.id_value;}

	@Override
	public	String	toString()
	{
		return "{" +
				id_nm + "," +
				id_value + "}";
	}

	public	static	long	getNextIDValue (DBConnectionHolder ch, String id_nm)	throws	SQLException
	{
		TIdManager data = selectForUpdate(ch, id_nm);
		if (data == null)
			throw new RuntimeException ("t_id_managerレコードが見つかりませんでした：" + id_nm);

		data.id_value++;
		data.update(ch);
		return data.id_value;
	}

	/** ID名を指定して更新用に１件取得する */
	public	static	TIdManager	selectForUpdate (DBConnectionHolder ch, String id_nm)
	{
		String condition = "WHERE id_nm = " + SQLUtil.getDBStringExpression(id_nm)
				+ "FOR UPDATE";
		ArrayList<TIdManager> result = _select (ch, condition);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}

	private	static	ArrayList<TIdManager>	_select (DBConnectionHolder ch, String sql_option)
	{
		String sql = "SELECT " + "id_nm,id_value,updated_on"
				+ "\r\nFROM t_id_manager";

		if (StringUtil.isNotEmpty (sql_option))
			sql = sql + "\r\n" + sql_option;

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TIdManager>	selector = new Selector<TIdManager>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TIdManager>
	{
		@Override
		public	TIdManager	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TIdManager d = new TIdManager();

			d.id_nm = rs.getString ("id_nm");
			d.id_value = rs.getLong ("id_value");

			return d;
		}
	}

	/** 自インスタンスのデータを使用してテーブルをupdateする。 */
	public	int	update (DBConnectionHolder ch)	throws	SQLException
	{
		String sql = "UPDATE t_id_manager SET id_value = ? WHERE id_nm = ?";
		PreparedStatement st = null;
		try {
			st = ch.getConnection().prepareStatement(sql);
			st.setLong(1, id_value);
			st.setString(2, id_nm);
			int updated_num = st.executeUpdate();
			return updated_num;
		} finally {
			if (st != null) {
				try {
					st.close ();
				} catch (SQLException e2) {
				}
			}
		}
	}
}
