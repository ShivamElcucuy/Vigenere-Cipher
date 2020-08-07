import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sb = new StringBuilder();
        for(int i=whichSlice;i<message.length();i=i+totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        String[] s = new String[klength];
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for(int i=0;i<klength; i++){
            s[i] = sliceString(encrypted, i, klength);
            //System.out.println(s[i]);
            key[i] = cc.getKey(s[i]);
            //System.out.println(key[i]);
        }
        return key;
    }

    
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String s: fr.lines()){
            s= s.toLowerCase();
            dictionary.add(s);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        int count = 0;
        for(String s : message.split("\\W")){
            if(dictionary.contains(s)){
                count+=1;
            }
        }
        return count;
    }
    
    private int maxIndex(int[] keyCount){
        int maxIndex =-1;
        int max = -1;
        for(int i=0;i<keyCount.length;i++){
            if(max==-1){
                max=keyCount[i];
                maxIndex= i;
            }
            else{
                if(max<keyCount[i]){
                    max=keyCount[i];
                    maxIndex= i;
                }
            }
        }
        return maxIndex;
    }
    
    public String breakforLanguage(String encrypted, HashSet<String> dictionary, char mostCommon){
        int[] keyCount = new int[101];
        String decrypted = "";
        //char mostCommon = 'e';
        for(int i = 1; i<keyCount.length;i++){
            int[] key = tryKeyLength(encrypted , i, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            decrypted = vc.decrypt(encrypted);
            keyCount[i] = countWords(decrypted,dictionary);
        }
        int max = maxIndex(keyCount);
        int[] key = tryKeyLength(encrypted , max, mostCommon);
        //System.out.println(key.length+ " valid words are " + keyCount[max]);
        VigenereCipher vc = new VigenereCipher(key);
        decrypted = vc.decrypt(encrypted);
        return decrypted;
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] count = new int[26];
        for(String s:dictionary){
            for(int i= 0;i<s.length();i++){
                char temp = s.charAt(i);
                temp = Character.toLowerCase(temp);
                int index = alphabet.indexOf(temp);
                //System.out.println("index " + index);
                if(index!=-1){
                    count[index]+=1;
                }
                
            }
        }
        int max = maxIndex(count);
        return alphabet.charAt(max);
    }
    
    public String breakForAllLanguage(String encrypted, HashMap<String,HashSet<String>> languages){
        HashMap<String,String> decrypt = new HashMap<String, String>();
        for(String s: languages.keySet()){
            HashSet<String> dictionary = languages.get(s);
            char mostCommon = mostCommonCharIn(dictionary);
            String decrypted = breakforLanguage(encrypted, dictionary,mostCommon);
            decrypt.put(s, decrypted);
        }
        int maxCount = -1;
        String name = "";
        for(String s : decrypt.keySet()){
            String currDecrypt = decrypt.get(s);
            HashSet<String> dictionary = languages.get(s);
            int count = countWords(currDecrypt,dictionary);
            if(name.isEmpty()){
                name = s;
                maxCount = count;
            }
            else{
                if(count>maxCount){
                    name = s;
                    maxCount = count;
                }
            }
        }
        System.out.println(" language is " + name);
        return decrypt.get(name);
    }
    
    public void testMostCommonCharacter(){
        FileResource fr = new FileResource();
        HashSet<String> dictionary = readDictionary(fr);
        char mostCommon = mostCommonCharIn(dictionary);
        System.out.println("the most common character in dictionary is " + mostCommon);
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        //char mostCommon = 'e';
        //int klength= 4;
        //String temp = "Hhdiu LVXNEW uxh WKWVCEW, krg k wbbsqa si Mmwcjiqm";
        //int[] key = tryKeyLength(encrypted , klength, mostCommon);
        //VigenereCipher vc = new VigenereCipher(key);
        //String decrypted = vc.decrypt(temp);
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        String[] names = {"English", "Danish", "Dutch", "French", "German"
                            , "Italian", "Portuguese" , "Spanish"};
        for(int i =0;i<names.length;i++){
            String s = names[i];
            String filePath = "dictionaries/" + s;
            FileResource fr2 = new FileResource(filePath);
            HashSet<String> dictionary = readDictionary(fr2);
            languages.put(s,dictionary);
        }
        
        String decrypted = breakForAllLanguage(encrypted, languages);
        //String decrypted = breakforLanguage(encrypted, dictionary,mostCommon);
        //int validWords = countWords(decrypted, dictionary);
        //System.out.println("no of valid words are " + validWords);
        System.out.println();
        System.out.println(decrypted.substring(0,200));
    }
    
    public void testValidWords(){
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        char mostCommon = 'e';
        int klength= 38;
        int[] key = tryKeyLength(encrypted , klength, mostCommon);
        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        FileResource fr2 = new FileResource();
        HashSet<String> dictionary = readDictionary(fr2);
        int validWords = countWords(decrypted, dictionary);
        System.out.println("no of valid words are " + validWords);
    }
    
    public void test(){
        FileResource fr = new FileResource();
        String s = fr.asString();
        //System.out.println(s);
        int klen =4;
        char mostCommon = 'e';
        int[] key = tryKeyLength(s, klen, mostCommon);
        for(int i=0;i<klen;i++){
            System.out.println(key[i]);
        }
    }
}
