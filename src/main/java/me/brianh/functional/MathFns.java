package me.brianh.functional;

import java.math.BigInteger;

// TODO These need testing
@SuppressWarnings("rawtypes")
public class MathFns {
	
	public static final BinaryFn add = new BinaryFn() {
		@Override
		public Object apply(Object o1, Object o2) {
			if ( o1 instanceof Integer ) {
				Integer i1 = ((Integer)o1);
				if ( o2 instanceof Integer ) {
					Integer i2 = ((Integer)o2);
					return i1.intValue() + i2.intValue();
				}
			}
			// TODO permutations...
			return null;
		}
	};

	public static final UnaryFn isEven = new UnaryFn() {
		@Override
		public Boolean apply( Object n ) {
			if ( n instanceof Integer ) {
				return ((Integer)n).intValue() % 2 == 0;
			}
			if ( n instanceof Long ) {
				return ((Long)n).longValue() % 2 == 0;
			}
			if ( n instanceof BigInteger ) {
				// never messed with big ints.  need to test this
				return ((BigInteger)n).remainder( BigInteger.valueOf( 2 ) ).intValue() == 0;
			}
			return false;
		}
	};
	
	public static final BinaryFn mult = new BinaryFn() {
		@Override
		public Object apply(Object o1, Object o2) {
			if ( o1 instanceof Integer ) {
				Integer i1 = ((Integer)o1);
				if ( o2 instanceof Integer ) {
					Integer i2 = ((Integer)o2);
					return i1.intValue() * i2.intValue();
				}
			}
			// TODO permutations...
			return null;
		}
	};

	public static UnaryFn sqr = new UnaryFn() {
		@Override
		public Object apply( Object o ) {
			if ( o instanceof Integer ) {
				Integer i = ((Integer)o);
				return i.intValue() * i.intValue();
			}
			if ( o instanceof Double ) {
				Integer i = ((Integer)o);
				return i.doubleValue() * i.doubleValue();
			}
			// TODO permutations...
			return null;
		}
	};
}
