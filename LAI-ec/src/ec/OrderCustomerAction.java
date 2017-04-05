package ec;

import dao.TCustomer;
import dao.TOdrHeader;
import data.CartInfo;

/**
 * 注文：顧客情報入力ページのアクション。
 */
public class OrderCustomerAction extends AbstractCustomerEditAction
{
	@Override
	protected TCustomer getCustomer()
	{
		CartInfo cart = super.getCart();
		if (cart == null)
			return null;
		else
			return cart.getCustomer();
	}

	@Override
	protected void saveCustomer(TCustomer customer)
	{
		CartInfo cart = super.getCart();
		TOdrHeader h = cart.getHeader();
		h.setCustomer_nm(customer_nm);
		h.setAddress_1(address_1);
		h.setAddress_2(address_2);
		h.setAddress_3(address_3);
		h.setAddress_4(address_4);
	}
}
