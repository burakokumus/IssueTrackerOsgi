package com.ekinoks.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.ekinoks.model.Comment;

@SuppressWarnings("serial")
public class CommentsView extends JDialog
{
	private JTable table;
	private DefaultTableModel model;
	private JButton newCommentButton;

	public CommentsView()
	{
		initialize();

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

		model = new DefaultTableModel(new String[]
		{ "Comment ID", "Comment", "User", "Date" }, 0);
		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		newCommentButton = new JButton("New Comment");
		GridBagConstraints gbc_newCommentButton = new GridBagConstraints();
		gbc_newCommentButton.gridx = 0;
		gbc_newCommentButton.gridy = 2;
		getContentPane().add(newCommentButton, gbc_newCommentButton);

		this.pack();
	}

	public void addRowToTable(Comment comment)
	{
		model.addRow(new String[]
		{ Integer.toString(comment.getCommentID()), comment.getComment(), comment.getUserName(), comment.getDate() });
	}

	public JButton getNewCommentButton()
	{
		return newCommentButton;
	}

	public void clearJTable()
	{
		int numOfRows = model.getRowCount();
		for (int i = numOfRows - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
	}

}
