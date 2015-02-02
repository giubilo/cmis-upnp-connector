package org.allegria.upnp;

import java.util.HashSet;
import java.util.Set;

public interface Service {

	void startService() throws Exception;
	
	void stopService() throws Exception;
	
	/*
	 * TODO - should the ServiceStateTable be split out as a new class so
	 * that can change the implementation later to anything.
	 */
	
	Set<StateVariable> serviceStateTable  = new HashSet<StateVariable>();

	void SetStateVariable(String variableKey, Object variableValue);
	
	StateVariable GetStateVariable(String variableKey);
	
	
	
	
		
}
