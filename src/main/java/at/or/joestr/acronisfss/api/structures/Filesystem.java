/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.structures;

/**
 * Type of file system that the device is using
 * 
 * <ul>
 *  <li>0 - any non-iOS device and iOS devices using the standard (non-MDM managed) app</li>
 *  <li>1 - iOS device using the BlackBerry Dynamics app</li>
 * </ul>
 * 
 * @author joestr
 */
public enum Filesystem {
  Type0,
  Type1
}
