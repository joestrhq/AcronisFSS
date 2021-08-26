/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import at.or.joestr.acronisfss.api.filter.AuditLogEntriesListFilter;
import at.or.joestr.acronisfss.api.filter.DeviceListFilter;
import at.or.joestr.acronisfss.api.structures.AuditLogEntry;
import at.or.joestr.acronisfss.api.structures.Device;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 *
 * @author joestr
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestApi {

	private static Api acronisApi = null;
	
	@Test
	@Order(0)
	public void init() {
		try {
			acronisApi = new Api(
				new URI("https://dev-cloud.acronis.com/fc/api/v1"),
				new URI("https://dev-cloud.acronis.com/api/2/idp"),
				System.getProperty("at.or.joestr.acronisfss.api.username"),
				System.getProperty("at.or.joestr.acronisfss.api.password")
			);
		} catch (URISyntaxException ex) {
			
		}
		
		Assertions.assertNotEquals(null, acronisApi);
	}
	
	@Test
	@Order(10)
	public void authorize() {
		acronisApi.authorize();
		Assertions.assertNotEquals(null, acronisApi.getToken(), "is token not null");
	}
	
	@Test
	@Order(11)
	public void isTokenABearerToken() {
		Assertions.assertEquals("bearer", acronisApi.getToken().getTokenType(), "is token a bearer token");
	}
	
	@Test
	@Order(12)
	public void getAuditLog() {
		ArrayList<AuditLogEntry> entries = (ArrayList) acronisApi.getAuditLog(new AuditLogEntriesListFilter());
		
		Assertions.assertNotEquals(null, entries.get(0), "is audit log not empty");
	}
  
  @Test
  @Order(13)
  public void getAuditLogWithFilter() {
    ArrayList<AuditLogEntry> entries = (ArrayList) acronisApi.getAuditLog(new AuditLogEntriesListFilter()
        .filterText("this string is not in the log yet")
    );
    
    Assertions.assertEquals(0, entries.size(), "is audit log empty");
  }
  
  @Test
  @Order(14)
  public void getDevicesList() {
    ArrayList<Device> entries = (ArrayList) acronisApi.getDevices(new DeviceListFilter());
    
    Assertions.assertEquals(0, entries.size(), "is device list empty");
  }
	
	@Test
	@Order(90)
	public void reauthorize() {
		// Api#reauthorize() does not work properly
		//acronisApi.reauthorize();
		Assertions.assertNotEquals(null, acronisApi.getToken(), "is token not null");
	}
}
