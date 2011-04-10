package me.brianh.functional;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Really just a namespace...
 * 
 * @author brian
 */
@SuppressWarnings("rawtypes")
public class Fns {
	public static final BinaryFn<Boolean, Boolean, Boolean> and =
			new BinaryFn<Boolean, Boolean, Boolean>() {
		@Override
		public Boolean apply(Boolean b1, Boolean b2) {
			return b1 && b2;
		}
	};
	
	/**
	 * Applies the predicate to every item in the iterator until it 
	 * fails (returns <code>false</code>).
	 * 
	 * @param pred - predicate to apply to items
	 * @param items - things of interest
	 * 
	 * @return <code>true</code> if all items evaluate to <code>true</code> with 
	 * the predicate, else <code>false</code>
	 */
	public static final <T> Boolean every( final UnaryFn<T, Boolean> pred,
			final Iterator<? extends T> items ) {
		
		boolean result = true;
		
		while ( items.hasNext() && result ) {
			result = pred.apply( items.next() );
		}
		return result;
	}

	
	/**
	 * Applies the predicate to every item in the iterable until it 
	 * fails (returns <code>false</code>).
	 * 
	 * @param pred - predicate to apply to items
	 * @param items - things of interest
	 * 
	 * @return <code>true</code> if all items evaluate to <code>true</code> with 
	 * the predicate, else <code>false</code>
	 */
	public static final <T> Boolean every( final UnaryFn<T, Boolean> pred, 
			final Iterable<? extends T> items ) {
		return every( pred, items.iterator() );
	}
	
	/**
	 * Filters items from the iterator that evaluate to <code>false</code> with the
	 * provided predicate.
	 * 
	 * @param pred - predicate to apply to items for inclusion in results
	 * @param tings - items to process
	 * 
	 * @return iterator that produces items that passed the predicate
	 */
	public static final <T> Iterator<T> filter( final UnaryFn<T, Boolean> pred, 
			final Iterator<? extends T> tings ) {
		
		return new Iterator<T>() {
			private T ting;
			
			@Override
			public boolean hasNext() {
				ting = first( pred, tings );
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

	/**
	 * Filters items from the iterable that evaluate to <code>false</code> with the
	 * provided predicate.
	 * 
	 * @param pred - predicate to apply to items for inclusion in results
	 * @param tings - items to process
	 * 
	 * @return iterator that produces items that passed the predicate
	 */
	public static final <T> Iterator<T> filter( UnaryFn<T, Boolean> pred, Iterable<? extends T> ting ) {
		return filter( pred, ting.iterator() );
	}
	
	
	public static final <T> T first( Iterator<? extends T> tings ) {
		return tings.hasNext() ? tings.next() : null;
	}
	
	public static final <T> T first( Iterable<? extends T> tings ) {
		return first( tings.iterator() );
	}
	
	/**
	 * Locates the first item in the iterator that evaluates to <code>true</code>.
	 * 
	 * @param pred - predicate to apply to each item in the iterator
	 * @param iter - hmmm....
	 * 
	 * @return first item in the list to evaluate to <code>true</code> or <code>null</code> if none
	 * 			is found
	 */
	public static final <T> T first( UnaryFn<T, Boolean> pred, Iterator<? extends T> iter ) {
		T t;
		while ( iter.hasNext() ) {
			t = iter.next();
			if ( pred.apply( t ) ) {
				return t;
			}
		}
		return null;
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
				ting = first( pred, tings );
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
	 * Reduces the provided collection of things to a single thing of the same type by applying the
	 * result of the seed and the first item in the list to the function and then taking that 
	 * result & applying the next item in the list and taking... you get the idea.  Also called fold
	 * left(?) is some languages. 
	 * 
	 * @param fn - function to reduce things with
	 * @param seed - first function call seed value
	 * @param items - things to reduce
	 * 
	 * @return result of repeatedly applying the function to it's result with the items.  If 
	 * 			items is an empty iterator, returns the seed
	 */
	public static final <T> T reduce( BinaryFn<T, T, T> fn, T seed, Iterator<? extends T> items ) {
		T result = seed;
		
		while ( items.hasNext() ) {
			result = fn.apply( result, items.next() );
		}		
		return result;
	}
	
	public static final <T> T reduce( BinaryFn<T, T, T> fn, T seed, Iterable<? extends T> items ) {
		return reduce( fn, seed, items.iterator() );
	}
	
	public static final <T> T reduce( BinaryFn<T, T, T> fn, Iterator<? extends T> items ) {
		// first moves the underlying iterator, therefore nothing else to do here...
		return reduce( fn, first( items ), items );
	}
	
	public static final <T> T reduce( BinaryFn<T, T, T> fn, Iterable<? extends T> items ) {
		// first in this instance does NOT affect iterable, therefore, we need
		// to call rest(...)...
		return reduce( fn, first( items ), rest( items ) );
	}
	
	public static final <T> Iterator<T> repeatedly( final NullaryFn<T> fn ) {
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return true;
			}

			@Override
			public T next() {
				return fn.apply();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Repeatedly does not support removal!" );
			}
		};
	}

	public static final <T> Iterator<T> repeatedly( int n, NullaryFn<T> fn ) {
		return take( n, repeatedly( fn ) );
	}
	
	/**
	 * Advances the provided iterator if possible.
	 * <p>
	 * I don't trust this function...
	 * 
	 * @param tings
	 * 
	 * @return iterator of the rest of the things
	 */
	public static final <T> Iterator<T> rest( Iterator<T> tings ) {
		if ( tings.hasNext() ) {
			tings.next();
			return tings;
		}
		return new Iterator<T>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public T next() {
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException( "Rest does not support removal!" );
			}
		};
	}
	
	/**
	 * Builds an iterator & drops the first item from the list.
	 * 
	 * @param tings - items of interest
	 * 
	 * @return iterator without the first item
	 */
	public static final <T> Iterator<T> rest( Iterable<T> tings ) {
		return rest( tings.iterator() );
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
	public static final <T> Iterator<T> take( final int num, final Iterator<T> tings ) {
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
	public static final <T> Iterator<T> take( int num, Iterable<T> tings ) {
		return take( num, tings.iterator() );
	}

	public static final UnaryFn toString = new UnaryFn() {
		@Override
		public String apply( Object o ) {
			return o.toString();
		}
	};
}
