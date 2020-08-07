import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of CodonCount here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodonCount {
    private HashMap<String,Integer> codonCount;
    
    public CodonCount(){
        codonCount= new HashMap<String,Integer>();
    }

    public void buildCodonMap(int start, String dna){
        codonCount.clear();
        int i =start;
        while(i<dna.length()){
            if(i+2<dna.length()){
                String currCodon = dna.substring(i,i+3);
                if(!codonCount.containsKey(currCodon)){
                    codonCount.put(currCodon, 1);
                }
                else{
                    int value = codonCount.get(currCodon);
                    codonCount.put(currCodon, value +1);
                }
            }
            i=i+3;
        }
        
       
    }
    
    public String getMostCommonCodon(){
        String mostCommonCodon = "";
        int max=-1;
        for(String s: codonCount.keySet()){
            int value = codonCount.get(s);
            if(max==-1){
                max = value;
                mostCommonCodon = s;
            }
            else{
                if(value>max){
                    max= value;
                    mostCommonCodon= s;
                }
            }
        }
        return mostCommonCodon;
    }
    
    public void printAllCodon(int start , int end){
        for(String s:codonCount.keySet()){
            int value = codonCount.get(s);
            if(value <= end && value >= start){
                System.out.println(s+ "  " + codonCount.get(s));
            }
        }
    }
    
    public void testBuildCodonMap(){
        int startIndex =0;
        //String dna = "CGTTCAAGTTCAA";
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(startIndex,dna);
        System.out.println();
        int count =0;
        for(String s:codonCount.keySet()){
            count++;
            int value = codonCount.get(s);
            if(value==7){
                System.out.println(s+ " no of codon is  " + codonCount.get(s));
            }
            
        }
        System.out.println("codon count is " + count);
    }
    
    public void testMostCommonCodon(){
         int startIndex =2;
        //String dna = "CGTTCAAGTTCAA";
        FileResource fr = new FileResource();
        String dna = fr.asString();
        buildCodonMap(startIndex,dna);
        
        int start = 1;
        int end = 5;
        //System.out.println();
        //printAllCodon(start,end);
        //System.out.println();
        String mostCommonCodon = getMostCommonCodon();
        System.out.println("most common codon is " + mostCommonCodon+ "  no of occurances " +codonCount.get(mostCommonCodon) );
    }
    
}

