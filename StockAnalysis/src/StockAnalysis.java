import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Put a short phrase describing the program here.
 *
 * @author Pranav Khetarpal
 *
 */
public final class StockAnalysis {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private StockAnalysis() {
    }
    
    /**
     * Computes the lowest closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @requires
     * [values must contain at least one entry]
     * @return minimum
     * 			  the lowest closing price of the stock
     */
    public static double minimum(List<Double> values) {
    	
    	double min = values.get(0);
    	
    	for (int index = 1; index < values.size(); index++) {
    		if (values.get(index) < min)
    			min = values.get(index);
    	}
    	
    	return min;
    }
    
    /**
     * Computes the lowest closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the minimum value
     * @requires
     * [the values of days is less than or equal to the number of entries in values, and values 
     * must contain at least one entry]
     * @return minimum
     * 			  the lowest closing price of the stock within the past days specified
     */
    public static double minimum(List<Double> allValues, int days) {
    	
    	int size = allValues.size();
    	List<Double> values = allValues.subList(size - days, size);
    	
    	double min = values.get(0);
    	
    	for (int index = 1; index < values.size(); index++) {
    		if (values.get(index) < min)
    			min = values.get(index);
    	}
    	
    	return min;
    }
    
    /**
     * Computes the highest closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @requires
     * [values must contain at least one entry]
     * @return maximum
     * 			  the highest closing price of the stock
     */
    public static double maximum(List<Double> values) {
    	
    	double max = values.get(0);
    	
    	for (int index = 1; index < values.size(); index++) {
    		if (values.get(index) > max)
    			max = values.get(index);
    	}
    	
    	return max;
    }
    
    /**
     * Computes the highest closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the maximum value
     * @requires
     * [the values of days is less than or equal to the number of entries in values, and values 
     * must contain at least one entry]
     * @return maximum
     * 			  the highest closing price of the stock within the past days specified
     */
    public static double maximum(List<Double> allValues, int days) {
    	
    	int size = allValues.size();
    	List<Double> values = allValues.subList(size - days, size);
    	
    	double max = values.get(0);
    	
    	for (int index = 1; index < values.size(); index++) {
    		if (values.get(index) > max)
    			max = values.get(index);
    	}
    	
    	return max;
    }
    
    /**
     * Computes the average closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the average value
     * @return average
     * 			  the average closing price of the stock
     */
    public static double average(List<Double> values) {
    	
    	double sum = 0;
    	int size = values.size();
    	
    	for (int index = 0; index < size; index++) {
    		sum += values.get(index);
    	}
    	
    	return sum / size;
    }
    
    /**
     * Computes the average closing price and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the average value
     * @requires
     * [the values of days is less than the number of entries in values]
     * @return average
     * 			  the average closing price of the stock within the past days specified
     */
    public static double average(List<Double> allValues, int days) {
    	
    	int size = allValues.size();
    	List<Double> values = allValues.subList(size - days, size);
    	
    	double sum = 0;
    	
    	for (int index = 0; index < values.size(); index++) {
    		sum += values.get(index);
    	}
    	
    	return sum / values.size();
    }
    
    /**
     * Computes the momentum percentage change in the stock over the given time period.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days the momentum percentage will be calculated of
     * @return momentumPercentage
     * 			  the momentum percentage of the stocks change in price from the beginning of the time
     * 			  period to the latest closing price 
     */
    public static double momentumPercentage(List<Double> values, int days) {
    	
    	int size = values.size();
    	
    	double priceBefore = values.get(size  - 1 - days);
    	double priceAfter = values.get(size - 1);
    	
    	return (priceAfter / priceBefore) * 100;
    }
    
    /**
     * Computes and returns the Exponential Moving Average of a stock for the past number of days
     * specified.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days the Exponential Moving Average will be calculated of
     * @param originalDays
     *            number of days the original call for the Exponential Moving Average was to be 
     *            calculated of    
     * @requires
     * [the value of days is less than the number of entries in values]
     * @return exponentialMovingAverage
     * 			  the Exponential Moving Average of a stock for the past number of days given
     */
    public static double exponentialMovingAverage(List<Double> values, int days, int originalDays) {
    	
    	double EMA;
    	/*
    	 * The base case is when the exponential moving average is to be calculated over the past 0
    	 * days. When this happens, we simple take the value of the simple moving average days days ago
    	 * and of the past days from that point.
    	 */
    	if (days == 0) {
    		int size = values.size();
    		List<Double> SMAList = values.subList(0, size - days);
    		EMA = average(SMAList, originalDays);
    	} else {
        	double weightingFactor = 2.0 / (days + 1);
        	/*
    		 * The formula to calculate the exponential moving average requires recursive calls to find
    		 * the last available exponential moving average.
    		 */
        	double previousEMA = exponentialMovingAverage(values, days - 1, originalDays);
        	
        	EMA = weightingFactor * (values.get(0) - previousEMA) + previousEMA;
    		
    	}
    	
    	return EMA;
    	
    }
    
    /**
     * Calculates and returns the Exponential Moving Average of a stock for the past number of days
     * given a previous Exponential Moving Average.
     * @param values
     *            List of all the closing prices of the stock
     * @param previousEMA
     *            the Exponential Moving Average of the previous number of days
     * @param days
     *            number of days the Exponential Moving Average will be calculated of
     * @param previousDays
     *            the number of days the previous Exponential Moving Average calculated
     * @return exponentialMovingAverageGivenPrevious
     *            the Exponential Moving Average of a stock for the past number of days specified
     */
    public static double exponentialMovingAverageGivenPrevious(List<Double> values, double previousEMA, 
    		int days, int previousDays) {
    	
    	double EMA;
    	/*
    	 * The base case is when the exponential moving average is to be calculated over the number of
    	 * days already calculated by the previous exponential moving average.
    	 */
    	if ((days - previousDays)== 0) {
    		EMA = previousEMA;
    	} else {
        	double weightingFactor = 2 / (days + 1);
        	/*
    		 * The formula to calculate the exponential moving average requires recursive calls to find
    		 * the last available exponential moving average.
    		 */        	
        	EMA = weightingFactor * (values.get(0) - previousEMA) + previousEMA;
    		
    	}
    	
    	return EMA;
    	
    }
    
    /**
     * Prints to output the information regarding the user's choices as to what information they may
     * like to see.
     */
    public static void displayChoices() {
    	System.out.println();
    	System.out.println("DATA OPTIONS");
    	System.out.println("Price [p]");
    	System.out.println("Basic [b]");
    	System.out.println("	Minimum [min]");
    	System.out.println("	Maximum [max]");
    	System.out.println("	Average [avg]");
    	System.out.println("Momentum [m]");
    	System.out.println("	Momentum Percentage [mp]");
    	System.out.println("Exponential Moving Average [ema]");
    	System.out.println("Moving Average Convirgence Divergence [macd]");
    	System.out.println();
    }

    
    /**
     * Main method.
     *
     * @param args
     * 			  the command line arguments
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        
    	/*
    	 * Asking user for the name of file containing data
    	 */
    	System.out.print("Enter the name of the file, preceded by data/, containing the"
    			+ " historical adjusted closing prices of the stock in question: ");
    	Scanner keyboard = new Scanner(System.in);
    	String fileName = keyboard.nextLine();
    	/*
    	 * Scanner object for the file given by user
    	 */
    	File file = new File(fileName); // change to fileName later
    	Scanner sheetReader = new Scanner(file);
    	/*
    	 * ArrayList of all the values of the closing prices
    	 */
    	List<Double> closingValues = new ArrayList<>();
    	/* 
    	 * loop reads all the prices of SPY's adjusted close prices since May 18, 2022 
    	 */
    	while (sheetReader.hasNext()) {
    		closingValues.add(sheetReader.nextDouble());
    	}
    	    	
    	displayChoices();
    	
    	/*
    	 * loop is designed to run as long as the user does not enter an empty string
    	 */
    	String userText = "poop";
    	while (!userText.isEmpty()) {
    		
    		
    		System.out.print("What kind of data would you like to see: ");
    		userText = keyboard.nextLine();
    		
    		if (userText.equals("p")) {
    			System.out.print("How many days back would you like to know the closing price of: ");
    			int days = Integer.parseInt(keyboard.nextLine());
    	    	int size = closingValues.size();
    			System.out.print("The closing price of the stock " + days + " ago is " + 
    					closingValues.get(size - 1 - days));
    		} else if (userText.equals("b")) {
    			
    			System.out.print("What basic information would you like to see: ");
    			String dataChoice = keyboard.nextLine();
    			
    			System.out.print("Is there a specific number of days back you would like to see this"
    					+ " information [yes or no]: ");
    			String daysChoice = keyboard.nextLine();
    			
    			if (daysChoice.equals("yes")) {
    				System.out.print("How many days back would you like to see: ");
    				int days = Integer.parseInt(keyboard.nextLine());
    				
    				if (dataChoice.equals("min")) {
    					System.out.println("Minimum Value over the last " + 
    							days + " days: " + minimum(closingValues, days));
        			} else if (dataChoice.equals("max")) {
    					System.out.println("Maximum Value over the last " +
    							days + " days: " + maximum(closingValues, days));
        			} else if (dataChoice.equals("avg")) {
    					System.out.println("Average Value over the last " +
    							days + " days: " + average(closingValues, days));
        			}
    			} else if (daysChoice.equals("no")) {
    				if (dataChoice.equals("min")) {
    					System.out.println("Minimum Value: " + minimum(closingValues));
        			} else if (dataChoice.equals("max")) {
    					System.out.println("Maximum Value: " + maximum(closingValues));
        			} else if (dataChoice.equals("avg")) {
    					System.out.println("Average Value: " + average(closingValues));
        			}
    			}
        		
    		} else if (userText.equals("m")) {
    			System.out.print("What momentum information would you like to see: ");
    			String dataChoice = keyboard.nextLine();
    			
    			if (dataChoice.equals("mp")) {
    				System.out.print("How many days back would you like to see: ");
    				int days = Integer.parseInt(keyboard.nextLine());
        			
        			System.out.println("Momentum Percentage over the last " + days + " days: " +
        			momentumPercentage(closingValues, days));
    			}
    		} else if (userText.equals("ema")) {
    			
    			System.out.print("How many days back would you like to know the exponential moving "
    					+ "average of: ");
    			int days = Integer.parseInt(keyboard.nextLine());
    			System.out.println("Exponential Moving Average over the past " + days + 
    					" days: " + exponentialMovingAverage(closingValues, days, days));
    			
    		} else if (userText.equals("macd")) {
    			
    			double nine_DayEMA = exponentialMovingAverage(closingValues, 9, 9);
    			double twelve_DayEMA = exponentialMovingAverageGivenPrevious(closingValues, nine_DayEMA, 12, 9);
    			double twenty_six_DayEMA = exponentialMovingAverageGivenPrevious(closingValues, twelve_DayEMA, 26, 12);

    			double MACD = twenty_six_DayEMA - twelve_DayEMA;
    			
    			System.out.println("Moving Average Convergence Divergence result: " + MACD);
    			
    			if (MACD > nine_DayEMA) {
    				System.out.println("Using the 26/12/9 day approach, you should BUY");
    			} else {
    				System.out.println("Using the 26/12/9 day approach, you should SELL");

    			}
    			
    		} else if (!userText.isEmpty()){
    			displayChoices();
    		}
    		
    		System.out.println();
    	}
    	
        /*
         * Close input and output streams
         */
    	keyboard.close();
    	sheetReader.close();
    }

}
