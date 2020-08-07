import java.util.*;
import java.lang.*;
import edu.duke.*;
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CharactersInPlay {
    private ArrayList<String> characterName;
    private ArrayList<Integer> characterFreqs;
    
    public CharactersInPlay(){
        characterName = new ArrayList<String>();
        characterFreqs = new ArrayList<Integer>();
    }
    
    public void update(String person){
        int index = characterName.indexOf(person);
        if(index==-1){
            characterName.add(person);
                    characterFreqs.add(1);
        }
        else{
            int value= characterFreqs.get(index);
            characterFreqs.set(index, value+1);
        }
        
    }
    
    public void findAllCharacter(){
        characterName.clear();
        characterFreqs.clear();
        FileResource fr = new FileResource();
        int index;
        for(String l: fr.lines()){
            index=l.indexOf(".");
            if(index!=-1){
                String currName = l.substring(0,index);
                update(currName);                
            }
            
        }
    }
    
    public void charactersWithNumParts(int num1, int num2){
        for(int i=0;i<characterName.size();i++){
            if(characterFreqs.get(i)>=num1 && characterFreqs.get(i)<=num2){
                System.out.println(characterName.get(i) +"\t" + characterFreqs.get(i));
            }
        }
    }
    
    public void testCharctersWithNumParts(){
        findAllCharacter();
        charactersWithNumParts(60,900);
        
    }
    
    public void testFindAllCharacter(){
        findAllCharacter();
        for(int i=0;i<characterName.size();i++){
            if(characterFreqs.get(i)<100){
                System.out.println(characterName.get(i) +"\t" + characterFreqs.get(i));
            }
        }
    }
}
