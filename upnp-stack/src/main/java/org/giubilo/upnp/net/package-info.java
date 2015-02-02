/**
 * The The UPnP Device Architecture (UDA) enables network connectivity with the 
 * following steps;
 * (Step 0) addressing - where devices get a network address. 
 * (Step 1) discovery - where control points find interesting device(s).  
 * (Step 2) description - where control points learn about device capabilities, 
 * (Step 3) control - where a control point sends commands to device(s), 
 * (Step 4) eventing - where control points listen to state changes in device(s), and 
 * (Step 5) presentation - where control points display a user interface for device(s).  
 * 
 * Each step in the UDA defines its own protocol stack for describing device 
 * protocols, messages and XML-based device schemas for the purpose of enabling 
 * communication between controllers (control points), and devices.  
 * 
 * This package uses <a href="http://netty.io">Netty</a>Netty as its non-blocking IO (NIO)
 * client - server framework.  Netty encapsulates the TCP and UDP socket network programming
 * and uses its bootstrap, channels, channel pipeline and handlers to build a stack as a 
 * service for each step in the UDA.  
 */
package org.allegria.upnp.net;