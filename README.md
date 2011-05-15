Functional programming package in java
=============

Why
---

Why not.  Part of the answer is the place I work doesn't really allow using Clojure, so I was curious to see what could be done using just Java code and, I must say, it's pretty ugly.  As for functional itself, I really like how it abstracts out the iteration.  If I had a nickle for every time I'd typed out a for loop...

If your curious, functional programming is better described [here](http://en.wikipedia.org/wiki/Functional_programming).  If you want to do real functional programming, I'd suggest checking out [Clojure](http://clojure.org).


What's been done so far
-----------------------

Currently, most of the function processors (reduce, map, etc.) are static methods.  This is to support the use of the generics for now.  Functions are defined using arity classes that support currying of arguments (ok, maybe partial application but curry# is shorter ;).  The only thing that needs to be implemented is the apply(...) method.  The rest is free.

    BinaryFn<Integer,Integer, Integer> multiply = new BinaryFn<Integer,Integer, Integer>() {
        @Override
        public Integer apply( Integer i1, Integer i2 ) {
            return i1 * i2;
        }
    }

I warned you!  Is that fugly or what?  All to just multiply two integers!  Gotta love those generics....


Where it's going
----------------

Have no idea.  I'd like to see if I can get the functions to implement Runnable.  As well, I may play with not using generics to see how that affects things.  Generics are kinda nice for the user but they are a PITA for the code interplay within the package.  Anyway, I'll probably try and implement something (maybe a functional state machine or a constraint solver) and let that shape things.

Examples
--------

To sum the first 100 integers with this package, do this:

    reduce( add, take( 100, new NaturalInts() ) );

rather than the Java way:

    int sum = 0;
    
    for ( int i = 0; i < 100; i++ ) {
        sum += i;
    }

Now, to sum the first 100 odd numbers, try this:

    reduce( add, take( 100, filter( conjugate( isEven ), new NaturalInts())));

versus Java:

    int sum = 0;
    
    for ( int i = 0; i < 100; i++ ) {
        if ( ! ( i % 2 == 0 ) ) {
            sum += i;
        }
    }

Now, how about multiplying the first 100 even numbers by 13?

    listComprehension( isEven, multiply.curry1( 13 ), take( 100, new NaturalInts() ))

versus, well, you probably get the idea.
