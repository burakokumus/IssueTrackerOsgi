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
		commentsView.getAddImageButton().addActionListener(e -> addImage());
	}

	private void addComment()
	{
		String issueTitle = DatabaseManager.getInstance().getIssueNameById(issueID);
		@SuppressWarnings("unused")
		AddCommentDialogController addCommentDialogController = new AddCommentDialogController(issueTitle,
				currentUserName, this);
	}

	private void addImage()
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
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		byte[] imageInByte = byteArrayOutputStream.toByteArray();
		byte[] encode = Base64.getEncoder().encode(imageInByte);
		String temp = new String(encode);
		int userId = DatabaseManager.getInstance().getUserIdByName(currentUserName);
		DatabaseManager.getInstance().saveImage(temp, issueID, userId);
		/*
		 * byte[] decode = Base64.getDecoder().decode(encode); ByteArrayInputStream
		 * biInputStream = new ByteArrayInputStream(decode); BufferedImage read = null;
		 * try { read = ImageIO.read(biInputStream); } catch (IOException e1) {
		 * e1.printStackTrace(); } ImageIcon imageIcon2 = new ImageIcon(read); JButton
		 * jbnPopup = new JButton(); jbnPopup.setIcon(imageIcon2);
		 */
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
