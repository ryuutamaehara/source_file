package fw;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ＤＢ接続を持ってるもの
 * @author tsuhtan
 */
public interface DBConnectionHolder
{
	public	Connection	getConnection()	throws	SQLException;
}
