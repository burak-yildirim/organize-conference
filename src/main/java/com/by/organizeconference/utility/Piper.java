package com.by.organizeconference.utility;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author burakY
 */
public class Piper {
    
    /**
     * Lets you pipe your functions without calling 'andThen' explicitly.
     * In return, you have to explicitly cast the returned function's returned object
     * and instead '{@code Math::sqrt}', you have to use '{@code num -> Math.sqrt((double) num)}' 
     * and explicitly cast the parameter 'num'.
     * @param func first function. This parameter provides obligation to pass at least one function.
     * @param functions unzipped array of functions
     * @return Non-generic function which requires explicit casting on returned object
     */
    public static Function pipe(Function func, Function... functions) {
        try {
            func = Stream.of(functions).reduce(func, (firstFunc, secondFunc) -> firstFunc.andThen(secondFunc));
        } catch (Exception e) {e.printStackTrace();}
        return func;
    }
    
}
