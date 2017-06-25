package com.nkunku.listApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Class containing methods which are likely to be used by other classes.
 * @author Mike.
 */
public class MethodUtils {

	/**
	 * @param pMethod The method to invoke.
	 * @param pLists The arguments to call the method with.
	 * @return The elapsed time.
	 * @throws IllegalAccessException When the provided method cannot be accessed.
	 * @throws IllegalArgumentException When the arguments are not of the correct type.
	 * @throws InvocationTargetException When the method cannot be called on the object.
	 */
	public static long getElapsedTime(final Method pMethod, final List<String>... pLists) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		long startTime = System.currentTimeMillis();
		System.out.format("Method name: %s\nResult: %s\n", pMethod.getName(), pMethod.invoke(null, (Object[]) pLists));
		long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}
}
