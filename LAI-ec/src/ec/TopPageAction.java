package ec;

import java.util.ArrayList;

import dao.TNews;
import dao.VPrdRec;
import fw.AbstractAction;

/**
 * トップページ表示に関する処理。
 */
public	class	TopPageAction extends AbstractAction
{
	private	ArrayList<TNews>	news_list = null;
	/** ニュースリストを取得する */
	public	ArrayList<TNews>	getNews_list(){return news_list;}

	private	ArrayList<dao.VPrdRec>	prd_rec_list = null;
	/** 商品レコメンドリストを取得する。 */
	public	ArrayList<dao.VPrdRec>	getPrd_rec_list(){return prd_rec_list;}

	@Override
	public	String	execute()
	{
		super.loadMasterCateList();
		news_list = TNews.selectForNow(this);
		prd_rec_list = VPrdRec.selectForNow(this);

		return SUCCESS;
	}
}
