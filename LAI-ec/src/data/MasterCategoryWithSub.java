package data;

import java.util.ArrayList;
import java.util.HashMap;

import dao.TCate;
import dao.TMasterCate;
import fw.DBConnectionHolder;

/**
 * マスタカテゴリ＋サブカテゴリ情報。
 */
public class MasterCategoryWithSub
{
	private	static	ArrayList<MasterCategoryWithSub>	cate_tree = null;

	/** カテゴリツリーを読み込む。既に読込済みであればそれを返す。 */
	public	synchronized	static	ArrayList<MasterCategoryWithSub>	loadCategoryTree (DBConnectionHolder ch)
	{
		if (cate_tree != null && cate_tree.size() > 0)
			return cate_tree;

		cate_tree = new ArrayList<MasterCategoryWithSub>();
		ArrayList<TMasterCate> masters = TMasterCate.selectAll (ch);
		HashMap<String, MasterCategoryWithSub> master_map = new HashMap<String, MasterCategoryWithSub>();
		for (TMasterCate m : masters)
		{
			MasterCategoryWithSub mdata = MasterCategoryWithSub.newInstance(m);
			cate_tree.add (mdata);
			master_map.put (m.getMaster_cate_cd(), mdata);
		}

		ArrayList<TCate> cates = TCate.selectAll(ch);
		for (TCate c : cates)
		{
			MasterCategoryWithSub mdata = master_map.get(c.getMaster_cate_cd());
			mdata.addCategory(c);
		}

		return cate_tree;
	}

	public	static	MasterCategoryWithSub	newInstance (TMasterCate core)
	{
		MasterCategoryWithSub d = new MasterCategoryWithSub();
		d.core = core;
		return d;
	}

	private	MasterCategoryWithSub(){}

	private	TMasterCate	core = null;
	public	TMasterCate	getCore(){return core;}

	private	ArrayList<TCate> categories = new ArrayList<TCate>();
	public	void	addCategory (TCate cate) {categories.add(cate);}
	public	ArrayList<TCate>	getCategories(){return categories;}


	public	String	toString()
	{
		return "{" + core + "," + categories + "}";
	}
}
