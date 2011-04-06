package me.brianh.functional;

import static me.brianh.functional.Fns.filter;
import static me.brianh.functional.Fns.first;
import static me.brianh.functional.Fns.into;
import static me.brianh.functional.Fns.listComprehension;
import static me.brianh.functional.Fns.map;
import static me.brianh.functional.Fns.repeatedly;
import static me.brianh.functional.Fns.rest;
import static me.brianh.functional.Fns.take;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FnsTest {
	private UnaryFn<Integer, Integer> sqr = new UnaryFn<Integer, Integer>() {
		@Override
		public Integer apply( Integer i ) {
			return i * i;
		}
	};
	
	@Test
	public void testFilter() {
		List<Integer> evens = new ArrayList<Integer>();
		into( evens, filter( MathFns.isEven, take( 100, new NaturalInts() ) ) );
		assertEquals( 50, evens.size() );
		assertEquals( 0, evens.get( 0 ).intValue() );
		assertEquals( 2, evens.get( 1 ).intValue() );
		assertEquals( 20, evens.get( 10 ).intValue() );
		assertEquals( 98, evens.get( 49 ).intValue() );
	}
	
	@Test
	public void testFirst() {
		assertEquals( 0, first( new NaturalInts() ).intValue() );
	}

	@Test
	public void testInto() throws Exception {
		// Arraylist
		List<Integer> negs = new ArrayList<Integer>();
		negs.addAll( Arrays.asList( -4, -3, -2, -1 ) );
		
		into( negs, take( 10, new NaturalInts() ) );

		System.out.println( );
		assertEquals( 14, negs.size() );
		assertEquals( -4, negs.get( 0 ).intValue() );
		assertEquals( 0, negs.get( 4 ).intValue() );
		assertEquals( 9, negs.get( 13 ).intValue() );
		
		// Set
		Set<Integer> ns = new HashSet<Integer>();
		ns.addAll( Arrays.asList( 4, -2, 8, -1 ) );
		
		into( ns, take( 10, new NaturalInts() ) );
		
		assertEquals( 12, ns.size() );
		assertTrue( ns.contains( -2 ) );
		assertTrue( ns.contains( 4 ) );
		assertTrue( ns.contains( 8 ) );
		assertTrue( ns.contains( -1 ) );
		assertTrue( ns.contains( 0 ) );
		assertTrue( ns.contains( -2 ) );
		assertTrue( ns.contains( 6 ) );
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListComprehension() {
		List<String> numStrings = new ArrayList<String>();
		
		into( numStrings, listComprehension( MathFns.isEven, Fns.toString, take( 20, new NaturalInts() ) ) );

		assertEquals( "0", numStrings.get( 0 ) );
		assertEquals( "2", numStrings.get( 1 ) );
		assertEquals( "12", numStrings.get( 6 ) );
	}
	
	@Test
	public void testMap() {
		Iterator<Integer> resultIter = map( sqr, take( 10, new NaturalInts() ) );

		for ( int i = 0; i < 10 && resultIter.hasNext() ; i++ ) {
			assertEquals( i * i, resultIter.next().intValue() );
		}
	}
	
	@Test
	public void testMapWithResultCollection() {
		// now passing a collection for catching the results
		Iterator<Integer> results = map( sqr, take( 10, new NaturalInts() ) );

		for ( int i = 0; i < 10 && results.hasNext(); i++ ) {
			assertEquals( i * i, results.next().intValue() );
		}
	}
	
	@Test
	public void testRepeatedly() {
		List<Integer> nums = new ArrayList<Integer>();
		NullaryFn<Integer> fives = new NullaryFn<Integer>() {
			@Override
			public Integer apply() {
				return 5;
			}
		};
		
		into( nums, take( 5, repeatedly( fives ) ) );
		
		assertEquals( 5, nums.size() );
		assertEquals( 5, nums.get( 0 ).intValue() );
		assertEquals( 5, nums.get( 4 ).intValue() );
		
		nums.clear();
		
		into( nums, repeatedly( 10, fives ) );

		assertEquals( 10, nums.size() );
		assertEquals( 5, nums.get( 0 ).intValue() );
		assertEquals( 5, nums.get( 9 ).intValue() );
	}
	
	@Test
	public void testRest() {
		List<Integer> nums = new ArrayList<Integer>();
		
		into( nums, rest( take( 6, new NaturalInts() ) ) );
		
		assertEquals( 5, nums.size() );
		assertEquals( 1, nums.get( 0 ).intValue() );
		assertEquals( 5, nums.get( 4 ).intValue() );
		
		nums.clear();
		
		into( nums, rest( take( 1, new NaturalInts() ) ) );
		
		assertEquals( 0, nums.size() );
	}
}
