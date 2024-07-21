import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.lang.Object;

/**
 * Put a short phrase describing the program here.
 *
 * @author Pranav Khetarpal
 *
 */
public final class StockAnalysisOnline {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private StockAnalysisOnline() {
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
    	
    	Scanner keyboard = new Scanner(System.in);
    	
    	System.out.print("Enter the ticker of the stock you would like to analyze: ");
    	String ticker = keyboard.nextLine();
    	/*
    	 * This is the url object of the web page
    	 */
    	System.out.print("Enter the URL of the Web Page containing stock information: ");
    	String address = "https://finance.yahoo.com/quote/" + ticker + "/history/";
    	/*
    	 * Creating the URL of the web address and then using a scanner object to read the source code.
    	 */
        URL url = new URL(address);
        Scanner pageScanner = new Scanner(url.openStream());
        /*
         * This segment of code serves to get retrieve the useful segment of the source code, and stop
         * reading after the information is gotten.
         */
        StringBuilder stringBuilder = new StringBuilder();
        
        String line = pageScanner.nextLine();
        while (!line.equals("window._adPerfData = []; ")) {
        	stringBuilder.append(line);
            line = pageScanner.nextLine();
        }
        
        pageScanner.close();
        
        /*
         * Deleting the part of source code that is not useful.
         */
        stringBuilder.delete(0, stringBuilder.indexOf("HistoricalDataTable"));
        
        int index = 0;
        int length = stringBuilder.length();
        String identifier = "class=\"Py(10px) Pstart(10px)\"><span>";
        int identifierLength = identifier.length();
        
        StringBuilder numbers = new StringBuilder();
        /*
         * Loop serves to only retrieve the numbers contained in the source code that have relation to
         * the stock.
         */
        while (stringBuilder.toString().contains(identifier)) {
        	index = stringBuilder.indexOf(identifier) + identifierLength;
        	/*
        	 * Retrieving all the numbers until a character is reached in the string.
        	 */
        	char nextCharacter = stringBuilder.charAt(index);
        	while (nextCharacter != '<') {
        		numbers.append(nextCharacter);
        		index++;
        		nextCharacter = stringBuilder.charAt(index);
        	}     
        	
        	stringBuilder.delete(0, index);
        }
        /*
         * List serves to extract only the closing prices of the stock from the number data given.
         */
        List<Double> closingValues = new ArrayList<>();
        while (numbers.toString().contains(".")) {
        	numbers.delete(0, numbers.indexOf(".") + 3);
        	numbers.delete(0, numbers.indexOf(".") + 3);
        	numbers.delete(0, numbers.indexOf(".") + 3);
        	String closingPrice = numbers.substring(0, numbers.indexOf(".") + 3);
        	closingValues.add(Double.parseDouble(closingPrice));
        	numbers.delete(0, numbers.indexOf(".") + 3);
        	numbers.delete(0, numbers.indexOf(".") + 3);
        }
        
        
        
        System.out.print(closingValues);
        
        keyboard.close();
    }

}
