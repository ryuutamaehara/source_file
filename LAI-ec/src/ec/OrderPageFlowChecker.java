package ec;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import dao.TOdrHeader;
import data.CartInfo;
import fw.AbstractAction;
import fw.Const;

/**
 * 買物カゴが注文手続きに入れる状態かどうかをチェックするインターセプタ
 */
public class OrderPageFlowChecker extends AbstractInterceptor
{
	public	final	static	String	BACK_TO_CART = "back_to_cart";
	public	final	static	String	BACK_TO_START = "back_to_order_1";
	public	final	static	String	BACK_TO_CUSTOMER_ENTRY = "back_to_order_2";

	@Override
	public String intercept (ActionInvocation invocation) throws Exception
	{
		AbstractAction action = (AbstractAction)invocation.getAction();
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		CartInfo cart = (CartInfo)session.get(Const.Session.CART);
		if (cart == null)	// カートがなかったら
			return BACK_TO_CART;
		else if (cart.getDetail_list().size() == 0)	//	カートが空だったら
			return BACK_TO_CART;
		else
		{
			TOdrHeader header = cart.getHeader();
			if (action instanceof OrderLoginAction)
				return defaultReturn (invocation);

			if (cart.getCustomer() == null)	// 顧客情報枠なし
				return BACK_TO_START;

			if (action instanceof OrderCustomerAction)
				return defaultReturn (invocation);

			if (header.getCustomer_nm() == null)	//	顧客情報まだ入力されてない
				return BACK_TO_CUSTOMER_ENTRY;

			return defaultReturn (invocation);
		}
	}

	private	String	defaultReturn (ActionInvocation invocation)	throws Exception
	{
		String result = invocation.invoke();
		return result;
	}
}
