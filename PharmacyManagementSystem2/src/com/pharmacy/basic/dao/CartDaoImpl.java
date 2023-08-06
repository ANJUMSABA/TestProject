package com.pharmacy.basic.dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;
import com.pharmacy.basic.entity.Medicine;

public class CartDaoImpl implements CartDao 
{
	
	private static EntityManager entityManager =MyConnection.getEntityManager();
	private EntityTransaction entityTransaction=entityManager.getTransaction();
	@Override
	public String addToCart(CartItems cartItems) {
		entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(cartItems);
		entityTransaction.commit();
		return "medicines added to cart";	
	}
	@Override
	public List<CartItems> viewCart() {
	
		String jpql="SELECT c FROM CartItems c";
		Query query= entityManager.createQuery(jpql);
		List<CartItems> cartList= query.getResultList();
		return cartList;
	}
	@Override
	public String removeCartItems(CartItems cartItems) {
		entityTransaction.begin();
		entityManager.remove(cartItems);
		entityTransaction.commit();
		return "Items remove from the cart";
	}
	@Override
	public String sellMedicines(CartItems cartItems) {
		entityTransaction.begin();
		String jpql="SELECT c FROM CartItems c";
		Query query=entityManager.createQuery(jpql);
		List<CartItems> cartItemList=query.getResultList();
		
		return " Medicines sold successfully"; 
		
	}
	@Override
	public String billGenerate(Bill bill) {
		entityTransaction.begin();
		entityManager.persist(bill);
		entityTransaction.commit();
	
		return "BILL GENERATED SUCCESSFULLY";
		
		
	}
	@Override
	public Bill findByBillId(Integer billId) {
		
		return entityManager.find(Bill.class, billId);
	}
}