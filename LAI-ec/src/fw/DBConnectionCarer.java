package fw;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/** DBコネクションお世話係 */
public class DBConnectionCarer extends AbstractInterceptor
{
	@Override
	public String intercept (ActionInvocation invocation) throws Exception
	{
		AbstractAction action = (AbstractAction)invocation.getAction();
		String result = invocation.invoke();

		if (action.hasConnection())
			action.releaseConnection();

		return result;
	}

}
