package com.pharmacy.basic.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import com.pharmacy.basic.entity.Admin;
import com.pharmacy.basic.entity.Medicine;


public  class MedicineDaoImpl implements MedicineDao {
	private static EntityManager entityManager =MyConnection.getEntityManager();
	private EntityTransaction entityTransaction=entityManager.getTransaction();
	
		
	@Override
	public String addMedicine(Medicine medicine) {
		entityTransaction.begin();
		entityManager.persist(medicine);
		entityTransaction.commit();
		return "MEDICINES ADDED SUCCESSFULLY";
		
	}
	
	@Override
	public List<Medicine> viewMedicine(String medicineName) {
		String jpql="SELECT m FROM Medicine m WHERE m.medicineName=:name ";
		Query query=entityManager.createQuery(jpql);
		query.setParameter("name", medicineName);
		
		List<Medicine> medicineList=query.getResultList();
		return medicineList;
	}
		
	@Override
	public String removeMedicinesRecord(Medicine medicine) {
		entityTransaction.begin();
		entityManager.remove(medicine);
		entityTransaction.commit();
		return "MEDICINE RECORD DELETED SUCCESSFULLY";
	}
	@Override
	public String updateMedicine(Medicine medicine) {
		
		entityTransaction.begin();
		Medicine existingMedicine=entityManager.find(Medicine.class, medicine.getMedicineId());
		if(existingMedicine!=null) {
		existingMedicine.setMedicineName(medicine.getMedicineName());
		existingMedicine.setMedicineId(medicine.getMedicineId());
		existingMedicine.setCompanyName(medicine.getCompanyName());
		existingMedicine.setManufacturingDate(medicine.getManufacturingDate());
		existingMedicine.setExpiryDate(medicine.getExpiryDate());
		existingMedicine.setQuantity(medicine.getQuantity());
		existingMedicine.setPricePerUnit(medicine.getPricePerUnit());
		}
		else {
			return  "MEDICINE WITH ID"+ medicine.getMedicineId()+" does not found";
		}
		entityTransaction.commit();
		
		return "MEDICINE RECORD UPDATED SUCCESSFULLY";
	}
	@Override
	public List<Medicine> viewAllMedicines() {
		String jpql="SELECT m from Medicine m";
		Query query=entityManager.createQuery(jpql);

		List<Medicine> medicineList = query.getResultList();
		return medicineList;
	}

	@Override
	public String decreaseMedicineQuantity(Medicine medicine, Integer quantityToDecrease) {
		 entityTransaction.begin();
		    
		   
		    Medicine existingMedicine = entityManager.find(Medicine.class, medicine.getMedicineId());
		    if (existingMedicine != null) {
		        
		        Integer currentQuantity = existingMedicine.getQuantity();
		        Integer newQuantity = currentQuantity - quantityToDecrease;

		        
		        if (newQuantity < 0) {
		           
		            newQuantity = 0;
		        }

		     
		        existingMedicine.setQuantity(newQuantity);
		        entityManager.merge(existingMedicine);
		        entityTransaction.commit();
		
	}
			return " ";
			
	}

	@Override
	public String increaseMedicineQuantity(Medicine medicine, Integer quantityToIncrease) {
		entityTransaction.begin();
		
		Medicine existingMedicine = entityManager.find(Medicine.class, quantityToIncrease);
		if(existingMedicine!=null)
		{
			Integer currentQuantity=existingMedicine.getQuantity();
			Integer newQuantity=currentQuantity + quantityToIncrease;
			
			existingMedicine.setQuantity(newQuantity);
			entityManager.merge(existingMedicine);
			entityTransaction.commit();
			
		}
		return "" ;
	}
}
	
	
	
	
	
	
	
	
	
