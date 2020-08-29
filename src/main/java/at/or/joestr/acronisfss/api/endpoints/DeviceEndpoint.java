/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.endpoints;

import at.or.joestr.acronisfss.api.structures.Device;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author joestr
 */
public class DeviceEndpoint {

	public DeviceEndpoint() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Device> getDevices(int perPage, int page, UUID tenantId) {
		ArrayList<Device> result = null;
		
		return null;
	}
	
	public static Device getDeviceInformation(UUID deviceUuuid, UUID tenantId) {
		Device result = null;
		
		return result;
	}
	
	public static void updateDevice(UUID deviceUuid, Device device) {
		
		return;
	}
	
	public static void deleteDevice(UUID deviceUuid, Device device) {
		
		return;
	}
}
