/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author joestr
 */
public class TestApi {

	private Api acronisApi = null;
	
	@BeforeAll
	public void test1() {
		try {
			this.acronisApi = new Api(
				new URI("https://dev-cloud.acronis.com/fc/api/1"),
				new URI("https://dev-cloud.acronis.com/api/2/idp"),
				System.getProperty("at.or.joestr.acronisfss.api.username"),
				System.getProperty("at.or.joestr.acronisfss.api.password")
			);
		} catch (URISyntaxException ex) {
			Assertions.assertNotEquals(null, this.acronisApi);
		}
	}
	
	@Test
	public void test2() {
		this.acronisApi.authorize();
		Assertions.assertNotEquals(null, this.acronisApi.getToken());
	}
	
	@Test
	public void test3() {
		// Api#reauthorize() does not work properly
		//this.acronisApi.reauthorize();
		Assertions.assertNotEquals(null, this.acronisApi.getToken());
	}
}
