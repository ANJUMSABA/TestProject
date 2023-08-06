package com.pharmacy.basic.dao;

import java.util.List;

import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;
import com.pharmacy.basic.entity.Medicine;

public interface CartDao {
	String addToCart(CartItems cartItems);
	List<CartItems> viewCart();
	String removeCartItems(CartItems cartItems);
	String sellMedicines(CartItems cartItems);
	String billGenerate(Bill bill);
	Bill findByBillId(Integer billId);
}