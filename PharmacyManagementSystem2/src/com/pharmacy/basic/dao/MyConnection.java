package com.pharmacy.basic.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MyConnection {

private static EntityManagerFactory entityManagerFactory;
private static EntityManager entityManager;

private MyConnection() {
}

public static EntityManager getEntityManager()
{
	if(entityManagerFactory==null)
	{
		entityManagerFactory=Persistence.createEntityManagerFactory("pharmacymanagement1");
		entityManager=entityManagerFactory.createEntityManager();
	}
	return entityManager;
		

	}

}
