/**
 * Presentation is Step 5 in UPnPTM networking. 
 * 
 * If a device has a URL for presentation, then the control point can retrieve a page 
 * from this URL, load the page into a browser and, depending on the capabilities of 
 * the page, allow a user to control the device and/or view device status. The degree 
 * to which each of these can be accomplished depends on the specific capabilities of 
 * the presentation page and device.
 * 
 * Device / service operations are passed via a basic HTTP-GET/RESP exchange. 
 * 
 */
package org.allegria.upnp.net.presentation;