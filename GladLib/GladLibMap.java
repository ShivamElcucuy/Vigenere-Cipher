import edu.duke.*;
import java.util.*;

public class GladLibMap {
    private HashMap<String , ArrayList<String>> myMap;
    private ArrayList<String> trackList;
    private ArrayList<String> trackFile;
    private int countChanges ;
    
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        trackList = new ArrayList<String>();
        countChanges =0;
        trackFile = new ArrayList<String>();
        initializeFromSource(source);
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        String[] label = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for(String s: label){
            ArrayList<String> list = readIt(source + "/" + s + ".txt");
            myMap.put(s, list);
        }
        trackList = new ArrayList<String>();
        countChanges =0;
        trackFile = new ArrayList<String>();
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        
        String key = w.substring(first+1,last);
        if(!trackFile.contains(key)){
            trackFile.add(key);
        }
        int index = trackList.indexOf(sub);
        while(index!=-1){
            sub = getSubstitute(w.substring(first+1,last));
            index = trackList.indexOf(sub);
        }
        trackList.add(sub);
        //System.out.println(prefix + "   abcx   " + suffix);
        //System.out.println(sub);
        //System.out.println();
        countChanges++;
        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }
    
    public int totalWordsInMap(){
        int total=0;
        for(String s : myMap.keySet()){
            total += myMap.get(s).size();
        }
        return total;
    }
    
    public int totalWordsConsidered(){
        int total=0;
        for(int i = 0; i<trackFile.size(); i++){
            String s = trackFile.get(i);
            //System.out.println();
            //System.out.println("label is " + s);
            //System.out.println("label is " + s + "  " + myMap.get(s));
            if(!s.equals("number")){
                total+= myMap.get(s).size();
            }
        }
        return total;
    }
    
    public void makeStory(){
        System.out.println("\n");
        trackList.clear();
        trackFile.clear();
        countChanges=0;
        
        String story = fromTemplate("data/madtemplate3.txt");
        
               
        printOut(story, 60);
        System.out.println();
        System.out.println("no of changes  "+countChanges);
        System.out.println();
        int total = totalWordsInMap();
        System.out.println("total words in the map is " + total);
        
        System.out.println();
        int totalWords = totalWordsConsidered();
        System.out.println("total words considered in the map is " + totalWords);
        
        
    }
    


}
