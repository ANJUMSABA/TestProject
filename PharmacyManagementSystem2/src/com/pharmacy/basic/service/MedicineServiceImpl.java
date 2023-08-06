package com.pharmacy.basic.service;

import java.util.List;

import com.pharmacy.basic.dao.MedicineDao;
import com.pharmacy.basic.dao.MedicineDaoImpl;
import com.pharmacy.basic.entity.Medicine;

public class MedicineServiceImpl implements MedicineService {
	
	MedicineDao  medicineDao= new MedicineDaoImpl();

	@Override
	public String addMedicine(Medicine medicine) {
		
		return medicineDao.addMedicine(medicine);
	}

	@Override
	public List<Medicine> viewMedicine(String medicineName) {
		
		return medicineDao.viewMedicine(medicineName);
	}

	@Override
	public String removeMedicinesRecord(Medicine medicine) {
		
		return medicineDao.removeMedicinesRecord(medicine);
	}

	@Override
	public String updateMedicine(Medicine medicine) {
		
		return medicineDao.updateMedicine(medicine);
	}

	
	@Override
	public List<Medicine> viewAllMedicines() {
		
		return medicineDao.viewAllMedicines();
	}

	@Override
	public String decreaseMedicineQuantity(Medicine medicine, Integer quantityToDecrease) {
		
		return medicineDao.decreaseMedicineQuantity(medicine, quantityToDecrease);
	}

	@Override
	public String increaseMedicineQuantity(Medicine medicine, Integer quantityToIncrease) {
		
		return medicineDao.increaseMedicineQuantity(medicine, quantityToIncrease);
	}

	

}
