package me.brianh.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BinaryFnTest {
	private static final double epsilon = 0.0000001;
	
	private BinaryFn<Double, Double, Double> mult = new BinaryFn<Double, Double, Double>() {
		@Override
		public Double apply( Double a1, Double a2 ) {
			return a1 * a2;
		}
	};
	
	@Test
	public void simpleTest() {
		assertEquals( 42.0, mult.apply( 6.0, 7.0 ), epsilon );
		assertEquals( -42.0, mult.apply( 6.0, -7.0 ), epsilon );
		assertEquals( -42.0, mult.apply( -6.0, 7.0 ), epsilon );
		assertEquals( 42.0, mult.apply( -6.0, -7.0 ), epsilon );
	}
	
	@Test
	public void curryTest() {
		UnaryFn<Double, Double> times6 = mult.curry1( 6.0 );
		assertEquals( 36.0, times6.apply( 6.0 ), epsilon );
		assertEquals( -42.0, times6.apply( -7.0 ), epsilon );
		
		UnaryFn<Double, Double> times7 = mult.curry2( 7.0 );
		assertEquals( 42.0, times7.apply( 6.0 ), epsilon );
		assertEquals( -49.0, times7.apply( -7.0 ), epsilon );
	}
}
