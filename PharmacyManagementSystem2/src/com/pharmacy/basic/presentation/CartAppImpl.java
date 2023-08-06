package com.pharmacy.basic.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.pharmacy.basic.entity.Admin;
import com.pharmacy.basic.entity.Bill;
import com.pharmacy.basic.entity.CartItems;
import com.pharmacy.basic.entity.Medicine;
import com.pharmacy.basic.service.AdminService;
import com.pharmacy.basic.service.AdminServiceImpl;
import com.pharmacy.basic.service.CartService;
import com.pharmacy.basic.service.CartServiceImpl;
import com.pharmacy.basic.service.MedicineService;
import com.pharmacy.basic.service.MedicineServiceImpl;


public class CartAppImpl implements CartApp {
	
	Scanner scanner=new Scanner(System.in);
	CartServiceImpl cartService=new CartServiceImpl();
	MedicineService medicineService=new MedicineServiceImpl();
	AdminService adminService= new AdminServiceImpl();

	@Override
	public void addToCart() {
		
		    System.out.println("========================================================================");
		    System.out.println("ADD TO CART MEDICINE");
		    System.out.println("========================================================================");
		    Scanner scanner = new Scanner(System.in);
		    Integer choice ;
		    List<CartItems> cartItems = new ArrayList<>();

		   
		        System.out.print("Enter the Medicine Name to add to the cart: ");
		        String medicineName = scanner.nextLine();

		        List<Medicine> medicineList = medicineService.viewMedicine(medicineName);
		        if (medicineList.isEmpty()) {
		            System.out.println("No medicine is found with the given name.");
		        } else {
		            boolean allMedicinesAvailable = true;

		            for (Medicine medicine : medicineList) {
		                CartItems cartItem = new CartItems();
		                cartItem.setMedicine(medicine);
		                System.out.print("Enter the quantity: ");
		                Integer quantity = scanner.nextInt();
		                scanner.nextLine();

		                Integer availableQuantity = medicine.getQuantity();
		                if (quantity <= availableQuantity) {
		                    cartItem.setQuantity(quantity);
		                    cartItems.add(cartItem);
		                    System.out.println("Medicine added to the cart successfully.");
		                    String decreaseResult = medicineService.decreaseMedicineQuantity(medicine, quantity);
		                    System.out.println(decreaseResult);
		                } else {
		                    System.out.println("Only " + availableQuantity + " quantities of " + medicineName + " are available.");
		                    System.out.print("Do you want to add " + availableQuantity + " quantities? (yes/no): ");
		                    String answer = scanner.nextLine().toLowerCase();

		                    if (answer.equals("yes")) {
		                        cartItem.setQuantity(availableQuantity);
		                        cartItems.add(cartItem);
		                        System.out.println("Added " + availableQuantity + " quantities of " + medicineName + " to the cart.");
		                        String decreaseResult = medicineService.decreaseMedicineQuantity(medicine, availableQuantity);
		                        System.out.println(decreaseResult);
		                    } else {
		                        System.out.println("The medicine was not added to the cart.");
		                        allMedicinesAvailable = false;
		                        break;
		                    }
		                }
		            }
		            
		            for (CartItems cartItem : cartItems) {
		            	cartItem.setMedicineName(medicineName);
		            	
		         
		                cartService.addToCart(cartItem);
		            }
		        }
		        while (true) {
		        System.out.println("Choose an action:");
		        System.out.println("1. Add medicine to the cart");
		        System.out.println("2. Remove medicine from the cart");
		        System.out.println("3. Increase medicine quantity");
		        System.out.println("4. Decrease medicine quantity");
		        System.out.println("5. View cart");
		        System.out.println("6. Exit");
		        System.out.print("Enter your choice: ");
		         choice = scanner.nextInt();
		        scanner.nextLine(); 

		        switch (choice) {
		            case 1:
		                System.out.print("Enter the medicine name to add to the cart: ");
		                String medicineNameToAdd = scanner.nextLine();

		              CartItems cartItemToAdd = new CartItems();
		              cartItemToAdd.setMedicineName(medicineNameToAdd);
		              cartItems.add(cartItemToAdd);

		              System.out.println("Medicine added to the cart successfully.");
		                
		                break;

		            case 2:
		                System.out.print("Enter the medicine name to remove from the cart: ");
		                String medicineNameToRemove = scanner.nextLine();
		                cartItems.removeIf(cartItem -> cartItem.getMedicine().getMedicineName().equalsIgnoreCase(medicineNameToRemove));
		                System.out.println("Medicines removed from the cart successfully.");
		                break;

		            case 3:
		                System.out.print("Enter the medicine name to increase quantity: ");
		                String medicineNameToIncrease = scanner.nextLine();
		                System.out.print("Enter the quantity to increase: ");
		                 Integer quantityToAdd = scanner.nextInt();
		                scanner.nextLine(); 
		                CartItems cartItemToIncrease = null;
		                for (CartItems cartItem : cartItems) {
		                    if (cartItem.getMedicine().getMedicineName().equalsIgnoreCase(medicineNameToIncrease)) {
		                        cartItemToIncrease = cartItem;
		                        break;
		                    }
		                }

		               
		                if (cartItemToIncrease != null) {
		                    Integer currentQuantity = cartItemToIncrease.getQuantity();
		                    cartItemToIncrease.setQuantity(currentQuantity + quantityToAdd);
		                    System.out.println("Quantity increased successfully.");
		                } else {
		                    System.out.println("Medicine not found in the cart. Please enter a valid medicine name.");
		                }
		          break;
		                

		            case 4:
		            	 System.out.print("Enter the medicine name to decrease quantity: ");
			                String medicineNameToDecrease = scanner.nextLine();
			                System.out.print("Enter the quantity to decrease: ");
			                Integer quantityToDecrease = scanner.nextInt();
			                scanner.nextLine(); 
			                CartItems cartItemToDecrease = null;
			                for (CartItems cartItem : cartItems) {
			                    if (cartItem.getMedicine().getMedicineName().equalsIgnoreCase(medicineNameToDecrease)) {
			                        cartItemToDecrease = cartItem;
			                        break;
			                    }
			                }			               
			                if (cartItemToDecrease != null) {
			                    Integer currentQuantity = cartItemToDecrease.getQuantity();
			                    cartItemToDecrease.setQuantity(currentQuantity - quantityToDecrease);
			                    System.out.println("Quantity decreased successfully.");
			                } else {
			                    System.out.println("Medicine not found in the cart. Please enter a valid medicine name.");
			                }
			          break;
			                
		                

		            case 5:
		                viewCartItems();
		               
		                break;

		            case 6:
		                System.out.println("Exiting cart...");
		                return;

		            default:
		                System.out.println("Invalid choice. Please try again.");
		                break;
		        }

		            System.out.println("========================================================================");
		        }
	}
	   

	@Override
	public void viewCartItems() {

		System.out.println("========================================================================");
		System.out.println("VIEW CART ITEMS ");
		  List<CartItems> cartItems = cartService.viewCart();
		  if(cartItems.isEmpty())
		  {
			  System.out.println("Cart is empty");
		  }
		  else {
		 
		  double totalPrice = 0.0;
		    
		    System.out.println("Medicine\t\t| Quantity\t| Price_Per_Unit");
			
		    for (CartItems cartItem : cartItems) {
		        Medicine medicine = cartItem.getMedicine();
		        int quantity = cartItem.getQuantity();
		        
		        if (medicine != null) {
		            double price = medicine.getPricePerUnit();
		            double itemTotalPrice = price * quantity;

		            System.out.println(medicine.getMedicineName() +
		                    "\t\t| " + quantity +
		                    "\t\t| " + price +"\t\t| ");

		            totalPrice += itemTotalPrice;
		        } 
		    
		    }
		    System.out.println("========================================================================");
		    System.out.println("Total Cart Price: " + totalPrice);
		    System.out.println("========================================================================");
		    }
	}
	

	@Override
	public void removeCartItems() {
		System.out.println("========================================================================");
		System.out.println("REMOVE CART ITEMS");
		 System.out.println("DO YOU WANT TO REMOVE CART ITEMS? (Y/N)");
		    String choice = scanner.next();

		    if (choice.equalsIgnoreCase("Y")) {
		    	List<CartItems> cartItems = cartService.viewCart();

		         for (CartItems cartItem : cartItems) {
				        Medicine medicine = cartItem.getMedicine();
				        Integer quantity = cartItem.getQuantity();
				        
				        if (medicine != null) {
		            
		            System.out.println("Removing item: " + medicine.getMedicineName());
		            
		  
		            cartService.removeCartItems(cartItem); 
		            
		            System.out.println("Medicine removed successfully");
		        }

		        System.out.println("All items removed from the cart.");
		        System.out.println("========================================================================");
		         }		
		    }
	}

	@Override
	public void sellMedicines() {
		System.out.println("========================================================================");
		System.out.println("SELL MEDICINE");
		List<CartItems> cartItems=cartService.viewCart();
		
		if(cartItems.isEmpty())
		{
			System.out.println("CART IS EMPTY");
			return ;
		}
		
		double totalPrice=0.0;
		
		System.out.println("CartItems:");
		 System.out.println("-----------------------------------------------------------");
		 System.out.println("Medicine\t\t| Quantity\t| Price_Per_Unit");
		    System.out.println("--------------------------------------------------------");
		for(CartItems cartItem: cartItems) {
			Medicine medicine=cartItem.getMedicine();
			Integer quantity=cartItem.getQuantity();
			
			if(medicine!=null)
			{
				System.out.println( medicine.getMedicineName()+"\t\t| "
			+" Quantity:"+quantity+ "\t\t| "+" Price:"+medicine.getPricePerUnit());
				totalPrice +=medicine.getPricePerUnit() * quantity;
				
				medicine.setQuantity(medicine.getQuantity()-quantity);
				medicineService.updateMedicine(medicine);		
			}
		}
		System.out.println("Total Price:"+totalPrice);
		
		System.out.println("SELECT PAYMENT METHOD:");
		System.out.println("1.CASH");
		System.out.println("2.CARD");
		Integer paymentMethod=scanner.nextInt();
		
		switch(paymentMethod) {
		case 1:
			processCashPayment(totalPrice);
			break;
		case 2:
			processCardPayment(totalPrice);
			break;
			default:
				System.out.println("INVALID PAYMENT METHOD SELECTED");
				break;
		}
		
		
		billGenerated();
	}
	private void processCashPayment(double totalPrice) {
		while(true) {
	    System.out.println("Enter cash payment amount:");
	    double cashPayment = scanner.nextDouble();

	    if (cashPayment == totalPrice) {
	        
	        System.out.println("PAYMENT SUCCESSFUL. THANK YOU FOR YOUR PURCHASE!");
	      break;
	        } 
	    
	    else if(cashPayment>totalPrice) 
	    {
	    	System.out.println("PAYMENT SUCCESSFUL. THANK YOU FOR YOUR PURCHASE!");
	    }
	    else
	    {
	        System.out.println("INSUFFICIENT CASH PAYMENT. PLEASE PAY THE FULL AMOUNT.");
	    }
	}
	}

	private void processCardPayment(double totalPrice) {
	    System.out.println("PROCESSING CARD PAYMENT......");
	    

	    System.out.println("PAYMENT SUCCESSFUL. THANK YOU FOR YOUR PURCHASE!");
	    System.out.println("=========================================================================================");
	
		
	}

	@Override
	public void billGenerated() {
	    System.out.println("========================================================================================");
	    System.out.println("GENERATE BILL");

	    List<CartItems> cartItems = cartService.viewCart();
	    if (cartItems.isEmpty()) {
	        System.out.println("CART IS EMPTY");
	        return;
	    }
	    
	    Scanner scanner = new Scanner(System.in);
	    System.out.print("Enter the adminId who generated the bill: ");
	    Integer adminId = scanner.nextInt();
	    scanner.nextLine();

	    Admin admin = adminService.findAdminById(adminId);
	    if (admin == null) {
	        System.out.println("Admin not found with the given ID.");
	        return;
	    }


	   
	    Bill bill = new Bill();
	    bill.setDate(new Date());
	   
	   
	    Double totalPrice = 0.0;
	    System.out.println("========================================================================================");
	    System.out.println("BILL:");
	    System.out.println("========================================================================================");
	    System.out.println("DATE:" + bill.getDate());
	    System.out.println("MEDICINE NAME\t\tQUANTITY\t\tPRICE");
	    System.out.println("========================================================================================");

	    for (CartItems cartItem : cartItems) {
	        Medicine medicine = cartItem.getMedicine();
	        Integer quantity = cartItem.getQuantity();

	        if (medicine != null) {
	            System.out.println(medicine.getMedicineName() + "\t\t\t" + quantity + "\t\t\t" + medicine.getPricePerUnit());
	            totalPrice += medicine.getPricePerUnit() * quantity;
	        }
	    }

	    System.out.println("========================================================================================");
	    System.out.println("TOTAL AMOUNT:" + totalPrice);

	    bill.setAdmin(admin);
	    bill.setTotalPrice(totalPrice);
	    cartService.billGenerate(bill);
	    System.out.println("========================================================================================");
	    System.out.println("BILL ID:" + bill.getBillId());
	    
	    
	    for(CartItems cartItem:cartItems)
			cartService.removeCartItems(cartItem);
	    System.out.println("========================================================================================");
	}


	@Override
	public void findBillById() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
		    System.out.println("========================================================================================================================");
		    System.out.println("VIEW BILL");
		    System.out.println("========================================================================================================================");
		    System.out.println("ENTER THE BILL ID:");
		    Integer billId = scanner.nextInt();
		    scanner.nextLine(); 
		    System.out.println("==========================================================================================================================");
		    System.out.println("BILL ID:\t" + "\tDATE:\t" + "\tADMIN ID:\t" + "\tTotalPrice:\t");
		    Bill bill = cartService.findByBillId(billId);
		    if (bill != null) {
		        System.out.println(bill.getBillId() + "\t\t" + bill.getDate() + "\t" + bill.getAdmin().getAdminId() + "\t\t" + bill.getTotalPrice());
		     System.out.println("==========================================================================================================================");
  
		       
		    }
		       
		    else {
		        System.out.println("Bill not found with the given ID.");
		    }
		    

		    System.out.println("DO YOU WANT TO VIEW ANOTHER BILL? (Y/N)");
		    String choice = scanner.nextLine();
		    
		    if (!choice.equalsIgnoreCase("Y")) {
		        break;
		    } 
		    }
		}
	
}



