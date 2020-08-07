import java.lang.*;
import java.util.*;
import edu.duke.*;
/**
 * Write a description of WordsLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordsLength {
    public void countWordsLengths(FileResource resource, int[] counts){
        int length = counts.length;
        for(String words : resource.words()){
            int wordLength = words.length();
            int start = 0;
            int end=0;
            if(!Character.isLetter(words.charAt(0))){
                start=1;
            }
            if(!Character.isLetter(words.charAt(wordLength-1))){
                end=1;
            }
            int finalLength = wordLength - start - end;
            if(finalLength<0){
                finalLength=0;
            }
            if(finalLength<length){
                counts[finalLength]++;
            }
            else{
                counts[length-1]++;
            }
        }
    }
    
    public void testCountWordsLengths(){
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordsLengths(resource, counts);
        int max=-1;
        int index=-1;
        for(int i=0; i<counts.length; i++){
            //System.out.println("no of words of length " + i + " is " + counts[i]);
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
        System.out.println(index);
    }
}
