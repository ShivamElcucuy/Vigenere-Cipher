import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipher {
    public void countLetter(String message , int[] counts){
        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for(int i=0; i<message.length();i++){
            int index = alpha.indexOf(Character.toLowerCase(message.charAt(i)));
            if(index!=-1){
                counts[index]++;
            }
        }
    }
    
    public int getIndexOfMaxFrequency(int[] counts){
        int max=-1;
        int index=-1;
        for(int i=0; i<counts.length;i++){
            if(max==-1){
                max = counts[i];
                index=i;
            }
            else{
                if(counts[i]>max){
                    max= counts[i];
                    index =i;
                }
            }
        }
        return index;
    }
    
    public int getKey(int index){
        int deKey = index-4;
        
        if(deKey<0){
            deKey = 26+deKey;
        }
        //deKey= 26-deKey;
        return deKey;
    }
    
    public String breakCaesarCipher(String input){
        int[] counts = new int[25]; 
        countLetter(input, counts);
        int index = getIndexOfMaxFrequency(counts);
        int key = getKey(index);
        CaesarCipher cc = new CaesarCipher(key);
        String decrypt = cc.decrypt(input);
        return decrypt;
    }
    
    public void testBreakCaesarCipher(){
        String encrypted = "ufb csfbl ffffff";
        String decrypted = breakCaesarCipher(encrypted);
        System.out.println(decrypted);
    }
    
    public void simpleTest(){
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input ="Can you imagine life WITHOUT the internet AND computers in your pocket?";
        CaesarCipher cc = new CaesarCipher(15);
        String encrypted = cc.encrypt(input);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
    }
    
}
