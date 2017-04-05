package fw;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ＤＢ接続を持ってるもの
 */
public interface DBConnectionHolder
{
	/** ＤＢ接続を取得する */
	public	Connection	getConnection()	throws	SQLException;

	/** 指定されたプロパティを取得する */
	public	String	getText (String key);
	/** 指定されたintプロパティを取得する */
	public	int	getInt (String key);
}
