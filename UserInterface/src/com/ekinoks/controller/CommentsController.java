package com.ekinoks.controller;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JTable;

import com.ekinoks.database.DatabaseManager;
import com.ekinoks.model.Comment;
import com.ekinoks.view.CommentsView;

public class CommentsController
{
	private CommentsView commentsView;
	private int issueID;
	private String currentUserName;

	public CommentsController(int id, String currentUserName)
	{
		this.issueID = id;
		this.currentUserName = currentUserName;
		this.commentsView = new CommentsView();
		this.addAllCommentsToJTable();
		this.commentsView.setVisible(true);
		this.initController();
	}

	private void initController()
	{
		commentsView.getAddCommentButton().addActionListener(e -> addComment());
		commentsView.getAddImageButton().addActionListener(e ->
		{
			JTable table = commentsView.getTable();
			int selectedRow = table.getSelectedRow();
			Object valueAt = table.getValueAt(selectedRow, table.convertColumnIndexToModel(0));

			addImage(Integer.valueOf((String) valueAt));
		});
	}

	private void addComment()
	{
		String issueTitle = DatabaseManager.getInstance().getIssueNameById(issueID);
		@SuppressWarnings("unused")
		AddCommentDialogController addCommentDialogController = new AddCommentDialogController(issueTitle,
				currentUserName, this);
	}

	private void addImage(int commentId)
	{
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		File selectedFile = fileChooser.getSelectedFile();
		ImageIcon imageIcon = new ImageIcon(selectedFile.getPath());
		BufferedImage bImage = new BufferedImage(imageIcon.getIconWidth(), imageIcon.getIconHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bImage.createGraphics();
		// Paint the icon on to the buffered image
		imageIcon.paintIcon(null, g, 0, 0);
		g.dispose();
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try
		{
			ImageIO.write(bImage, "jpg", byteArrayOutputStream);
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		byte[] imageInByte = byteArrayOutputStream.toByteArray();
		byte[] encode = Base64.getEncoder().encode(imageInByte);
		String temp = new String(encode);
		DatabaseManager.getInstance().addImageToComment(commentId, temp);
	}

	public void addAllCommentsToJTable()
	{
		ArrayList<Comment> comments = DatabaseManager.getInstance().getAllCommentsOfIssue(issueID);
		for (Comment com : comments)
		{
			this.commentsView.addRowToTable(com);
		}
	}

	public void clearJTable()
	{
		commentsView.clearJTable();
	}
}
