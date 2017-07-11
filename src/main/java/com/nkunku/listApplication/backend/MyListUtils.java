package com.nkunku.listApplication.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.nkunku.listApplication.ListApplicationException;

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
			if (!output.contains(str) || isMoreThanOnceInList(pList2, str)) {
				output.add(str);
			}
		}
		return output;
	}

	/**
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The intersection of the lists.
	 * @throws ListApplicationException When at least one of the lists is null.
	 */
	public static List<String> intersection(final List<String> pList1, final List<String> pList2) {
		List<String> output = new ArrayList<String>(pList1);
		for (String elt : pList2) {
			boolean isMoreThanOnceInL1 = isMoreThanOnceInList(pList1, elt);
			boolean isMoreThanOnceInL2 = isMoreThanOnceInList(pList2, elt);
			boolean isMoreThanOnceInOneList = (isMoreThanOnceInL1 && !isMoreThanOnceInL2) || (isMoreThanOnceInL2 && !isMoreThanOnceInL1); 
			if (!pList1.contains(elt) || isMoreThanOnceInOneList) {
				output.remove(elt);
			}
		}
		return output;
	}

	/**
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The difference of the lists.
	 * @throws ListApplicationException When at least one of the lists is null.
	 */
	public static List<String> difference(final List<String> pList1, final List<String> pList2) {
		List<String> output = new ArrayList<String>(pList1);
		for (String elt : pList2) {
			output.remove(elt);
		}
		return output;
	}

	/**
	 * @param pList1 The first list.
	 * @param pList2 The second list.
	 * @return The symmetric difference of the two lists.
	 */
	public static List<String> disjunction(final List<String> pList1, final List<String> pList2) {
		List<String> differenceL1L2 = difference(pList1, pList2);
		List<String> differenceL2L1 = difference(pList2, pList1);
		List<String> output = new ArrayList<String>(differenceL1L2);
		output.addAll(differenceL2L1);
		return output;
	}

	/**
	 * @param pListStr The list as a String.
	 * @param pDelimiter The delimiter in the provided String.
	 * @return The corresponding list of String.
	 * @throws ListApplicationException When the provided list is invalid (null or empty).
	 */
	public static List<String> getListFromString(final String pListStr, final String pDelimiter) throws ListApplicationException {
		if (StringUtils.isBlank(pListStr)) {
			throw new ListApplicationException("The list is invalid");
		}

		String delimiter = StringUtils.defaultIfEmpty(pDelimiter, DEFAULT_DELIMITER);
		return Arrays.asList(pListStr.split(delimiter));
	}

	/**
	 * @param pList The list.
	 * @param pElt The element to look for.
	 * @return Whether the element is present more than once in the list.
	 */
	private static boolean isMoreThanOnceInList(final List<String> pList, final String pElt) {
		return pList.indexOf(pElt) != pList.lastIndexOf(pElt);
	}
}
