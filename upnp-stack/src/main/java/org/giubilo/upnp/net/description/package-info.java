/**
 * Description is Step 2 in UPnPTM networking.
 *  
 * The UPnP description for a device is partitioned into two logical parts: 
 * - a device description describing the physical and logical (embedded) containers, and 
 * - service descriptions describing the capabilities exposed by the device.
 *  
 *  A control point retrieves vendor specific XML description files that are based on 
 *  UPnP Forum Schema using an HTTP-GET/RESP exchange.  
 */
package org.allegria.upnp.net.description;