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
	//private Severty severty;
	private UUID nodeUuid;
	private UUID shareUuid;
	private UUID ownerUuid;
	//private Share share;
	//private NodeAuditLogEntry node;
	//private UserAUditLogEntry user;
}
