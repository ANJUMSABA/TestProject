package com.pharmacy.basic.validation;

import java.util.List;

import com.pharmacy.basic.entity.Medicine;
import com.pharmacy.basic.service.MedicineService;
import com.pharmacy.basic.service.MedicineServiceImpl;

public class PharmacyValidation {
	private static MedicineService medicineService=new MedicineServiceImpl();


	 private static final String NAME_REGEX = "^[A-Za-z0-9]+$";
	    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

	    public boolean validateName(String userName) {
	        return userName.matches(NAME_REGEX);
	    }

	    public boolean isValidEmail(String email) {
	        return email.matches(EMAIL_REGEX);
	    }
	
	    public static boolean validatepricePerUnit(Double pricePerUnit)
	    {
	        return pricePerUnit > 0;
	    }
	   
	    public static boolean validateQuantity(Integer quantity) 
	    {
	        return quantity > 0;
	    }
	    
	    public static boolean isPrimaryKeyUnique(Integer medicineId) {
	        List<Medicine> existingMedicines =medicineService. viewAllMedicines();
	        return existingMedicines.stream().noneMatch(medicine -> medicine.getMedicineId().equals(medicineId));
	    
}
	    public static boolean isMedicineNameUnique(String medicineName) {
	    	List<Medicine> existingMedicines=medicineService.viewAllMedicines();
			return existingMedicines.stream().noneMatch(medicine->medicine.getMedicineName().equals(medicineName));
	    	
	    }
}

