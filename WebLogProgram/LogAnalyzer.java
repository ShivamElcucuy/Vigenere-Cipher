
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for( String line : fr.lines()){
             LogEntry currEntry = WebLogParser.parseEntry(line);
             if(!records.contains(currEntry)){
                 records.add(currEntry);
             }
         }
     }
     
     //count no of unique ip
     public int countUniqueIp(){
         HashMap<String,Integer> counts = countVisitPerIp();
         return counts.size();
     }
     
     //given a day returns a arraylist of all unique ips on that day
     public ArrayList<String> uniqueIpVisitOnDay(String someday){
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             date = date.substring(4,10);
             //System.out.println( date);
             if(date.equals(someday)){
                 String ip = le.getIpAddress();
                 if(!uniqueIp.contains(ip)){
                     uniqueIp.add(ip);
                 }
             }
         }
         return uniqueIp;
     }
     
     // count unique ips in the given range of the status code 
     public int countUniquePsInRange(int low , int high){
         int count=0;
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for(LogEntry le : records){
             int status  = le.getStatusCode();
             String ip = le.getIpAddress();
             if(status >= low && status<=high){
                 if(!uniqueIp.contains(ip)){
                     uniqueIp.add(ip);
                 }
             }
         }
         return uniqueIp.size();
     }
     
     
     //return a hashmap of unique ips and their count
     public HashMap<String, Integer> countVisitPerIp(){
         HashMap<String,Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         return counts;
     }
     
     //returns the count of ip that occur the most in the given hashmap
     public int mostNumberVisitByIp(HashMap<String,Integer> counts){
         int max=-1;
         for(String s : counts.keySet()){
             if(max ==-1){
                 max=counts.get(s);
             }
             else{
                 if(max<counts.get(s)){
                     max= counts.get(s);
                 }
             }
         }
         return max;
     }
     
     
     //returns arraylist of the ips that occurs the most int hashmap
     public ArrayList<String> iPsMostVisit(HashMap<String, Integer> counts){
         ArrayList<String> ips = new ArrayList<String>();
         int max = mostNumberVisitByIp(counts);
         for(String s : counts.keySet()){
             if(counts.get(s)==max){
                 if(!ips.contains(s)){
                     ips.add(s);
                 }
             }
         }
         return ips;
     }
     
     //returns hashmap of days to arraylist of ips
     public HashMap<String,ArrayList<String>> iPsForDays(){
         HashMap<String,ArrayList<String>> ipDays = new HashMap<String,ArrayList<String>>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString();
             date = date.substring(4,10);
             String ip =le.getIpAddress();
             if(!ipDays.containsKey(date)){
                 ArrayList<String> temp = new ArrayList<String>();
                 temp.add(ip);
                 ipDays.put(date,temp);
             }
             
             else{
                 ArrayList<String> temp= ipDays.get(date);
                 //if(!temp.contains(ip)){
                 temp.add(ip);
                 ipDays.put(date,temp);
                 //}
             }
         }
         return ipDays;
     }
        
     //return the day with the most ip visits in the hashmap
     public String daysWithMostIpVisits(HashMap<String,ArrayList<String>> ipDays){
         String mostDay = "";
         int max =-1;
         for(String currDate : ipDays.keySet()){
             if(mostDay.isEmpty()){
                 mostDay = currDate;
                 max= ipDays.get(currDate).size();
             }
             else{
                 if(max<ipDays.get(currDate).size()){
                     mostDay = currDate;
                     max = ipDays.get(currDate).size();
                 }
             }
         }
         return mostDay;
     }
     
     //returns arraylist of ips that occur the most in the given day
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String,ArrayList<String>> ipDays, String date){
         ArrayList<String> al = ipDays.get(date);
         HashMap<String,Integer> counts = new HashMap<String,Integer>();
         for(String ip :al){
             if(!counts.containsKey(ip)){
                 counts.put(ip,1);
             }
             else{
                 counts.put(ip,counts.get(ip)+1);
             }
         }
         ArrayList<String> ips = iPsMostVisit(counts);
         return ips;
     }
     
     //print all records in which status code is higher than the num
     public void printAllHigherThanNum(int num){
         for(LogEntry le : records){
             int status  = le.getStatusCode();
             if(status > num){
                 System.out.println("log whose status value is greater than " + num + " is " + le);
             }
         }
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}
