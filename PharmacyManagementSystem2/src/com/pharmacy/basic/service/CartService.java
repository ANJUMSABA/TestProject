package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;

public interface CartService {
	String addToCart(CartItems cartItems);
	List<CartItems> viewCart();
	String removeCartItems(CartItems cartItems);
	String sellMedicines(CartItems cartItems);
	String billGenerate(Bill bill);
	Bill findByBillId(Integer billId);
}
