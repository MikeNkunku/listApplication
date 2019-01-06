package com.nkunku.listApplication.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

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
 *
 * @author Mike.
 */
public class ListApplicationFrame extends JFrame {

	/** The serial version UID. */
	private static final long serialVersionUID = -2071241925464497755L;


	/** Cannot be instantiated. */
	private ListApplicationFrame() { }


	/** User field : <b>DELIMITER</b>. */
	private static final String DELIMITER_FIELD = "delimiterField";
	/** User field : <b>NUMBER OF RUNS</b>. */
	private static final String NB_RUNS_FIELD = "nbRunsField";
	/** User field : <b>FIRST LIST</b>. */
	private static final String FIRST_LIST_FIELD = "1stListField";
	/** User field : <b>SECOND LIST</b>. */
	private static final String SECOND_LIST_FIELD = "2ndListField";
	/** User field : <b>LIST OPERATION</b>. */
	private static final String LIST_OPERATION_FIELD = "listOperationField";
	/** Array of user fields. */
	private static final String[] USER_TEXT_FIELDS = new String[] {DELIMITER_FIELD, NB_RUNS_FIELD, FIRST_LIST_FIELD, SECOND_LIST_FIELD};


	/** The singleton instance. */
	private static final ListApplicationFrame instance = new ListApplicationFrame();

	/** The instance constraints. */
	private static final GridBagConstraints constraints = new GridBagConstraints();

	/** The map containing the components. */
	private static final Map<String, JComponent> components = new HashMap<>();

	static {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = .01;
		constraints.weighty = .01;
	}


	/**
	 * Adds the provided component to the instance's content pane with the given key.
	 * @param pComponent The component to add.
	 */
	private static void addComponent(final JComponent pComponent) {
		components.put(pComponent.getName(), pComponent);
		instance.add(pComponent, constraints);
	}

	/**
	 * Sets the main frame's configuration when the instance is first initialized.
	 */
	public static void launch() {
		instance.setTitle(ListApplication.class.getSimpleName());
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Default operation when the frame is closed.
		instance.setLayout(new GridBagLayout());
		instance.createRootPane();
		instance.setMaximumSize(new Dimension(400, 500));

		addLabelPanel();
		addFieldPanel();
		addRunPanel();
		addResultsPanel();

		instance.pack(); // Sizes the frame so that all its contents are displayed at their preferred size or above.

		// Set the bounds.

		// Centers the frame.
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		instance.setLocation((d.width - instance.getSize().width) / 2, (d.height - instance.getSize().height) / 2);

		instance.setVisible(true);
	}

	/**
	 * Displays the provided message in a dialog window with the given title and level.
	 * @param pMessage The message.
	 * @param pLevel The level (info, warning, error).
	 */
	private static void displayDialog(final String pMessage, final int pLevel) {
		JOptionPane.showMessageDialog(instance.getContentPane(), pMessage, "Error", pLevel);
	}

	/**
	 * Adds the label panel.
	 */
	private static void addLabelPanel() {
		constraints.gridx = constraints.gridy = 0;
		constraints.insets = new Insets(5, 10, 5, 10);

		JLabel delimiterLabel = new JLabel("Delimiter");
		delimiterLabel.setToolTipText("Delimiter to use for the lists.\nIf none is provided, the default one will be used instead.");
		instance.add(delimiterLabel, constraints);

		JLabel nbRunsLabel = new JLabel("Nb. runs");
		nbRunsLabel.setToolTipText("The number of times that the operation should be run to have a meaningful result.");
		constraints.gridy++;
		instance.add(nbRunsLabel, constraints);

		JLabel firstListLabel = new JLabel("First list");
		constraints.gridy++;
		instance.add(firstListLabel, constraints);

		JLabel secondListLabel = new JLabel("Second list");
		constraints.gridy++;
		instance.add(secondListLabel, constraints);

		JLabel listOperationLabel = new JLabel("List operation");
		constraints.gridy++;
		instance.add(listOperationLabel, constraints);
	}

	/**
	 * Adds the panel containing all the fields to be filled by the user.
	 */
	private static void addFieldPanel() {
		constraints.gridx = 1;
		constraints.insets = new Insets(5, 10, 5, 10);

		Border border = BorderFactory.createEmptyBorder(0, 5, 0, 10);
		JTextField delimiterField = new JTextField();
		delimiterField.setEditable(true);
		delimiterField.setName(DELIMITER_FIELD);
		delimiterField.setBorder(border);
		constraints.gridy = 0;
		addComponent(delimiterField);

		JTextField nbRunsField = new JTextField();
		nbRunsField.setName(NB_RUNS_FIELD);
		nbRunsField.setEditable(true);
		nbRunsField.setBorder(border);
		constraints.gridy++;
		addComponent(nbRunsField);

		JTextField firstListField = new JTextField();
		firstListField.setName(FIRST_LIST_FIELD);
		firstListField.setEditable(true);
		firstListField.setBorder(border);
		constraints.gridy++;
		addComponent(firstListField);

		JTextField secondListField = new JTextField();
		secondListField.setName(SECOND_LIST_FIELD);
		secondListField.setEditable(true);
		secondListField.setBorder(border);
		constraints.gridy++;
		addComponent(secondListField);

		JList<MyListUtilsOperation> listOperations = new JList<>(MyListUtilsOperation.values());
		listOperations.setLayoutOrientation(JList.VERTICAL);
		listOperations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOperations.setVisibleRowCount(2);
		listOperations.setName(LIST_OPERATION_FIELD);
		JScrollPane listOperationsScroller = new JScrollPane(listOperations);
		constraints.gridy++;
		components.put(LIST_OPERATION_FIELD, listOperations);
		instance.add(listOperationsScroller, constraints);

		delimiterField.requestFocusInWindow();
	}

	/**
	 * Adds the run panel (only containing the button to run the picked list operation). 
	 */
	private static void addRunPanel() {
		constraints.gridy = 5;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.insets = new Insets(5, 10, 5, 10);

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
		constraints.gridy = 6;
		constraints.gridx = 0;
		constraints.gridwidth = 2;
		constraints.insets =  new Insets(5, 10, 5, 10);

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
	private static JComponent getComponent(final String pKey) {
		return components.get(pKey);
	}

	/**
	 * @param pFieldName The field name.
	 * @return The field value.
	 * @throws ListApplicationException When the field does not exist.
	 */
	@SuppressWarnings("unchecked")
	private static String getUserValue(final String pFieldName) throws ListApplicationException {
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
			int errorLevel = e instanceof ListApplicationException ? ((ListApplicationException) e).getLevel() : JOptionPane.ERROR_MESSAGE;
			displayDialog(e.getMessage(), errorLevel);
		}
	}


	/**
	 * Sets the results to display.
	 *
	 * @param pString The message to display.
	 */
	private static void setResults(final String pString) {
		((JTextArea) getComponent("results")).setText(pString);
	}
}
