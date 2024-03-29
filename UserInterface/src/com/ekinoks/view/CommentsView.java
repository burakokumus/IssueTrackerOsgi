package com.ekinoks.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.ekinoks.model.Comment;

@SuppressWarnings("serial")
public class CommentsView extends JDialog
{
	private JTable table;
	private DefaultTableModel model;
	private JPanel buttonPanel;
	private JButton addCommentButton;
	private JButton addImageButton;

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
		{ 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		model = new DefaultTableModel(new String[]
		{ "Comment ID", "Comment", "Image", "User", "Date" }, 0);
		table = new JTable(model)
		{
			@Override
			public TableCellRenderer getCellRenderer(int row, int column)
			{
				if (table.convertColumnIndexToModel(column) != 2)
				{
					return super.getCellRenderer(row, column);
				}
				else
				{
					return new ImageCellRenderer();
				}

			}

			@Override
			public TableCellEditor getCellEditor(int row, int column)
			{
				return new ImageCellEditor();
			}

		};

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(800, 600));
		scrollPane.setMinimumSize(new Dimension(800, 600));

		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);

		buttonPanel = new JPanel();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 3;
		getContentPane().add(buttonPanel, gbc_buttonPanel);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		addImageButton = new JButton("Add Image");
		buttonPanel.add(addImageButton);

		addCommentButton = new JButton("Add Comment");
		buttonPanel.add(addCommentButton);
		this.setModal(true);
		this.setLocationRelativeTo(this.getParent());
		this.pack();
	}

	public void addRowToTable(Comment comment)
	{
		ImageIcon first = comment.getImage();
		model.addRow(new Object[]
		{ Integer.toString(comment.getCommentID()), comment.getComment(), first, comment.getUserName(),
				comment.getDate() });
	}

	public JButton getAddCommentButton()
	{
		return addCommentButton;
	}

	public JButton getAddImageButton()
	{
		return addImageButton;
	}

	public void clearJTable()
	{
		int numOfRows = model.getRowCount();
		for (int i = numOfRows - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
	}

	public JTable getTable()
	{
		return table;
	}

}

class ImageCellRenderer implements TableCellRenderer
{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column)
	{
		JLabel label = new JLabel();
		if (value instanceof ImageIcon)
		{
			ImageIcon image = (ImageIcon) value;
			Image scaledImage = getScaledImage(image.getImage(), 80, 40);
			label.setIcon(new ImageIcon(scaledImage));
		}
		return label;
	}

	private Image getScaledImage(Image srcImg, int w, int h)
	{
		BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, w, h, null);
		g2.dispose();

		return resizedImg;
	}
}

@SuppressWarnings("serial")
class ImageCellEditor extends AbstractCellEditor implements TableCellEditor
{

	public Object getCellEditorValue()
	{
		return null;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (value == null)
		{

			return null;
		}
// TODO firetabledatachange
		if (table.convertColumnIndexToModel(column) == 2)
		{
			JDialog jDialog = new JDialog(SwingUtilities.getWindowAncestor(table));
			jDialog.setModal(true);
			jDialog.setLocationRelativeTo(jDialog.getParent());
			JLabel jLabel = new JLabel();
			jDialog.add(jLabel);
			jLabel.setIcon((ImageIcon) value);
			jDialog.pack();
			jDialog.setVisible(true);
		}
		return null;
	}

	@Override
	public boolean isCellEditable(EventObject e)
	{
		return true;
	}

}
