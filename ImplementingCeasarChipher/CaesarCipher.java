import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of CeaserCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    public String encrypt(String input, int key){
        key =key%26;
        String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String small = "abcdefghijklmnopqrstuvwxyz";
        
        String encryptedCaps = caps.substring(key) + caps.substring(0,key);
        String encryptedSmall = small.substring(key) + small.substring(0,key);
        String unencryptFinal = caps + small;
        String encryptFinal = encryptedCaps + encryptedSmall;
        
        StringBuilder encrypted = new StringBuilder(input);
        
        for(int i= 0;i<input.length();i++){
            char currChar = input.charAt(i);
            int found = unencryptFinal.indexOf(currChar);
            if(found !=-1){
                char replace = encryptFinal.charAt(found);
                encrypted.setCharAt(i, replace);
            }
            
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input , int key1, int key2){
        StringBuilder encrypted = new StringBuilder();
        for(int i=0; i<input.length(); i++){
            String currString = ""+input.charAt(i);
            if(i%2==0){
                encrypted.append(encrypt(currString,key1));
            }
            else{
                encrypted.append(encrypt(currString,key2));
            }
        }
        return encrypted.toString();
    }
    
    public void testEncryptTwoKeys(){
        //FileResource fr = new FileResource();
        String input = "eveee eegestrd eeesteeeeeeeeeeeeeeeee";//eveee eegestrd eeesteeeeeeeeeeeeeeeee
        int key1 = 17;
        int key2 =3;
        String output= encryptTwoKeys(input,key1,key2);
        System.out.println(output);
        
    }
    
    
    public void testEncrypt(){
        String input = "Laer. My necessaries are embark'd. Farewell.And, sister, as the winds give benefit";
        int key =15;
        String output = encrypt(input, key);
        String output1 = encrypt(output, 26-key);
        System.out.println(output );
        System.out.println(output1);
    }
    
}
