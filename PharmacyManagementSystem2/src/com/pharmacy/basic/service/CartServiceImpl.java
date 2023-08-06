package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.dao.CartDao;
import com.pharmacy.basic.dao.CartDaoImpl;
import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;

public class CartServiceImpl implements CartDao {
	CartDao cartDao=new CartDaoImpl();
	
	
	@Override
	public String addToCart(CartItems cartItems) {
	
		return cartDao.addToCart(cartItems);
	}

	@Override
	public List<CartItems> viewCart() {
		
		return cartDao.viewCart();
	}

	@Override
	public String removeCartItems(CartItems cartItems) {
		
		return cartDao.removeCartItems(cartItems);
	}

	@Override
	public String sellMedicines(CartItems cartItems) {
		
		return cartDao.sellMedicines(cartItems);
	}

	@Override
	public String billGenerate(Bill bill) {
		
		return cartDao.billGenerate(bill);
	}

	@Override
	public Bill findByBillId(Integer billId) {
		
		return cartDao.findByBillId(billId);
	}

	

}
