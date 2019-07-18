package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

@SuppressWarnings("serial")
public class SelectProjectDialogView extends JDialog
{
	private JComboBox<String> projectComboBox;
	private JButton selectButton;
	private DefaultComboBoxModel<String> projectComboBoxModel;
	private String[] allProjects;

	public SelectProjectDialogView(String[] allProjects)
	{
		this.allProjects = allProjects;
		initialize();

	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		projectComboBoxModel = new DefaultComboBoxModel<>(allProjects);
		projectComboBox = new JComboBox<>(projectComboBoxModel);
		GridBagConstraints gbc_projectComboBox = new GridBagConstraints();
		gbc_projectComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_projectComboBox.anchor = GridBagConstraints.WEST;
		gbc_projectComboBox.gridx = 0;
		gbc_projectComboBox.gridy = 0;
		getContentPane().add(projectComboBox, gbc_projectComboBox);

		selectButton = new JButton("Select");
		GridBagConstraints gbc_selectButton = new GridBagConstraints();
		gbc_selectButton.gridx = 1;
		gbc_selectButton.gridy = 0;
		getContentPane().add(selectButton, gbc_selectButton);
		this.pack();
	}

	public JButton getSelectButton()
	{
		return selectButton;
	}

	public String getSelectedProject()
	{
		return projectComboBox.getSelectedItem().toString();
	}

}
