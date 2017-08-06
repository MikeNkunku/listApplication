package com.nkunku.listApplication.backend;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit testing class for {@link MethodUtils}.
 * @author Mike.
 */
public class MethodUtilsTest {

	// --------------------------------------------------------------------------------------------------------------------------------
	// Tests
	// --------------------------------------------------------------------------------------------------------------------------------

	@Test
	public void testGetMeanElaspedTimeOnce() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		testGetMeanElapsedTime(-1);
	}

	@Test
	public void testGetMeanElaspedTimeTenTimes() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		testGetMeanElapsedTime(10);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Factorized methods to test
	// --------------------------------------------------------------------------------------------------------------------------------

	@SuppressWarnings("unchecked")
	private void testGetMeanElapsedTime(final int pNbRuns) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		long meanElapsedTime = MethodUtils.getMeanElapsedTime(MyListUtils.class.getMethod("union", List.class, List.class), pNbRuns, Arrays.asList("1", "2"),
				Arrays.asList("1", "2"));
		assertTrue("The mean of the elapsed time should be valid", meanElapsedTime > 0);
	}
}