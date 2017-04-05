package vv;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserProfile;
import dao.Voice;
import fw.AbstractDBAction;
import fw.SQLUtil;

/**
 * 自分の投稿一覧を表示する。
 * @author tsuhtan
 */
@SuppressWarnings ("serial")
public class CopyOfMyVoiceListAction extends AbstractDBAction
{
	//field
	private String search_keyword = null;
	//getter&setter
	public String getSearch_keyword(){
		return this.search_keyword;
	}
	public void setSearch_keyword(String serch_keyword){
		this.search_keyword = serch_keyword;
	}

	private Voice[] voices = null;
	private Voice[] getVoices(){
		return this.voices;
	}

	/** 一覧ページの表示 */
	public String display(){
		try{
			voices = queryVoices();
			return SUCCESS;
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	private Voice[] queryVoices() throws SQLException{
		String condition = createCondition();
		Voice[] voices = Voice.selectVoicesForList(this,condition);
		return voices;
	}


	private String createCondition(){
		UserProfile my_profile = super.getCurrentUserProfile();
		String condition = "user_id=" + SQLUtil.getDBStringExpression(my_profile.getUserId());
		if(search_keyword == null){
			return condition;
		}
		search_keyword = search_keyword.trim();
		String[] keys = search_keyword.split(" ");
		ArrayList<String> keys_list = new ArrayList<String>();
		for(String k:keys){
			if(k.length() > 0){
				keys_list.add(k);
			}

		}
		if(keys_list.size() == 0){
			return condition;
		}
		keys = keys_list.toArray(new String[0]);
		condition += "AND(" + SQLUtil.createLikeCondition(new String[]{"subject","voice_body"},keys) + ")";

		return condition;
	}





}
