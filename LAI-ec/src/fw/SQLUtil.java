package fw;

/**
 * SQL関連ユーティリティクラス。
 */
public class SQLUtil
{
	public	final	static	char	C_SINGLE_QUOTATION = '\'';
	public	final	static	char	C_ESCAPE_CHAR = '\\';
	private	static	String	chars_to_escape = new String (new char[]{C_SINGLE_QUOTATION, C_ESCAPE_CHAR});

	private	SQLUtil(){}

	/**
	 * SQL用文字列表現を取得
	 * @param value 元の文字列
	 */
	public	static	String	getDBStringExpression (String value)
	{
		if (value == null)
			return "NULL";

		StringBuilder res = new StringBuilder();
		res.append (C_SINGLE_QUOTATION);
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt (i);
			res.append (c);
			if (chars_to_escape.indexOf(c) >= 0) {
				res.append (c);
			}
		}
		res.append (C_SINGLE_QUOTATION);
		return new String (res);
	}

	/**
	 * 指定された項目と値の組み合わせについてのLIKE条件を生成する
	 * @param columns 検索対象項目名（OR結合）
	 * @param _values 検索対象文字列（AND結合）
	 */
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

	/**
	 * 指定された項目と値の組み合わせについてのLIKE条件を生成する
	 * @param buffer SQL文字列各要用バッファ
	 * @param colnames 検索対象項目名（OR結合）
	 * @param value 検索対象文字列
	 */
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

	/**
	 * 指定された項目と値の組み合わせについてのLIKE条件を生成する
	 * @param buffer SQL文字列各要用バッファ
	 * @param colname 検索対象項目名
	 * @param value 検索対象文字列
	 */
	public	static	StringBuilder	createLikeCondition (StringBuilder buffer, String colname, String value)
	{
		buffer.append (colname).append (" LIKE ").append (value);

		return buffer;
	}

	/**
	 * 指定された期間項目の間に現在日時（now()）が含まれることを示す条件を生成する
	 * @param term_start_col_name 期間開始を示す項目名
	 * @param term_end_col_name 期間終了を示す項目名
	 */
	public	static	String	createForNowCondition (String term_start_col_name, String term_end_col_name)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append("(NOW() BETWEEN ").append (term_start_col_name).append(" AND ").append(term_end_col_name).append(")");
//		buffer.append(term_start_col_name).append(" <= NOW() AND NOW() <= ").append(term_end_col_name);
		return new String (buffer);
	}

	/**
	 * ２つの条件を"AND"結合する。
	 * @param cond1
	 * @param cond2
	 * @return
	 */
	public	static	String	concatConditionAND (String cond1, String cond2)
	{
		return concatCondition (cond1, cond2, "AND");
	}

	/**
	 * ２つの条件を"OR"結合する。
	 * @param cond1
	 * @param cond2
	 * @return
	 */
	public	static	String	concatConditionOR (String cond1, String cond2)
	{
		return concatCondition (cond1, cond2, "OR");
	}

	private	static	String	concatCondition (String cond1, String cond2, String conjunction)
	{
		if (cond1 == null && cond2 == null)
			return null;
		if (cond1 == null)
			return cond2;
		if (cond2 == null)
			return cond1;
		String cond = "(" + cond1 + ") " + conjunction + " (" + cond2 + ")";
		return cond;
	}

	/** "in"条件を作成する。valuesの要素数が１件の場合は、"="条件とする */
	public	static	String	createInCondition (String colname, long[] values)
	{
		if (values.length == 0)
			return "";
		if (values.length == 1)
			return colname + " = " + values[0];

		StringBuilder buffer = new StringBuilder();
		buffer.append(colname).append(" IN (").append(values[0]);
		for (int i = 1; i < values.length; i++)
			buffer.append(", ").append(values[i]);
		buffer.append(')');

		return new String (buffer);
	}
}
