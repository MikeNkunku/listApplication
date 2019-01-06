package com.nkunku.listApplication.backend;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Enumeration class to apply the available operations of {@link MyListUtils}.
 *
 * @author Mike.
 */
public enum MyListUtilsOperation {

	/** Union operation. */
	UNION(MyListUtils::union, "union"),
	/** Difference operation. */
	DIFFERENCE(MyListUtils::difference, "substract"),
	/** Intersection operation. */
	INTERSECTION(MyListUtils::intersection, "intersection"),
	/** Disjunction operation. */
	DISJUNCTION(MyListUtils::disjunction, "disjunction");

	/** List operation to execute. */
	private final BiFunction<List<String>, List<String>, List<String>> method;
	/** The name of the equivalent method within {@linkplain org.apache.commons.collections.CollectionUtils CollectionUtils}. */
	private final String equivalent;


	/**
	 * Private constructor.
	 *
	 * @param pMethod     The list operation to execute.
	 * @param pEquivalent The name of the equivalent method in CollectionUtils.
	 */
	MyListUtilsOperation(final BiFunction<List<String>, List<String>, List<String>> pMethod, final String pEquivalent) {
		this.method = pMethod;
		this.equivalent = pEquivalent;
	}


	/**
	 * @param list1 The first list provided.
	 * @param list2 The second list provided.
	 * @return The resulting list.
	 */
	public List<String> execute(final List<String> list1, final List<String> list2) {
		return this.method.apply(list1, list2);
	}

	/** @return The name of the equivalent method within {@linkplain org.apache.commons.collections.CollectionUtils CollectionUtils}. */
	public String equivalent() {
		return this.equivalent;
	}
}
