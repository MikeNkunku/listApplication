package com.nkunku.listApplication;

/**
 * Project global exception.
 * @author Mike.
 */
public class ListApplicationException extends Exception {

	/** Exception identifier. */
	private static final long serialVersionUID = 3125735059819350757L;

	/** Error level (INFO, WARNING, ERROR). */
	private final int fLevel;

	/**
	 * Constructor with error message.
	 * @param pMessage The error message.
	 * @param pLevel The error level.
	 */
	public ListApplicationException(final String pMessage, final int pLevel) {
		super(pMessage);
		fLevel = pLevel;
	}

	/**
	 * @return The error level.
	 */
	public int getLevel() {
		return fLevel;
	}
}
