package me.brianh.functional;

/**
 * Function taking one argument.
 * 
 * @author brian
 *
 * @param <ARG> Type of the single argument.
 * @param <R> Result type of this function.
 */
public abstract class UnaryFn<ARG, R> {

    public abstract R apply( ARG arg );

    public NullaryFn<R> curry( final ARG arg ) {
        return new NullaryFn<R>() {
            public R apply() {
                return UnaryFn.this.apply( arg );
            }
        };
    }
}
