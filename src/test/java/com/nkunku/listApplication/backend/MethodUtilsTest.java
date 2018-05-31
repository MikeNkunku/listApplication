package com.nkunku.listApplication.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


/**
 * Unit testing class for {@link MethodUtils}.
 * @author Mike.
 */
public class MethodUtilsTest {

	@Test
	public void testGetMeanElaspedTimeOnce() throws Exception {
		testGetMeanElapsedTime(-1);
	}

	@Test
	public void testGetMeanElaspedTimeTenTimes() throws Exception {
		testGetMeanElapsedTime(10);
	}

	@Test
	public void testGetMillisecondsFromNanoseconds() {
		assertThat(MethodUtils.getMillisecondsFromNanoseconds(900_000L)).isEqualTo(1);
		assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_130_300L)).isEqualTo(1);
		assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_499_999L)).isEqualTo(1);
		assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_500_000L)).isEqualTo(2);
	}


	/**
	 * @param   pNbRuns     The number of times to run the {@linkplain MyListUtils#union union} method.
	 * @throws  Exception   When the union method didn't get the right parameters or an error occurred while computing the mean elapsed time.
	 */
	private void testGetMeanElapsedTime(final int pNbRuns) throws Exception {
		long meanElapsedTime = MethodUtils.getMeanElapsedTime(MyListUtils.class.getMethod("union", List.class, List.class), pNbRuns, Arrays.asList("1", "2"),
				Arrays.asList("1", "2"));
		assertThat(meanElapsedTime).isGreaterThan(0);
	}
}