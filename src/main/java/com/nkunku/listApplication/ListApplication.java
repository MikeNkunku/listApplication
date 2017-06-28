package com.nkunku.listApplication;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import com.nkunku.listApplication.backend.MethodUtils;
import com.nkunku.listApplication.backend.MyListUtils;

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
	@SuppressWarnings("unchecked")
	public static void main(final String[] pArgs) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		System.out.format("Time elapsed after call to MyListUtils.union(String, String) : %dms", MethodUtils.getElapsedTime(MyListUtils.class.getMethod("union", List.class, List.class), Arrays.asList("1"), Arrays.asList("2")));
	}
}
