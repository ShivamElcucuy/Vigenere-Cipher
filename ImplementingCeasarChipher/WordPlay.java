import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of WordPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordPlay {
    public boolean isVowel(char ch){
        String vowel= "aeiouAEIOU";
        String charTemp = ""+ch;
        //System.out.println(charTemp);
        return vowel.contains(charTemp);
        
    }

    public String replaceVowel(String s, char ch){
        StringBuilder sb = new StringBuilder(s);
        for(int i=0; i<s.length();i++){
            char currChar = s.charAt(i);
            if(isVowel(currChar)){
                sb.setCharAt(i, ch);
            }
            
        }
        return sb.toString();
    }
    
    public String emphasize(String phrase, char ch){
        char evenIndex = '*';
        char oddIndex = '+';
        StringBuilder sb = new StringBuilder(phrase);
        for(int i =0; i<phrase.length();i++){
            char currChar = phrase.charAt(i);
            if(i%2==0&& currChar==ch){
                sb.setCharAt(i, evenIndex);
            }
            if(i%2!=0 && currChar == ch){
                sb.setCharAt(i,oddIndex);
            }
            
        }
        
        return sb.toString();
    }
    
    public void testEmphasize(){
        String phrase = "dna ctgaaactga";
        char ch = 'a';
        String newString = emphasize(phrase, ch);
        System.out.println(newString);
    }
    
    public void testReplaceVowel(){
        String s = "shivam is awesome";
        char ch = '*';
        String newString = replaceVowel(s,ch);
        System.out.println(newString);
    }
}
