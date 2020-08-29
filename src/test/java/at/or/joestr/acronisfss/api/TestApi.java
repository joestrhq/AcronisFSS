/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api;

import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author joestr
 */
public class TestApi {
	
	@Test
	public void test1() throws URISyntaxException {
		Api acronisApi = new Api(
			new URI("https://dev-cloud.acronis.com/fc/api/1"),
			new URI("https://dev-cloud.acronis.com/api/2/idp")
		);
		
		Assert.assertEquals(acronisApi.getAuditLog(), null);
	}
}
