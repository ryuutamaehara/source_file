package fw;

/**
 * String関連ユーティリティクラス。
 */
public class StringUtil
{
	private	StringUtil(){};

	/** 指定された文字列がnullまたは空文字列相当であればfalse、それ以外はtrueを返す。 */
	public	static	boolean	isNotEmpty (String s)
	{
		return s != null && s.trim().length() > 0;
	}

	/** 指定された文字列がnullまたは空文字列相当であればtrue、それ以外はfalseを返す。 */
	public	static	boolean	isEmpty (String s)
	{
		return s == null || s.trim().length() == 0;
	}

	public	final	static	String	HEX_CHARS = "0123456789abcdef";

	/** バイト列の内容を16進数表記の文字列に変換する。 */
	public	static	String	toHexString (byte[] bytes)
	{
		StringBuilder buffer = new StringBuilder();
		for (byte b : bytes)
		{
			buffer.append (HEX_CHARS.charAt (((int)b & 0xf0) >> 4));
			buffer.append (HEX_CHARS.charAt (b & 0x0f));
		}

		return new String (buffer);
	}

	/**
	 * 配列の文字列表現を作成する。
	 * @param values 変換対象文字列配列
	 */
	public	static	String	toString (String[] values)
	{
		StringBuilder buffer = new StringBuilder();
		buffer.append('[');
		if (values.length > 0)
		{
			append1 (buffer, values[0]);
			for (int i = 1; i < values.length; i++)
				append1 (buffer.append(','), values[i]);
		}
		buffer.append(']');
		return new String (buffer);
	}

	private	static	StringBuilder	append1 (StringBuilder buffer, String value)
	{
		buffer.append ('"').append (value.replaceAll("\"", "\\\"")).append ('"');
		return buffer;
	}
}
