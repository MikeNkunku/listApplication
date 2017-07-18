package com.nkunku.listApplication.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;

import com.nkunku.listApplication.ListApplication;

/**
 * The main frame for the application.
 * @author Mike.
 */
public class ListApplicationFrame extends JFrame {

	// --------------------------------------------------------------------------------------------------------------------------------
	// Constants
	// --------------------------------------------------------------------------------------------------------------------------------

	/** The serial version UID. */
	private static final long serialVersionUID = -2071241925464497755L;

	// --------------------------------------------------------------------------------------------------------------------------------
	// Fields
	// --------------------------------------------------------------------------------------------------------------------------------

	/** The singleton instance. */
	private static ListApplicationFrame fInstance;

	// --------------------------------------------------------------------------------------------------------------------------------
	// Methods
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Set the main frame's configuration when the instance is first initialized.
	 */
	public static void launch() {
		ListApplicationFrame instance = getInstance();
		instance.setTitle(ListApplication.class.getSimpleName());
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default operation when the frame is closed.

		// Centers the frame.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		instance.setLocation((d.width - instance.getSize().width) / 2, (d.height - instance.getSize().height) / 2);

		// Delimiter.
		JLabel delimiterLabel = new JLabel("Delimiter");
		delimiterLabel.setToolTipText("Delimiter to use for the lists.\nIf none is provided, the default one will be used instead");
		instance.add(delimiterLabel);

		JTextPane delimiterField = new JTextPane();
		delimiterField.setEditable(true);
		instance.add(delimiterField);

		// Number of runs.
		JLabel nbRunsLabel = new JLabel("Nb. runs");
		nbRunsLabel.setToolTipText("The number of times that the operation should be run to have a meaningful result.");
		instance.add(nbRunsLabel);

		JTextPane nbRunsField = new JTextPane();
		nbRunsField.setEditable(true);
		instance.add(nbRunsField);

//		JLabel firstListLabel = new JLabel("First list");
//		JLabel secondListLabel = new JLabel("Second list");
//		JLabel listOperationLabel = new JLabel("List operation");
//
//		JButton runButton = new JButton("Run !");
//		runButton.setEnabled(true);
//		instance.getContentPane().add(runButton);

		instance.pack(); // Sizes the frame so that all its contents are displayed at their preferred size or above.
		instance.setVisible(true);
	}

	/**
	 * Displays the provided message in a dialog window with the given title and level.
	 * @param pMessage The message.
	 * @param pDialogTitle The title.
	 * @param pLevel The level (info, warning, error).
	 */
	public static void displayDialog(final String pMessage, final String pDialogTitle, final int pLevel) {
		JOptionPane.showInternalMessageDialog(getInstance(), pMessage, pDialogTitle, pLevel);
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Getters
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @return The instance.
	 */
	public static final ListApplicationFrame getInstance() {
		if (fInstance == null) {
			fInstance = new ListApplicationFrame();
			fInstance.setLayout(new GridLayout(2, 2));
		}
		return fInstance;
	}
}