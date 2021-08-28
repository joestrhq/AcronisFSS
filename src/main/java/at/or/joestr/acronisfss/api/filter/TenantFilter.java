/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.filter;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author joestr
 */
public class TenantFilter {
  
  private String tenantId = null;

  public TenantFilter() {
    super();
  }

  public TenantFilter tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public List<NameValuePair> build() {

    ArrayList<NameValuePair> result = new ArrayList<>();
    
    if (this.tenantId != null) {
      result.add(new BasicNameValuePair("tenant_id", this.tenantId));
    }

    return result;
  }
}
