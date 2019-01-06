package com.nkunku.listApplication.backend;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Unit testing class for {@link MethodUtils}.
 * @author Mike.
 */
@RunWith(Enclosed.class)
public class MethodUtilsTest {

	/** Default list. */
	private static final List<String> LIST = Arrays.asList("1", "2");


	@RunWith(Parameterized.class)
	public static class ParameterizedPart {

		/** The method to execute. */
		private final Method fMethod;
		/** The instance to execute the method on. */
		private final Object fInstance;

		public ParameterizedPart(final Method pMethod, final Object pInstance) {
			fMethod = pMethod;
			fInstance = pInstance;
		}

		@Parameterized.Parameters(name = "{index} - {0}")
		public static Collection<Object[]> data() throws NoSuchMethodException {
			return Arrays.asList(new Object[][] {
				{ MyListUtilsOperation.UNION.getClass().getMethod("execute", List.class, List.class), MyListUtilsOperation.UNION },
				{ CollectionUtils.class.getMethod("union", Collection.class, Collection.class), null }
			});
		}

		@Test
		public void getMeanElapsedTimeOnce() throws Exception {
			testGetMeanElapsedTime(-1);
		}

		@Test
		public void getMeanElapsedTimeTenTimes() throws Exception {
			testGetMeanElapsedTime(10);
		}

		/**
		 * @param pNbRuns The number of times to run the {@linkplain MyListUtils#union union} method.
		 *
		 * @throws Exception When the union method didn't get the right parameters or an error occurred while computing the mean elapsed time.
		 */
		private void testGetMeanElapsedTime(final int pNbRuns) throws Exception {
			long meanElapsedTime = MethodUtils.getMeanElapsedTime(fMethod, pNbRuns, fInstance, LIST, LIST);
			assertThat(meanElapsedTime).isGreaterThan(0);
		}
	}


	public static class ClassicPart {
		@Test
		public void getMillisecondsFromNanoseconds() {
			assertThat(MethodUtils.getMillisecondsFromNanoseconds(900_000L)).isEqualTo(1);
			assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_130_300L)).isEqualTo(1);
			assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_499_999L)).isEqualTo(1);
			assertThat(MethodUtils.getMillisecondsFromNanoseconds(1_500_000L)).isEqualTo(2);
		}
	}
}