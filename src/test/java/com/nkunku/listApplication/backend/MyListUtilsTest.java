package com.nkunku.listApplication.backend;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.nkunku.listApplication.ListApplicationException;
import com.nkunku.listApplication.backend.MyListUtils;

/**
 * Unit testing class for {@link MyListUtils}.
 * @author Mike.
 */
public class MyListUtilsTest {

	// --------------------------------------------------------------------------------------------------------------------------------
	// Constants
	// --------------------------------------------------------------------------------------------------------------------------------

	/** First list. */
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

	// --------------------------------------------------------------------------------------------------------------------------------
	// Setup methods
	// --------------------------------------------------------------------------------------------------------------------------------

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

	// --------------------------------------------------------------------------------------------------------------------------------
	// Tests
	// --------------------------------------------------------------------------------------------------------------------------------

	@Test
	public void testGetListFromStringNullListString() {
		try {
			MyListUtils.getListFromString(null, null);
		} catch (ListApplicationException e) {
			assertThat("The list string was expected to be empty", e.getMessage(), equalTo("The list is invalid"));
		}
	}

	@Test
	public void testGetListFromStringValidListStringWithDfltDelimiter() throws ListApplicationException {
		assertThat("The list is supposed to have a size of 3", LIST_1, hasSize(3));
		assertThat("The list is supposed to contain the provided elements", LIST_1, hasItems("1", "2", "3"));
	}

	@Test
	public void testGetListFromStringValidListWithUserDelimiter() throws ListApplicationException {
		List<String> list = MyListUtils.getListFromString("1;3;4;4;6", ";");
		assertThat("The list is supposed to have 5 elements", list, hasSize(5));
		assertThat("The list should contain the 5 provided elements", list, hasItems("1", "3", "6"));
		assertEquals("The list should contain \"4\" twice", 2, Collections.frequency(list, "4"));
	}

	@Test
	public void testUnionSameList() throws ListApplicationException {
		List<String> unionList = MyListUtils.union(LIST_1, LIST_1);
		assertThat("The list is supposed to have 3 elements", unionList, hasSize(3));
		assertThat("The union list is supposed to contain the provided elements", unionList, hasItems("1", "2", "3"));
	}

	@Test
	public void testUnionDifferentLists() throws ListApplicationException {
		List<String> unionList = MyListUtils.union(LIST_1, LIST_2);
		assertThat("The list is supposed to have 6 elements", unionList, hasSize(6));
		assertThat("The union of those 2 different lists is supposed to contain the provided elements", unionList,
				hasItems("1", "2", "3", "4", "5", "6"));
	}

	@Test
	public void testUnionListsWithDuplications() throws ListApplicationException {
		List<String> unionList = MyListUtils.union(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		String msgPrefix = "The union of those lists with doublons should contain";
		assertThat(String.format("%s 9 elements", msgPrefix), unionList, hasSize(9));
		assertThat(String.format("%s contain the provided elements", msgPrefix), unionList,
				hasItems("1", "1", "2", "2", "3", "3", "4", "5", "5"));
	}

	@Test
	public void testIntersectionWithSameList() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_1, LIST_1);
		String msgPrefix = "The intersection of the same list should contain";
		assertThat(String.format("%s 3 elements", msgPrefix), intersectList, hasSize(3));
		assertThat(String.format("%s the provided elements", msgPrefix), intersectList, hasItems("1", "2", "3"));
	}

	@Test
	public void testInterserctionDifferentLists() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_1, LIST_2);
		assertTrue("The intersection of the two different lists should be empty", intersectList.isEmpty());
	}

	@Test
	public void testIntersectionListsWithDuplications() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		String msgPrefix = "The intersection of the two lists with duplications should";
		assertThat(String.format("%s contain only one element", msgPrefix), intersectList, hasSize(1));
		assertThat(String.format("%s be \"2\"", msgPrefix), intersectList, hasItem("2"));
	}

	@Test
	public void testIntersectionListsWithCommonDuplications() throws ListApplicationException {
		List<String> intersectionList = MyListUtils.intersection(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2);
		String msgPrefix = "The intersection of the two lists with common duplications should";
		assertThat(String.format("%s contain 6 elements", msgPrefix), intersectionList, hasSize(6));
		assertThat(String.format("%s contain the provided elements", msgPrefix), intersectionList,
				hasItems("1", "1", "2", "3", "5", "5"));
	}

	@Test
	public void testDifferenceSameList() throws ListApplicationException {
		List<String> differenceList = MyListUtils.difference(LIST_1, LIST_1);
		assertTrue("The difference of the same list should be empty", differenceList.isEmpty());
	}

	@Test
	public void testDifferenceDifferentLists() throws ListApplicationException {
		List<String> differenceList = MyListUtils.difference(LIST_1, LIST_2);
		String msgPfx = "The difference of those 2 lists should";
		assertThat(String.format("%s contain 3 elements", msgPfx), differenceList, hasSize(3));
		assertThat(String.format("%s be equal to the \"LIST_1\"", msgPfx), differenceList, equalTo(LIST_1));
	}
 
	@Test
	public void testDifferenceListsWithCommonDuplications() throws ListApplicationException {
		List<String> differenceList = MyListUtils.difference(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2);
		String msgPfx = "The difference of the two lists with common duplication should";
		assertThat(String.format("%s contain %d elements", msgPfx, 2), differenceList, hasSize(2));
		assertThat(String.format("%s contain the provided elements", msgPfx), differenceList, hasItems("3", "5"));
	}

	@Test
	public void testDisjunctionWithSameList() throws ListApplicationException {
		List<String> disjunctionList = MyListUtils.disjunction(LIST_1, LIST_1);
		assertTrue("The disjunction of the same list should be empty", disjunctionList.isEmpty());
	}

	@Test
	public void testDisjunctionWithDifferentLists() throws ListApplicationException {
		List<String> disjunctionList = MyListUtils.disjunction(LIST_1, LIST_2);
		String msgPfx = "The disjunction of those 2 different lists should";
		assertThat(String.format("%s contain %d elements", msgPfx, 6), disjunctionList, hasSize(6));
		assertThat(String.format("%s contain the provided elements", msgPfx), disjunctionList, hasItems("1", "2", "3", "4", "5", "6"));
	}

	@Test
	public void testDisjunctionListsWithDuplications() throws ListApplicationException {
		List<String> disjunctionList = MyListUtils.disjunction(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		assertThat("The disjunction of those two lists with duplications should contain 8 elements", disjunctionList, hasSize(8));
		assertEquals(String.format("The \"%s\" should occur %d times", "1", 2), 2, Collections.frequency(disjunctionList, "1"));
		assertEquals(String.format("The \"%s\" should occur %d times", "2", 1), 1, Collections.frequency(disjunctionList, "2"));
		assertEquals(String.format("The \"%s\" should occur %d times", "3", 2), 2, Collections.frequency(disjunctionList, "3"));
		assertEquals(String.format("The \"%s\" should occur %d times", "4", 1), 1, Collections.frequency(disjunctionList, "4"));
		assertEquals(String.format("The \"%s\" should occur %d times", "5", 2), 2, Collections.frequency(disjunctionList, "5"));
	}

	@Test
	public void testDisjunctionListsWithCommonDuplications() throws ListApplicationException {
		List<String> disjunctionList = MyListUtils.disjunction(LIST_COMMON_DUPLICATIONS_1, LIST_COMMON_DUPLICATIONS_2);
		assertThat("The disjunction should contain 3 elements", disjunctionList, hasSize(3));
		assertThat("The disjunction should contain the provided elements", disjunctionList, hasItems("2", "3", "5"));
	}
}
