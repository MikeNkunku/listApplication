package com.nkunku.listApplication.backend;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.*;

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
		assertThat("The list should contain the 5 provided elements", list, hasItems("1", "3", "4", "4", "6"));
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
		assertThat("The union of those 2 different lists is supposed to contain the provided elements", unionList, hasItems("1", "2", "3", "4", "5", "6"));
	}

	@Test
	public void testUnionListsWithDuplications() throws ListApplicationException {
		List<String> unionList = MyListUtils.union(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		assertThat("The union of those lists with doublons should contain 9 elements", unionList, hasSize(9));
		assertThat("The union of those lists with doublons should contain the provided elements", unionList, hasItems("1", "1", "2", "2", "3", "3", "4", "5", "5"));
	}

	@Test
	public void testIntersectionWithSameList() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_1, LIST_1);
		assertThat("The intersection of the same list should contain 3 elements", intersectList, hasSize(3));
		assertThat("The intersection of the same list should contain the provided elements", intersectList, hasItems("1", "2", "3"));
	}

	@Test
	public void testInterserctionDifferentLists() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_1, LIST_2);
		assertTrue("The intersection of the two different lists should be empty", intersectList.isEmpty());
	}

	@Test
	public void testIntersectionListsWithDuplications() throws ListApplicationException {
		List<String> intersectList = MyListUtils.intersection(LIST_DUPLICATIONS_1, LIST_DUPLICATIONS_2);
		assertThat("The intersection of the two lists with duplications should contain only one element", intersectList, hasSize(1));
		assertThat("The intersection of the two lists with duplications should be \"2\"", intersectList, hasItem("2"));
	}
}
