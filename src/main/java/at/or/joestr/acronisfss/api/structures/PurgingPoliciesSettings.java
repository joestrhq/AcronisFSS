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
public class PurgingPoliciesSettings {
	private boolean purgeDeletedFilesOlderThanEnabled;
	private int purgeDeletedFilesOlderThanN;
	//private ? purgeDeletedFilesOlderThanUnits;
	private boolean purgeRevisionsOlderThabEnabled;
	private int purgeRevisionsOlderThanN;
	//private ? purgeRevisionsOlderThanUnits;
	private boolean keepAtLeastNRevisionsEnabled;
	private int keepAtLeastNRevisions;
	private boolean onlyKeepNRevisionsEnabled;
	private int onlyKeepNRevisions;
	private boolean allowUsersToSelectivelyPurgeRevisions;
}
