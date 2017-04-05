package ec;

import java.util.HashMap;
import java.util.Map;

import data.BadCodeException;
import data.CartInfo;
import data.OdrDetailInfo;
import fw.AbstractAction;
import fw.StringUtil;

/**
 * カートページに関する処理。
 */
public class CartPageAction extends AbstractAction
{
	private	final	static	String	RES_STAY = "stay";
	private	final	static	String	GO_TO_TOP = "go_to_top";
	private	final	static	String	RES_START_ORDER = "start_order";

	private	String	command = null;	// なし, "RECALC", "REMOVE", "START_ORDER"のいずれか
	public	void	setCommand (String command){this.command = command;}

	private	String	prd_cd = null;
	public	void	setPrd_cd (String prd_cd){this.prd_cd = prd_cd;}

	private	CartInfo	cart = null;
	public	CartInfo	getCart(){return this.cart;}
	private	boolean	cart_modified = false;

	@Override
	public	String	execute()
	{
		if (command == null)
			command = "";

		cart = super.getCart();
		if (cart == null)
		{
			cart = CartInfo.newInstance(this);
			super.setCart(cart);
		}

		try {
			refresh();
		} catch (BadCodeException e) {
			return super.displayError(super.getText("Message.BAD_PRD_CD"));
		}

		if (cart.getDetail_list().size() == 0)
			super.setError_message(super.getText("Message.CART_IS_EMPTY"));
		if (cart_modified)
		{
			if (command.equalsIgnoreCase("start_order"))
				super.setError_message(super.getText("Message.CART_MODIFIED"));

			return RES_STAY;
		}
		else
		{
			switch (command.toLowerCase())
			{
				case "go_to_top":
					return GO_TO_TOP;
				case "start_order":
					if (cart.getDetail_list().size() == 0)
						return RES_STAY;
					else
						return RES_START_ORDER;
				default:
					return RES_STAY;
			}
		}
	}

	private	final	static	String	PARAM_QUANTITY = "quantity.";
	private	final	static	int	PARAM_QUANTITY_VALUE_START_INDEX = PARAM_QUANTITY.length();

	private	void	refresh()	throws BadCodeException
	{
		processAddOrRemove();
		processQuantity();

		if (cart_modified)
			cart.recalculate(this);
	}

	private	void	processAddOrRemove()	throws BadCodeException
	{
		if (StringUtil.isNotEmpty(prd_cd))
		{
			if (command.equalsIgnoreCase("remove"))
				cart.removeProduct(this, prd_cd);
			else
				cart.addProduct(this, prd_cd);

			cart_modified = true;
		}
	}

	private	void	processQuantity()	throws BadCodeException
	{
		HashMap<String, String[]> params = super.getParameters();
		for (Map.Entry<String, String[]> entry : params.entrySet())
		{
			String name = entry.getKey();
			if (! name.startsWith(PARAM_QUANTITY))
				continue;

			String target_prd_cd = name.substring(PARAM_QUANTITY_VALUE_START_INDEX);
			int new_quantity = Integer.parseInt(entry.getValue()[0]);
			OdrDetailInfo odi = cart.getDetail(this, target_prd_cd);
			if (odi != null && odi.detail.getQuantity() != new_quantity)
			{
				odi.setQuantity(new_quantity);
				cart_modified = true;
			}
		}
	}
}
