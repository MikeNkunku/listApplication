package com.nkunku.listApplication.backend;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Class containing methods which are likely to be used by other classes.
 *
 * @author Mike.
 */
public final class MethodUtils {

	/** Cannot be instantiated. */
	private MethodUtils() {}


	/**
	 * @param method	The method to invoke.
	 * @param nbRuns	The number of times that the method must be executed.
	 * @param instance	The instance to call the provided method on.
	 * @param list1	The first list.
	 * @param list2	The second list.
	 *
	 * @return  The mean elapsed time (in <b>nanoseconds</b>) for the method with the provided arguments.
	 *
	 * @throws  IllegalAccessException      When the provided method cannot be accessed.
	 * @throws  IllegalArgumentException    When the arguments are not of the correct type.
	 * @throws  InvocationTargetException   When the method cannot be called on the object.
	 */
	public static long getMeanElapsedTime(final Method method, final int nbRuns, final Object instance, final List<String> list1, final List<String> list2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		int validNbRuns = nbRuns < 2 ? 1 : nbRuns;
		int runIdx = 0;
		long sumElapsedTimes = 0;

		long startTime;
		while (runIdx < validNbRuns) {
			startTime = System.nanoTime();
			method.invoke(instance, list1, list2);
			sumElapsedTimes = System.nanoTime() - startTime;
			runIdx++;
		}
		return sumElapsedTimes / validNbRuns;
	}

	/**
	* @param   timeInNanoSeconds The time in <b>nanoseconds</b>.
	* @return  The time in <b>milliseconds</b>.
	*/
	public static long getMillisecondsFromNanoseconds(final long timeInNanoSeconds) {
		return Math.round((timeInNanoSeconds / Math.pow(10, 6)));
	}
}
