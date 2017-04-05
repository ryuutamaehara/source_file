package fw;

/**
 * プロパティに関連する例外。
 */
public abstract	class PropertyException extends RuntimeException
{
	protected	PropertyException (String message)
	{
		super (message);
	}

	protected	PropertyException (String message, Throwable cause)
	{
		super(message, cause);
	}
}
