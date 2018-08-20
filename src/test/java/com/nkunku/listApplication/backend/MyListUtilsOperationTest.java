package com.nkunku.listApplication.backend;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * UT class for {@link MyListUtilsOperation}.
 */
@RunWith(Enclosed.class)
public class MyListUtilsOperationTest {

	/** One of the lists to provide in tests. */
	private static final List<String> LIST_1 = Arrays.asList("1", "2", "3");
	/** One of the lists to provide in tests. */
	private static final List<String> LIST_2 = Arrays.asList("3", "4", "5");


	/** Inner class dedicated to the testing of the equivalent parameter. */
	@RunWith(Parameterized.class)
	public static class EquivalentTest {

		/** {@link MyListUtilsOperation} instance. */
		private MyListUtilsOperation fInstance;
		/** The expected equivalent for the instance. */
		private String fExpectedEquivalent;

		public EquivalentTest(final MyListUtilsOperation pInstance, final String pExpectedEquivalent) {
			fInstance = pInstance;
			fExpectedEquivalent = pExpectedEquivalent;
		}

		@Parameterized.Parameters(name = "{0}")
		public static Collection<Object[]> data() {
			return Arrays.asList(
				new Object[]{ MyListUtilsOperation.UNION, "union" },
				new Object[]{ MyListUtilsOperation.DIFFERENCE, "substract" },
				new Object[]{ MyListUtilsOperation.DISJUNCTION, "disjunction" },
				new Object[]{ MyListUtilsOperation.INTERSECTION, "intersection" }
			);
		}

		@Test
		public void equivalent() {
			assertThat(fInstance.equivalent()).isEqualTo(fExpectedEquivalent);
		}
	}


	/**
	 * Inner test class dedicated to the testing of the method.
	 */
	public static class MethodTest {
		@Test
		public void union() {
			List<String> result = MyListUtilsOperation.UNION.execute(LIST_1, LIST_2);
			assertThat(result).hasSize(5).contains("1", "2", "3", "4", "5");
		}

		@Test
		public void difference() {
			List<String> result = MyListUtilsOperation.DIFFERENCE.execute(LIST_1, LIST_2);
			assertThat(result).hasSize(2).contains("1", "2");
		}

		@Test
		public void intersection() {
			List<String> result = MyListUtilsOperation.INTERSECTION.execute(LIST_1, LIST_2);
			assertThat(result).containsOnly("3");
		}

		@Test
		public void disjunction() {
			List<String> result = MyListUtilsOperation.DISJUNCTION.execute(LIST_1, LIST_2);
			assertThat(result).hasSize(4).contains("1", "2", "4", "5");
		}
	}
}