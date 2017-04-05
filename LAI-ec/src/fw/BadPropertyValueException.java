package fw;

/**
 * プロパティの設定値が不正であることを通知するランタイム例外。
 */
public class BadPropertyValueException extends PropertyException
{
	public	BadPropertyValueException (String name, String value, Exception cause)
	{
		super ("プロパティ：\"" + name + "の値：\"" + value + "\" は不適切です。", cause);
	}
}
