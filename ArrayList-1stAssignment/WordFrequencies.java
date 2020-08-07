import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        FileResource fr = new FileResource();
        myWords.clear();
        myFreqs.clear();
        for(String word : fr.words()){
            int index = myWords.indexOf(word.toLowerCase());
            if(index==-1){
                myWords.add(word.toLowerCase());
                myFreqs.add(1);
            }
            else{
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        int max =-1;
        int maxIndex = -1;
        for(int i=0;i<myFreqs.size();i++){
            if(max==-1){
                max= myFreqs.get(i);
                maxIndex=i;
            }
            else{
                if(myFreqs.get(i)>max){
                    max= myFreqs.get(i);
                    maxIndex=i;
                }
            }
        }
        return maxIndex;
    }
    
    public void testFindIndexOfMax(){
        findUnique();
        int maxIndex = findIndexOfMax();
        System.out.println(maxIndex + "  " + myWords.get(maxIndex)+ "\t" +myFreqs.get(maxIndex));
    }
    
    public void testFindUnique(){
        findUnique();
        System.out.println(myWords.size());
        //for(int i=0;i<myWords.size();i++){
           // System.out.println(myWords.get(i) +"\t" + myFreqs.get(i));
        //}
        
    }
}
