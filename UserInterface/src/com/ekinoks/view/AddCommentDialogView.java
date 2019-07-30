package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AddCommentDialogView extends JDialog
{
	private JTextArea commentTextArea;
	private JButton addButton;
	private JLabel issueTitleLabel;

	public AddCommentDialogView(String issueTitle, CommentsView commentsView)
	{
		super(commentsView);
		initialize();
		this.issueTitleLabel.setText(issueTitle);
	}

	private void initialize()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		issueTitleLabel = new JLabel("Issue Title");
		GridBagConstraints gbc_issueTitleLabel = new GridBagConstraints();
		gbc_issueTitleLabel.insets = new Insets(0, 0, 5, 0);
		gbc_issueTitleLabel.gridx = 0;
		gbc_issueTitleLabel.gridy = 0;
		getContentPane().add(issueTitleLabel, gbc_issueTitleLabel);

		commentTextArea = new JTextArea();
		GridBagConstraints gbc_commentTextArea = new GridBagConstraints();
		gbc_commentTextArea.insets = new Insets(0, 0, 5, 0);
		gbc_commentTextArea.fill = GridBagConstraints.BOTH;
		gbc_commentTextArea.gridx = 0;
		gbc_commentTextArea.gridy = 1;
		getContentPane().add(commentTextArea, gbc_commentTextArea);

		addButton = new JButton("Add");
		GridBagConstraints gbc_addButton = new GridBagConstraints();
		gbc_addButton.gridx = 0;
		gbc_addButton.gridy = 2;
		getContentPane().add(addButton, gbc_addButton);

		this.pack();
	}

	public JButton getAddButton()
	{
		return addButton;
	}

	public String getComment()
	{
		return commentTextArea.getText();
	}

}
