/**
 * Eventing is Step 4 in UPnPTM networking. 
 * 
 * A UPnP service description includes a list of actions the a service responds 
 * to and a list of variables that model the state of the service at run time. 
 * If one or more of these state variables are evented, then the service publishes 
 * updates expressed in XML when these variables change, and a control point MAY 
 * subscribe to receive these event messages.
 * 
 * Subscription and uni-cast event messages are passed using the General Event 
 * Notification Architecture (<a href="http://en.wikipedia.org/wiki/GENA">GENA</a> 
 * an extension based on the HTTP codec).  Multi-cast event messages may also be 
 * passed using an HTTP like codec over UDP.
 *    
 */
package org.allegria.upnp.net.eventing;