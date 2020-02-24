package com.epam.Lokesh.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import CalculateInterest.CompoundInterest;
import CalculateInterest.SimpleInterest;
import ConstructionCost.HouseConstructionCost;

/**
 * Hello world!
 *
 */
public class App 
{
	static Scanner inputScanner = new Scanner(System.in);
	private static Logger LOGGER = LogManager.getLogger(App.class);
	
	static double initialPrincipalAmount;
	static double annualRateOfInterest;
	static double timeInYears;
	static int compoundingFrequencyPerYear;
	static double compoundInterest;
	static double simpleInterest;
	
    public static void main( String[] args )
    {
    	try 
    	{
	    	do 
	    	{
		        LOGGER.info("	Select Program	\n"
		        		+ "** Interest Calculator **\n"
		        		+ "1. Simple Interest\n"
		        		+ "2. Compound Interest\n\n"
		        		+ "** Estimating House Construction Cost **\n"
		        		+ "3. Select A Construction Plan : \n");
		        
		        int choice = inputScanner.nextInt();
		        switch(choice) 
		        {
		        case 1:
		        	getSimpleInterestValues();
		        	SimpleInterest simpleInterest = new SimpleInterest(initialPrincipalAmount, annualRateOfInterest, timeInYears);
		        	LOGGER.info("Simple Interest = " + simpleInterest.calculateSimpleInterest());
		        	break;
		        case 2:
		        	getCompoundInterestValues();
		        	CompoundInterest compoundInterest = new CompoundInterest(initialPrincipalAmount, annualRateOfInterest, timeInYears , compoundingFrequencyPerYear);
		        	LOGGER.info("Compound Interest = " + compoundInterest.calculateCompoundInterest());
		        	break;
		        case 3:
		        	houseConstructionEstimation();
		        	break;
		        default:
		        	LOGGER.info("Invalid Option Selected");
		        }
		        LOGGER.info("\nWould You Like To Continue (y/n) : ");
	    	} while(inputScanner.next().charAt(0)  == 'y');
	    	
	    	LOGGER.info("Program Terminated Successfully\n");
	        inputScanner.close();
    	}
    	catch(Exception e) {
    		LOGGER.info("\nError Occurred " + e);
    	}
    }
    
    public static void houseConstructionEstimation() 
    {
    	HouseConstructionCost houseObject = new HouseConstructionCost();
    	
    	Set< Map.Entry< String,Integer> > constructionPlans = houseObject.getConstructionPlans().entrySet();
        int index = 1, length, breadth;
        String selectedPlan;
        ArrayList<String> options = new ArrayList<String>();
        LOGGER.info("List Of Construction Plans");
        for (Map.Entry< String,Integer> plan : constructionPlans) 
        {
        	options.add(plan.getKey());
            LOGGER.info(index + ". " + plan.getKey() + " Material Costs " + plan.getValue() + " per square feet");
            index++;
        }
        index = inputScanner.nextInt();
        if(index > 0 && index < 5) 
        {
        	selectedPlan = options.get(index-1);
        	houseObject.selectPlan(selectedPlan);
        }
        else 
        {
        	LOGGER.info("Invalid Option\n");
        	return ;
        }
        LOGGER.info("Enter Area Of The House (Length x breadth) : \n");
        length = inputScanner.nextInt();
        breadth = inputScanner.nextInt();
        houseObject.setDimensionsOfHouse(length, breadth);
        LOGGER.info("Cost for construction = " + houseObject.getEstimatedCost() + " INR");
    }

	public static void getSimpleInterestValues()
    {
    	LOGGER.info("Enter the principal amount , rate of interest , time in years: ");
    	initialPrincipalAmount = inputScanner.nextDouble();
    	annualRateOfInterest = inputScanner.nextDouble();
    	timeInYears = inputScanner.nextDouble();
    }
    
    public static void getCompoundInterestValues()
    {
    	LOGGER.info("enter the principal amount , rate of interest per annum , time in years , frequency of compounding in a year");
    	initialPrincipalAmount = inputScanner.nextDouble();
    	annualRateOfInterest = inputScanner.nextDouble();
    	timeInYears = inputScanner.nextDouble();
    	compoundingFrequencyPerYear = inputScanner.nextInt();
    }
}
