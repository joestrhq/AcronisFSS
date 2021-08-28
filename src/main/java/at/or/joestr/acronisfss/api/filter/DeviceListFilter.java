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
public class DeviceListFilter {

  private Integer perPage = null;
  private Integer page = null;
  private String tenantId = null;

  public DeviceListFilter() {
    super();
  }

  public DeviceListFilter perPage(Integer perPage) {
    this.perPage = perPage;
    return this;
  }

  public DeviceListFilter page(Integer page) {
    this.page = page;
    return this;
  }

  public DeviceListFilter tenantId(String tenantId) {
    this.tenantId = tenantId;
    return this;
  }

  public List<NameValuePair> build() {

    ArrayList<NameValuePair> result = new ArrayList<>();

    if (this.perPage != null) {
      result.add(new BasicNameValuePair("per_page", String.valueOf(this.perPage)));
    }
    if (this.page != null) {
      result.add(new BasicNameValuePair("page", String.valueOf(this.page)));
    }
    if (this.tenantId != null) {
      result.add(new BasicNameValuePair("tenant_id", this.tenantId));
    }

    return result;
  }
}
