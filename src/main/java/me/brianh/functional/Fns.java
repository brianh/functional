package me.brianh.functional;

import java.util.Collection;
import java.util.Iterator;

/**
 * Really just a namespace...
 * 
 * @author brian
 */
@SuppressWarnings("rawtypes")
public class Fns {	
	public static final <T> Iterator<T> filter( final UnaryFn<T, Boolean> pred, 
			final Iterator<? extends T> tings ) {
		
		return new Iterator<T>() {
			private T ting;
			
			@Override
			public boolean hasNext() {
				ting = advanceUntil( pred, tings );
				return ting != null;
			}

			@Override
			public T next() {
				return ting;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Remove from a filter is not allowed!" );
			}
		};
	}
	public static final <T> Iterator<T> filter( final UnaryFn<T, Boolean> pred, 
			final Iterable<? extends T> ting ) {
		return filter( pred, ting.iterator() );
	}
	
	/**
	 * Adds things to a collection.
	 * 
	 * @param targetColl - where the tings go
	 * @param tings - the tings that go
	 * 
	 * @return collection being added to for chaining calls
	 */
	public static final <T> Collection<T> into( Collection<T> targetColl, 
			Iterator<? extends T> tings ) {
		
		while ( tings.hasNext() ) {
			targetColl.add( tings.next() );
		};
		return targetColl;
	}

	/**
	 * Adds things to a collection.
	 * 
	 * @param targetColl - where the tings go
	 * @param tings - the tings that go
	 * 
	 * @return collection being added to for chaining calls
	 */
	public static final <T> Collection<T> into( Collection<T> targetColl,
			Iterable<? extends T> tings ) {
		return into( targetColl, tings.iterator() );
	}
	
	public static final <S,T> Iterator<S> listComprehension( final UnaryFn<T, Boolean> pred, 
			final UnaryFn<T, S> fn, final Iterator<? extends T> tings ) {
		
		return new Iterator<S>() {
			private T ting;
			
			@Override
			public boolean hasNext() {
				ting = advanceUntil( pred, tings );
				return ting != null;
			}

			@Override
			public S next() {
				return fn.apply( ting );
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Remove from a listComprehension is not allowed!" );
			}
		};
	}

	public static final <S,T> Iterator<S> listComprehension( final UnaryFn<T, Boolean> pred, 
			final UnaryFn<T, S> fn, final Iterable<? extends T> tings ) {
		return listComprehension( pred, fn, tings.iterator() );
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.
	 * <p>
	 * Does NOT alter the iterator. 
	 *
	 * @param fn - function to be applied to each thing
	 * @param tings - that which needs operating
	 * 
	 * @return iterator containing the results
	 */
	public static final <T,R> Iterator<R> map( final UnaryFn<T, R> fn, 
			final Iterator<? extends T> tings ) {

		return new Iterator<R>() {
			@Override
			public boolean hasNext() {
				return tings.hasNext();
			}

			@Override
			public R next() {
				return fn.apply( tings.next() );
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Remove from a map iterator is not allowed!" );
			}
		};
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.
	 * <p>
	 * Does NOT alter the iterator. 
	 *
	 * @param fn - function to be applied to each ting
	 * @param tings - that which needs operating
	 * 
	 * @return iterator containing the results
	 */
	public static final <T,R> Iterator<R> map( UnaryFn<T, R> fn, Iterable<? extends T> tings ) {
		return map( fn, tings.iterator() );
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.  Iteration 
	 * stops when any argument iterator is exhausted.
	 * <p>
	 * Does NOT alter any iterator. 
	 *
	 * @param fn - function to be applied to each thing
	 * @param fings - first argument iterator
	 * @param sings - second argument iterator
	 * 
	 * @return iterator containing the results
	 */
	public static final <F,S,R> Iterator<R> map( final BinaryFn<F, S, R> fn, 
			final Iterator<? extends F> fings, final Iterator<? extends S> sings ) {

		return new Iterator<R>() {
			@Override
			public boolean hasNext() {
				return fings.hasNext() && sings.hasNext();
			}

			@Override
			public R next() {
				return fn.apply( fings.next(), sings.next() );
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Remove from a map iterator is not allowed!" );
			}
		};
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.  Iteration 
	 * stops when any argument iterator is exhausted.
	 * <p>
	 * Does NOT alter the iterator. 
	 *
	 * @param fn - function to be applied to each thing
	 * @param things - that which needs operating
	 * @param sings - second arg to function
	 * 
	 * @return iterator containing the results
	 */
	public static final <T,S,R> Iterator<R> map( BinaryFn<T, S, R> fn, Iterable<? extends T> tings,
			Iterable<? extends S> sings ) {
		return map( fn, tings.iterator(), sings.iterator() );
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.  Iteration 
	 * stops when any argument iterator is exhausted.
	 * <p>
	 * Does NOT alter any iterator. 
	 *
	 * @param fn - function to be applied to each thing
	 * @param fings - first argument iterator
	 * @param sings - second argument iterator
	 * @param tings - third argument iterator
	 * 
	 * @return iterator containing the results
	 */
	public static final <F,S,T,R> Iterator<R> map( final TernaryFn<F, S, T, R> fn, 
			final Iterator<? extends F> fings, final Iterator<? extends S> sings, 
			final Iterator<? extends T> tings ) {

		return new Iterator<R>() {
			@Override
			public boolean hasNext() {
				return fings.hasNext() && sings.hasNext() && tings.hasNext();
			}

			@Override
			public R next() {
				return fn.apply( fings.next(), sings.next(), tings.next() );
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Remove from a map iterator is not allowed!" );
			}
		};
	}
	
	/**
	 * Functional map.  Applies the provided function to each element in the iterator.  Iteration 
	 * stops when any argument iterator is exhausted.
	 * <p>
	 * Does NOT alter any iterator. 
	 *
	 * @param fn - function to be applied to each thing
	 * @param fings - first argument iterator
	 * @param sings - second argument iterator
	 * @param tings - third argument iterator
	 * 
	 * @return iterator containing the results
	 */
	public static final <F,S,T,R> Iterator<R> map( final TernaryFn<F, S, T, R> fn,
			final Iterable<? extends F> fings, final Iterable<? extends S> sings, 
			final Iterable<? extends T> tings ) {
		return map( fn, fings.iterator(), sings.iterator(), tings.iterator() );
	}
	
	/**
	 * An actual map function (vs. method) that applies a function to one iterator.
	 */
	public static final BinaryFn<UnaryFn, Iterator, Iterator> map = 
		new BinaryFn<UnaryFn, Iterator, Iterator>() {
			@SuppressWarnings("unchecked")
			@Override
			public Iterator apply( UnaryFn fn, Iterator iter ) {
				return map( fn, iter );
			}
	};
	
	/**
	 * This stinks but not much to be done with generic type erasure.
	 * 
	 * An actual map function (vs. method) that applies a function to two iterators.
	 */
	public static final TernaryFn<BinaryFn, Iterator, Iterator, Iterator> map2 = 
		new TernaryFn<BinaryFn, Iterator, Iterator, Iterator>() {
			@SuppressWarnings("unchecked")
			@Override
			public Iterator apply( BinaryFn fn, Iterator iter, Iterator iter2 ) {
				return map( fn, iter, iter2 );
			}
	};
	
	/**
	 * Takes the provided number of things from the iterator.  If the provided number is
	 * greater than the number of things in the iterator, simply stops when the underlying iterator
	 * is exhausted.
	 * 
	 * @param num - how many to take
	 * @param tings - things to take
	 * 
	 * @return iterator that will cease when the number of things have been retrieved or the
	 * 			provided iterator is exhausted
	 */
	public static final <T> Iterator<T> take( final int num, final Iterator<? extends T> tings ) {
		return new Iterator<T>() {
			private int numTaken = 0;
			
			@Override
			public boolean hasNext() {
				return numTaken++ < num && tings.hasNext();
			}

			@Override
			public T next() {
				return tings.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Take does not support removal!" );
			}
		};
	}

	/**
	 * Takes the provided number of things from the iterator.  If the provided number is
	 * greater than the number of things in the iterator, simply stops when the underlying iterator
	 * is exhausted.
	 * 
	 * @param num - how many to take
	 * @param tings - things to take
	 * 
	 * @return iterator that will cease when the number of things have been retrieved or the
	 * 			provided iterator is exhausted
	 */
	public static final <T> Iterator<T> take( int num, Iterable<? extends T> tings ) {
		return take( num, tings.iterator() );
	}

	public static final UnaryFn toString = new UnaryFn() {
		@Override
		public String apply( Object o ) {
			return o.toString();
		}
	};
	
	private static final <T> T advanceUntil( UnaryFn<T, Boolean> pred, Iterator<? extends T> iter ) {
		T t;
		while (iter.hasNext()) {
			t = iter.next();
			if ( pred.apply( t ) ) {
				return t;
			}
		}
		return null;
	}
}
