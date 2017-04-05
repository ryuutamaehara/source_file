package vv;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserProfile;
import dao.Voice;
import fw.AbstractDBAction;
import fw.SQLUtil;

/**
 * 王様用声一覧表示アクション。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class VoiceListAction extends AbstractDBAction
{
	private String search_keyword = null;
	public String getSearch_keyword(){
		return this.search_keyword;
	}
	public void setSearch_keyword(String search_keyword){
		this.search_keyword = search_keyword;
	}
	private Voice[] voices = null;
	public  Voice[] getVoices(){return this.voices;}

	/** 一覧ページの表示 */
	public String display(){
		try{
			voices = queryVoices();
			return SUCCESS;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	private Voice[] queryVoices() throws SQLException
	 {
	  String condition = createCondition();
	  Voice[] voices = Voice.selectVoicesForList (this, condition);
	  //CurrentUserProfileでユーザープロファイルのセッション情報を取得
	  UserProfile currentUserProfile = super.getCurrentUserProfile();
	  /*getIsSuperUser()の戻り値がSUPERUSERなので、否定演算子をつけてスーパーユーザー以外は
	  処理を実行するという条件にしてる*/
	  if(!currentUserProfile.getIsSuperUser()){
		  	//対象ユーザーの発言リストの、名前を"--"で上書きする
		  	for(Voice voice:voices){
		  		voice.setUserName("--");
		  	}
	  }
	  return voices;
	 }

	private String createCondition(){
		/*UserProfile my_profile = super.getCurrentUserProfile();
		String condition = "user_id = " + SQLUtil.getDBStringExpression(my_profile.getUserId());*/
		if(search_keyword == null){
			return null;
			//return condition;
		}
		search_keyword = search_keyword.trim();
		String[] keys = search_keyword.split(" ");
		ArrayList<String> keys_list = new ArrayList<String>();
		for(String k : keys){
				if(k.length()>0){
					keys_list.add(k);
				}
		}
		if(keys_list.size() == 0){
			return null;
			//return condition;
		}
		keys = keys_list.toArray(new String[0]);
		//ここがよくわかんない。m2fbで聞きたいとこ。
		String condition = SQLUtil.createLikeCondition(new String[]{"subject","voice_body"},keys);

		//condition += " AND (" + SQLUtil.createLikeCondition(new String[]{"subject","voice_body"},keys) + ")";

		return condition;
	}






}
