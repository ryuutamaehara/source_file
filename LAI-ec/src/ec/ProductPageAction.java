package ec;

import dao.TCate;
import dao.TPrd;
import fw.AbstractAction;

/**
 * 商品ページ表示アクション。
 */
public class ProductPageAction extends AbstractAction
{
	private	TPrd	prd = null;
	public	TPrd	getPrd(){return this.prd;}

	private	TCate	cate = null;
	public	TCate	getCate(){return this.cate;}

	@Override
	public	String	execute()
	{
		super.loadMasterCateList();

		String prd_cd = super.getRequestedCdFromURI();
		prd = TPrd.selectWithPrdCd(this, prd_cd);

		cate = TCate.select(this, prd.getLeading_category_cd());

		return SUCCESS;
	}
}
