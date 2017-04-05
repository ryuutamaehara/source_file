package ec;

import java.util.ArrayList;

import dao.TCate;
import dao.TPrd;
import fw.AbstractAction;
import fw.SQLUtil;
import fw.StringUtil;

/**
 * 検索結果表示ページアクション。
 */
public class SearchPageAction extends AbstractAction
{
	private	TCate	cate = null;

	private	ArrayList<TPrd>	prd_list = null;
	public	ArrayList<TPrd>	getPrd_list(){return this.prd_list;}

	private	String	page_title = null;
	public	String	getPage_title(){return this.page_title;};

	@Override
	public	String	execute()
	{
		setCateCdWithURL();

		super.loadMasterCateList();

		String order_by = "ORDER BY prd_cd";
		String condition = createCondition();
		prd_list = TPrd.selectWithCondition(this, condition, order_by);

		preparePageTitle();

		return SUCCESS;
	}

	private	void	setCateCdWithURL()
	{
		if (super.cate_cd != null)	//	パラメタから取得できていたらそちらを優先。
			return;

		String uri = super.getRequestContextLocalURI();
		if (! uri.startsWith("/cate"))
			return;

		int x1 = uri.lastIndexOf('/') + 1;
		int x2 = uri.lastIndexOf('.');
		if (x2 >= 0)
			cate_cd = uri.substring(x1, x2);	// /cate/癒し/C100010.html 形式
		else
		{
			cate_cd = uri.substring(x1);	// /cate/癒し/C100010 or /cate/癒し/C100010/
			x2 = uri.lastIndexOf('/');
			if (x2 >= 0)
				cate_cd = cate_cd.substring(0, x2);
		}
	}

	private	String	createCondition() //where句のコードを生成するメソッド
	{
		String condition = null;
		if (StringUtil.isNotEmpty(cate_cd))
		{
			cate = TCate.select(this, cate_cd);
			if (cate != null)	// カテゴリコードが正しくなければ無視。
				condition = "leading_category_cd =" + fw.SQLUtil.getDBStringExpression(cate_cd);

		}

		if (StringUtil.isNotEmpty(search_keyword))
			condition = SQLUtil.concatConditionAND (condition, createSearchKeywordCond());

		if (StringUtil.isNotEmpty(condition))
			condition = "WHERE " + condition;

		return condition;
	}

	private	String	createSearchKeywordCond()
	{
		String[] keys = search_keyword.trim().split(" ");
		ArrayList<String> key_list = new ArrayList<String>();
		for (String key : keys)
			if (key.length() > 0)
				key_list.add(key);

		if (key_list.isEmpty())
			return null;

		String condition = SQLUtil.createLikeCondition(
				new String[]{"prd_nm"}, key_list.toArray(new String[0]));

		return condition;
	}

	private	void	preparePageTitle()
	{
		ArrayList<String> titles = new ArrayList<String>();
		if (StringUtil.isNotEmpty(cate_cd))
			titles.add ("「" + cate.getCate_nm() + "」カテゴリの商品");
		if (StringUtil.isNotEmpty(search_keyword))
			titles.add ("検索結果：「" + search_keyword + "」");

		switch (titles.size())
		{
			case 0:
				page_title = "全商品";
				break;
			case 1:
				page_title = titles.get(0);
				break;
			default:
				page_title = titles.get(0) + "からの" + titles.get(1);
				break;
		}
	}
}
