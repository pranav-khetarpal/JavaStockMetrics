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
     * Computes the lowest closing price within the past days days and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the minimum value
     * @requires
     * [the values of days is less than the number of entries in values, and values 
     * must contain at least one entry]
     * @return minimum
     * 			  the lowest closing price of the stock within the past days specified
     */
    public static double minimum(List<Double> values, int days) {
    	
    	int size = values.size();    	
    	double min = values.get(size - days);
    	
    	for (int index = size - days; index < size; index++) {
    		if (values.get(index) < min)
    			min = values.get(index);
    	}
    	
    	return min;
    }
    
    /**
     * Computes the highest closing price within the past days days and returns it.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days back user wishes to analyze for the maximum value
     * @requires
     * [the values of days is less than the number of entries in values, and values 
     * must contain at least one entry]
     * @return maximum
     * 			  the highest closing price of the stock within the past days specified
     */
    public static double maximum(List<Double> values, int days) {
    	
    	int size = values.size();    	
    	double max = values.get(size - days);
    	
    	for (int index = size - days; index < size; index++) {
    		if (values.get(index) > max)
    			max = values.get(index);
    	}
    	
    	return max;
    }
    
    /**
     * Computes the average closing price within the past days days and returns it.
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
    public static double average(List<Double> values, int days) {
    	
    	int size = values.size();    	
    	double sum = 0.0;
    	
    	for (int index = size - days; index < size; index++) {
    		sum += values.get(index);
    	}
    	
    	return sum / days;
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
    	System.out.println("Momentum [mom]");
    	System.out.println("	Momentum Percentage [mom]");
    	System.out.println("	Momentum Percentage [momp]");
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
    		
    		System.out.print("How many days back are we analyzing: ");
			int days = Integer.parseInt(keyboard.nextLine());
    		System.out.print("What kind of data would you like to see: ");
    		userText = keyboard.nextLine();
	    	int size = closingValues.size();

    		if (userText.equals("p")) {
    			
    			System.out.print("Closing Price: " + closingValues.get(size - 1 - days));
    			
    		} else if (userText.equals("b")) {
    			
    			System.out.print("What basic information would you like to see: ");
    			String dataChoice = keyboard.nextLine();
    			
    			if (dataChoice.equals("min")) {
					System.out.println("Minimum Value: " + minimum(closingValues, days));
    			} else if (dataChoice.equals("max")) {
					System.out.println("Maximum Value: " + maximum(closingValues, days));
    			} else if (dataChoice.equals("avg")) {
					System.out.println("Average Value: " + average(closingValues, days));
    			}
    			
    		} else if (userText.equals("mom")) {
    			
    			System.out.print("What momentum information would you like to see: ");
    			String dataChoice = keyboard.nextLine();
    			
    			if (dataChoice.equals("mom")) {
    				double momentum = closingValues.get(size - 1) - closingValues.get(size - 1 - days);
    				System.out.print("Momentum: " + momentum);
    			}
    			if (dataChoice.equals("momp")) {
        			System.out.println("Momentum Percentage: " + MomentumIndicators.momentumPercentage(closingValues, days));
    			}
    			
    		} else if (userText.equals("ema")) {
    			
    			System.out.println("Exponential Moving Average: " + MomentumIndicators.exponentialMovingAverage(closingValues, days));
    			
    		} else if (userText.equals("macd")) {
    			
    			/*
    			 * We need the last 17 days MACD values to analyze the 9 day EMA of the MACD
    			 */
    			List<Double> seventeen_dayMACDValues = MomentumIndicators.movingAverageConvergenceDivervengeValues(closingValues);
    			
    			double lastDayMACD = seventeen_dayMACDValues.get(16);
    			System.out.println("Moving Average Convergence Divergence: " + lastDayMACD);
    			
    			/*
    			 * We now need compute the 9 day EMA of the MACD values and analyze the value
    			 */
    			double nine_dayEMAOfMACD = MomentumIndicators.exponentialMovingAverage(seventeen_dayMACDValues, 9);
    			System.out.print(nine_dayEMAOfMACD);
    			if (lastDayMACD > nine_dayEMAOfMACD) {
    				System.out.println("Using the 26/12/9 day approach you should BUY");
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
