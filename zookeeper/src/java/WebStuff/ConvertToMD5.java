/*
 * IT-145 Found in App Development
 * 3.15 Program: Text message expander (Java)
 * Bourama Mangara
 * 19 August 2018
 */
package WebStuff;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *The convertToMD5 class convert users entered password to an encrypted password and checked it against the password in the credential file.
 * @author Bourama Mangara
 */
public class ConvertToMD5 {
    
    private String passWord = "";

    public ConvertToMD5() {
        
    }
    /**
     * The convertToMD5 method is called in the main class to convert user password.
     * @param passWord 
     * @return return the encrypted password as a string.
     * @throws NoSuchAlgorithmException 
     */
    public String ConvertToMD5(String passWord) throws NoSuchAlgorithmException{
        
        MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(passWord.getBytes());
		byte[] digest = md.digest();
        StringBuffer sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
    this.passWord = sb.toString();
    return this.passWord;
        
        
    }
   
    
}
