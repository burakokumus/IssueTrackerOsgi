package com.ekinoks.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.ekinoks.database.DatabaseManager;

@SuppressWarnings("serial")
public class ProjectAvailabilityView extends JDialog
{
	private String userName;
	private JPanel mainPanel;
	@SuppressWarnings("unused")
	private JDialog parentWindow;
	private JButton setButton;
	private String[] allProjects;
	private JCheckBox[] checkBoxList;

	public ProjectAvailabilityView(String userName, JDialog parentWindow, ArrayList<String> allProjects)
	{
		super(parentWindow);
		this.userName = userName;
		this.parentWindow = parentWindow;
		this.setLocationRelativeTo(parentWindow);
		this.allProjects = new String[allProjects.size()];
		for (int i = 0; i < allProjects.size(); i++)
			this.allProjects[i] = allProjects.get(i);
		initialize();
	}

	private void initialize()
	{
		checkBoxList = new JCheckBox[allProjects.length];
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		GridBagLayout gbl_mainPanel = new GridBagLayout();
		gbl_mainPanel.columnWidths = new int[]
		{ 0 };
		gbl_mainPanel.rowHeights = new int[]
		{ 0 };
		gbl_mainPanel.columnWeights = new double[]
		{ Double.MIN_VALUE };
		gbl_mainPanel.rowWeights = new double[]
		{ Double.MIN_VALUE };
		mainPanel.setLayout(gbl_mainPanel);
		for (int i = 0; i < allProjects.length; i++)
		{
			checkBoxList[i] = new JCheckBox(allProjects[i]);
			mainPanel.add(checkBoxList[i]);
			if (DatabaseManager.getInstance().isProjectAvailableToUser(userName, allProjects[i]))
			{
				checkBoxList[i].setSelected(true);
			}
			else
			{
				checkBoxList[i].setSelected(false);
			}
		}

		setButton = new JButton("Set");
		mainPanel.add(setButton);
		this.setTitle("Available Projects for " + userName);
		this.pack();
	}

	public JButton getSetButton()
	{
		return setButton;
	}

	public JCheckBox[] getAllCheckBoxes()
	{
		return checkBoxList;
	}

}
