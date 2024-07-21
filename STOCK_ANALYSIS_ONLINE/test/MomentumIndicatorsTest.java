import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class MomentumIndicatorsTest {
	/*
	 * Tests of average without days
	 */
	@Test
	public void testAverage_1() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			list.add(0.0);
		}
		double expected = 0.0;
		double result = MomentumIndicators.average(list);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testAverage_2() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			double temporaryValue = index;
			list.add(temporaryValue);
		}
		double expected = 4.5;
		double result = MomentumIndicators.average(list);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of momentumPercentage
	 */
	@Test
	public void testMomentumPercentage_1() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			list.add(2.0);
		}
		int days = 5;
		double expected = 100.0;
		double result = MomentumIndicators.momentumPercentage(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testMomentumPercentage_2() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			double temporary = index;
			list.add(temporary);
		}
		int days = 5;
		double expected = 225.0;
		double result = MomentumIndicators.momentumPercentage(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of exponentialMovingAverage
	 */
	@Test
	public void testExponentialMovingAverage_1() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			list.add(2.0);
		}
		int days = 4;
		double expected = 2.0;
		double result = MomentumIndicators.exponentialMovingAverage(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testExponentialMovingAverage_2() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 10; index++) {
			double temporary = index;
			list.add(temporary);
		}
		int days = 4;
		double expected = 7.5;
		double result = MomentumIndicators.exponentialMovingAverage(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testExponentialMovingAverage_3() {
		List<Double> list = new ArrayList<>(List.of(22.27,22.19,22.08,22.17,22.18,22.13,22.23,22.43,22.24,22.29,22.15,22.39,22.38,22.61,23.36,24.05,23.75,23.83,23.95,23.63,23.82,23.87,23.65,23.19,23.10,23.33,22.68,23.10,22.49,22.17));
		int days = 10;
		double expected = 22.92;
		double result = MomentumIndicators.exponentialMovingAverage(list, days);
		double delta = 0.01;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testExponentialMovingAverage_4() {
		List<Double> list = new ArrayList<>(List.of(55.0, 58.0, 61.0, 59.0, 58.0, 63.0, 65.0, 68.0, 66.0, 63.0, 61.0, 62.0, 60.0, 64.0, 68.0, 74.0, 76.0, 78.0, 80.0));
		int days = 10;
		double expected = 70.9621;
		double result = MomentumIndicators.exponentialMovingAverage(list, days);
		double delta = 0.01;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of internalExponentialMovingAverage
	 */
	@Test
	public void testInternalExponentialMovingAverage_1() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 20; index++) {
			list.add(2.0);
		}
		int days = 4;
		int daysPrior = 8;
		double expected = 2.0;
		double result = MomentumIndicators.internalExponentialMovingAverage(list, days, daysPrior);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testInternalExponentialMovingAverage_2() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 20; index++) {
			double temporary = index;
			list.add(temporary);
		}
		int days = 4;
		int daysPrior = 8;
		double expected = 9.5;
		double result = MomentumIndicators.internalExponentialMovingAverage(list, days, daysPrior);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of movingAverageConvergenceDivervengeValues
	 */
	@Test
	public void testMACD_1() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 100; index++) {
			list.add(2.0);
		}
		List<Double> expectedList = new ArrayList<>();
		for (int index = 0; index < 17; index++) {
			expectedList.add(0.0);
		}
		List<Double> resultList = MomentumIndicators.movingAverageConvergenceDivervengeValues(list);
		assertEquals(expectedList, resultList);
	}
	
	@Test
	public void testMACD_2() {
		List<Double> list = new ArrayList<>();
		for (int index = 0; index < 100; index++) {
			double temporary = index;
			list.add(temporary);
		}
		List<Double> expectedList = new ArrayList<>();
		for (int index = 0; index < 17; index++) {
			double MACDValue = MomentumIndicators.internalExponentialMovingAverage(list, 26, index) - 
					MomentumIndicators.internalExponentialMovingAverage(list, 12, index);
			expectedList.add(MACDValue);
		}
		List<Double> resultList = MomentumIndicators.movingAverageConvergenceDivervengeValues(list);
		assertEquals(expectedList, resultList);
	}
}
