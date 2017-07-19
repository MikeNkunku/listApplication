package com.nkunku.listApplication.frontend;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import com.nkunku.listApplication.ListApplication;
import com.nkunku.listApplication.backend.MyListUtilsOperation;

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

	/** The map containing the components. */
	private static final Map<String, JComponent> fComponents = new HashMap<String, JComponent>();

	// --------------------------------------------------------------------------------------------------------------------------------
	// Methods
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Adds the provided component to the instance's content pane with the given key.
	 * @param pKey The key to find the component later.
	 * @param pComponent The component to add.
	 */
	private static void addComponent(final String pKey, final JComponent pComponent) {
		getInstance().add(pKey, pComponent);
		fComponents.put(pKey, pComponent);
	}

	/**
	 * Set the main frame's configuration when the instance is first initialized.
	 */
	public static void launch() {
		ListApplicationFrame instance = getInstance();
		instance.setTitle(ListApplication.class.getSimpleName());
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default operation when the frame is closed.

		// Delimiter.	
		JLabel delimiterLabel = new JLabel("Delimiter");
		delimiterLabel.setToolTipText("Delimiter to use for the lists.\nIf none is provided, the default one will be used instead");
		addComponent("delimiterLabel", delimiterLabel);

		JTextPane delimiterField = new JTextPane();
		delimiterField.setEditable(true);
		addComponent("delimiterField", delimiterField);

		// Number of runs.
		JLabel nbRunsLabel = new JLabel("Nb. runs");
		nbRunsLabel.setToolTipText("The number of times that the operation should be run to have a meaningful result.");
		addComponent("nbRunsLabel", nbRunsLabel);

		JTextPane nbRunsField = new JTextPane();
		nbRunsField.setEditable(true);
		addComponent("nbRunsField", nbRunsField);

		// First list.
		JLabel firstListLabel = new JLabel("First list");
		addComponent("firstListLabel", firstListLabel);

		JTextPane firstListField = new JTextPane();
		firstListField.setEditable(true);
		addComponent("firstListField", firstListField);

		// Second list.
		JLabel secondListLabel = new JLabel("Second list");
		addComponent("secondListLabel", secondListLabel);

		JTextPane secondListField = new JTextPane();
		secondListField.setEditable(true);
		addComponent("secondListLabel", secondListField);

		// List operation.
		JLabel listOperationLabel = new JLabel("List operation");
		addComponent("listOperationLabel", listOperationLabel);

		JList<MyListUtilsOperation> listOperations = new JList<MyListUtilsOperation>(MyListUtilsOperation.values());
		listOperations.setLayoutOrientation(JList.VERTICAL);
		listOperations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOperations.setVisibleRowCount(1);
		JScrollPane listOperationsScroller = new JScrollPane(listOperations);
		listOperationsScroller.setPreferredSize(new Dimension(200, 20));
		addComponent("listOperationsScroller", listOperationsScroller);

		// Run button.
		JButton runButton = new JButton("Run !");
		runButton.setEnabled(true);
		runButton.setHorizontalAlignment(JButton.CENTER);
		runButton.setVerticalAlignment(JButton.CENTER);
		addComponent("runButton", runButton);

		instance.setPreferredSize(new Dimension(400, 500));
		instance.pack(); // Sizes the frame so that all its contents are displayed at their preferred size or above.

		// Centers the frame.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		instance.setLocation((d.width - instance.getSize().width) / 2, (d.height - instance.getSize().height) / 2);

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
			fInstance.setLayout(new GridLayout(6, 2, 15, 15));
		}
		return fInstance;
	}

	/**
	 * @param pKey The component key.
	 * @return The component if found.<br/>
	 * <code>null</code> otherwise.
	 */
	public static final JComponent getComponent(final String pKey) {
		return fComponents.get(pKey);
	}
}
