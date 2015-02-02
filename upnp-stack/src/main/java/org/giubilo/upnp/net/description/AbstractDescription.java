package org.allegria.upnp.net.description;

import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.tree.xpath.XPathExpressionEngine;

public abstract class AbstractDescription implements Description {

	protected XMLConfiguration config;

	/*
	 * TODO org.apache.commons.configuration.XMLConfiguration
	 * IS NOT THREADSAFE
	 */	
	
	public AbstractDescription(String configFile) {
		
		try {
			config = new XMLConfiguration(configFile);
	        config.setExpressionEngine(new XPathExpressionEngine());
	        
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String GetValue(String nodeKey) {
		return config.getString(nodeKey);
	}

	@Override
	public void SetValue(String nodeKey, String nodeValue) {
		config.setProperty(nodeKey, nodeValue);
		try {
			config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
