import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestCaesarCipherTwo {
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
    
     public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<message.length();i=i+2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public String breakCaesarCipher(String input){
        String s1 = halfOfString(input, 0);
        String s2 = halfOfString(input, 1);
        int[] count1 = new int[26]; 
        int[] count2 = new int[26]; 
        countLetter(s1, count1);
        countLetter(s2, count2);
        int index1 = getIndexOfMaxFrequency(count1);
        int index2 = getIndexOfMaxFrequency(count2);
        int key1 = getKey(index1);
        int key2 = getKey(index2);
        System.out.println(key1+" , " + key2);
        CaesarCipher2 cc = new CaesarCipher2(key1,key2);
        String decrypt = cc.decrypt(input);
        return decrypt;
    }
    
    public void testBreakCaesarCipher(){
        FileResource fr = new FileResource();
        String input = fr.asString();
        //String input ="Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String decrypt = breakCaesarCipher(input);
        System.out.println(decrypt);
        
    }
    
     public void simpleTest(){
        //FileResource fr = new FileResource();
        //String input = fr.asString();
        String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
        CaesarCipher2 cc = new CaesarCipher2(14,24);
        //String encrypted = cc.encrypt(input);
        //System.out.println(encrypted);
        String decrypted = cc.decrypt(input);
        System.out.println(decrypted);
    }
}
