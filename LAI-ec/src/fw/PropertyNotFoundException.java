package fw;

/**
 * 設定されているべきプロパティが見つからなかったことを通知するランタイム例外。
 */
public class PropertyNotFoundException extends PropertyException
{
	public	PropertyNotFoundException (String property_name)
	{
		super ("必要なプロパティ：\"" + property_name + "が定義されていません。");
	}
}
