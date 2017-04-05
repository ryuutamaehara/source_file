package vv;

import dao.UserProfile;
import fw.AbstractDBAction;

/**
 * ログインユーザプロファイルに応じて、トップページにリダイレクトするAction
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class GoToTopAction extends AbstractDBAction
{
	@Override
	public String execute() throws Exception{
		//getCurrentUserProfileからプロファイル情報を取得して、SUPERUSERならばTrueでSUPERUSERを戻す。
		//FalseならばSUCCESSを戻す。
		UserProfile current_user_profile  = super.getCurrentUserProfile();
		if(current_user_profile.getIsSuperUser()){
			return SUPERUSER;
		}else{
			return SUCCESS;
		}

	}

}
