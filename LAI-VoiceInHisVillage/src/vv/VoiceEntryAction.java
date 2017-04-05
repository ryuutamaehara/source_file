package vv;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.UserProfile;
import dao.Voice;
import fw.AbstractDBAction;

/**
 * 声を登録するアクション。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class VoiceEntryAction extends AbstractDBAction
{
	private String subject = null;

	public String getSubject() {
		return this.subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}

	private String voice_body = null;

	public String getVoice_body() {
		return this.voice_body;
	}
	public void setVoice_body(String voice_body) {
		this.voice_body = voice_body;
	}

	/**
	 * 登録を実行する。
	 */

	public String execute(){
			Voice voice = prepareToSave();
			try{
				voice.insert(this);	//AbstractDAOを参照すると、更新系の処理が書かれている。(executeupdate)
				Connection con = super.getConnection();
				con.commit();
			}catch(SQLException e){
				return super.displayError(e);
			}
			return SUCCESS;

	}

	private Voice prepareToSave(){
		Voice voice = new Voice();
		voice.setVoiceId(assignVoiceId());
		UserProfile user_profile = super.getCurrentUserProfile();
		voice.setPostedBy(user_profile.getUserId());
		voice.setPostedOn(new Timestamp (System.currentTimeMillis()));	//long型のsystem日付を取得し、コンストラクタで現在日次を生成
		voice.setSubject(subject);
		voice.setVoiceBody(voice_body);

		return voice;
	}

	/*乱数をつけて一意IDを生成する。(実際の業務ではこの処理だけでは
	この処理だけで一意のIDを作成できないので、既存のIDとコンペアを実行し、重複したIDならばもう一度処理を
	実行する、といった動作が多い。（らしい）*/
	private String assignVoiceId(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss-");
		StringBuilder buffer = new StringBuilder();
		buffer.append(df.format(new Date()));
		String random_value = Integer.toString((int)(999 * Math.random()));
		for(int i = 3-random_value.length(); i > 0; i--){
			buffer.append('0');
		}
		buffer.append(random_value);
		return new String(buffer);

	}




}
