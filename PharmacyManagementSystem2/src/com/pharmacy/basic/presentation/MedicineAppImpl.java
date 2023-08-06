package com.pharmacy.basic.presentation;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.pharmacy.basic.dao.CartDao;
import com.pharmacy.basic.dao.CartDaoImpl;
import com.pharmacy.basic.dao.MedicineDao;
import com.pharmacy.basic.dao.MedicineDaoImpl;
import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;
import com.pharmacy.basic.entity.Medicine;
import com.pharmacy.basic.service.MedicineService;
import com.pharmacy.basic.service.MedicineServiceImpl;
import com.pharmacy.basic.validation.PharmacyValidation;

public class MedicineAppImpl implements MedicineApp {

	

		MedicineService medicineService=new MedicineServiceImpl();
				
		Scanner scanner=new Scanner(System.in);
			
		public void addMedicine() {
			System.out.println("========================================================================");
		System.out.println("ADD MEDICINE");
		
		 Integer medicineId;
	        boolean isUnique;
	        do {
	            System.out.println("ENTER THE MEDICINE ID:");
	            medicineId = scanner.nextInt();
	            isUnique = PharmacyValidation.isPrimaryKeyUnique(medicineId);

	            if (!isUnique) {
	                System.out.println("Medicine ID already exists. Please enter a unique ID.");
	            }
	        } while (!isUnique);
	        
	        scanner.nextLine(); 
	        
	        boolean addMedicineName = true;
	        while (addMedicineName) {
	            String medicineName;
	            boolean isNameUnique;
	            do {
	                System.out.println("ENTER THE MEDICINE NAME (type 'exit' to stop):");
	                medicineName = scanner.nextLine();
	                
	                if (medicineName.equalsIgnoreCase("exit")) {
	                    addMedicineName = false;
	                    break;
	                }
	                isNameUnique = PharmacyValidation.isMedicineNameUnique(medicineName);
	                if (!isNameUnique) {
	                    System.out.println("Medicine name already exists. Please enter a different name.");
	                }
	            } while (!isNameUnique);

	            if (!addMedicineName) {
	                break;
	            }
 
		System.out.println("ENTER THE COMPANY NAME:");
		String companyName=scanner.nextLine();
		
		Integer quantity;
		while (true) {
		    System.out.println("ENTER THE QUANTITY:");
		    quantity = scanner.nextInt();
		    if (!PharmacyValidation.validateQuantity(quantity)) {
		        System.out.println("Invalid quantity. Please enter a quantity greater than 0.");
		    } else {
		        break; 
		    }
		}

        			
        LocalDate manufacturingDate;
        LocalDate expiryDate;
        do {
            System.out.println("ENTER THE MANUFACTURING DATE (YYYY-MM-DD):");
            String manufacturingDateStr = scanner.next();
            manufacturingDate = LocalDate.parse(manufacturingDateStr);

            System.out.println("ENTER THE EXPIRY DATE (YYYY-MM-DD):");
            String expiryDateStr = scanner.next();
            expiryDate = LocalDate.parse(expiryDateStr);

            if (manufacturingDate.equals(expiryDate)) {
                System.out.println("Manufacturing date and expiry date should not be the same. Please enter different dates.");
            }
            else if(expiryDate.isBefore(manufacturingDate))
            	System.out.println("Expiry date should not be less than manufacturing date. Please enter valid dates.");
        } while (manufacturingDate.equals(expiryDate)||expiryDate.isBefore(manufacturingDate));
		
        Double pricePerUnit;
        while (true) {
            System.out.println("ENTER THE PRICE PER UNIT:");
            pricePerUnit = scanner.nextDouble();
            if (PharmacyValidation.validatepricePerUnit(pricePerUnit)) {
                break;
            } else {
                System.out.println("Invalid price per unit. Please enter a price greater than 0.");
            }
        }

		  
		 Medicine medicine=new Medicine();
		medicine.setMedicineId(medicineId);
		medicine.setMedicineName(medicineName);
		medicine.setCompanyName(companyName);
		medicine.setManufacturingDate(manufacturingDate);
		medicine.setExpiryDate(expiryDate);
		medicine.setCompanyName(companyName);
		medicine.setQuantity(quantity);
		medicine.setPricePerUnit(pricePerUnit);
	
		 
		 System.out.println(medicineService.addMedicine(medicine));
		 
		 System.out.println("Do you want to add another medicine? (yes/no)");
         scanner.nextLine(); 
         String addAnother = scanner.nextLine();
         addMedicineName = addAnother.equalsIgnoreCase("yes");
     }
		 System.out.println("========================================================================");
	}
		public void viewMedicine()
		{
			Scanner scanner = new Scanner(System.in);

			do {
			    System.out.println("========================================================================================================================");
			    System.out.println("VIEW MEDICINE");
			    System.out.println("========================================================================================================================");
			    System.out.println("ENTER THE MEDICINE NAME:");
			    String medicineName = scanner.nextLine();

			    List<Medicine> medicineList = medicineService.viewMedicine(medicineName);
			    if (medicineList.isEmpty()) {
			        System.out.println("NO MEDICINE IS FOUND WITH THE GIVEN NAME.");
			    } else {
			        System.out.println("MEDICINE FOUND:");
			        Iterator<Medicine> itr = medicineList.iterator();
			        System.out.println("MEDICINE ID:\tMEDICINE NAME:\tCOMPANY NAME:    \tQUANTITY:    \tMANUFACTURING DATE:    \tEXPIRY DATE: \tPRICE PER UNIT:");

			        for (Medicine medicine : medicineList) {
			            System.out.print(medicine.getMedicineId() + "\t\t");
			            System.out.print(medicine.getMedicineName() + "\t\t");
			            System.out.print(medicine.getCompanyName() + "\t\t");
			            System.out.print(medicine.getQuantity() + "\t\t");
			            System.out.print(medicine.getManufacturingDate() + "\t\t");
			            System.out.print(medicine.getExpiryDate() + "\t");
			            System.out.println(medicine.getPricePerUnit()+"\t\t");
			        }

			        System.out.println("===========================================================================================================================================");
			        System.out.println("DO YOU WANT TO VIEW ANOTHER MEDICINE? (Y/N)");
			        String choice = scanner.next();
			        scanner.nextLine(); 

			        
			        if (!choice.equalsIgnoreCase("Y")) {
			            break; 
			        }
			    }
			} while (true);

			

			}		
		@Override
		public void updateMedicine() {
		    String choice = "";
		    do {
		        System.out.println("========================================================================");
		        System.out.println("UPDATE MEDICINE");
		        System.out.println("1. Update Medicine Information");
		        System.out.println("2. Increase Medicine Quantity");
		        System.out.println("3. Exit");
		        System.out.print("Enter your choice: ");
		        choice = scanner.next();
		        scanner.nextLine(); 

		        switch (choice) {
		            case "1":
		                updateMedicineInformation();
		                break;
		            case "2":
		                increaseMedicineQuantity();
		                break;
		            case "3":
		                System.out.println("Exit!");
		                return; 
		            default:
		                System.out.println("Invalid choice. Please try again.");
		                break;
		        }
		    } while (true);
		}

	        	
	        
	        
			private void updateMedicineInformation(){
			System.out.println("========================================================================");
			System.out.println("ENTER THE MEDICINE NAME:");
			String medicineName=scanner.nextLine();
			       
			List<Medicine> medicineList=medicineService.viewMedicine(medicineName);
			if(medicineList.isEmpty())
			{
				System.out.println("NO MEDICINE IS FOUND WITH THE GIVEN NAME.");
			}
			else
			{
				System.out.println("MEDICINE FOUND");
				Iterator<Medicine> itr=medicineList.iterator();
				while(itr.hasNext()) {
					Medicine medicine=itr.next();
					System.out.println("MEDICINE ID:\tMEDICINE NAME:\tCOMPANY NAME:    \tQUANTITY:    \tMANUFACTURING DATE:    \tEXPIRY DATE: \tPRICE PER UNIT:");

			        for (Medicine medicine1 : medicineList) {
			            System.out.print(medicine1.getMedicineId() + "\t\t");
			            System.out.print(medicine1.getMedicineName() + "\t\t");
			            System.out.print(medicine1.getCompanyName() + "\t\t");
			            System.out.print(medicine1.getQuantity() + "\t\t");
			            System.out.print(medicine1.getManufacturingDate() + "\t\t");
			            System.out.print(medicine1.getExpiryDate() + "\t");
			            System.out.println(medicine1.getPricePerUnit()+"\t\t");
			        }

					System.out.println("========================================================================");
									
				for(Medicine medicine1:medicineList)
				{
					System.out.println("UPDATING MEDICINE:"+medicine1.getMedicineName());
					System.out.println("ENTER NEW DETAILS FOR THE MEDICINE: ");
					
					
					System.out.println("Enter the new Medicine Name (leave empty to keep existing): ");
					String newMedicineName=scanner.nextLine().trim();
					if(!newMedicineName.isEmpty()) {
						medicine1.setMedicineName(newMedicineName);
					}
					
					System.out.print("Enter the new Company Name (leave empty to keep existing): ");
			        String newCompanyName = scanner.nextLine().trim();
			        if (!newCompanyName.isEmpty()) {		            
					medicine1.setCompanyName(newCompanyName);}
		 			
			        
			        LocalDate manufacturingDate;
			        LocalDate expiryDate;
			        do {
			            System.out.println("ENTER THE MANUFACTURING DATE (YYYY-MM-DD):");
			            String manufacturingDateStr = scanner.next();
			            manufacturingDate = LocalDate.parse(manufacturingDateStr);

			            System.out.println("ENTER THE EXPIRY DATE (YYYY-MM-DD):");
			            String expiryDateStr = scanner.next();
			            expiryDate = LocalDate.parse(expiryDateStr);

			            if (manufacturingDate.equals(expiryDate)) {
			                System.out.println("Manufacturing date and expiry date should not be the same. Please enter different dates.");
			            }
			            else if(expiryDate.isBefore(manufacturingDate))
			            	System.out.println("Expiry date should not be less than manufacturing date. Please enter valid dates.");
			        } while (manufacturingDate.equals(expiryDate)||expiryDate.isBefore(manufacturingDate));
					
			        Double pricePerUnit;
			        while (true) {
			            System.out.println("ENTER THE PRICE PER UNIT:");
			            pricePerUnit = scanner.nextDouble();
			            if (PharmacyValidation.validatepricePerUnit(pricePerUnit)) {
			                break;
			            } else {
			                System.out.println("Invalid price per unit. Please enter a price greater than 0.");
			            }
			        }
					medicine1.setPricePerUnit(pricePerUnit);
					
					System.out.println(medicineService.updateMedicine(medicine1));
					System.out.println("========================================================================");
			
				}
				}
			}
			}
			
				private void increaseMedicineQuantity() {
				 System.out.println("INCREASE MEDICINE QUANTITY");
				 System.out.println("ENTER THE MEDICINE NAME:");
					String medicineName=scanner.nextLine();
					       
					List<Medicine> medicineList=medicineService.viewMedicine(medicineName);
					if(medicineList.isEmpty())
					{
						System.out.println("NO MEDICINE IS FOUND WITH THE GIVEN NAME.");
					}
					else{
				       
				        System.out.println("MEDICINE FOUND");
						Iterator<Medicine> itr=medicineList.iterator();
						while(itr.hasNext()) {
							Medicine medicine=itr.next();
							System.out.println("Current Quantity:");
					        for (Medicine medicine1 : medicineList) {
					            System.out.println(medicine1.getMedicineName() + " : "  + medicine1.getQuantity());
					        }

					       
					        System.out.print("Enter the quantity to increase: ");
					        int quantityToAdd = scanner.nextInt();
					        scanner.nextLine(); 


								    Integer currentQuantity = medicine.getQuantity();
								    Integer newQuantity = currentQuantity + quantityToAdd;

								   
								    if (newQuantity < 0) {
								        System.out.println("Error: The quantity cannot be decreased below 0.");
								        return;
								    }

								    medicine.setQuantity(newQuantity);

								    String result = medicineService.updateMedicine(medicine);
								    System.out.println(result);
								    System.out.println("========================================================================");
								}
						}
					}
				
		@Override
		public void viewAllMedicine() {
			System.out.println("========================================================================================================================================================");
			System.out.println("VIEW ALL MEDICINE");
			System.out.println("========================================================================================================================================================");
			List<Medicine> medicineList=medicineService.viewAllMedicines();
			if (medicineList.isEmpty()) {
	            System.out.println("No medicines found.");
	        } else {
	            System.out.println("All Medicines:");
			
	            System.out.println("MEDICINE ID:\tMEDICINE NAME:\tCOMPANY NAME:    \tQUANTITY:    \tMANUFACTURING DATE:    \tEXPIRY DATE: \tPRICE PER UNIT:");

		        for (Medicine medicine : medicineList) {
		            System.out.print(medicine.getMedicineId() + "\t\t");
		            System.out.print(medicine.getMedicineName() + "\t\t");
		            System.out.print(medicine.getCompanyName() + "\t\t");
		            System.out.print(medicine.getQuantity() + "\t\t");
		            System.out.print(medicine.getManufacturingDate() + "\t\t");
		            System.out.print(medicine.getExpiryDate() + "\t");
		            System.out.println(medicine.getPricePerUnit()+"\t\t");
			System.out.println("========================================================================================================================================================");
		}
					
		}
		
		}
}



		   
		
		
