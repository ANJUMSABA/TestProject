package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.entity.Medicine;

public interface MedicineService {
	
	String addMedicine( Medicine medicine);
	List<Medicine> viewMedicine(String medicineName);
	String  removeMedicinesRecord(Medicine medicine);
	String updateMedicine(Medicine medicine);
	String decreaseMedicineQuantity(Medicine medicine,Integer quantityToDecrease);
	List<Medicine> viewAllMedicines();
	String increaseMedicineQuantity(Medicine medicine,Integer quantityToIncrease);
	
}
