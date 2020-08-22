/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.objects;

import java.util.UUID;

/**
 *
 * @author Joel
 */
public class Member {
	private UUID id;
	private boolean readOnly;
	private boolean viewMembers;
	private boolean invite;
	private String email;
	private String name;
	//private ? permissionFor;
	//private ? beChanged;
}
