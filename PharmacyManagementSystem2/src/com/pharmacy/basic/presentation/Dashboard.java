package com.pharmacy.basic.presentation;


import java.util.Scanner;

import com.pharmacy.basic.dao.AdminDao;
import com.pharmacy.basic.dao.AdminDaoImpl;
import com.pharmacy.basic.entity.Admin;



public class Dashboard {
   
    public static void main(String[] args) {
    	 AdminAppImpl adminApp = new AdminAppImpl();
    	
        Scanner scanner = new Scanner(System.in);

        System.out.println("************************************************************");
        System.out.println("           WELCOME TO PHARMACY MANAGEMENT SYSTEM            ");
        System.out.println("************************************************************");
       
        while (true) {
            System.out.println("1. LOGIN");
            System.out.println("2. REGISTER");
            System.out.println("3. EXIT");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                   
                   adminApp.login();
                    break;

                case 2:
                   
                   adminApp.register();
                   break;

                case 3:
                   
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN.");
                    break;
            }
        }
    }
        
  void mainMenu() {
        MedicineAppImpl medicineApp = new MedicineAppImpl();
        CartAppImpl cartApp=new CartAppImpl();
        Scanner scanner = new Scanner(System.in);{
      

        while (true) {
        	System.out.println("========================================================================");
            System.out.println("1. ADD MEDICINE");
            System.out.println("2. VIEW MEDICINE");
            System.out.println("3. UPDATE MEDICINE");
            System.out.println("4. VIEW ALL MEDICINES");
            System.out.println("5. ADD MEDICINES TO CART");
            System.out.println("6. SELL MEDICINES");
            System.out.println("7. VIEW BILL");
            System.out.println("9. LOGOUT");
            System.out.println("========================================================================");
            System.out.print("Enter your choice: ");
            int ch = scanner.nextInt();
            scanner.nextLine(); 

            switch (ch) {
                case 1:
                    medicineApp.addMedicine();
                    break;
                case 2:
                    medicineApp.viewMedicine();
                    break;
                case 3:
                    medicineApp.updateMedicine();
                    break;
                case 4:
                    medicineApp.viewAllMedicine();
                    break;
                case 5:
                    cartApp.addToCart();
                    break;
               
                case 6:
                    cartApp.sellMedicines();
                    break;
                case 7:
                    cartApp.findBillById();
                    break;
               
               
                case 8:
                    System.out.println("LOGGING OUT. GOODBYE!");
                    System.exit(0);
                    return;
                default:
                    System.out.println("INVALID CHOICE. PLEASE TRY AGAIN.");
                    break;
            }
        }
    }
    }
}
