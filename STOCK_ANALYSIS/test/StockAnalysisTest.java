import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StockAnalysisTest {
	/*
	 * Tests of minimum without days given
	 */
//	@Test
//	public void testMinimum_1() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			list.add(0.0);
//		}
//		double expected = 0.0;
//		double result = StockAnalysis.minimum(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
//	
//	@Test
//	public void testMinimum_2() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			double temporaryValue = index;
//			list.add(temporaryValue);
//		}
//		double expected = 0.0;
//		double result = StockAnalysis.minimum(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
	/*
	 * Tests of minimum with days
	 */
	@Test
	public void TestMinimumDays_1() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			list.add(0.0);
		}
		double expected = 0.0;
		double result = StockAnalysis.minimum(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void TestMinimumDays_2() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			double temporaryValue = index;
			list.add(temporaryValue);
		}
		double expected = 5.0;
		double result = StockAnalysis.minimum(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of maximum without days
	 */
//	@Test
//	public void testMaximum_1() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			list.add(0.0);
//		}
//		double expected = 0.0;
//		double result = StockAnalysis.maximum(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
//	
//	@Test
//	public void testMaximum_2() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			double temporaryValue = index;
//			list.add(temporaryValue);
//		}
//		double expected = 9.0;
//		double result = StockAnalysis.maximum(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
	/*
	 * Tests of maximum with days
	 */
	@Test
	public void testMaximumDays_1() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			list.add(0.0);
		}
		double expected = 0.0;
		double result = StockAnalysis.maximum(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testMaximumDays_2() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			double temporaryValue = index;
			list.add(temporaryValue);
		}
		double expected = 9.0;
		double result = StockAnalysis.maximum(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	/*
	 * Tests of average without days
	 */
//	@Test
//	public void testAverage_1() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			list.add(0.0);
//		}
//		double expected = 0.0;
//		double result = StockAnalysis.average(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
//	
//	@Test
//	public void testAverage_2() {
//		List<Double> list = new ArrayList<>();
//		for (int index = 0; index < 10; index++) {
//			double temporaryValue = index;
//			list.add(temporaryValue);
//		}
//		double expected = 4.5;
//		double result = StockAnalysis.average(list);
//		double delta = 0.0001;
//		assertEquals(expected, result, delta);
//	}
	/*
	 * Tests of average with days
	 */
	@Test
	public void testAverageDays_1() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			list.add(0.0);
		}
		double expected = 0.0;
		double result = StockAnalysis.average(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}
	
	@Test
	public void testAverageDays_2() {
		List<Double> list = new ArrayList<>();
		int days = 5;
		for (int index = 0; index < 10; index++) {
			double temporaryValue = index;
			list.add(temporaryValue);
		}
		double expected = 7.0;
		double result = StockAnalysis.average(list, days);
		double delta = 0.0001;
		assertEquals(expected, result, delta);
	}

}
