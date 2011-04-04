package me.brianh.functional;

import java.util.Iterator;

/**
 * Natural numbers from zero to {@link Integer} MAX_VALUE.
 * 
 * @author brian
 */
public class NaturalInts implements Iterable<Integer> {

	private int num;

	@Override
	public Iterator<Integer> iterator() {
		return new Iterator<Integer>() {

			@Override
			public boolean hasNext() {
				return num < Integer.MAX_VALUE;
			}

			@Override
			public Integer next() {
				return num++;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Naturals does not support removal!" );
			}
		};
	}
}
