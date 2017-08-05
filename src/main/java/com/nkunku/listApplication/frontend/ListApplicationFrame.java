package com.nkunku.listApplication.frontend;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import com.nkunku.listApplication.backend.MyListUtilsOperation;
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

	// --------------------------------------------------------------------------------------------------------------------------------
	// Fields
	// --------------------------------------------------------------------------------------------------------------------------------

	/** The singleton instance. */
	private static final ListApplicationFrame fInstance = new ListApplicationFrame();

	/** The instance constraints. */
	private static final GridBagConstraints fConstraints = new GridBagConstraints();

	/** The map containing the components. */
	private static final Map<String, JComponent> fComponents = new HashMap<String, JComponent>();

	static {
		fConstraints.fill = GridBagConstraints.HORIZONTAL;
	}

	// --------------------------------------------------------------------------------------------------------------------------------
	// Methods
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * Adds the provided component to the instance's content pane with the given key.
	 * @param pKey The key to find the component later.
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
//		JOptionPane.getDesktopPaneForComponent(fInstance);

		addLabelPanel();
		addFieldPanel();
		addRunPanel();
		addResultsPanel();

		fInstance.pack(); // Sizes the frame so that all its contents are displayed at their preferred size or above.

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
		delimiterLabel.setToolTipText("Delimiter to use for the lists.\nIf none is provided, the default one will be used instead");
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

		JTextPane delimiterField = new JTextPane();
		delimiterField.setEditable(true);
		delimiterField.setName(DELIMITER_FIELD);
		fConstraints.gridy = 0;
		addComponent(delimiterField);

		JTextPane nbRunsField = new JTextPane();
		nbRunsField.setName(NB_RUNS_FIELD);
		nbRunsField.setEditable(true);
		fConstraints.gridy++;
		addComponent(nbRunsField);

		JTextPane firstListField = new JTextPane();
		firstListField.setName(FIRST_LIST_FIELD);
		firstListField.setEditable(true);
		fConstraints.gridy++;
		addComponent(firstListField);

		JTextPane secondListField = new JTextPane();
		secondListField.setName(SECOND_LIST_FIELD);
		secondListField.setEditable(true);
		fConstraints.gridy++;
		addComponent(secondListField);

		JList<MyListUtilsOperation> listOperations = new JList<MyListUtilsOperation>(MyListUtilsOperation.values());
		listOperations.setLayoutOrientation(JList.VERTICAL);
		listOperations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listOperations.setVisibleRowCount(1);
		JScrollPane listOperationsScroller = new JScrollPane(listOperations);
		listOperationsScroller.setName(LIST_OPERATION_FIELD);
		fConstraints.gridy++;
		addComponent(listOperationsScroller);

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
		runButton.setEnabled(true);
		runButton.setToolTipText("Run the picked list operation for the provided list.");
		runButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(final ActionEvent pEvt) {
//				((JTextArea) fComponents.get("results")).setText("Clicked on \"run\" !\nYOU SAY I'M JUST A FRIEND...");
				((JTextArea) fComponents.get("results")).setText(getUserValue("delimiterField"));
			}
		});
		fInstance.add(runButton, fConstraints);
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

	// --------------------------------------------------------------------------------------------------------------------------------
	// Getters
	// --------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @return The instance.
	 */
	public static final ListApplicationFrame getInstance() {
		return fInstance;
	}

	/**
	 * @return The constraints.
	 */
	public static final GridBagConstraints getConstraints() {
		return fConstraints;
	}

	/**
	 * @param pKey The component key.
	 * @return The component if found.<br/>
	 * <code>null</code> otherwise.
	 */
	public static final JComponent getComponent(final String pKey) {
		return fComponents.get(pKey);
	}

	/**
	 * @param pFieldName The field name.
	 * @return The field value.
	 */
	public static final String getUserValue(final String pFieldName) {
		return ((JTextPane) getComponent(pFieldName)).getText();
	}
}
