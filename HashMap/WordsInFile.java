import java.util.*;
import java.lang.*;
import edu.duke.*;
import java.io.*;
/**
 * Write a description of WordsInFile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsInFile {
    private HashMap<String,ArrayList> wordsInFile;
    private int MIN;
    
    public WordsInFile(){
        wordsInFile = new HashMap<String,ArrayList>();
        MIN = -999999;
    }
    
    public void addWordsFromFile(File f){
        FileResource fr = new FileResource(f);
        for(String words : fr.words()){
            if(!wordsInFile.containsKey(words)){
                ArrayList<String> al = new ArrayList<String>();
                al.add(f.getName());
                wordsInFile.put(words, al);
            }
            
            else{
                ArrayList<String> al = wordsInFile.get(words);
                if(!al.contains(f.getName())){
                    al.add(f.getName());
                    wordsInFile.put(words, al);
                }
                
            }
        }
    }
    
    public void buildWordMap(){
        wordsInFile.clear();
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }
    
    public int maxNumber(){
        int max=MIN;
        for(String s : wordsInFile.keySet()){
            int size = wordsInFile.get(s).size();
            max= Math.max(max, size);
        }
        return max;
    }
    
    public ArrayList<String> wordsInNumFile(int number){
        ArrayList<String> words = new ArrayList<String>();
        for(String s : wordsInFile.keySet()){
            int value = wordsInFile.get(s).size();
            if(value == number){
                words.add(s);
            }
        }
        return words;
    }
 
    public void printFilesIn(String word){
        ArrayList<String> al =  wordsInFile.get(word);
        for(int i= 0; i<al.size();i++){
            System.out.println(word + " is present in " + al.get(i));
        }
    }
    
    public void tester(){
        buildWordMap();
        int num = 4;
        int maxNumber = maxNumber();
        ArrayList<String> al = wordsInNumFile(num);
        System.out.println("max number of files for any given word is " + maxNumber);
        System.out.println();
        //for(int i =0 ; i< al.size(); i++){
            //String word = al.get(i);
            //System.out.println("the word is " + word);
            //printFilesIn(word);
        //}
        printFilesIn("tree");
        System.out.println(" that occur in all  files are  " + al.size());
        //for(String s : wordsInFile.keySet()){
          //  System.out.println("words is "+ s + " and no of files it is present in are "+ wordsInFile.get(s).size());
        //}
        
    }
    
    
}
