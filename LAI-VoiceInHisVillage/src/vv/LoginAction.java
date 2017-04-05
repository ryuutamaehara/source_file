package vv;

import java.sql.SQLException;

import dao.UserProfile;
import fw.AbstractDBAction;

/**
 * ログインアクション。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class LoginAction extends AbstractDBAction
{
	private String user_id = null;
	public void setUser_id(String user_id){
		this.user_id=user_id;
	}

	private String password = null;
	public void setPassword(String password){
		this.password=password;
	}

	/** ログインページの表示 */
	public String display(){
		return SUCCESS;
	}
	/** 認証処理を実行する */
	public String execute(){
		try{
			UserProfile userProfile = queryUserProfile();
			if(userProfile==null){
				return super.displayError("ユーザー名またはパスワードに誤りがあります。");
			}

			super.setCurrentUserProfile(userProfile);
			if(userProfile.getIsSuperUser()){
				return SUPERUSER;
			}
			else{
				return SUCCESS;
			}

		}catch(SQLException e){
			return displayError(e);
		}
	}

	private UserProfile queryUserProfile() throws SQLException{
		if(user_id==null || password == null){
			return null;
		}
		return UserProfile.selectForAuth(this,user_id,password);
	}
}
