package com.nkunku.listApplication.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

import com.nkunku.listApplication.ListApplicationException;

/**
 * Project main class.
 * @author Mike.
 */
public final class MyListUtils {

	/** Default delimiter. */
	public static final String DEFAULT_DELIMITER = ",";


	/**
	 * Cannot be instantiated.
	 */
	private MyListUtils() {}


	/**
	 * @param   pList1 The first list.
	 * @param   pList2 The second list.
	 * @return  The union of the lists.
	 */
	public static List<String> union(final List<String> pList1, final List<String> pList2) {
		List<String> output = new ArrayList<>(pList1);
		int counter = 0;
		String str;
		while (counter < pList2.size()) {
			str = pList2.get(counter);
			if (!pList1.contains(str) || isMoreThanOnceInList(pList2, counter, str)) {
				output.add(str);
			}
			counter++;
		}
		return output;
	}

	/**
	 * @param   pList1 The first list.
	 * @param   pList2 The second list.
	 * @return  The intersection of the lists.
	 */
	public static List<String> intersection(final List<String> pList1, final List<String> pList2) {
		boolean firstBiggerThanSecond = pList1.size() > pList2.size();
		List<String> smallerList = new ArrayList<>(firstBiggerThanSecond ? pList2 : pList1);
		List<String> output = new ArrayList<>(smallerList);
		List<String> otherList = new ArrayList<>(firstBiggerThanSecond ? pList1 : pList2);
		for (String elt : smallerList) {
			if (!otherList.contains(elt)) {
				output.remove(elt);
			}
			otherList.remove(elt);
		}
		return output;
	}

	/**
	 * @param   pList1 The first list.
	 * @param   pList2 The second list.
	 * @return  The difference of the lists.
	 */
	public static List<String> difference(final List<String> pList1, final List<String> pList2) {
		List<String> output = new ArrayList<>(pList1);
		for (String elt : pList2) {
			output.remove(elt);
		}
		return output;
	}

	/**
	 * @param   pList1 The first list.
	 * @param   pList2 The second list.
	 * @return  The symmetric difference of the two lists.
	 */
	public static List<String> disjunction(final List<String> pList1, final List<String> pList2) {
		List<String> differenceL1L2 = difference(pList1, pList2);
		List<String> differenceL2L1 = difference(pList2, pList1);
		List<String> output = new ArrayList<>(differenceL1L2);
		output.addAll(differenceL2L1);
		return output;
	}

	/**
	 * @param   pListStr    The list as a String.
	 * @param   pDelimiter  The delimiter in the provided String.
	 * @return  The corresponding list of String.
	 * @throws  ListApplicationException When the provided list is invalid (null or empty).
	 */
	public static List<String> getListFromString(final String pListStr, final String pDelimiter) throws ListApplicationException {
		if (StringUtils.isBlank(pListStr)) {
			throw new ListApplicationException(String.format("The list \"%s\" is invalid.", pListStr), JOptionPane.ERROR_MESSAGE);
		}

		String delimiter = StringUtils.defaultIfEmpty(pDelimiter, DEFAULT_DELIMITER);
		return Arrays.asList(pListStr.split(delimiter));
	}

	/**
	 * @param   pList   The list.
	 * @param   pIndex  The current index of the list.
	 * @param   pElt    The element to look for.
	 * @return  Whether the element is present more than once in the list.
	 */
	private static boolean isMoreThanOnceInList(final List<String> pList, final int pIndex, final String pElt) {
		List<String> subList = pList.subList(pIndex, pList.size());
		return subList.indexOf(pElt) != subList.lastIndexOf(pElt);
	}
}
