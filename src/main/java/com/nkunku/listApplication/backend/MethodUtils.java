package com.nkunku.listApplication.backend;

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
	 * @param pNbRuns The number of times that the method must be executed.
	 * @param pLists The arguments to call the method with.
	 * @return The mean elapsed time (in <b>milliseconds</b>) for the method with the provided arguments.
	 * @throws IllegalAccessException When the provided method cannot be accessed.
	 * @throws IllegalArgumentException When the arguments are not of the correct type.
	 * @throws InvocationTargetException When the method cannot be called on the object.
	 */
	public static long getMeanElapsedTime(final Method pMethod, final int pNbRuns, final List<String>... pLists) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int nbRuns = pNbRuns < 2 ? 1 : pNbRuns;
		int runIdx = 0;
		long startTime;
		long sumElapasedTimes = 0;
		while (runIdx < nbRuns) {
			startTime = System.currentTimeMillis();
			pMethod.invoke(null, (Object[]) pLists);
			sumElapasedTimes += System.currentTimeMillis() - startTime;
			runIdx++;
		}
		return sumElapasedTimes / nbRuns;
	}
}
