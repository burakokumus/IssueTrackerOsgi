package com.ekinoks.database;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager
{
	private static LogManager logManager = new LogManager();
	private final String PATH = "C:/Users/burak/Desktop/MyLogFile.log";
	
	Logger logger = Logger.getLogger("MyLog");
	FileHandler fh;

	public static LogManager getInstance()
	{
		return logManager;
	}

	private LogManager()
	{
	}

	public void log(String logInput)
	{
		try
		{
			fh = new FileHandler(PATH, true);
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);

			logger.info(logInput);

		}
		catch (SecurityException e)
		{
			System.err.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.err.println(e.getMessage());
		}
		fh.close();
	}

}
