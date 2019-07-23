package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class SettingsDialogView extends JDialog
{
	private JCheckBox projectNameCheckbox;
	private JCheckBox issueIDCheckbox;
	private JCheckBox titleCheckBox;
	private JCheckBox priorityCheckbox;
	private JCheckBox authorCheckbox;
	private JCheckBox descriptionCheckbox;
	private JCheckBox stateCheckbox;
	private JButton applyButton;
	private String storeURL = "C:\\Users\\burak\\Desktop\\config.properties";
	private JCheckBox typeCheckBox;

	public SettingsDialogView()
	{
		initialize();
		setInitialSelects();

	}

	private void initialize()
	{
		this.setTitle("Settings");
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		projectNameCheckbox = new JCheckBox("Project Name");
		GridBagConstraints gbc_projectNameCheckbox = new GridBagConstraints();
		gbc_projectNameCheckbox.anchor = GridBagConstraints.WEST;
		gbc_projectNameCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_projectNameCheckbox.gridx = 0;
		gbc_projectNameCheckbox.gridy = 1;
		getContentPane().add(projectNameCheckbox, gbc_projectNameCheckbox);

		issueIDCheckbox = new JCheckBox("Issue ID");
		GridBagConstraints gbc_IssueIDCheckbox = new GridBagConstraints();
		gbc_IssueIDCheckbox.insets = new Insets(0, 0, 5, 0);
		gbc_IssueIDCheckbox.anchor = GridBagConstraints.WEST;
		gbc_IssueIDCheckbox.gridx = 1;
		gbc_IssueIDCheckbox.gridy = 1;
		getContentPane().add(issueIDCheckbox, gbc_IssueIDCheckbox);

		titleCheckBox = new JCheckBox("Title");
		GridBagConstraints gbc_titleCheckBox = new GridBagConstraints();
		gbc_titleCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_titleCheckBox.anchor = GridBagConstraints.WEST;
		gbc_titleCheckBox.gridx = 0;
		gbc_titleCheckBox.gridy = 2;
		getContentPane().add(titleCheckBox, gbc_titleCheckBox);

		priorityCheckbox = new JCheckBox("Priority");
		GridBagConstraints gbc_priorityCheckbox = new GridBagConstraints();
		gbc_priorityCheckbox.insets = new Insets(0, 0, 5, 0);
		gbc_priorityCheckbox.anchor = GridBagConstraints.WEST;
		gbc_priorityCheckbox.gridx = 1;
		gbc_priorityCheckbox.gridy = 2;
		getContentPane().add(priorityCheckbox, gbc_priorityCheckbox);

		authorCheckbox = new JCheckBox("Author");
		GridBagConstraints gbc_authorCheckbox = new GridBagConstraints();
		gbc_authorCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_authorCheckbox.anchor = GridBagConstraints.WEST;
		gbc_authorCheckbox.gridx = 0;
		gbc_authorCheckbox.gridy = 3;
		getContentPane().add(authorCheckbox, gbc_authorCheckbox);

		descriptionCheckbox = new JCheckBox("Description");
		GridBagConstraints gbc_descriptionCheckbox = new GridBagConstraints();
		gbc_descriptionCheckbox.insets = new Insets(0, 0, 5, 0);
		gbc_descriptionCheckbox.anchor = GridBagConstraints.WEST;
		gbc_descriptionCheckbox.gridx = 1;
		gbc_descriptionCheckbox.gridy = 3;
		getContentPane().add(descriptionCheckbox, gbc_descriptionCheckbox);

		stateCheckbox = new JCheckBox("State");
		GridBagConstraints gbc_stateCheckbox = new GridBagConstraints();
		gbc_stateCheckbox.anchor = GridBagConstraints.WEST;
		gbc_stateCheckbox.insets = new Insets(0, 0, 5, 5);
		gbc_stateCheckbox.gridx = 0;
		gbc_stateCheckbox.gridy = 4;
		getContentPane().add(stateCheckbox, gbc_stateCheckbox);

		applyButton = new JButton("Apply");
		GridBagConstraints gbc_applyButton = new GridBagConstraints();
		gbc_applyButton.insets = new Insets(0, 0, 5, 0);
		gbc_applyButton.anchor = GridBagConstraints.WEST;
		gbc_applyButton.gridx = 1;
		gbc_applyButton.gridy = 4;
		getContentPane().add(applyButton, gbc_applyButton);

		this.getRootPane().setDefaultButton(applyButton);

		typeCheckBox = new JCheckBox("Type");
		GridBagConstraints gbc_typeCheckBox = new GridBagConstraints();
		gbc_typeCheckBox.anchor = GridBagConstraints.WEST;
		gbc_typeCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_typeCheckBox.gridx = 0;
		gbc_typeCheckBox.gridy = 5;
		getContentPane().add(typeCheckBox, gbc_typeCheckBox);

		this.pack();
	}

	private void setInitialSelects()
	{
		Properties properties = new Properties();
		try
		{
			properties.load(new FileInputStream(storeURL));
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}

		projectNameCheckbox.setSelected(properties.getProperty("projectName").equals("true"));
		issueIDCheckbox.setSelected(properties.getProperty("id").equals("true"));
		priorityCheckbox.setSelected(properties.getProperty("priority").equals("true"));
		authorCheckbox.setSelected(properties.getProperty("author").equals("true"));
		typeCheckBox.setSelected(properties.getProperty("type").equals("true"));
		descriptionCheckbox.setSelected(properties.getProperty("description").equals("true"));
		stateCheckbox.setSelected(properties.getProperty("state").equals("true"));
		titleCheckBox.setSelected(properties.getProperty("title").equals("true"));
		/**
		 * state=true title=true
		 */
	}

	public JButton getApplyButton()
	{
		return applyButton;
	}

	public boolean isProjectNameSelected()
	{
		return projectNameCheckbox.isSelected();
	}

	public boolean isIssueIdSelected()
	{
		return issueIDCheckbox.isSelected();
	}

	public boolean isTitleSelected()
	{
		return titleCheckBox.isSelected();
	}

	public boolean isProritySelected()
	{
		return priorityCheckbox.isSelected();
	}

	public boolean isAuthorSelected()
	{
		return authorCheckbox.isSelected();
	}

	public boolean isDescriptionSelected()
	{
		return descriptionCheckbox.isSelected();
	}

	public boolean isStateSelected()
	{
		return stateCheckbox.isSelected();
	}

	public boolean isTypeSelected()
	{
		return typeCheckBox.isSelected();

	}
}
