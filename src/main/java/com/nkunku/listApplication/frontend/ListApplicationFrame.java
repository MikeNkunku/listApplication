package com.nkunku.listApplication.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.nkunku.listApplication.backend.MethodUtils;
import com.nkunku.listApplication.backend.MyListUtils;
import com.nkunku.listApplication.backend.MyListUtilsOperation;
import com.nkunku.listApplication.ListApplication;
import com.nkunku.listApplication.ListApplicationException;

/**
 * The main frame for the application.
 * @author Mike.
 */
public class ListApplicationFrame extends JFrame {

	/** The serial version UID. */
	private static final long serialVersionUID = -2071241925464497755L;
	/** User field : <b>DELIMITER</b>. */
	public static final String DELIMITER_FIELD = "delimiterField";
	/** User field : <b>NUMBER OF RUNS</b>. */
	public static final String NB_RUNS_FIELD = "nbRunsField";
	/** User field : <b>FIRST LIST</b>. */
	public static final String FIRST_LIST_FIELD = "1stListField";
	/** User field : <b>SECOND LIST</b>. */
	public static final String SECOND_LIST_FIELD = "2ndListField";
	/** User field : <b>LIST OPERATION</b>. */
	public static final String LIST_OPERATION_FIELD = "listOperationField";
	/** Array of user fields. */
	protected static final String[] USER_TEXT_FIELDS = new String[] {DELIMITER_FIELD, NB_RUNS_FIELD, FIRST_LIST_FIELD, SECOND_LIST_FIELD};


	/** The singleton instance. */
	private static final ListApplicationFrame fInstance = new ListApplicationFrame();

	/** The instance constraints. */
	private static final GridBagConstraints fConstraints = new GridBagConstraints();

	/** The map containing the components. */
	private static final Map<String, JComponent> fComponents = new HashMap<String, JComponent>();

	static {
		fConstraints.fill = GridBagConstraints.HORIZONTAL;
	}


	/**
	 * Adds the provided component to the instance's content pane with the given key.
	 * @param pComponent The component to add.
	 */
	private static void addComponent(final JComponent pComponent) {
		fComponents.put(pComponent.getName(), pComponent);
		fInstance.add(pComponent, fConstraints);
	}

	/**
	 * Set the main frame's configuration when the instance is first initialized.
	 */
	public static void launch() {
		fInstance.setTitle(ListApplication.class.getSimpleName());
		fInstance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default operation when the frame is closed.
		fInstance.setLayout(new GridBagLayout());
		fInstance.createRootPane();
		fInstance.setMaximumSize(new Dimension(400, 500));

		addLabelPanel();
		addFieldPanel();
		addRunPanel();
		addResultsPanel();

		fInstance.pack(); // Sizes the frame so that all its contents are displayed at their preferred size or above.

		// Set the bounds.

		// Centers the frame.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		fInstance.setLocation((d.width - fInstance.getSize().width) / 2, (d.height - fInstance.getSize().height) / 2);

		fInstance.setVisible(true);
	}

	/**
	 * Displays the provided message in a dialog window with the given title and level.
	 * @param pMessage The message.
	 * @param pDialogTitle The title.
	 * @param pLevel The level (info, warning, error).
	 */
	public static void displayDialog(final String pMessage, final String pDialogTitle, final int pLevel) {
		JOptionPane.showMessageDialog(fInstance.getContentPane(), pMessage, pDialogTitle, pLevel);
	}

	/**
	 * Adds the label panel.
	 */
	private static void addLabelPanel() {
		fConstraints.gridx = fConstraints.gridy = 0;
		fConstraints.insets = new Insets(5, 10, 5, 10);

		JLabel delimiterLabel = new JLabel("Delimiter");
		delimiterLabel.setToolTipText("Delimiter to use for the lists.\nIf none is provided, the default one will be used instead.");
		fInstance.add(delimiterLabel, fConstraints);

		JLabel nbRunsLabel = new JLabel("Nb. runs");
		nbRunsLabel.setToolTipText("The number of times that the operation should be run to have a meaningful result.");
		fConstraints.gridy++;
		fInstance.add(nbRunsLabel, fConstraints);

		JLabel firstListLabel = new JLabel("First list");
		fConstraints.gridy++;
		fInstance.add(firstListLabel, fConstraints);

		JLabel secondListLabel = new JLabel("Second list");
		fConstraints.gridy++;
		fInstance.add(secondListLabel, fConstraints);

		JLabel listOperationLabel = new JLabel("List operation");
		fConstraints.gridy++;
		fInstance.add(listOperationLabel, fConstraints);
	}

	/**
	 * Adds the panel containing all the fields to be filled by the user.
	 */
	private static void addFieldPanel() {
		fConstraints.gridx = 1;
		fConstraints.insets = new Insets(5, 10, 5, 10);

		Border border = BorderFactory.createEmptyBorder(0, 5, 0, 10);
		JTextField delimiterField = new JTextField();
		delimiterField.setEditable(true);
		delimiterField.setName(DELIMITER_FIELD);
		delimiterField.setBorder(border);
		fConstraints.gridy = 0;
		addComponent(delimiterField);

		JTextField nbRunsField = new JTextField();
		nbRunsField.setName(NB_RUNS_FIELD);
		nbRunsField.setEditable(true);
		nbRunsField.setBorder(border);
		fConstraints.gridy++;
		addComponent(nbRunsField);

		JTextField firstListField = new JTextField();
		firstListField.setName(FIRST_LIST_FIELD);
		firstListField.setEditable(true);
		firstListField.setBorder(border);
		fConstraints.gridy++;
		addComponent(firstListField);

		JTextField secondListField = new JTextField();
		secondListField.setName(SECOND_LIST_FIELD);
		secondListField.setEditable(true);
		secondListField.setBorder(border);
		fConstraints.gridy++;
		addComponent(secondListField);

		JList<MyListUtilsOperation> listOperations = new JList<MyListUtilsOperation>(MyListUtilsOperation.values());
		listOperations.setLayoutOrientation(JList.VERTICAL);
		listOperations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOperations.setVisibleRowCount(2);
		listOperations.setName(LIST_OPERATION_FIELD);
		JScrollPane listOperationsScroller = new JScrollPane(listOperations);
		fConstraints.gridy++;
		fComponents.put(LIST_OPERATION_FIELD, listOperations);
		fInstance.add(listOperationsScroller, fConstraints);

		delimiterField.requestFocusInWindow();
	}

	/**
	 * Adds the run panel (only containing the button to run the picked list operation). 
	 */
	private static void addRunPanel() {
		fConstraints.gridy = 5;
		fConstraints.gridx = 0;
		fConstraints.gridwidth = 2;
		fConstraints.insets = new Insets(5, 10, 5, 10);

		JButton runButton = new JButton("Run");
		runButton.setName("runButton");
		runButton.setEnabled(true);
		runButton.setToolTipText("Run the picked list operation for the provided list.");
		runButton.addActionListener((pEvent) -> executeListOperation());
		addComponent(runButton);
	}

	/**
	 * Adds the the non-editable text area which displays the results.
	 */
	private static void addResultsPanel() {
		fConstraints.gridy = 6;
		fConstraints.gridx = 0;
		fConstraints.gridwidth = 2;
		fConstraints.insets =  new Insets(5, 10, 5, 10);

		JTextArea resultsArea = new JTextArea(5, 2);
		resultsArea.setEditable(false);
		resultsArea.setBackground(Color.DARK_GRAY);
		resultsArea.setName("results");
		resultsArea.setForeground(Color.WHITE); // Text color.
		addComponent(resultsArea);
	}


	/**
	 * @param pKey The component key.
	 * @return The component if found.<br/>
	 * <code>null</code> otherwise.
	 */
	private static final JComponent getComponent(final String pKey) {
		return fComponents.get(pKey);
	}

	/**
	 * @param pFieldName The field name.
	 * @return The field value.
	 * @throws ListApplicationException When the field does not exist.
	 */
	@SuppressWarnings("unchecked")
	private static final String getUserValue(final String pFieldName) throws ListApplicationException {
		if (ArrayUtils.contains(USER_TEXT_FIELDS, pFieldName)) {
			return ((JTextField) getComponent(pFieldName)).getText();
		} else if (LIST_OPERATION_FIELD.equals(pFieldName)) {
			return ((JList<MyListUtilsOperation>) getComponent(LIST_OPERATION_FIELD)).getSelectedValue().name();
		}
		throw new ListApplicationException("This field does not exist.", JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * @return The number of times to run the list operation.
	 * @throws ListApplicationException When an error occurs while parsing the value.
	 */
	private static int getNbRuns() throws ListApplicationException {
		int nbRuns;
		try {
			nbRuns = Integer.valueOf(getUserValue(NB_RUNS_FIELD));
		} catch (NumberFormatException e) {
			throw new ListApplicationException("The number of runs is invalid.", JOptionPane.ERROR_MESSAGE);
		}
		return nbRuns;
	}

	/**
	 * Runs the list operation.
	 */
	private static void executeListOperation() {
		try {
			long myListUtilsMeanRunTime = 0;
			long collectionUtilsMeanRunTime = 0;
			int nbRuns = getNbRuns();
			String delimiter = getUserValue(DELIMITER_FIELD);
			List<String> list1 = MyListUtils.getListFromString(getUserValue(FIRST_LIST_FIELD), delimiter);
			List<String> list2 = MyListUtils.getListFromString(getUserValue(SECOND_LIST_FIELD), delimiter);
			String listOperation = getUserValue(LIST_OPERATION_FIELD);
			String msgBase =  "Delimiter : \"%s\""
					+ "\nNb. runs : %d"
					+ "\nList #1 : \"%s\""
					+ "\nList #2 : \"%s\""
					+ "\nList operation : \"%s\""
					+ "\nExecution time for MyListUtils : \"%d ms\""
					+ "\nExecution time for CollectionUtils : \"%d ms\"";
			MyListUtilsOperation operation = MyListUtilsOperation.valueOf(listOperation);
			long myListUtilsET = MethodUtils.getMeanElapsedTime(operation.getClass().getDeclaredMethod("execute", List.class, List.class), nbRuns, operation, list1, list2);
			long collectionUtilsET = MethodUtils.getMeanElapsedTime(CollectionUtils.class.getDeclaredMethod(operation.name().toLowerCase(), Collection.class, Collection.class), nbRuns, null, list1, list2);
			String msg = String.format(msgBase, StringUtils.defaultIfBlank(delimiter, MyListUtils.DEFAULT_DELIMITER),
					nbRuns, list1, list2, listOperation, myListUtilsET, collectionUtilsET);
			setResults(msg);
		} catch (Exception e) {
			displayDialog(e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//			displayDialog(e.getMessage(), "Error", e.getLevel());
		}
	}


	/**
	 * Set the results to display.
	 * @param pString The message to display.
	 */
	private static void setResults(final String pString) {
		((JTextArea) getComponent("results")).setText(pString);
	}
}
