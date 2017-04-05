package vv;

import java.sql.Connection;
import java.sql.SQLException;

import dao.UserProfile;
import dao.Voice;
import fw.AbstractDBAction;

/**
 * 声を削除するアクション。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class VoiceDeleteAction extends AbstractDBAction
{

	private String voice_id = null;

	public String getVoice_id() {
		return this.voice_id;
	}
	public void setVoice_id(String voice_id) {
		this.voice_id = voice_id;
	}

/**
 * 削除を実行する。
 */

	public String execute(){
		UserProfile my_profile = super.getCurrentUserProfile();
		try{
			Voice voice = Voice.selectToDelete(this ,voice_id,my_profile.getUserId());
			if(voice == null){
				return super.displayError(super.getText("TARGET_NOT_FOUND_MESSAGE"));
			}
			voice.delete(this);
			Connection con = super.getConnection();
			con.commit();

		}catch(SQLException e){
			return displayError(e);
		}
		return SUCCESS;
	}

}
