package me.brianh.functional;

import static me.brianh.functional.Fns.into;
import static me.brianh.functional.Fns.map;
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
	
	private List<Integer> nums = Arrays.asList( 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 );

	@Test
	public void testInto() throws Exception {
		// Arraylist
		List<Integer> negs = new ArrayList<Integer>();
		negs.addAll( Arrays.asList( -4, -3, -2, -1 ) );
		
		into(negs, nums);

		assertEquals( 14, negs.size() );
		assertEquals( new Integer( -4 ), negs.get( 0 ) );
		assertEquals( new Integer( 0 ), negs.get( 4 ) );
		assertEquals( new Integer( 9 ), negs.get( 13 ) );
		
		// Set
		Set<Integer> ns = new HashSet<Integer>();
		ns.addAll( Arrays.asList( 4, -2, 8, -1 ) );
		
		into( ns, nums );
		
		assertEquals( 12, ns.size() );
		assertTrue( ns.contains( -2 ) );
		assertTrue( ns.contains( 4 ) );
		assertTrue( ns.contains( 8 ) );
		assertTrue( ns.contains( -1 ) );
		assertTrue( ns.contains( 0 ) );
		assertTrue( ns.contains( -2 ) );
		assertTrue( ns.contains( 6 ) );
	}
	
	@Test
	public void testMap() {
		Iterator<Integer> resultIter = map( sqr, nums );

		for ( int i = 0; i < nums.size() && resultIter.hasNext() ; i++ ) {
			assertEquals( new Integer( i * i ), resultIter.next() );
			// make sure we didn't alter the original collection
			assertEquals( new Integer( i ), nums.get( i ) );
		}
	}
	
	@Test
	public void testMapWithResultCollection() {
		// now passing a collection for catching the results
		Iterator<Integer> results = map( sqr, nums );

		for ( int i = 0; i < nums.size() && results.hasNext(); i++ ) {
			assertEquals( new Integer( i * i ), results.next() );
			// make sure we didn't alter the original collection
			assertEquals( new Integer( i ), nums.get( i ) );
		}
	}
}
