package com.nkunku.listApplication.backend;

import static org.assertj.core.api.Assertions.*;
import com.nkunku.listApplication.ListApplicationException;
import org.assertj.core.api.Condition;
import org.junit.Test;

import java.util.List;

/**
 * Unit testing class for {@link MyListUtils}.
 * @author Mike.
 */
public class MyListUtilsTest {

	/** First list. */
	@SuppressWarnings("CanBeFinal")
	private static List<String> LIST_1;

	/** Second list. */
	private static List<String> LIST_2;

	/** First list with common duplications. */
	private static List<String> LIST_COMMON_DUPLICATIONS_1;

	/** Second list with common duplications. */
	private static List<String> LIST_COMMON_DUPLICATIONS_2;

	/** First list with duplications. */
	private static List<String> LIST_DUPLICATIONS_1;

	/** Second list with duplications. */
	private static List<String> LIST_DUPLICATIONS_2;


	static {
		try {
			LIST_1 = MyListUtils.getListFromString("1,2,3", null);
			LIST_2 = MyListUtils.getListFromString("4,5,6", null);
			LIST_COMMON_DUPLICATIONS_1 = MyListUtils.getListFromString("1,1,2,3,3,5,5,5", null);
			LIST_COMMON_DUPLICATIONS_2 = MyListUtils.getListFromString("1,1,2,2,3,5,5", null);
			LIST_DUPLICATIONS_1 = MyListUtils.getListFromString("1,1,2,3,3", null);
			LIST_DUPLICATIONS_2 = MyListUtils.getListFromString("2,2,4,5,5", null);
		} catch (ListApplicationException e) {
			System.out.format("%s unit tests could not be run due to static initialization errors\n", MyListUtilsTest.class.getSimpleName());
		}
	}


	@Test
	public void getListFromStringNullListString() {
		try {
			MyListUtils.getListFromString(null, null);
		} catch (ListApplicationException e) {
			assertThat(e.getMessage()).isEqualTo("The list \"null\" is invalid.");
		}
	}

	@Test
	public void getListFromStringValidListStringWithDfltDelimiter() {
		assertThat(LIST_1)
			.hasSize(3)
			.contains("1", "2", "3");
	}

	@Test
	public void getListFromStringValidListWithUserDelimiter() throws ListApplicationException {
		assertThat(MyListUtils.getListFromString("1;3;4;4;6", ";"))
			.hasSize(5)
			.containsOnlyOnce("1", "3", "6")
			.haveExactly(2, new Condition<>("4"::equals, "4 is present twice"));
	}

	@Test
	public void unionWithSameList() {
		assertThat(MyListUtils.union(LIST_1, LIST_1))
			.hasSize(3)
			.contains("1", "2", "3");
	}

	@Test
	public void unionWithDifferentLists() {
		assertThat(MyListUtils.union(LIST_1, LIST_2))
			.hasSize(6)
			.containsOnlyOnce("1", "2", "3", "4", "5", "6");
	}

	@Test
	public void unionListsWithDuplications() {
		List<String> unionList = MyListUtils.union(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		assertThat(unionList)
			.hasSize(9)
			.containsOnlyOnce("4");

		String[] eltsInDouble = new String[] {"1", "2", "3", "5"};
		for (String elt : eltsInDouble) {
			assertPresentTwice(unionList, elt);
		}
	}

	@Test
	public void intersectionWithSameList() {
		assertThat(MyListUtils.intersection(LIST_1, LIST_1))
			.hasSize(3)
			.contains("1", "2", "3");
	}

	@Test
	public void intersectionWithDifferentLists() {
		assertThat(MyListUtils.intersection(LIST_1, LIST_2)).isEmpty();
	}

	@Test
	public void intersectionListsWithDuplications() {
		assertThat(MyListUtils.intersection(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2))
			.hasSize(1)
			.containsOnly("2");
	}

	@Test
	public void intersectionListsWithCommonDuplications() {
		List<String> intersectionList = MyListUtils.intersection(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2);
		assertThat(intersectionList)
			.hasSize(6)
			.containsOnlyOnce("2", "3");

		String[] eltsInDouble = new String[] {"1", "5"};
		for (String elt : eltsInDouble) {
			assertPresentTwice(intersectionList, elt);
		}
	}

	@Test
	public void differenceWithSameList() {
		assertThat(MyListUtils.difference(LIST_1, LIST_1)).isEmpty();
	}

	@Test
	public void differenceWithDifferentLists() {
		assertThat(MyListUtils.difference(LIST_1, LIST_2))
			.hasSize(3)
			.isEqualTo(LIST_1);
	}

	@Test
	public void differenceListsWithCommonDuplications() {
		assertThat(MyListUtils.difference(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2))
			.hasSize(2)
			.containsOnlyOnce("3", "5");
	}

	@Test
	public void disjunctionWithSameList() {
		assertThat(MyListUtils.disjunction(LIST_1, LIST_1)).isEmpty();
	}

	@Test
	public void disjunctionWithDifferentLists() {
		assertThat(MyListUtils.disjunction(LIST_1, LIST_2))
			.hasSize(6)
			.containsOnlyOnce("1", "2", "3", "4", "5", "6");
	}

	@Test
	public void disjunctionListsWithDuplications() {
		List<String> disjunctionList = MyListUtils.disjunction(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		assertThat(disjunctionList)
			.hasSize(8)
			.containsOnlyOnce("2", "4");

		String[] eltsInDouble = new String[] {"1", "3", "5"};
		for (String elt : eltsInDouble) {
			assertPresentTwice(disjunctionList, elt);
		}
	}

	@Test
	public void disjunctionListsWithCommonDuplications() {
		assertThat(MyListUtils.disjunction(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2))
			.hasSize(3)
			.containsOnlyOnce("2", "3", "5");
	}


	/**
	 * @param   pList   The list to assert on.
	 * @param   pElt    The element which is to be present twice.
	 */
	private void assertPresentTwice (final List<String> pList, final String pElt) {
		assertThat(pList).haveExactly(2, new Condition<>(pElt::equals, "contains twice"));
	}
}