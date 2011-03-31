package me.brianh.functional;

/**
 * Function taking 3 arguments.
 * 
 * @author brian
 *
 * @param <ARG1> Type of the first argument.
 * @param <ARG2> Type of the second argument.
 * @param <ARG3> Type of the third argument.
 * @param <R> Result type of this function.
 */
public abstract class TernaryFn<ARG1, ARG2, ARG3, R> {

    public abstract R apply( ARG1 a1, ARG2 a2, ARG3 a3 );

    public BinaryFn<ARG2, ARG3, R> curry1( final ARG1 a1 ) {
        return new BinaryFn<ARG2, ARG3, R>() {
            public R apply( ARG2 a2, ARG3 a3 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public BinaryFn<ARG1, ARG3, R> curry2( final ARG2 a2 ) {
        return new BinaryFn<ARG1, ARG3, R>() {
            public R apply( ARG1 a1, ARG3 a3 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public BinaryFn<ARG1, ARG2, R> curry3( final ARG3 a3 ) {
        return new BinaryFn<ARG1, ARG2, R>() {
            public R apply( ARG1 a1, ARG2 a2 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public UnaryFn<ARG3, R> curry1and2( final ARG1 a1, final ARG2 a2 ) {
        return new UnaryFn<ARG3, R>() {
            public R apply( ARG3 a3 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public UnaryFn<ARG2, R> curry1and3( final ARG1 a1, final ARG3 a3 ) {
        return new UnaryFn<ARG2, R>() {
            public R apply( ARG2 a2 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public UnaryFn<ARG1, R> curry2and3( final ARG2 a2, final ARG3 a3 ) {
        return new UnaryFn<ARG1, R>() {
            public R apply( ARG1 a1 ) {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }

    public NullaryFn<R> curryAll( final ARG1 a1, final ARG2 a2, final ARG3 a3 ) {
        return new NullaryFn<R>() {
            public R apply() {
                return TernaryFn.this.apply( a1, a2, a3 );
            }
        };
    }
}
