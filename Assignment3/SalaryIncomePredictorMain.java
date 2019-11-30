package assignment3;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalaryIncomePredictorMain {

	private static DecimalFormat format = new DecimalFormat("#,###,##0.00");
	static String[] columnNamesForIncrement = {"Year",
            "Starting Salary",
            "Number of Increments",
            "Increment %",
            "Increment Amount"};
	
	static String[] columnNamesForDecrement = {"Year",
            "Starting Salary",
            "Number of Deduction",
            "Decrement %",
            "Deduction Amount"};
	static String[] columnNamesForPrediction = {"Year",
            "Starting Salary",
            "Increment Amount",
            "Deduction Amount",
            "Salary Growth"};
	
	static int horizontalLength=5;
	
	
	public static void main(String[] args) {

		InputParameter inputParameter = getInputs();

		List<InputParameter> incrementList = new ArrayList<InputParameter>();
		List<InputParameter> decrementList = new ArrayList<InputParameter>();
		double amount = 0, decrementAmount = 0;

		double salaryAmount = inputParameter.getStartingSalary();
		double decrementSalaryAmount = inputParameter.getStartingSalary();

		for (int i = 0; i < inputParameter.getPredictionYears(); i++) {

			amount = calculateIncrement(inputParameter.getIncrementPercentage(),inputParameter.getIncrementFrquency().getValue(), salaryAmount);

			InputParameter result = prepareIncomeResultObject(inputParameter, amount, salaryAmount);

			incrementList.add(result);
			salaryAmount = salaryAmount + amount;

			decrementAmount = calculateIncrement(inputParameter.getDeductionOnIncome(),inputParameter.getDeductionFrequency().getValue(), decrementSalaryAmount);

			InputParameter decrementResult = prepareDecrementResultObject(inputParameter, decrementAmount,decrementSalaryAmount);

			decrementList.add(decrementResult);
			decrementSalaryAmount = decrementSalaryAmount - decrementAmount;

		}

		calculateAndPrintIncrement(incrementList);
		calculateAndPrintDecrement(decrementList);
		predictionReport(incrementList,decrementList,inputParameter.getStartingSalary());

	}
	
	private static void predictionReport(List<InputParameter> incrementList, List<InputParameter> decrementList,double salaryAmount) {
		
		System.out.println("\n C . Prediction Report " );
		createColumn(horizontalLength);
		createHeaderRow(columnNamesForPrediction);
		createColumn(horizontalLength);
		
       int counter=1;
       double salaryGrouth=0;
       double startingSalary=salaryAmount;
		for(int i=0; i<incrementList.size(); i++) {
			
			InputParameter incrementObject=incrementList.get(i);
			InputParameter decrementObject=decrementList.get(i);
			
			
			 salaryGrouth=startingSalary+incrementObject.getAmount()-decrementObject.getAmount();
			 
			String[] str= {String.valueOf(counter),format.format(startingSalary),format.format(incrementObject.getAmount()),format.format(decrementObject.getAmount()),format.format(salaryGrouth)};
			createRow(str);
			createColumn(horizontalLength);
			counter++;
			startingSalary=salaryGrouth;
			
		}
		
	}

	private static void calculateAndPrintIncrement(List<InputParameter> incrementList) {
		System.out.println("\n A . Increment Report " );
		createColumn(horizontalLength);
		createHeaderRow(columnNamesForIncrement);
		createColumn(horizontalLength);
		int i=1;
		for (InputParameter result : incrementList) {
			String[] str= {String.valueOf(i),format.format(result.getStartingSalary()),String.valueOf(result.getIncrementFrquency().getValue()),String.valueOf(result.getIncrementPercentage()),format.format(result.getAmount())};
			createRow(str);
			createColumn(horizontalLength);
			i++;
		}
	}
	
	
private static void calculateAndPrintDecrement(List<InputParameter> decrementList) {
		
	System.out.println(" \n \n B . Deduction Report " );
	createColumn(horizontalLength);
	createHeaderRow(columnNamesForDecrement);
	createColumn(horizontalLength);
	int i=1;
	for (InputParameter result : decrementList) {
		String[] str= {String.valueOf(i),format.format(result.getStartingSalary()),String.valueOf(result.getDeductionFrequency().getValue()),String.valueOf(result.getDeductionOnIncome()),format.format(result.getAmount())};
		createRow(str);
		createColumn(horizontalLength);
		i++;
	}
	
	}

	private static InputParameter prepareIncomeResultObject(InputParameter inputParameter,double amount,double salaryAmount) {
		InputParameter inputParameterResult=new InputParameter();
		
		inputParameterResult.setIncrementFrquency(inputParameter.getIncrementFrquency());
		inputParameterResult.setIncrementPercentage(inputParameter.getIncrementPercentage());
		inputParameterResult.setPredictionYears(inputParameter.getPredictionYears());
		inputParameterResult.setStartingSalary(salaryAmount);
		inputParameterResult.setAmount(amount);
		
		return inputParameterResult;
	}
	
	private static InputParameter prepareDecrementResultObject(InputParameter inputParameter,double amount,double salaryAmount) {
		InputParameter inputParameterResult=new InputParameter();
		
		inputParameterResult.setDeductionFrequency(inputParameter.getDeductionFrequency());
		inputParameterResult.setDeductionOnIncome(inputParameter.getDeductionOnIncome());
		inputParameterResult.setPredictionYears(inputParameter.getPredictionYears());
		inputParameterResult.setStartingSalary(salaryAmount);
		inputParameterResult.setAmount(amount);
		
		return inputParameterResult;
	}
	
	private static InputParameter getInputs() {
		
		InputParameter inputParameter=new InputParameter();
		long startingSalary;
		Scanner inputScanner=new Scanner(System.in);
		System.out.println("Please Enter Starting Salary :");
		do {
		   
		    while (!inputScanner.hasNextLong()) {
		        System.out.println("Please Enter Positive Number!");
		        inputScanner.next(); // this is important!
		    }
		    startingSalary = inputScanner.nextLong();
		} while (startingSalary <= 0);
		
		
		inputParameter.setStartingSalary(startingSalary);
		
		System.out.println("Increment Received in percentage : ");
		int percentage ;
		
		do {
			   
		    while (!inputScanner.hasNextInt()) {
		        System.out.println("Please Enter Positive Number!");
		        inputScanner.next(); // this is important!
		    }
		    percentage = inputScanner.nextInt();
		} while (percentage <= 0);
		
		inputParameter.setIncrementPercentage(percentage);
		
		System.out.println("How Frequently is increment received ?");
		System.out.println("Please Enter 1 for Annualy,");
		System.out.println("Please Enter 2 for Half Yearly, ");
		System.out.println("Please Enter 4 for Quaterly. ");
		
		
		int incrementFrequency;
		
		do {
			   
		    while (!inputScanner.hasNextInt()) {
		        System.out.println("Please Enter Positive Number!");
		        inputScanner.next(); // this is important!
		    }
		    incrementFrequency = inputScanner.nextInt();
		} while (incrementFrequency <= 0);
		
		inputParameter.setIncrementFrquency(Frequency.findByValue(incrementFrequency));
		
		System.out.println("Please enter deduction on income : ");
		long deductionOnIncome= inputScanner.nextLong();
		inputParameter.setDeductionOnIncome(deductionOnIncome);
		
		System.out.println("How Frequently is Deduction ?");
		System.out.println("Please Enter 1 for Annualy,");
		System.out.println("Please Enter 2 for Half Yearly, ");
		System.out.println("Please Enter 4 for Quaterly. ");
		
		int deductionFrquency;
		
		do {
			   
		    while (!inputScanner.hasNextInt()) {
		        System.out.println("Please Enter Positive Number!");
		        inputScanner.next(); // this is important!
		    }
		    deductionFrquency = inputScanner.nextInt();
		} while (deductionFrquency <= 0);
		
		inputParameter.setDeductionFrequency(Frequency.findByValue(deductionFrquency));
		
		System.out.println("Please enter Prediction years (Numeric Only) : ");
		int predictionYears;
		
		do {
			   
		    while (!inputScanner.hasNextInt()) {
		        System.out.println("Please Enter Positive Number!");
		        inputScanner.next(); // this is important!
		    }
		    predictionYears = inputScanner.nextInt();
		} while (predictionYears <= 0);
		
		inputParameter.setPredictionYears(predictionYears);
		
		
		inputScanner.close();
		
		
		return inputParameter;
	}
	
	private static double calculateIncrement(double percentage,int frequency,double salaryAmount){
		
			
		double incrementPercent=(double)percentage/100;
		double salaryIncrement = salaryAmount * Math.pow((1 + incrementPercent),(frequency * 1));
        double increment =  salaryIncrement-salaryAmount;
       // System.out.println("salary increment after 1 year  "+increment);
       // System.out.println("total Salary after increment " +salaryIncrement);
        
        return increment;
        
	}
	
private static void calculateDecrement(long startingSalary,int decrementPercentage,int decrementFrquency){
		
		double decrementPercent=decrementPercentage/100;
		double salaryDecrement = startingSalary * Math.pow((1 + decrementPercent),(decrementFrquency * 1));
        double increment =  startingSalary-salaryDecrement;
         
       // System.out.println("salary increment after 1 year  "+salaryDecrement);
       /// System.out.println("total Salary after increment " +increment);
	}


 private static void createHeaderRow(String[] columnNames) {
	 
	 int columnLength=100/columnNames.length;
	 System.out.println();
	 for (int i = 0; i < columnNames.length; i++) {
			String spaces;
			
			 
				int spaceCount=(columnLength-columnNames[i].length())/2;
				int mod=(columnLength-columnNames[i].length()) % 2;
				
				if(spaceCount==0) {
					spaces=columnNames[i]; 
				}else {
					
					String firstSpaces = String.format("%" + (spaceCount) + "s", "");
					String secondSpace = String.format("%" + (spaceCount+mod) + "s", "");
					
					spaces = firstSpaces+columnNames[i] + secondSpace;
				}
			
			/*
			 * if(columnNames[i].length()<columnLength) { int
			 * spaceCount=columnLength-columnNames[i].length(); spaces =
			 * String.format("%"+(spaceCount)+"s", ""); spaces=columnNames[i]+spaces; }else
			 * { spaces=columnNames[i]; }
			 */
			 
						
			           if(i== columnNames.length-1) {
			        	   System.out.print("|"+spaces+"|");
			           }else {
						System.out.print("|"+spaces);
			           }
					}
 }
 
private static void createRow(String[] columnNames) {
	 
	 int columnLength=100/columnNames.length;
	 System.out.println();
	 for (int i = 0; i < columnNames.length; i++) {
			String spaces;
			
			if(columnNames[i].length() == 1) {
				int spaceCount=columnLength/2;
				
				String firstSpaces = String.format("%" + (spaceCount) + "s", "");
				String secondSpace = String.format("%" + (spaceCount-1) + "s", "");
				
				spaces = firstSpaces+columnNames[i] + secondSpace;
			}else 			
			if (columnNames[i].length() < columnLength) {
				int spaceCount = columnLength - columnNames[i].length();
				spaces = String.format("%" + (spaceCount) + "s", "");
				spaces =spaces+ columnNames[i] ;
			} else {
				spaces = columnNames[i];
			}
			 
						
			           if(i== columnNames.length-1) {
			        	   System.out.print("|"+spaces+"|");
			           }else {
						System.out.print("|"+spaces);
			           }
					}
 }
 

 
 private static void createColumn(int noOfColumns) {
	 System.out.println();
		for (int i = 0; i < noOfColumns; i++) {
			
			if(i== noOfColumns-1) {
			System.out.print("+--------------------+");
			}else {
				System.out.print("+--------------------");
			}
		}
 }
}
