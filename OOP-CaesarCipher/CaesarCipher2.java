import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of CaesarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher2 {
    //private String alphabetCaps;
    //private String alphabetSmall;
    //private String alphabet;
    //private String shiftedAlphabet1;
    //private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipher2(int key1, int key2){
        this.key1 = key1%26;
        this.key2 = key2%26;
        //alphabetSmall= "abcdefghijklmnopqrstuvwxyz";
        //alphabetCaps= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //alphabet = alphabetCaps + alphabetSmall;
        //shiftedAlphabet1= alphabetCaps.substring(key1) + alphabetCaps.substring(0,key1) +
         //                   alphabetSmall.substring(key1) + alphabetSmall.substring(0,key1);
        //shiftedAlphabet2= alphabetCaps.substring(key2) + alphabetCaps.substring(0,key2) +
         //                   alphabetSmall.substring(key2) + alphabetSmall.substring(0,key2);
    }
    
    public String encrypt(String input){
        StringBuilder encrypted = new StringBuilder();
        CaesarCipher cc1 = new CaesarCipher(key1);
        CaesarCipher cc2 = new CaesarCipher(key2);
        
        for(int i=0; i<input.length(); i++){
            String currString = ""+input.charAt(i);
            if(i%2==0){
                encrypted.append(cc1.encrypt(currString));
            }
            else{
                encrypted.append(cc2.encrypt(currString));
            }
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher2 cc = new CaesarCipher2(26-key1,26-key2);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
