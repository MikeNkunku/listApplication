package com.nkunku.listApplication;

import java.lang.reflect.InvocationTargetException;

/**
 * Class to run the application.
 * @author Mike.
 */
public class ListApplication {

	/** @param pArgs The application arguments. 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException */
	public static void main(final String[] pArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		System.out.format("Time elapsed after call to MyListUtils.union(String, String) : %dms", MethodUtils.getElapsedTime(MyListUtils.class.getMethod("union", String.class, String.class), "1", "2"));
	}
}
