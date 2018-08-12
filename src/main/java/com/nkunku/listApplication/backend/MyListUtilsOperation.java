package com.nkunku.listApplication.backend;

import java.util.List;
import java.util.function.BiFunction;

/**
 * Enumeration class to apply the available operations of {@link MyListUtils}.
 * @author Mike.
 */
public enum MyListUtilsOperation {

	/** Union operation. */
	UNION(MyListUtils::union),
	/** Difference operation. */
	DIFFERENCE(MyListUtils::difference),
	/** Intersection operation. */
	INTERSECTION(MyListUtils::intersection),
	/** Disjunction operation. */
	DISJUNCTION(MyListUtils::disjunction);

	/** List operation to execute. */
	private final BiFunction<List<String>, List<String>, List<String>> method;


	/**
	 * Private constructor.
	 * @param pMethod The list operation to execute.
	 */
	MyListUtilsOperation(final BiFunction<List<String>, List<String>, List<String>> pMethod) {
		method = pMethod;
	}

	/**
	 * @param pList1 The first list provided.
	 * @param pList2 The first list provided.
	 * @return The resulting list.
	 */
	public List<String> execute(final List<String> pList1, final List<String> pList2) {
		return method.apply(pList1, pList2);
	}
}
