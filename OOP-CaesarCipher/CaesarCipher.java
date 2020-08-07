import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabetCaps;
    private String alphabetSmall;
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher(int key){
        key =key%26;
        mainKey=key;
        alphabetSmall= "abcdefghijklmnopqrstuvwxyz";
        alphabetCaps= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        alphabet = alphabetCaps + alphabetSmall;
        shiftedAlphabet= alphabetCaps.substring(key) + alphabetCaps.substring(0,key) +
                            alphabetSmall.substring(key) + alphabetSmall.substring(0,key);
    }
    
    public String encrypt(String input){
               
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i= 0;i<input.length();i++){
            char currChar = input.charAt(i);
            int found = alphabet.indexOf(currChar);
            if(found !=-1){
                char replace = shiftedAlphabet.charAt(found);
                encrypted.setCharAt(i, replace);
            }
            
        }
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26-mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
}
