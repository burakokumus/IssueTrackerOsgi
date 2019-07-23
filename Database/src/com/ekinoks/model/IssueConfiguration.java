package com.ekinoks.model;

import java.util.ArrayList;
import java.util.List;

import com.ekinoks.ui.components.common.fieldGenerator.FieldConfiguration;
import com.ekinoks.ui.components.table.TableColumnPreference;
import com.ekinokssoftware.tropic.zemin.configuration.AbstractXMLConfiguration;
import com.ekinokssoftware.tropic.zemin.configuration.Config;
import com.ekinokssoftware.tropic.zemin.configuration.ConfigurationList;

public class IssueConfiguration extends AbstractXMLConfiguration
{

	@Config("sutun_konfigurasyonlari")
	@ConfigurationList(type = ArrayList.class, valueType = TableColumnPreference.class)
	public List<FieldConfiguration> columnPreferences = null;

	public IssueConfiguration()
	{
		create();
	}

	@Override
	protected String getPath()
	{
		return "C:\\Users\\burak\\Desktop\\sefa.xml";
	}

}
