package com.nkunku.listApplication.backend;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * UT class for {@link MyListUtilsOperation}.
 */
public class MyListUtilsOperationTest {

	/** One of the lists to provide in tests. */
	private static final List<String> LIST_1 = Arrays.asList("1", "2", "3");
	/** One of the lists to provide in tests. */
	private static final List<String> LIST_2 = Arrays.asList("3", "4", "5");


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