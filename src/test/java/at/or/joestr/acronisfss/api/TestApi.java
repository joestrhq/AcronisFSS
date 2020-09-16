/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author joestr
 */
public class TestApi {

	Api acronisApi;
	
	@Before
	public void init() throws URISyntaxException {
		this.acronisApi = new Api(
			new URI("https://dev-cloud.acronis.com/fc/api/1"),
			new URI("https://dev-cloud.acronis.com/api/2/idp"),
			"username",
			"password"
		);
	}
	
	@Test
	public void authorize() {
		this.acronisApi.authorize();
		
		Assert.assertNotEquals(null, this.acronisApi.getToken());
	}
}
