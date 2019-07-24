package com.ekinoks.view;

import java.awt.BorderLayout;

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

	public CommentsView()
	{
		model = new DefaultTableModel(new String[]
		{ "Comment ID", "Comment", "User", "Date" }, 0);
		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.pack();

	}

	public void addRowToTable(Comment comment)
	{
		model.addRow(new String[]
		{ Integer.toString(comment.getCommentID()), comment.getComment(), comment.getUserName(), comment.getDate() });
	}

}
