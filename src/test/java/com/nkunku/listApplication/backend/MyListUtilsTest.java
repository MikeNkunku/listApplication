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
		List<String> stringsList = MyListUtils.getListFromString("1,2", null);
		assertThat("The list is supposed to have a size of 2", stringsList, hasSize(2));
		assertThat("The list is supposed to contain the provided elements", stringsList, hasItems("1", "2"));
	}

	@Test
	public void testGetListFromStringValidListWithUserDelimiter() throws ListApplicationException {
		List<String> list = MyListUtils.getListFromString("1;3;4;4;6", ";");
		assertThat("The list is supposed to have 5 elements", list, hasSize(5));
		assertThat("The list should contain the 5 provided elements", list, hasItems("1", "3", "4", "4", "6"));
	}
}
