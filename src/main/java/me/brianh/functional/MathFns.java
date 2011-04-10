package me.brianh.functional;

import java.math.BigInteger;

// TODO These need testing
public class MathFns {

	public static final UnaryFn<Number, Boolean> isEven = new UnaryFn<Number, Boolean>() {
		@Override
		public Boolean apply( Number n) {
			if ( n instanceof Integer ) {
				return n.intValue() % 2 == 0;
			}
			if ( n instanceof Long ) {
				return n.longValue() % 2 == 0;
			}
			if ( n instanceof BigInteger ) {
				return ((BigInteger)n).remainder( BigInteger.valueOf( 2 ) ).intValue() == 0;
			}
			return false;
		}
	};

	public static UnaryFn<Number, Number> sqr = new UnaryFn<Number, Number>() {
		@Override
		public Number apply( Number i ) {
			if ( i instanceof Integer ) {
				return i.intValue() * i.intValue();
			}
			if ( i instanceof Double ) {
				return i.doubleValue() * i.doubleValue();
			}
			// TODO permutations...
			return null;
		}
	};
	
	public static final BinaryFn<Number, Number, Number> sum = new BinaryFn<Number, Number, Number>() {
		@Override
		public Number apply(Number a1, Number a2) {
			if ( a1 instanceof Integer ) {
				if ( a2 instanceof Integer ) {
					return a1.intValue() + a2.intValue();
				}
			}
			// TODO permutations...
			return null;
		}
	};
}
