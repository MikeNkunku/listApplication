package com.nkunku.listApplication;

/**
 * Project global exception.
 * @author Mike.
 */
public class ListApplicationException extends Exception {

	/** Exception identifier. */
	private static final long serialVersionUID = 3125735059819350757L;

	/**
	 * Constructor with error message.
	 * @param pMessage The error message.
	 */
	public ListApplicationException(final String pMessage) {
		super(pMessage);
	}
}
