import java.lang.*;
import java.util.*;
import edu.duke.*;
/**
 * Write a description of DecryptCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DecryptCaesarCipher {
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
    
    public String decrypt(String message){
        int[] counts =new int[26];
        countLetter(message, counts);
        CaesarCipher cc = new CaesarCipher();
        int maxFrequencyIndex=getIndexOfMaxFrequency(counts);
        
        int deKey = getKey(maxFrequencyIndex);
        
        String decrypted = cc.encrypt(message, deKey);
        return decrypted;
    }
    
    public String decryptTwoKeys(String message){
        int[] count1 =new int[26];
        int[] count2 = new int[26];
        String s1 = halfOfString(message,0);
        String s2 = halfOfString(message,1);
        countLetter(s1, count1);
        countLetter(s2, count2);
        
        CaesarCipher cc = new CaesarCipher();
        int maxFrequencyIndex1=getIndexOfMaxFrequency(count1);
        int maxFrequencyIndex2=getIndexOfMaxFrequency(count2);
        
        int deKey1 = getKey(maxFrequencyIndex1);
        int deKey2 = getKey(maxFrequencyIndex2);
        System.out.println(deKey1+ " "+deKey2);
        
        String decrypted = cc.encryptTwoKeys(message, deKey1, deKey2);
        return decrypted;
    }
    
    public int getKey(int index){
        int deKey = index-4;
        
        if(deKey<0){
            deKey = 26+deKey;
        }
        deKey= 26-deKey;
        return deKey;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder sb = new StringBuilder();
        for(int i=start; i<message.length();i=i+2){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }
    
    public void testDecryptTwoKeys(){
        FileResource fr = new FileResource();
        
        String message = fr.asString();
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
    
    public void testHalfOfString(){
        String s= halfOfString("Qbkm Zgic", 1);
        System.out.println(s);
    }
    
    public void testDecrypt(){
        String message = "Aptg. Bn ctrthhpgxth pgt tbqpgz's. Upgtltaa.Pcs, hxhitg, ph iwt lxcsh vxkt qtctuxi";
        String decrypted=decrypt(message);
        System.out.println(decrypted);
    }
}
