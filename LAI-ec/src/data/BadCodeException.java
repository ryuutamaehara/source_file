package data;

/**
 * 指定されたコードが不正であることを通知する例外。
 */
public class BadCodeException extends Exception
{
	/** コード名 */
	public	String	cd_name = null;
	/** コード値 */
	public	String	cd_value = null;

	public	BadCodeException (String cd_name, String cd_value)
	{
		super (cd_name + "としての値が不正です：" + cd_value);
	}
}
