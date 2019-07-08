package com.ekinoks.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class AddIssueDialogView extends JDialog
{
	private GridBagLayout gridBagLayout;
	private JTextField titleTextField;
	private JTextField priorityTextField;
	private JLabel issueNameLabel;
	private GridBagConstraints gbc_issueNameLabel;
	private GridBagConstraints gbc_titleTextField;
	private GridBagConstraints gbc_typeLabel;
	private GridBagConstraints gbc_typeComboBox;
	private JLabel typeLabel;
	private JComboBox<String> typeComboBox;
	private JLabel priorityLabel;
	private GridBagConstraints gbc_priorityLabel;
	private GridBagConstraints gbc_priorityTextField;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	private JButton addIssueButton;
	private GridBagConstraints gbc_descriptionLabel;
	private GridBagConstraints gbc_descriptionTextField;
	private GridBagConstraints gbc_addIssueButton;

	public AddIssueDialogView()
	{
		initialize();
	}

	/**
	 * initializes the UI
	 */
	private void initialize()
	{
		setMinimumSize(new Dimension(500, 500));
		gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		issueNameLabel = new JLabel(Messages.getString("AddIssueDialog.lblIssueName.text")); //$NON-NLS-1$
		gbc_issueNameLabel = new GridBagConstraints();
		gbc_issueNameLabel.anchor = GridBagConstraints.EAST;
		gbc_issueNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_issueNameLabel.gridx = 0;
		gbc_issueNameLabel.gridy = 0;
		getContentPane().add(issueNameLabel, gbc_issueNameLabel);

		titleTextField = new JTextField();
		titleTextField.setText(Messages.getString("AddIssueDialog.textField.text")); //$NON-NLS-1$
		gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.insets = new Insets(5, 5, 5, 0);
		gbc_titleTextField.anchor = GridBagConstraints.WEST;
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 0;
		getContentPane().add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);

		typeLabel = new JLabel(Messages.getString("AddIssueDialogView.lblType.text")); //$NON-NLS-1$
		gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(5, 5, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 1;
		getContentPane().add(typeLabel, gbc_typeLabel);

		typeComboBox = new JComboBox<>(new String[]
		{ "Type1", "Type2", "Type3" });
		gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(5, 5, 5, 0);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 1;
		getContentPane().add(typeComboBox, gbc_typeComboBox);

		priorityLabel = new JLabel(Messages.getString("AddIssueDialogView.lblPriority.text")); //$NON-NLS-1$
		gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.anchor = GridBagConstraints.EAST;
		gbc_priorityLabel.insets = new Insets(5, 5, 5, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 2;
		getContentPane().add(priorityLabel, gbc_priorityLabel);

		priorityTextField = new JTextField();
		priorityTextField.setText(Messages.getString("AddIssueDialogView.textField_1.text")); //$NON-NLS-1$
		gbc_priorityTextField = new GridBagConstraints();
		gbc_priorityTextField.insets = new Insets(5, 5, 5, 5);
		gbc_priorityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityTextField.gridx = 1;
		gbc_priorityTextField.gridy = 2;
		getContentPane().add(priorityTextField, gbc_priorityTextField);
		priorityTextField.setColumns(10);

		descriptionLabel = new JLabel(Messages.getString("AddIssueDialogView.lblDescrl.text")); //$NON-NLS-1$
		gbc_descriptionLabel = new GridBagConstraints();
		gbc_descriptionLabel.anchor = GridBagConstraints.EAST;
		gbc_descriptionLabel.insets = new Insets(5, 5, 5, 5);
		gbc_descriptionLabel.gridx = 0;
		gbc_descriptionLabel.gridy = 4;
		getContentPane().add(descriptionLabel, gbc_descriptionLabel);

		descriptionTextField = new JTextField();
		descriptionTextField.setText(Messages.getString("AddIssueDialogView.textField.text")); //$NON-NLS-1$
		gbc_descriptionTextField = new GridBagConstraints();
		gbc_descriptionTextField.insets = new Insets(5, 5, 5, 0);
		gbc_descriptionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descriptionTextField.gridx = 1;
		gbc_descriptionTextField.gridy = 4;
		getContentPane().add(descriptionTextField, gbc_descriptionTextField);
		descriptionTextField.setColumns(10);

		addIssueButton = new JButton(Messages.getString("AddIssueDialogView.btnAdd.text")); //$NON-NLS-1$
		gbc_addIssueButton = new GridBagConstraints();
		gbc_addIssueButton.anchor = GridBagConstraints.EAST;
		gbc_addIssueButton.gridx = 1;
		gbc_addIssueButton.gridy = 5;
		getContentPane().add(addIssueButton, gbc_addIssueButton);

		this.getRootPane().setDefaultButton(addIssueButton);
		this.pack();
	}

	/**
	 * makes the screen visible
	 */
	public void showScreen()
	{
		this.setVisible(true);
	}

	/**
	 * 
	 * @return the add issue button.
	 */
	public JButton getAddIssueButton()
	{
		return addIssueButton;
	}

	/**
	 * 
	 * @return the issue title.
	 */
	public String getIssueTitle()
	{
		return titleTextField.getText();
	}

	/**
	 * 
	 * @return the issue type.
	 */
	public String getIssueType()
	{
		String s = typeComboBox.getSelectedItem().toString();
		return s;
	}

	/**
	 * 
	 * @return the issue priority
	 */
	public int getIssuePriority()
	{
		String s = priorityTextField.getText().trim();
		if (isNumeric(s))
			return Integer.parseInt(s);
		else
			return -1;
	}

	/**
	 * 
	 * @return the issue description
	 */
	public String getIssueDescription()
	{
		return descriptionTextField.getText();
	}

	/**
	 * 
	 * @param text
	 * @return if the text is numeric.
	 */
	private boolean isNumeric(String text)
	{
		try
		{
			Integer.parseInt(text);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

}
