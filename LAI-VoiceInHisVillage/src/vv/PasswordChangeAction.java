package vv;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserProfile;
import fw.AbstractDBAction;

/**
 * パスワードを変更するAction。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class PasswordChangeAction extends AbstractDBAction
{
	private String password1 = null;
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword1() {
		return this.password1;
	}

	private String password2 = null;
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getPassword2() {
		return this.password2;
	}

	private String message = null;
	public String getMessage() {
		return this.message;
	}

	@Override
	public String execute() throws Exception{
		if(password1 == null || password2 == null){
			return SUCCESS;
		}

		UserProfile current_user_profile = super.getCurrentUserProfile(); //ログイン時に取得したユーザのプロファイルデータ
		UserProfile user_profile = UserProfile.selectForUpdate(this,current_user_profile.getUserId()); //最新のユーザプロファイルデータを取得し、レコードをロックする。

		user_profile.setPassword(password1);
		try{
			user_profile.updatePassword(this);
			Connection con = super.getConnection();
			con.commit();
		}catch(SQLException e){
			return super.displayError(e);
		}

		message = super.getText("DONE_MESSAGE");
		return SUCCESS;
	}

	@Override
	public void validate(){
		if(password1 == null || password2 == null){
			return;
		}
		if(! password1.equals(password2)){
			super.addActionError(getText("PASSWORDS_MISMATCH_MESSAGE"));
		}
	}








}
