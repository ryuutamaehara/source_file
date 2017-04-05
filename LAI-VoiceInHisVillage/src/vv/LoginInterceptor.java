package vv;

import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import dao.UserProfile;
import fw.Const;

/**
 * ログイン状態を判定するインーターセプタ。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class LoginInterceptor extends AbstractInterceptor
{
	public	final	static	String	LOGIN_REQUIRED = "login_required";

	/**
	 * ログイン状態でなければ"login_required"を返して終了。そうでなければ、処理を継続する。
	 */
	@Override
	public String intercept (ActionInvocation invocation) throws Exception
	{
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		UserProfile current_user = (UserProfile)session.get (Const.Session.CURRENT_USER);
		if (current_user == null) {
			return LOGIN_REQUIRED;
		}

		String result = invocation.invoke();
		return result;
	}
}
