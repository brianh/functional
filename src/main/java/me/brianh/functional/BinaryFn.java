package me.brianh.functional;

/**
 * Function taking 2 arguments.
 * 
 * @author brian
 *
 * @param <ARG1> Type of the first argument.
 * @param <ARG2> Type of the second argument.
 * @param <R> Result type of this function.
 */
public abstract class BinaryFn<ARG1, ARG2, R> {

    public abstract R apply( ARG1 a1, ARG2 a2 );

    public UnaryFn<ARG2, R> curry1( final ARG1 a1 ) {
        return new UnaryFn<ARG2, R>() {
            public R apply( ARG2 a2 ) {
                return BinaryFn.this.apply( a1, a2 );
            }
        };
    }

    public UnaryFn<ARG1, R> curry2( final ARG2 a2 ) {
        return new UnaryFn<ARG1, R>() {
            public R apply( ARG1 a1 ) {
                return BinaryFn.this.apply( a1, a2 );
            }
        };
    }

    public NullaryFn<R> curryAll( final ARG1 a1, final ARG2 a2 ) {
        return new NullaryFn<R>() {
            public R apply() {
                return BinaryFn.this.apply( a1, a2 );
            }
        };
    }
}
