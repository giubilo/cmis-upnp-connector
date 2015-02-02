package org.allegria.upnp.av.mediaserver.contentdirectory;

import org.allegria.upnp.Service;

/*
 * TODO - will need to think about is need to be able to tailor
 * the xml device-service nodes for each type of ContentDirectory
 * as capabilities might be different.
 */

public interface ContentDirectory extends Service {

	void GetSearchCapabilities();
	
	void GetSortCapabilities();
	
	void GetSortExtensionCapabilities();
	
	void GetFeatureList();
	
	void GetSystemUpdateID();
	
	void GetServiceResetToken();
	
	void Browse();

}
