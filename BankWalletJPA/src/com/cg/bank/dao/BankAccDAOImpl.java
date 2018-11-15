package com.cg.bank.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.cg.bank.dto.Customer;
import com.cg.bank.dto.Transaction;
import com.cg.bank.exception.BankAccException;

public class BankAccDAOImpl implements BankAccDAO{

	EntityManager entityManager;
	
	public BankAccDAOImpl(){
		
		EntityManagerFactory em = Persistence.createEntityManagerFactory("JPA-PU");
		entityManager = em.createEntityManager();
		
	}
	
	
	//---------------------------- Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	createAccount(Customer customer)
		 - Input Parameters	:	Customer customer
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	Adding Customer Account to the database
		 ********************************************************************************************************/
	
	@Override
	public void createAccount(Customer customer) {
		
		entityManager.getTransaction().begin();
		entityManager.persist(customer);
		entityManager.getTransaction().commit();
		
	}
	
	//----------------------------Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	deposit(String mobileNo, double amount)
		 - Input Parameters	:	String mobileNo, double amount
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	Depositing amount to the  Customer Account
		 ********************************************************************************************************/

	@Override
	public void deposit(String mobileNo, double amount) {
		
		
		entityManager.getTransaction().begin();
		
		Customer cust =  entityManager.find(Customer.class, mobileNo);
		double amt = cust.getInitialBalance();
		amt += amount;
		System.out.println(amt);
		cust.setInitialBalance(amt);
		
		entityManager.getTransaction().commit();
		
		Transaction t = new Transaction();
		t.setBalance(amt);
		t.setDebit(null);
		t.setCredit(String.valueOf(amount));
		t.setMobie_no(mobileNo);
		t.setName(cust.getName());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		t.setTrans(String.valueOf(dateFormat.format(date)));
		
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
		
		
	}

	//-------------------------- Wallet Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	withdraw(String mobileNo, double withdrawAmount)
		 - Input Parameters	:	String mobileNo, double withdrawAmount
		 - Return Type		:	void
		 - Author			:	CAPGEMINI
		 - Creation Date	:	10/30/2018
		 - Description		:	Withdrawing amount from the account
		 ********************************************************************************************************/
	
	
	@Override
	public void withdraw(String mobileNo, double withdrawAmount) {
		
		
		entityManager.getTransaction().begin();
		boolean flag = false;
		Customer cust = entityManager.find(Customer.class, mobileNo);
		double amount = cust.getInitialBalance();
		if(!(amount-withdrawAmount > 500)){
			System.err.println("Insufficient Balance in you account.\nNo amount deducted from your account.\nPlease try again");
			cust.setInitialBalance(amount);
		}
		else{
			amount -= withdrawAmount;
			cust.setInitialBalance(amount);
			System.out.println("Amount Rs."+withdrawAmount+" withdrawed successfully");
			flag = true;			
		}
		entityManager.getTransaction().commit();
		
		if(flag){
			Transaction t = new Transaction();
			t.setBalance(amount);
			t.setDebit(String.valueOf(withdrawAmount));
			t.setCredit(null);
			t.setMobie_no(mobileNo);
			t.setName(cust.getName());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			t.setTrans(String.valueOf(dateFormat.format(date)));
			
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		}
		
	}
	
	//-------------------------- Wallet Application --------------------------
			/*******************************************************************************************************
			 - Function Name	:	checkBalance(String mobileNo)
			 - Input Parameters	:	String mobileNo
			 - Return Type		:	void
			 - Author			:	CAPGEMINI
			 - Creation Date	:	10/30/2018
			 - Description		:	Checking balance for an account
			 ********************************************************************************************************/
		

	@Override
	public double checkBalance(String mobileNo) {
		
		entityManager.getTransaction().begin();
		
		Customer cust = entityManager.find(Customer.class, mobileNo);
		double amount = cust.getInitialBalance();
		entityManager.getTransaction().commit();
		
		return amount;
		
	}
	
	//-------------------------- Wallet Application --------------------------
	/*******************************************************************************************************
	 - Function Name	:	fundTransfer(String sender, String reciever, double amount)
	 - Input Parameters	:	String sender, String reciever, double amount
	 - Return Type		:	void
	 - Author			:	CAPGEMINI
	 - Creation Date	:	10/30/2018
	 - Description		:	Transfer of amount from one account to another
	 ********************************************************************************************************/

	

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		
		
		entityManager.getTransaction().begin();
		boolean flag = false;
		Customer custSender = entityManager.find(Customer.class, sender);
		Customer custreciever = entityManager.find(Customer.class, reciever);
		
		double senderAmount = custSender.getInitialBalance();
		double recieverAmount = custreciever.getInitialBalance();
		
		if((senderAmount - amount) > 500){
			senderAmount -= amount;
			recieverAmount += amount;
			custreciever.setInitialBalance(recieverAmount);
			custSender.setInitialBalance(senderAmount);
			flag = true;
			System.out.println("Fund of Rs."+amount+" transferred successfully! from "+custSender.getName()+" to "+custreciever.getName());
		}else{
			
			System.err.println("Invalid amount! As transfer amount is greater than your account balance.");
		}
		
		entityManager.getTransaction().commit();
		
		if(flag){
			Transaction tSender = new Transaction();
			tSender.setBalance(senderAmount);
			tSender.setDebit(String.valueOf(amount));
			tSender.setCredit(null);
			tSender.setMobie_no(sender);
			tSender.setName(custSender.getName());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			tSender.setTrans(String.valueOf(dateFormat.format(date)));
			
			entityManager.getTransaction().begin();
			entityManager.persist(tSender);
			entityManager.getTransaction().commit();
			
			
			
			Transaction tReciever = new Transaction();
			tReciever.setBalance(recieverAmount);
			tReciever.setDebit(null);
			tReciever.setCredit(String.valueOf(amount));
			tReciever.setMobie_no(sender);
			tReciever.setName(custreciever.getName());
			DateFormat dateFormatReciever = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date dateReciever = new Date();
			tReciever.setTrans(String.valueOf(dateFormatReciever.format(dateReciever)));
			
			entityManager.getTransaction().begin();
			entityManager.persist(tReciever);
			entityManager.getTransaction().commit();
			
			
		}
			
	}

	//-------------------------- Wallet Application --------------------------
	/*******************************************************************************************************
	 - Function Name	:	validateAccount(String mobileNo) throws BankAccountException
	 - Input Parameters	:	String mobileNo
	 - Return Type		:	void
	 - Author			:	CAPGEMINI
	 - Creation Date	:	10/30/2018
	 - Description		:	Validating account i.e whether account with given mobile number exist or not.
	 ********************************************************************************************************/

	
	@Override
	public boolean validateAccount(String mobileNo) throws BankAccException {
		
		Customer customer = entityManager.find(Customer.class, mobileNo);
		if(customer == null)
			return false;
		return true;
	}


	@Override
	public void getTransactionList(String mobileNo) {
		
		String sql = "select tr from Transaction tr where mobile_no ="+mobileNo;
		TypedQuery<Transaction> query = entityManager.createQuery(sql, Transaction.class);
		List<Transaction> list = query.getResultList();
		System.out.println("Mobile No          Name           Credit        Debit            Transaction Id         Balance");
		for(Transaction t : list){
			System.out.println(t.getMobile_no()+"          "+t.getName()+"          "+t.getCredit()+"           "+t.getDebit()+"        "+t.getTrans()+"          "+t.getBalance());
		}
	}


	
}
