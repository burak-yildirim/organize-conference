package com.by.organizeconference.helper;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 *
 * @author burakY
 */
public class Piper {
    
    /**
     * Lets you pipe your functions without calling 'andThen' explicitly.
     * In return, you have to cast explicitly the returned function's returned object
     * and instead '{@code Math::sqrt}', you have to use '{@code num -> Math.sqrt((double) num)}' 
     * explicitly to cast the parameter 'num'.
     * @param functions unzipped array of functions
     * @return Non-generic function which requires explicit casting on returned object
     */
    public static Function pipe(Function... functions) {
        return Stream.of(functions).reduce((firstFunc, secondFunc) -> firstFunc.andThen(secondFunc)).get();
    }
    
}
