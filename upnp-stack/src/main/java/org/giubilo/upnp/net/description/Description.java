package org.allegria.upnp.net.description;

public interface Description {

	String GetValue(String nodeKey);
	
	// TODO implement a thread safe implementation of SetValue
	
	void SetValue(String nodeKey, String nodeValue);
	
}
