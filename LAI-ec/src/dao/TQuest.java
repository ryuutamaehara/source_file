package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fw.DBConnectionHolder;
import fw.SQLUtil;

/**
 * t_quest（クエストテーブル）へのアクセス機能を提供する。
 */
public class TQuest extends AbstractDAO<TQuest>
{
	/** クエストコード */
	private	String	quest_cd = null;
	public	String	getQuest_cd(){return this.quest_cd;}

	/** クエスト名 */
	private	String	quest_nm = null;
	public	String	getQuest_nm(){return this.quest_nm;}

	/** 説明 */
	private	String	description = null;
	public	String	getDescription(){return this.description;}

	@Override
	public	String	toString()
	{
		return "{" +
				quest_cd + "," +
				quest_nm + "," +
				description + "}";
	}

	/** クエストコードを指定して１件取得する */
	public	static	TQuest	select (DBConnectionHolder ch, String quest_cd)
	{
		String sql = "SELECT " + "quest_cd,quest_nm,description "
				+ "\r\nFROM  t_quest"
				+ "\r\nWHERE quest_cd = " + SQLUtil.getDBStringExpression(quest_cd);

		ArrayList<TQuest> result = selector.select(ch, fetcher, sql);
		if (result.size() == 0)
			return null;
		else
			return result.get(0);
	}
	/** 全件取得する */
	public	static	ArrayList<TQuest>	selectAll (DBConnectionHolder ch)
	{
		String sql = "SELECT " + "quest_cd, quest_nm, description"
				+ "\r\nFROM t_quest"
				+ "\r\nORDER BY quest_cd";

		return selector.select(ch, fetcher, sql);
	}

	private	static	Selector<TQuest>	selector = new Selector<TQuest>();
	private	static	MyFetcher	fetcher = new MyFetcher();
	private	static	class	MyFetcher	implements	Fetcher<TQuest>
	{
		@Override
		public	TQuest	fetch (DBConnectionHolder ch,  ResultSet rs)	throws	SQLException
		{
			TQuest d = new TQuest();

			d.quest_cd = rs.getString ("quest_cd");
			d.quest_nm = rs.getString ("quest_nm");
			d.description = rs.getString ("description");

			return d;
		}
	}
}
