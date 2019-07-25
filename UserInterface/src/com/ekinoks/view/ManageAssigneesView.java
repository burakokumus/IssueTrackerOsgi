package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.ekinoks.database.DatabaseManager;

@SuppressWarnings("serial")
public class ManageAssigneesView extends JDialog
{
	private JButton removeAssigneeButton;
	private JButton inviteUserButton;
	private JComboBox<String> removeAssigneeComboBox;
	private JComboBox<String> inviteUserComboBox;
	private String issueTitle;
	private DefaultComboBoxModel<String> removeAssigneeModel;
	private DefaultComboBoxModel<String> inviteUserModel;
	public Vector<String> removeAssigneeVector;
	public Vector<String> inviteUserVector;

	public ManageAssigneesView(String issueTitle)
	{
		this.issueTitle = issueTitle;
		removeAssigneeVector = new Vector<>();
		inviteUserVector = new Vector<>();
		initialize();
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		ArrayList<String> assignees = DatabaseManager.getInstance().getAssigneesByIssueTitle(issueTitle);
		for (String as : assignees)
			removeAssigneeVector.add(as);
		removeAssigneeModel = new DefaultComboBoxModel<>(removeAssigneeVector);
		removeAssigneeComboBox = new JComboBox<>(removeAssigneeModel);
		GridBagConstraints gbc_removeAssigneeComboBox = new GridBagConstraints();
		gbc_removeAssigneeComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_removeAssigneeComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_removeAssigneeComboBox.gridx = 0;
		gbc_removeAssigneeComboBox.gridy = 0;
		getContentPane().add(removeAssigneeComboBox, gbc_removeAssigneeComboBox);

		removeAssigneeButton = new JButton("Remove");
		GridBagConstraints gbc_removeAssigneeButton = new GridBagConstraints();
		gbc_removeAssigneeButton.anchor = GridBagConstraints.EAST;
		gbc_removeAssigneeButton.insets = new Insets(0, 0, 5, 0);
		gbc_removeAssigneeButton.gridx = 1;
		gbc_removeAssigneeButton.gridy = 0;
		getContentPane().add(removeAssigneeButton, gbc_removeAssigneeButton);

		inviteUserVector = DatabaseManager.getInstance().getPossibleAssignees(issueTitle);
		inviteUserModel = new DefaultComboBoxModel<>(inviteUserVector);
		inviteUserComboBox = new JComboBox<>(inviteUserModel);
		GridBagConstraints gbc_inviteUserComboBox = new GridBagConstraints();
		gbc_inviteUserComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_inviteUserComboBox.anchor = GridBagConstraints.WEST;
		gbc_inviteUserComboBox.gridx = 0;
		gbc_inviteUserComboBox.gridy = 1;
		getContentPane().add(inviteUserComboBox, gbc_inviteUserComboBox);

		inviteUserButton = new JButton("Invite");
		GridBagConstraints gbc_inviteUserButton = new GridBagConstraints();
		gbc_inviteUserButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_inviteUserButton.gridx = 1;
		gbc_inviteUserButton.gridy = 1;
		getContentPane().add(inviteUserButton, gbc_inviteUserButton);

		this.pack();
	}

	public JButton getRemoveAssigneeButton()
	{
		return removeAssigneeButton;
	}

	public JButton getInviteUserButton()
	{
		return inviteUserButton;
	}

	public String getRemoveAssigneeName()
	{
		return removeAssigneeComboBox.getSelectedItem().toString();
	}

	public String getInviteUserName()
	{
		return inviteUserComboBox.getSelectedItem().toString();
	}

	public void setRemovableAssignees()
	{
		removeAssigneeModel.removeAllElements();
		for (String assignee : removeAssigneeVector)
		{
			removeAssigneeModel.addElement(assignee);
		}
	}

	public void setInvitableUsers()
	{
		inviteUserModel.removeAllElements();
		for (String user : inviteUserVector)
		{
			inviteUserModel.addElement(user);
		}
	}
}
