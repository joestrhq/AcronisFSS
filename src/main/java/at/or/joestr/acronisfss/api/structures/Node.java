/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

import java.time.LocalDateTime;

/**
 *
 * @author Joel
 */
public abstract class Node {
	
	private NodeUuid nodeUuid;
	private NodeName nodeName;
	private boolean directory;
	private long size;
	private LocalDateTime fileModificationDate;
	private boolean deleted;
	private boolean synced;
	private LocalDateTime expirationDate;
	private boolean readOnly;
	private boolean hasActiveLinks;
	private boolean hasSharedChildNodes;
	private boolean shared;
	private String ownerEmail;
	//private ? parents;
	private String checksum;
	private NodeUuid parentUuid;
	private boolean writableChildren;
	//private ? officeOnlinePermissions;
	//private Owner owner;
	//private Permissions permissions;
	//private Actions actions;
}
