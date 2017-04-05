package fw;

/**
 * SQL関連ユーティリティクラス。
 * @author tsuhtan
 */
public class SQLUtil
{
	public	final	static	char	C_SINGLE_QUOTATION = '\'';

	private	SQLUtil(){}
	public	static	String	getDBStringExpression (String value)
	{
		StringBuilder res = new StringBuilder();
		res.append (C_SINGLE_QUOTATION);
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt (i);
			res.append (c);
			if (c == C_SINGLE_QUOTATION) {
				res.append (c);
			}
		}
		res.append (C_SINGLE_QUOTATION);
		return new String (res);
	}

	public	static	String	createLikeCondition (String[] colnames, String[] _values)
	{
		String[] values = new String[_values.length];
		for (int i = 0; i < values.length; i++) {
			values[i] = getDBStringExpression ("%" + _values[i] + "%");
		}

		StringBuilder buffer = new StringBuilder();
		createLikeCondition (buffer, colnames, values[0]);
		for (int i = 1; i < values.length; i++) {
			buffer.append (" AND ");
			createLikeCondition (buffer, colnames, values[i]);
		}

		return new String (buffer);
	}

	public	static	StringBuilder	createLikeCondition (StringBuilder buffer, String[] colnames, String value)
	{
		if (colnames.length == 1) {
			createLikeCondition (buffer, colnames[0], value);
		}
		else {
			buffer.append ("(");
			createLikeCondition (buffer, colnames[0], value);
			for (int i = 1; i < colnames.length; i++) {
				createLikeCondition (buffer.append (" OR "), colnames[i], value);
			}

			buffer.append (")");
		}

		return buffer;
	}

	public	static	StringBuilder	createLikeCondition (StringBuilder buffer, String colname, String value)
	{
		buffer.append (colname).append (" LIKE ").append (value);

		return buffer;
	}
}
