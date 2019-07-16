package com.ekinoks.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class VerifiedDoneView extends JDialog
{

	private JSpinner daySpinner;
	private JSpinner hourSpinner;
	private JButton confirmButton;

	public VerifiedDoneView()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[]
		{ 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[]
		{ 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel timeSpentLabel = new JLabel("Time Spent:");
		GridBagConstraints gbc_timeSpentLabel = new GridBagConstraints();
		gbc_timeSpentLabel.insets = new Insets(0, 5, 5, 5);
		gbc_timeSpentLabel.gridx = 6;
		gbc_timeSpentLabel.gridy = 2;
		getContentPane().add(timeSpentLabel, gbc_timeSpentLabel);

		daySpinner = new JSpinner();
		daySpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_daySpinner = new GridBagConstraints();
		gbc_daySpinner.anchor = GridBagConstraints.EAST;
		gbc_daySpinner.insets = new Insets(0, 5, 5, 5);
		gbc_daySpinner.gridx = 4;
		gbc_daySpinner.gridy = 3;

		JComponent field = ((JSpinner.DefaultEditor) daySpinner.getEditor());
		Dimension prefSize = field.getPreferredSize();
		prefSize = new Dimension(200, prefSize.height);
		field.setPreferredSize(prefSize);

		getContentPane().add(daySpinner, gbc_daySpinner);

		JLabel dayLabel = new JLabel("days");
		GridBagConstraints gbc_dayLabel = new GridBagConstraints();
		gbc_dayLabel.insets = new Insets(0, 5, 5, 5);
		gbc_dayLabel.gridx = 5;
		gbc_dayLabel.gridy = 3;
		getContentPane().add(dayLabel, gbc_dayLabel);

		hourSpinner = new JSpinner();
		hourSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_hourSpinner = new GridBagConstraints();
		gbc_hourSpinner.anchor = GridBagConstraints.EAST;
		gbc_hourSpinner.insets = new Insets(0, 5, 5, 5);
		gbc_hourSpinner.gridx = 7;
		gbc_hourSpinner.gridy = 3;
		getContentPane().add(hourSpinner, gbc_hourSpinner);

		JLabel hourLabel = new JLabel("hours");
		GridBagConstraints gbc_hourLabel = new GridBagConstraints();
		gbc_hourLabel.gridwidth = 2;
		gbc_hourLabel.anchor = GridBagConstraints.EAST;
		gbc_hourLabel.insets = new Insets(0, 5, 5, 5);
		gbc_hourLabel.gridx = 8;
		gbc_hourLabel.gridy = 3;
		getContentPane().add(hourLabel, gbc_hourLabel);

		confirmButton = new JButton("Confirm");
		GridBagConstraints gbc_confirmButton = new GridBagConstraints();
		gbc_confirmButton.insets = new Insets(0, 0, 0, 5);
		gbc_confirmButton.gridx = 6;
		gbc_confirmButton.gridy = 4;
		getContentPane().add(confirmButton, gbc_confirmButton);
	}

	public int getDays()
	{
		return (Integer) daySpinner.getValue();
	}

	public int getHours()
	{
		return (Integer) hourSpinner.getValue();
	}

	public JButton getConfirmButton()
	{
		return confirmButton;
	}

}
