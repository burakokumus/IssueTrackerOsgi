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
public class PendingInvitationsView extends JDialog
{
	private String currentUserName;
	private JButton acceptButton;
	private JButton rejectButton;
	private JComboBox<String> invitationsComboBox;
	private DefaultComboBoxModel<String> invitationsComboBoxModel;
	public Vector<String> invitationsVector;

	public PendingInvitationsView(String currentUserName)
	{
		this.currentUserName = currentUserName;
		invitationsVector = new Vector<>();
		initialize();
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		ArrayList<String> temp = DatabaseManager.getInstance().getAllInvitationsForUser(currentUserName);
		for (String issue : temp)
			invitationsVector.add(issue);

		invitationsComboBoxModel = new DefaultComboBoxModel<>(invitationsVector);
		invitationsComboBox = new JComboBox<>(invitationsComboBoxModel);
		GridBagConstraints gbc_invitationsComboBox = new GridBagConstraints();
		gbc_invitationsComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_invitationsComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_invitationsComboBox.gridx = 0;
		gbc_invitationsComboBox.gridy = 0;
		getContentPane().add(invitationsComboBox, gbc_invitationsComboBox);

		acceptButton = new JButton("Accept");
		GridBagConstraints gbc_acceptButton = new GridBagConstraints();
		gbc_acceptButton.insets = new Insets(0, 0, 0, 5);
		gbc_acceptButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_acceptButton.gridx = 1;
		gbc_acceptButton.gridy = 0;
		getContentPane().add(acceptButton, gbc_acceptButton);

		rejectButton = new JButton("Reject");
		GridBagConstraints gbc_rejectButton = new GridBagConstraints();
		gbc_rejectButton.gridx = 2;
		gbc_rejectButton.gridy = 0;
		getContentPane().add(rejectButton, gbc_rejectButton);

		this.pack();

	}

	public JButton getAcceptButton()
	{
		return acceptButton;
	}

	public JButton getRejectButton()
	{
		return rejectButton;
	}

	public String getSelectedIssue()
	{
		return invitationsComboBox.getSelectedItem().toString();
	}

	public void setPendingInvitations()
	{
		invitationsComboBoxModel.removeAllElements();
		for (String issue : invitationsVector)
		{
			invitationsComboBoxModel.addElement(issue);
		}
	}
}
