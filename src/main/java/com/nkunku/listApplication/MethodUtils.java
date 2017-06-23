package com.nkunku.listApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class containing methods which are likely to be used by other classes.
 * @author Mike.
 */
public class MethodUtils {

	/**
	 * @param pMethod The method to invoke.
	 * @param pArgs The arguments to call the method with.
	 * @return The elapsed time.
	 * @throws IllegalAccessException When the provided method cannot be accessed.
	 * @throws IllegalArgumentException When the arguments are not of the correct type.
	 * @throws InvocationTargetException When the method cannot be called on the object.
	 */
	public static long getElapsedTime(final Method pMethod, final String... pArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		long startTime = System.currentTimeMillis();
		System.out.format("%s : %s\n", pMethod.getName(), pMethod.invoke(null, (Object[]) pArgs));
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
}
