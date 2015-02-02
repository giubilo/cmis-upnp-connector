package org.allegria.upnp.av.mediaserver;

import org.allegria.upnp.*;

public class MediaServer implements Device
{

	private Device deviceImpl;
   
    public MediaServer(Device device) {
    	this.deviceImpl = device;
    	
    	if (deviceImpl instanceof RootDevice) {
    		
    		
    	}

    }
	
    @Override
    public void startDevice() throws Exception {
        
    	this.deviceImpl.startDevice();
    	
    }
    	 
	@Override
	public void stopDevice() throws Exception {
		deviceImpl.stopDevice();
		
	}
 
    public void destroy() {
    	
    }
	
}
