package me.brianh.functional;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class NullaryFnTest {

	@Test
	public void simpleTest() {
		NullaryFn<Double> constantSix = new NullaryFn<Double>() {
			private Double n = 6.6;
			@Override
			public Double apply() {
				return n;
			}
		};
		
		assertEquals( 6.6, constantSix.apply(), 0.0000001 );
	}
}
