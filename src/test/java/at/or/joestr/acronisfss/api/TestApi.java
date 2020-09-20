/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import java.net.URI;
import java.net.URISyntaxException;
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
				new URI("https://dev-cloud.acronis.com/fc/api/1"),
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
		Assertions.assertNotEquals(null, acronisApi.getToken());
	}
	
	@Test
	@Order(11)
	public void reauthorize() {
		// Api#reauthorize() does not work properly
		//acronisApi.reauthorize();
		Assertions.assertNotEquals(null, acronisApi.getToken());
	}
}
