/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.filter;

import at.or.joestr.acronisfss.api.structures.Severity;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author joestr
 */
public class AuditLogFilter {
	
	private String filterUser = null;
	private String filterShare = null;
	private String filterNodeUuid = null;
	private Severity filterSeverty = null;
	private String filterText = null;
	private LocalDateTime filterFrom = null;
	private LocalDateTime filterTo = null;
	private Integer perPage = null;
	private Integer page = null;
	private String tenantId = null;

	public AuditLogFilter() {
		super();
	}
	
	public AuditLogFilter filterUser(String user) {
		this.filterUser = user;
		return this;
	}
	
	public AuditLogFilter filterShare(String share) {
		this.filterShare = share;
		return this;
	}
	
	public AuditLogFilter filterNodeUuid(String nodeUuid) {
		this.filterNodeUuid = nodeUuid;
		return this;
	}
	
	public AuditLogFilter filterSeverty(Severity severty) {
		this.filterSeverty = severty;
		return this;
	}
	
	public AuditLogFilter filterText(String text) {
		this.filterText = text;
		return this;
	}
	
	public AuditLogFilter filterFrom(LocalDateTime from) {
		this.filterFrom = from;
		return this;
	}
	
	public AuditLogFilter filterTo(LocalDateTime to) {
		this.filterTo = to;
		return this;
	}
	
	public AuditLogFilter perPage(Integer perPage) {
		this.perPage = perPage;
		return this;
	}
	
	public AuditLogFilter page(Integer page) {
		this.page = page;
		return this;
	}
	
	public AuditLogFilter tenantId(String tenantId) {
		this.tenantId = tenantId;
		return this;
	}
	
	public List<NameValuePair> build() {
		
		ArrayList<NameValuePair> result = new ArrayList<>() ;
		
		if (this.filterUser != null)
			result.add(new BasicNameValuePair("filter_user", this.filterUser));
		if (this.filterShare != null)
			result.add(new BasicNameValuePair("filter_share", this.filterShare));
		if (this.filterNodeUuid != null)
			result.add(new BasicNameValuePair("filter_node_uuid", this.filterNodeUuid));
		if (this.filterSeverty != null)
			result.add(
				new BasicNameValuePair("filter_severty", String.valueOf(this.filterSeverty.ordinal()+1))
			);
		if (this.filterText != null)
			result.add(new BasicNameValuePair("filter_text", this.filterText));
		if (this.filterFrom != null)
			result.add(
				new BasicNameValuePair("filter_from", this.filterFrom.format(DateTimeFormatter.ISO_INSTANT))
			);
		if (this.filterTo != null)
			result.add(
				new BasicNameValuePair("filter_to", this.filterTo.format(DateTimeFormatter.ISO_INSTANT))
			);
		if (this.perPage != null)
			result.add(new BasicNameValuePair("per_page", String.valueOf(this.perPage)));
		if (this.page != null)
			result.add(new BasicNameValuePair("page", String.valueOf(this.page)));
		if (this.tenantId != null)
			result.add(new BasicNameValuePair("tenant_id", this.tenantId));
		
		return result;
	}
}
