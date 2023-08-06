package com.pharmacy.basic.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.pharmacy.basic.entity.Admin;
import com.pharmacy.basic.entity.Medicine;

public class AdminDaoImpl implements AdminDao {
	
	private static EntityManager entityManager =MyConnection.getEntityManager();
	private EntityTransaction entityTransaction=entityManager.getTransaction();

	@Override
	public boolean login(String username, String email) {
		  String jpql = "SELECT a FROM Admin a WHERE a.username = :username AND a.email = :email";
	        TypedQuery<Admin> query = entityManager.createQuery(jpql, Admin.class);
	        query.setParameter("username", username);
	        query.setParameter("email", email);
	        Admin admin = query.getSingleResult();
	        
	       
	        return admin != null;
		
	}

	@Override
	public boolean register(Admin admin) {
		entityTransaction=entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(admin);
		entityTransaction.commit();
		return true;
	}
	public List<Admin> findAdminByEmail(String email) {

		String jpql="SELECT a FROM Admin a WHERE a.email=:email ";
		Query query=entityManager.createQuery(jpql);
		query.setParameter("email", email);
		
		List<Admin> adminList=query.getResultList();
		return adminList;
	    }

	@Override
	public boolean verifyCredentials(String userName, String email) {
		
            TypedQuery<Admin> query = entityManager.createQuery(
                    "SELECT a FROM Admin a WHERE a.username =:username AND a.email = :email", Admin.class);
            query.setParameter("username", userName);
            query.setParameter("email", email);
            return query.getResultList().size() > 0;
	}

	@Override
	public Admin findAdminById(Integer adminId) {
		 String jpql = "SELECT a FROM Admin a WHERE a.adminId = :adminId";
		
	        TypedQuery<Admin> query = entityManager.createQuery(jpql, Admin.class);
	        query.setParameter("adminId",adminId);
	        Admin admin = query.getSingleResult();
	
	
	     return admin;
	}
	}


