package me.brianh.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UnaryFnTest {
	private static final double epsilon = 0.0000001;
	
	private UnaryFn<Double, Double> floor = new UnaryFn<Double, Double>() {
		@Override
		public Double apply( Double a1 ) {
			return Math.floor( a1 );
		}
	};
	
	@Test
	public void simpleTest() {
		assertEquals( 6.0, floor.apply( 6.1 ), epsilon );
		assertEquals( 6.0, floor.apply( 6.99999 ), epsilon );
		assertEquals( -7.0, floor.apply( -6.00001), epsilon );
		assertEquals( -7.0, floor.apply( -6.9999 ), epsilon );
	}
	
	@Test
	public void curryTest() {
		NullaryFn<Double> foreverFloor = floor.curry( 6.4 );
		assertEquals( 6.0, foreverFloor.apply(), epsilon );
	}
}
