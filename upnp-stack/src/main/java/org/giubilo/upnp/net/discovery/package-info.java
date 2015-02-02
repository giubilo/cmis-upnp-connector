/**
 * Discovery is Step 1 in UPnPTM networking. 
 * 
 * When a device is added to the network, the UPnP discovery protocol 
 * (<a href="http://en.wikipedia.org/wiki/Simple_Service_Discovery_Protocol">SSDP</a> an
 * extension based on the HTTP codec) allows that device to advertise its services to 
 * control points on the network. 
 * 
 * Similarly, when a control point is added to the network, the UPnP 
 * discovery protocol allows that control point to search for devices of interest 
 * on the network. The fundamental exchange in both cases is a discovery message
 * that mimics the structure of HTTP messages and are deliver over uni/multi-cast
 * User Datagram Protocol (UDP).  
 * 
 */
package org.allegria.upnp.net.discovery;