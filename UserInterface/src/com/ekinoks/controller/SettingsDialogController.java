package com.ekinoks.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.ekinoks.view.MainView;
import com.ekinoks.view.SettingsDialogView;

public class SettingsDialogController
{
	private String storeURL = "C:\\Users\\burak\\Desktop\\config.properties";
	private SettingsDialogView settingsDialogView;
	private MainView mainView;

	public SettingsDialogController(MainView view)
	{
		this.mainView = view;
		this.settingsDialogView = new SettingsDialogView();
		this.settingsDialogView.setVisible(true);
		initController();
	}

	private void initController()
	{
		this.settingsDialogView.getApplyButton().addActionListener(e -> apply());
	}

	private void apply()
	{
		Properties properties = new Properties();
		try
		{

			properties.setProperty("projectName", Boolean.toString(this.settingsDialogView.isProjectNameSelected()));
			properties.setProperty("id", Boolean.toString(this.settingsDialogView.isIssueIdSelected()));
			properties.setProperty("title", Boolean.toString(this.settingsDialogView.isTitleSelected()));
			properties.setProperty("type", Boolean.toString(this.settingsDialogView.isTypeSelected()));
			properties.setProperty("priority", Boolean.toString(this.settingsDialogView.isProritySelected()));
			properties.setProperty("author", Boolean.toString(this.settingsDialogView.isAuthorSelected()));
			properties.setProperty("description", Boolean.toString(this.settingsDialogView.isDescriptionSelected()));
			properties.setProperty("state", Boolean.toString(this.settingsDialogView.isStateSelected()));

			properties.store(new FileOutputStream(storeURL), null);

			mainView.setColumnView(properties);

		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}

		settingsDialogView.dispose();
	}
}
