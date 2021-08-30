/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.or.joestr.acronisfss.api.util;

/**
 *
 * @author joestr
 */
public class CustomUtil {
  
  /**
   * Returns true if the needle is found in the haystack.
   * 
   * @param needle The number to find.
   * @param haystack Pool of numbers.
   * @return 
   */
  public static boolean contains(int needle, int ... haystack) {
    for (int i = 0; i < haystack.length; i++) {
      if (needle == haystack[i]) {
        return true;
      }
    }
    
    return false;
  }
  
}
