package com.nkunku.listApplication.backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Class containing methods which are likely to be used by other classes.
 * @author Mike.
 */
public final class MethodUtils {

	/** Cannot be instantiated. */
	private MethodUtils() {}


	/**
	 * @param pMethod	The method to invoke.
	 * @param pNbRuns	The number of times that the method must be executed.
	 * @param pInstance	The instance to call the provided method on.
	 * @param pList1	The first list.
	 * @param pList2	The second list.
	 *
	 * @return  The mean elapsed time (in <b>nanoseconds</b>) for the method with the provided arguments.
	 *
	 * @throws  IllegalAccessException      When the provided method cannot be accessed.
	 * @throws  IllegalArgumentException    When the arguments are not of the correct type.
	 * @throws  InvocationTargetException   When the method cannot be called on the object.
	 */
	public static long getMeanElapsedTime(final Method pMethod, final int pNbRuns, final Object pInstance, final List<String> pList1, final List<String> pList2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
		int nbRuns = pNbRuns < 2 ? 1 : pNbRuns;
		int runIdx = 0;
		long sumElapsedTimes = 0;

		long startTime;
		while (runIdx < nbRuns) {
			startTime = System.nanoTime();
			pMethod.invoke(pInstance, pList1, pList2);
			sumElapsedTimes = System.nanoTime() - startTime;
			runIdx++;
		}
		return sumElapsedTimes / nbRuns;
	}

	/**
	* @param   pTimeInNanoSeconds The time in <b>nanoseconds</b>.
	* @return  The time in <b>milliseconds</b>.
	*/
	public static long getMillisecondsFromNanoseconds(final long pTimeInNanoSeconds) {
		return Math.round((pTimeInNanoSeconds / Math.pow(10, 6)));
	}
}
