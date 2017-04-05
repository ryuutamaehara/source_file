package ec;

/** 注文手続きにおける例外 */
public class OrderProcessException extends Exception
{
	public OrderProcessException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public OrderProcessException(String message)
	{
		super(message);
	}
}
