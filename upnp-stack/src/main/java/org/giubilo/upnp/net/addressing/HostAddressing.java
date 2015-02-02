package org.allegria.upnp.net.addressing;

/*
 * pass in an interface name or an ip address
 * check for if ipv4 or ipv4 + ipv6
 * check if interface / address exists and is active
 * if don't pass in then get all active with at least ipv4
 * TODO work out if possible to receive request on the
 * loopback address e.g. local control point and if can
 * might be optimal to have some method to use it like the 
 * original c4j does.
 * 
 * TODO work out what security controls over the sockets
 * e.g. handling of proxies needs to be implemented including
 * test suite
 * 
 * just use one ipv4 interface and address to begin with
 * TODO use multiple interfaces and addresses including ipv6
 * TODO handle passing in a nic name as an argument
 * TODO handle addition and deletion of interfaces and addresses
 * 
 */


public class HostAddressing {

	public static boolean USE_LOOPBACK_ADDR = false;
	public static boolean USE_ONLY_IPV4_ADDR = false;
	
	
}
