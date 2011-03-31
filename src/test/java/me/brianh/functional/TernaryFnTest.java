package me.brianh.functional;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TernaryFnTest {
	private static final double epsilon = 0.0000001;
	
	public TernaryFn<Double, Double, Double, Double> stupidFn = 
		new TernaryFn<Double, Double, Double, Double>() {
			@Override
			public Double apply(Double a1, Double a2, Double a3) {
				return ( ( 2.0 * a1 ) + (3.0 * a2 ) ) * a3;
			}
	};
	
	@Test
	public void simpleTest() {
		assertEquals( 70.0, stupidFn.apply( 2.0, 2.0, 7.0), epsilon );
		assertEquals( -56.0, stupidFn.apply( 1.0, 2.0, -7.0), epsilon );
		assertEquals( 0.0, stupidFn.apply( -3.0, 2.0, 7.0), epsilon );
	}
	
	@Test
	public void curryTest() {
		BinaryFn<Double, Double, Double> arg1curry = stupidFn.curry1( 1.0 );
		assertEquals( 56.0, arg1curry.apply( 2.0, 7.0), epsilon );
		
		BinaryFn<Double, Double, Double> arg2curry = stupidFn.curry2( 2.0 );
		assertEquals( 56.0, arg2curry.apply( 1.0, 7.0), epsilon );
		
		BinaryFn<Double, Double, Double> arg3curry = stupidFn.curry3( 7.0 );
		assertEquals( 56.0, arg3curry.apply( 1.0, 2.0), epsilon );
		
		UnaryFn<Double, Double> arg1and2curry = stupidFn.curry1and2( 1.0, 2.0 );
		assertEquals( 56.0, arg1and2curry.apply( 7.0), epsilon );
		
		UnaryFn<Double, Double> arg1and3curry = stupidFn.curry1and3( 1.0, 7.0 );
		assertEquals( 56.0, arg1and3curry.apply( 2.0), epsilon );
		
		UnaryFn<Double, Double> arg2and3curry = stupidFn.curry2and3( 2.0, 7.0 );
		assertEquals( 56.0, arg2and3curry.apply( 1.0 ), epsilon );
		
	}
}
