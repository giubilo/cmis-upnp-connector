/**
 * Control is Step 3 in UPnPTM networking.
 * 
 * Given knowledge of a device and its services, a control point can ask those 
 * services to invoke actions and receive responses indicating the result of 
 * the action or errors. Invoking actions is a kind of remote procedure call.  
 * 
 * Control messages are sent inside Simple Object Access Protocol, 
 * (<a href="http://en.wikipedia.org/wiki/SOAP">SOAP</a>) envelopes
 * over HTTP.
 * 
 */
package org.allegria.upnp.net.control;