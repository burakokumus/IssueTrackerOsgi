package com.ekinoks.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.IssueType;

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
	private JLabel priorityLabel;
	private GridBagConstraints gbc_priorityLabel;
	private GridBagConstraints gbc_priorityTextField;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	private JButton addIssueButton;
	private GridBagConstraints gbc_descriptionLabel;
	private GridBagConstraints gbc_descriptionTextField;
	private GridBagConstraints gbc_addIssueButton;
	private JLabel projectLabel;

	private JComboBox<String> typeComboBox;

	private JComboBox<String> projectComboBox;
	private DefaultComboBoxModel<String> projectComboBoxModel;
	private JLabel detectedVersionLabel;
	private JTextField detectedVersionTextField;
	private JLabel targetVersionLabel;
	private JTextField targetVersionTextField;

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
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		String issueTypes[] = new String[IssueType.values().length];
		for (int i = 0; i < IssueType.values().length; i++)
		{
			issueTypes[i] = IssueType.values()[i].toString();
		}

		projectLabel = new JLabel(Messages.getString("AddIssueDialogView.lblProject.text")); //$NON-NLS-1$
		GridBagConstraints gbc_projectLabel = new GridBagConstraints();
		gbc_projectLabel.anchor = GridBagConstraints.EAST;
		gbc_projectLabel.insets = new Insets(5, 5, 5, 5);
		gbc_projectLabel.gridx = 0;
		gbc_projectLabel.gridy = 0;
		getContentPane().add(projectLabel, gbc_projectLabel);

		// Set Combo Box
		ArrayList<String> allProjects = DatabaseManager.getInstance().getAllProjectNames();
		String allProjectsArray[] = new String[allProjects.size()];
		for (int i = 0; i < allProjects.size(); i++)
		{
			allProjectsArray[i] = allProjects.get(i);
		}
		projectComboBoxModel = new DefaultComboBoxModel<>(allProjectsArray);
		projectComboBox = new JComboBox<>(projectComboBoxModel);

		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_projectComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_projectComboBox.gridx = 1;
		gbc_projectComboBox.gridy = 0;
		getContentPane().add(projectComboBox, gbc_projectComboBox);

		issueNameLabel = new JLabel(Messages.getString("AddIssueDialog.lblIssueName.text")); //$NON-NLS-1$
		gbc_issueNameLabel = new GridBagConstraints();
		gbc_issueNameLabel.anchor = GridBagConstraints.EAST;
		gbc_issueNameLabel.insets = new Insets(5, 5, 5, 5);
		gbc_issueNameLabel.gridx = 0;
		gbc_issueNameLabel.gridy = 1;
		getContentPane().add(issueNameLabel, gbc_issueNameLabel);

		titleTextField = new JTextField();
		titleTextField.setText(Messages.getString("AddIssueDialog.textField.text")); //$NON-NLS-1$
		gbc_titleTextField = new GridBagConstraints();
		gbc_titleTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleTextField.insets = new Insets(5, 5, 5, 5);
		gbc_titleTextField.gridx = 1;
		gbc_titleTextField.gridy = 1;
		getContentPane().add(titleTextField, gbc_titleTextField);
		titleTextField.setColumns(10);

		typeLabel = new JLabel(Messages.getString("AddIssueDialogView.lblType.text")); //$NON-NLS-1$
		gbc_typeLabel = new GridBagConstraints();
		gbc_typeLabel.anchor = GridBagConstraints.EAST;
		gbc_typeLabel.insets = new Insets(5, 5, 5, 5);
		gbc_typeLabel.gridx = 0;
		gbc_typeLabel.gridy = 2;
		getContentPane().add(typeLabel, gbc_typeLabel);
		typeComboBox = new JComboBox<>(issueTypes);
		gbc_typeComboBox = new GridBagConstraints();
		gbc_typeComboBox.insets = new Insets(5, 5, 5, 5);
		gbc_typeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_typeComboBox.gridx = 1;
		gbc_typeComboBox.gridy = 2;
		getContentPane().add(typeComboBox, gbc_typeComboBox);

		priorityLabel = new JLabel(Messages.getString("AddIssueDialogView.lblPriority.text")); //$NON-NLS-1$
		gbc_priorityLabel = new GridBagConstraints();
		gbc_priorityLabel.anchor = GridBagConstraints.EAST;
		gbc_priorityLabel.insets = new Insets(5, 5, 5, 5);
		gbc_priorityLabel.gridx = 0;
		gbc_priorityLabel.gridy = 3;
		getContentPane().add(priorityLabel, gbc_priorityLabel);

		priorityTextField = new JTextField();
		priorityTextField.setText(Messages.getString("AddIssueDialogView.textField_1.text")); //$NON-NLS-1$
		gbc_priorityTextField = new GridBagConstraints();
		gbc_priorityTextField.insets = new Insets(5, 5, 5, 5);
		gbc_priorityTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_priorityTextField.gridx = 1;
		gbc_priorityTextField.gridy = 3;
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
		gbc_descriptionTextField = new GridBagConstraints();
		gbc_descriptionTextField.insets = new Insets(5, 5, 5, 5);
		gbc_descriptionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_descriptionTextField.gridx = 1;
		gbc_descriptionTextField.gridy = 4;
		getContentPane().add(descriptionTextField, gbc_descriptionTextField);
		descriptionTextField.setColumns(10);

		detectedVersionLabel = new JLabel(Messages.getString("AddIssueDialogView.lblDetectedVersion.text")); //$NON-NLS-1$
		GridBagConstraints gbc_detectedVersionLabel = new GridBagConstraints();
		gbc_detectedVersionLabel.anchor = GridBagConstraints.EAST;
		gbc_detectedVersionLabel.insets = new Insets(5, 5, 5, 5);
		gbc_detectedVersionLabel.gridx = 0;
		gbc_detectedVersionLabel.gridy = 5;
		getContentPane().add(detectedVersionLabel, gbc_detectedVersionLabel);

		detectedVersionTextField = new JTextField();
		GridBagConstraints gbc_detectedVersionTextField = new GridBagConstraints();
		gbc_detectedVersionTextField.insets = new Insets(5, 5, 5, 5);
		gbc_detectedVersionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_detectedVersionTextField.gridx = 1;
		gbc_detectedVersionTextField.gridy = 5;
		getContentPane().add(detectedVersionTextField, gbc_detectedVersionTextField);
		detectedVersionTextField.setColumns(10);

		targetVersionLabel = new JLabel(Messages.getString("AddIssueDialogView.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_targetVersionLabel = new GridBagConstraints();
		gbc_targetVersionLabel.anchor = GridBagConstraints.EAST;
		gbc_targetVersionLabel.insets = new Insets(5, 5, 5, 5);
		gbc_targetVersionLabel.gridx = 0;
		gbc_targetVersionLabel.gridy = 6;
		getContentPane().add(targetVersionLabel, gbc_targetVersionLabel);

		targetVersionTextField = new JTextField();
		GridBagConstraints gbc_targetVersionTextField = new GridBagConstraints();
		gbc_targetVersionTextField.insets = new Insets(5, 5, 5, 5);
		gbc_targetVersionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_targetVersionTextField.gridx = 1;
		gbc_targetVersionTextField.gridy = 6;
		getContentPane().add(targetVersionTextField, gbc_targetVersionTextField);
		targetVersionTextField.setColumns(10);

		addIssueButton = new JButton(Messages.getString("AddIssueDialogView.btnAdd.text")); //$NON-NLS-1$
		gbc_addIssueButton = new GridBagConstraints();
		gbc_addIssueButton.anchor = GridBagConstraints.EAST;
		gbc_addIssueButton.gridx = 1;
		gbc_addIssueButton.gridy = 7;
		getContentPane().add(addIssueButton, gbc_addIssueButton);

		this.getRootPane().setDefaultButton(addIssueButton);
		this.pack();
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
	 * @return the selected project name.
	 */
	public String getSelectedProject()
	{
		return projectComboBox.getSelectedItem().toString();
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

	public JTextField getPriorityTextField()
	{
		return priorityTextField;
	}

	public String getIssueDetectedVersion()
	{
		return detectedVersionTextField.getText();
	}

	public String getIssueTargetVersion()
	{
		return targetVersionTextField.getText();
	}
}
