/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author joestr
 */
public class AuditLogEntry {
	private UUID uuid;
	private int code;
	private LocalDateTime createdAt;
	private String text;
	private Severity severty;
	private UUID nodeUuid;
	private UUID shareUuid;
	private UUID ownerUuid;
	private Share share;
	private NodeAuditLogEntry node;
	private UserAuditLogEntry user;

	public AuditLogEntry(UUID uuid, int code, LocalDateTime createdAt, String text, Severity severty) {
		this.uuid = uuid;
		this.code = code;
		this.createdAt = createdAt;
		this.text = text;
		this.severty = severty;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Severity getSeverty() {
		return severty;
	}

	public void setSeverty(Severity severty) {
		this.severty = severty;
	}

	public UUID getNodeUuid() {
		return nodeUuid;
	}

	public void setNodeUuid(UUID nodeUuid) {
		this.nodeUuid = nodeUuid;
	}

	public UUID getShareUuid() {
		return shareUuid;
	}

	public void setShareUuid(UUID shareUuid) {
		this.shareUuid = shareUuid;
	}

	public UUID getOwnerUuid() {
		return ownerUuid;
	}

	public void setOwnerUuid(UUID ownerUuid) {
		this.ownerUuid = ownerUuid;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public NodeAuditLogEntry getNode() {
		return node;
	}

	public void setNode(NodeAuditLogEntry node) {
		this.node = node;
	}

	public UserAuditLogEntry getUser() {
		return user;
	}

	public void setUser(UserAuditLogEntry user) {
		this.user = user;
	}

	
}
