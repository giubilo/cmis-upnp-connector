package org.allegria.upnp.net;

public abstract class AbstractNetService implements NetService {

	public final static String getServerName()
	{
		String osName = System.getProperty("os.name");
		String osVer = System.getProperty("os.version");
		return osName + "/"  + osVer + " UPnP/1.0 ";  //  + NAME + "/" + VERSION;
	}
	
	
}
