import java.util.ArrayList;
import java.util.List;

/**
 * Put a short phrase describing the program here.
 *
 * @author Pranav Khetarpal
 *
 */
public final class MomentumIndicators {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private MomentumIndicators() {
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
     * Computes the momentum percentage change in the stock over the given time period.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days the momentum percentage will be calculated of
     * @requires
     * [the values of days is less than the number of entries in values, and values 
     * must contain at least one entry] and [values must not contain any entries of 0]
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
     * @requires
     * [the value of  2 times days is less than the number of entries in values]
     * @return exponentialMovingAverage
     * 			  the Exponential Moving Average of a stock for the past number of days given
     */
    public static double exponentialMovingAverage(List<Double> values, int days) {
    	
    	double weightingFactor = 2.0 / (days + 1);
    	double EMA = 1.0;
    	int size = values.size();
    	/*
    	 * loop iterates over all the days we are calculating EMA of
    	 */
    	for (int index = days - 1; index < size; index++) {	
    		if (index >= days) {
    			EMA = values.get(index) * weightingFactor + EMA * (1 - weightingFactor);
    		} else {
    			/*
    			 * the first value of the EMA is simply the average of the days days leading
    			 * up to and including the first day
    			 */
    			double sum = 0.0;
    			for (int count = 0; count < days; count++) {
    				sum += values.get(count);
    			}
    			EMA = sum / days;
    		}
    	}
    	
    	return EMA;
    }
    
    /**
     * Computes and returns the Exponential Moving Average of a stock for the past number of days
     * specified.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @param days
     *            number of days the Exponential Moving Average will be calculated of
     * @param daysPrior
     * 			  number of days prior to the current day that the Exponential Moving Average
     * 			  should be calculated up to
     * @requires
     * [the value of 2 times days is less than the number of entries in values] and [the value of 
     * 2 times days plus priorDays is less than the number of entries in values]
     * @return exponentialMovingAverage
     * 			  the Exponential Moving Average of a stock for the past number of days given
     */
    public static double internalExponentialMovingAverage(List<Double> values, int days, int daysPrior) {
    	
    	double weightingFactor = 2.0 / (days + 1);
    	double EMA = 1.0;
    	int size = values.size();
    	/*
    	 * loop iterates over all the days we are calculating EMA of
    	 */
    	for (int index = days; index > 0; index--) {	
    		if (index < days) {
    			EMA = values.get(size - index - daysPrior) * weightingFactor + EMA * (1 - weightingFactor);
    		} else {
    			/*
    			 * the first value of the EMA is simply the average of the days days leading
    			 * up to and including the first day, taking into account the number of days
    			 * beforehand we are calculating
    			 */
    			double sum = 0.0;
    			for (int count = size - 2 * days - daysPrior + 1; count <= size - days - daysPrior; count++) {
    				sum += values.get(count);
    			}
    			EMA = sum / days;
    		}
    	}
    	
    	return EMA;
    }
    
    /**
     * Computes and returns the MACD values of the last 17 days leading up to the current day.
     * 
     * @param values
     *            List of all the closing prices of the stock
     * @return movingAverageConvergenceDivervengeValues
     * 			  List containing the MACD values of the last 17 days
     */
    public static List<Double> movingAverageConvergenceDivervengeValues(List<Double> values) {
    	
    	/*
    	 * array of 17 days leading up to current day's MACD values
    	 */
    	List<Double> valuesOfMACD = new ArrayList<>();
    	/*
    	 * loop calculates the MACD line for the 17 days leading up to the current day,
    	 * storing them in the list valuesOfMACD
    	 */
    	for (int index = 16; index >= 0; index--) {
    		
    		double twelve_dayEMA = internalExponentialMovingAverage(values, 12, index);
    		double twenty_six_dayEMA = internalExponentialMovingAverage(values, 26, index);
    		
    		valuesOfMACD.add(twelve_dayEMA - twenty_six_dayEMA);
    	}
    	
    	return valuesOfMACD;
    }

    
}