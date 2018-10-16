package com.nkunku.listApplication;

/**
 * Project global exception.
 *
 * @author Mike.
 */
public class ListApplicationException extends Exception {

	/** Exception identifier. */
	private static final long serialVersionUID = 3125735059819350757L;

	/** Error level (INFO, WARNING, ERROR). */
	private final int level;

	/**
	 * Constructor with error message.
     *
	 * @param message  The error message.
	 * @param level    The error level.
	 */
	public ListApplicationException(final String message, final int level) {
		super(message);
		this.level = level;
	}

	/**
	 * @return The error level.
	 */
	public int getLevel() {
		return level;
	}
}
