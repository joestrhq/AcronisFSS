/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

/**
 *
 * @author Joel
 */
public class ErrorResponse {
	private String domain;
	private int code;
	private String message;
	private String context;

	public ErrorResponse(String domain, int code, String message, String context) {
		this.domain = domain;
		this.code = code;
		this.message = message;
		this.context = context;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "Error{" + "domain=" + domain + ", code=" + code + ", message=" + message + ", context=" + context + '}';
	}
	
	
}
