package me.brianh.functional;

public class MathFns {

	public static final UnaryFn<Integer, Boolean> isEven = new UnaryFn<Integer, Boolean>() {
		@Override
		public Boolean apply(Integer n) {
			return n % 2 == 0;
		}
	};
}
