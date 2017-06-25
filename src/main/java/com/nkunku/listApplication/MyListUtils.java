package com.nkunku.listApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Project main class.
 * @author Mike.
 */
public final class MyListUtils {

	// --------------------------------------------------------------------------------------------------------------------------------
	// Constants
	// --------------------------------------------------------------------------------------------------------------------------------

	/** Default delimiter. */
	private static final String DEFAULT_DELIMITER = ",";

	// --------------------------------------------------------------------------------------------------------------------------------
	// Methods
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The union of the lists.
	 * @throws ListApplicationException When at least one of the lists is null.
	 */
	public static List<String> union(final List<String> pList1, final List<String> pList2) {
		List<String> output = new ArrayList<String>(pList1);
		for (String str : pList2) {
			if (!output.contains(str) || (pList2.indexOf(str) != pList2.lastIndexOf(str))) {
				output.add(str);
			}
		}
		return output;
	}

	/** TODO : implement.
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The intersection of the lists.
	 * @throws ListApplicationException When at least one of the lists is null.
	 */
	public static String intersect(final String pList1, final String pList2) {
		return null;
	}

	/** TODO : implement.
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The difference of the lists.
	 * @throws ListApplicationException When at least one of the lists is null.
	 */
	public static String difference(final String pList1, final String pList2) {
		return null;
	}

	/**
	 * @param pListStr The list as a String.
	 * @param pDelimiter The delimiter in the provided String.
	 * @return The corresponding list of String.
	 * @throws ListApplicationException When the provided list is invalid (null or empty).
	 */
	public static List<String> getListFromString(final String pListStr, final String pDelimiter) throws ListApplicationException {
		String delimiter = StringUtils.defaultIfEmpty(pDelimiter, DEFAULT_DELIMITER);
		if (StringUtils.isBlank(pListStr)) {
			throw new ListApplicationException("The list is invalid");
		}
		return Arrays.asList(pListStr.split(delimiter));
	}
}
