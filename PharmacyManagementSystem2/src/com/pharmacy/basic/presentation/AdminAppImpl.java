package com.pharmacy.basic.presentation;

import java.util.List;

import java.util.Scanner;


import com.pharmacy.basic.entity.Admin;
import com.pharmacy.basic.service.AdminService;
import com.pharmacy.basic.service.AdminServiceImpl;
import com.pharmacy.basic.validation.PharmacyValidation;


public class AdminAppImpl implements AdminApp {
	
	Scanner scanner= new Scanner(System.in);
	AdminService adminService= new AdminServiceImpl();
	Dashboard dashboard= new Dashboard();
	PharmacyValidation pharmacyValidation= new PharmacyValidation();
	

	@Override
	public void login() {
		
        System.out.print("Enter your username: ");
        String userName = scanner.nextLine();
       
        System.out.print("Enter your email: ");
        String  email = scanner.nextLine();
       
        boolean isAuthenticated = adminService.verifyCredentials(userName, email);

        if (isAuthenticated) {
            System.out.println("LOGIN SUCCESSFUL! Welcome, " + userName + ".");
            dashboard.mainMenu();
        } else {
            System.out.println("LOGIN FAILED. INVALID CREDENTIALS.");
        }
		
	}
		
				public void register() {
				    boolean flagUserName = true;
				    boolean flagEmail = true;
				    String userName = "";
				    String email = "";

				    do {
				        System.out.print("Enter a username for registration: ");
				        userName = scanner.nextLine();

				        flagUserName = pharmacyValidation.validateName(userName);
				        if (!flagUserName) {
				            System.out.println("Invalid name. Please enter a name containing only letters.");
				        }
				    } while (!flagUserName);

				    do {
				        System.out.print("Enter an email for registration: ");
				        email = scanner.nextLine().trim(); 

				        flagEmail = pharmacyValidation.isValidEmail(email);
				        if (!flagEmail) {
				            System.out.println("Invalid email format. Please enter an email in format (Example12@gmail.com)");
				        } else {
				            List<Admin> existingAdmins = adminService.findAdminByEmail(email);
				            if (!existingAdmins.isEmpty()) {
				                System.out.println("This email is already registered.");
				                flagEmail = false;
				            }
				        }
				    } while (!flagEmail);

				    if (flagUserName && flagEmail) {
				        Admin admin = new Admin();
				        admin.setUsername(userName);
				        admin.setEmail(email);

				        if (adminService.register(admin)) {
				            System.out.println("Registration successful! You can now log in.");
				        } else {
				            System.out.println("Registration failed. Please try again.");
				        }
				    }
				}

				

}