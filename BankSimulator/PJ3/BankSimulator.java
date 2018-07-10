package PJ3;

import java.util.*;
import java.io.*;

// You may add new functions or data in this class 
// You may modify any functions or data members here
// You must use Customer, Teller and ServiceArea classes
// to implement Bank simulator

class BankSimulator {

  // input parameters
  private int numTellers, customerQLimit;
  private int simulationTime, dataSource;
  private int chancesOfArrival, maxTransactionTime;

  // statistical data
  private int numGoaway, numServed, totalWaitingTime;

  // internal data
  private int customerIDCounter;   // customer ID counter
  private ServiceArea servicearea; // service area object
  private Scanner dataFile;	   // get customer data from file
  private Random dataRandom;	   // get customer data using random function

  // most recent customer arrival info, see getCustomerData()
  private boolean anyNewArrival;  
  private int transactionTime;

  // initialize data fields
  private BankSimulator()
  {
	numGoaway = 0;
	numServed = 0;
	totalWaitingTime = 0;
	customerIDCounter = 0;
  }

  private void setupParameters()
  {
	// read input parameters
	// setup dataFile or dataRandom
	// add statements
    System.out.println("\n\t****    Get Simulation Parameters    ****\n"); 
    Scanner userInput = new Scanner(System.in);
   
    do 
       {
          System.out.print("Enter simulation time (positive integer)      :");
          simulationTime = userInput.nextInt();
       }
     while(simulationTime > 10000 || simulationTime < 0);
    
    do 
       {
         System.out.print("Enter the number of tellers                    :");
         numTellers = userInput.nextInt();
       }
    while(numTellers > 10 || numTellers < 0);
    
    do 
       {
          System.out.print("Enter chances (0% < & <= 100%) of new customer:");
          chancesOfArrival = userInput.nextInt();
       }
    while(chancesOfArrival > 100 || chancesOfArrival <=0);
    
    do 
       {
          System.out.print("Enter maximum transaction time of customers   :");
          maxTransactionTime = userInput.nextInt();
       }
    while(maxTransactionTime > 500 || maxTransactionTime < 0);
    
    do
       {
          System.out.print("Enter customer queue limit                    :");
          customerQLimit = userInput.nextInt();
       }
    while(customerQLimit > 50 || customerQLimit < 0);
    
    do
       {
          System.out.print("Enter 0/1 to get data from Random/file        :");
          dataSource = userInput.nextInt();
       }
    while(dataSource > 1 || dataSource < 0);
    
    if (dataSource == 1)
       {
          System.out.print("Enter file name                               :");
          try
             {
                dataFile = new Scanner( new File(userInput.next()));
             }
          catch (FileNotFoundException exception)
             {
                System.out.println("The file was not found.");
                dataSource = 0;
             }
       }
    else
       {
          System.out.println("Data is Randomizing.");
       }
    
    userInput.close();
    dataRandom = new Random();
    
  }

  // Refer to step 1 in doSimulation()
  private void getCustomerData()
  {
	// get next customer data : from file or random number generator
	// set anyNewArrival and transactionTime
        // see Readme file for more info
        // add statements
     
     if (dataSource == 1)
        {
           int firstData = 0;
           int secondData = 0;
           
           if (dataFile.hasNextInt())
              {
                 firstData = dataFile.nextInt();
                 secondData = dataFile.nextInt();
              }
           anyNewArrival = (((firstData %100)+1) <= chancesOfArrival);
           transactionTime = (secondData % maxTransactionTime)+1;
           
        }
     else
        {
           anyNewArrival = ((dataRandom.nextInt(100)+1) <= chancesOfArrival);
           transactionTime = dataRandom.nextInt(maxTransactionTime)+1;
        }
     
  }

  private void doSimulation()
  {
	// add statements

     System.out.println("\n\t****   Start Simulation    ****\n");
	// Initialize ServiceArea
     servicearea = new ServiceArea(numTellers, customerQLimit);
     

	// Time driver simulation loop
     for (int currentTime = 0; currentTime < simulationTime; currentTime++) 
        {
            System.out.println("------------------------------------------------------");
  	    System.out.println("Time    : " + (currentTime+1));
  	    System.out.println("Queue   : " + servicearea.numWaitingCustomers() + "/" + customerQLimit);
  	      
  	    totalWaitingTime = (servicearea.numWaitingCustomers() > 0) ? totalWaitingTime+1 : 0;
  	      
  	      
    		// Step 1: any new customer enters the bank?
    		getCustomerData();

    		if (anyNewArrival) {

      		    // Step 1.1: setup customer data
    		   customerIDCounter++;
    		   System.out.println("\tCustomer #" + customerIDCounter + " arrives with transaction time " + transactionTime + "units.");
    		   
    		   
      		    // Step 1.2: check customer waiting queue too long?
                    //           if it is too long, update numGoaway
                    //           else enter customer queue
    		   if (servicearea.isCustomerQTooLong())
    		      {
    		         System.out.println("\tCustomer queue full. Customer #" + customerIDCounter + " leaves..");
    		         numGoaway++;
    		      }
    		   else
    		      {
    		         System.out.println("\tCustomer #" + customerIDCounter + " waits in the customer queue.");
    		         servicearea.insertCustomerQ(new Customer(customerIDCounter, transactionTime, currentTime));
    		      }
    		   
    		} 
    		else 
    		{
      		    System.out.println("\tNo new customer!");
    		}

                // Step 2: free busy tellers that are done at currentTime, add to free cashierQ
    		
    		while (servicearea.numBusyTellers() > 0 && servicearea.getFrontBusyTellerQ().getEndBusyTime() == currentTime) {
            Teller teller = servicearea.removeBusyTellerQ();
            teller.busyToFree();
            servicearea.insertFreeTellerQ(teller);
            
            System.out.println("\tCustomer #" + teller.getCustomer().getCustomerID() + " is done.");
            System.out.println("\tTeller #" + teller.getTellerID() + " is free.");
        }
    		
    		
    		
                // Step 3: get free tellers to serve waiting customers at currentTime
    		 while (servicearea.numFreeTellers() > 0 && servicearea.numWaitingCustomers() > 0) {
             Customer customer = servicearea.removeCustomerQ();
             Teller teller = servicearea.removeFreeTellerQ();
             teller.freeToBusy(customer, currentTime);
             servicearea.insertBusyTellerQ(teller);
             numServed++;
             
             System.out.println("\tCustomer #" + customer.getCustomerID() + " gets teller #"
                     + teller.getTellerID() + " for " + customer.getTransactionTime() + " unit(s).");
         }
         
    		
  	} // end simulation loop

  	// clean-up - close scanner
  
  }

  private void printStatistics()
  {
	// add statements into this method!
	// print out simulation results
	// see the given example in project statement
        // you need to display all free and busy gas pumps


        // need to free up all customers in queue to get extra waiting time.
        // need to free up all tellers in free/busy queues to get extra free & busy time.
     
     System.out.println("\n===============================================================\n");
     System.out.println("End of simulation report \n\n");
     System.out.println("\t\t# total arrival customers : " + customerIDCounter);
     System.out.println("\t\t# customers gone away     : " + numGoaway);
     System.out.println("\t\t# customers served        : " + numServed);
     
     
     System.out.println("\n\n\t*** Current Tellers info. ***\n\n");
     servicearea.printStatistics();
     
     System.out.println("\n\n\t\tTotal waiting time   : " + totalWaitingTime);
     double averageWaitingTime = ( servicearea.emptyCustomerQ() )
             ? 0.0 : (double)totalWaitingTime / servicearea.numWaitingCustomers();
     System.out.printf("\t\tAverage waiting time : %.2f\n", averageWaitingTime);
     
     System.out.println("\n\n\t*** Busy Tellers info. ***\n\n");
     if (!servicearea.emptyBusyTellerQ()) {
         while (servicearea.numBusyTellers() > 0) {
             Teller teller = servicearea.removeBusyTellerQ();
             
             teller.printStatistics();
         }
     } else {
         System.out.println("\t\tNo busy tellers.\n");
     }
     
     System.out.println("\n\t*** Free Tellers Info. ***\n\n");
     if (!servicearea.emptyFreeTellerQ()) {
         while (servicearea.numFreeTellers() > 0) {
             Teller teller = servicearea.removeFreeTellerQ();
            
             teller.printStatistics();
         }
     } else {
         System.out.println("\t\tNo free tellers.\n");
     }
     System.out.println();
  }

  // *** main method to run simulation ****

  public static void main(String[] args) {
   	BankSimulator runBankSimulator=new BankSimulator();
   	runBankSimulator.setupParameters();
   	runBankSimulator.doSimulation();
   	runBankSimulator.printStatistics();
  }

}
