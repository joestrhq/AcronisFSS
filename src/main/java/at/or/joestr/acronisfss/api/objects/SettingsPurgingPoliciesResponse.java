/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.objects;

/**
 *
 * @author Joel
 */
public class SettingsPurgingPoliciesResponse {
	private String purge_deleted_files_older_than_enabled;
	private String purge_deleted_files_older_than_n;
	private String purge_deleted_files_older_than_units;
	private String purge_revisions_older_than_enabled;
	private String purge_revisions_older_than_n;
	private String purge_revisions_older_than_units;
	private String keep_at_least_n_revisions_enabled;
	private String keep_at_least_n_revisions;
	private String only_keep_n_revisions_enabled;
	private String only_keep_n_revisions;
	private String allow_users_to_selectively_purge_revisions;
}
