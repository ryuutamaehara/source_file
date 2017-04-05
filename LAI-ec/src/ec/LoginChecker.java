package ec;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import dao.TCustomer;
import fw.Const;

/**
 * ログイン状態をチェックするインターセプタ
 */
public class LoginChecker extends AbstractInterceptor
{
	public	final	static	String	LOGIN_REQUIRED = "login_required";

	@Override
	public String intercept (ActionInvocation invocation) throws Exception
	{
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		TCustomer customer = (TCustomer)session.get(Const.Session.CURRENT_CUSTOMER);
		if (customer == null)
			return LOGIN_REQUIRED;

		String result = invocation.invoke();
		return result;
	}

}
