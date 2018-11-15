package com.cg.bank.ui;

import java.util.Scanner;

import com.cg.bank.dto.Customer;
import com.cg.bank.exception.BankAccException;
import com.cg.bank.service.BankAccServiceImpl;

public class MainClient {
	public static void main(String args[]) throws BankAccException{
		
		BankAccServiceImpl service = new BankAccServiceImpl();
		
		Scanner sc = new Scanner(System.in);
		
		String name,mobileNo;
		float age;
		double amount;
		int ch = 0;
		do{System.out.println("****Welcome To Bank Wallet****\n");
			System.out.println("1.Customer Creation\n2.Deposit Amount in Your Account\n3.Withdraw Amount From Your Account\n4.Fund transfer\n5.Check Balance of Your Account\n6.Print Transaction Statement\n7.Exit");
			System.out.println("Enter your choice : ");
			ch = sc.nextInt();
			Customer customer;
			switch(ch){
				case 1 :
						customer = new Customer();						
					
						do{
							System.out.println("Enter Customer Name : ");
							name = sc.next();
							if(!service.validateName(name))
								System.err.println("Invalid Name!\nName should start with capital letter \nIt should not contain numbers\nLength should be minimum 4 and maximum 10\n");
							else
								break;
						}while(true);
						do{
							System.out.println("Enter mobile no. : ");
							mobileNo = sc.next();
							if(!service.validateMoileNo(mobileNo))
								System.err.println("Number should be of length 10 !!!\n And must contain only digits");
							else if(service.validateAccount(mobileNo))
								System.err.println("Account already exist with this number!\nPlease try again!!");
							else
								break;								
						}while(true);
						do{
							System.out.println("Enter Age : ");
							age = sc.nextFloat();
							if(service.validateAge(age))
								break;
						}while(true);
						do{
							System.out.println("Enter Initial Amount : ");
							amount = sc.nextDouble();
							if(!service.validateAmount(amount))
								System.err.println("Amount shoulb be minimum Rs.100 !!!\nShould contain only digits!!!");
							else
								break;							
						}while(true);
						
						
						
						customer.setName(name);
						customer.setMobileNo(mobileNo);
						customer.setAge(age);
						customer.setInitialBalance(amount);
						
						service.createAccount(customer);	
						System.out.println(customer.getName()+" with Mobile Number "+customer.getMobileNo()+ " Account Created Successfully\n");
					
					break;
					
				case 2 :
					do{
						System.out.println("Enter Your Mobile Number : ");
						mobileNo = sc.next();
						
						System.out.println("Enter the amount you want to deposit");
						amount = sc.nextDouble();
						if(service.validateMoileNo(mobileNo)&& service.validateAmount(amount)){
							if(service.validateAccount(mobileNo))
								break;
						}
					}while(true);
					System.out.println("Amount Deposited");
					service.deposit(mobileNo, amount);
					System.out.println("Your Updated Balance is Rs."+service.checkBalance(mobileNo));
					
					
					
				
				break;
					
				case 3 :
						do{
							System.out.println("Enter Your Mobile Number : ");
							mobileNo = sc.next();
							if(!service.validateMoileNo(mobileNo))
								System.err.println("Invalid Mobile Number!\nNumber should be of length 10 \n And must contain only digits");
							else if(!service.validateAccount(mobileNo))
								System.err.println("Account already exist with this number!\nPlease try again!!");
							else
								break;
						}while(true);
						do{
							System.out.println("Enter The Amount You Want To Withdraw : ");
							amount = sc.nextDouble();
							if(service.validateMoileNo(mobileNo) && service.validateAmount(amount)){
								if(service.validateAccount(mobileNo))
									break;
							}
						}while(true);
						
						service.withdraw(mobileNo, amount);
						System.out.println("Your Updated Balance is Rs."+service.checkBalance(mobileNo));
					break;
				
				case 4 :
						String mobileNoReciever;
						do{
							System.out.println("Enter Sender's Mobile Number : ");
							mobileNo = sc.next();
							
							System.out.println("Enter The Amount You Want To Transfer : ");
							amount = sc.nextDouble();
							
							System.out.println("Enter Receiver's Mobile Number : ");
							mobileNoReciever = sc.next();
							if(mobileNo.equals(mobileNoReciever)){								
								System.out.println("Both numbers are same!\nPlease enter unique numbers...");
								continue;
							}
							if(service.validateMoileNo(mobileNo) && service.validateMoileNo(mobileNoReciever) && service.validateAmount(amount)){
								if(service.validateAccount(mobileNoReciever) && service.validateAccount(mobileNo))
									break;
							}
						}while(true);
						service.fundTransfer(mobileNo, mobileNoReciever, amount);
						System.out.println("Your Updated Balance is Rs."+service.checkBalance(mobileNo));		
					break;
					
				case 5 :
						do{
							System.out.println("Enter The Moible Number To Check Balance");
							mobileNo = sc.next();
							if(service.validateMoileNo(mobileNo)){
								if(service.validateAccount(mobileNo))																	
									break;
								else
									System.err.println("Mobile number not found.\nPlease enter correct mobile number.");
							}
							else{
								System.err.println("Invalid mobile number!\nEnter valid mobile nummber\nAccount should be present with the number you are entering..\nNumber should be of length 10 \n And must contain only digits");
							}
						}while(true);
						
						System.out.println("Current Amount is Rs.\n"+service.checkBalance(mobileNo));
						
					break;
					
				case 6 :
					do{
						System.out.println("Enter The Moible Number To Get Transaction Statement:");
						mobileNo = sc.next();
						if(service.validateMoileNo(mobileNo)){
							if(service.validateAccount(mobileNo))																	
								break;
							else
								System.err.println("Mobile number not found.\nPlease enter correct mobile number.");
						}
						else{
							System.err.println("Invalid mobile number!\nEnter valid mobile nummber\nAccount should be present with the number you are entering..\nNumber should be of length 10 \n And must contain only digits");
						}
					}while(true);
					
					service.getTransactionList(mobileNo);
					break;
					
				case 7 :
						System.out.println("ThankYou for Banking With us");
					break;
				default : System.out.println("Invalid input!\n Please Choose (1-7)");
			}
			
		}while(ch != 7);
		
	}
}
