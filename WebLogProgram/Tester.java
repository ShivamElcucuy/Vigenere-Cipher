
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
       LogAnalyzer la = new LogAnalyzer();
       la.readFile("short-test_log.txt");
       la.printAll();
    }
    
    public void testUniqueIP(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int uniqueIp = la.countUniqueIp();
        System.out.println("no of unique ip are  " + uniqueIp);
    }
    
    public void testPrintAllHigherThanNum(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog1_log");
        int num = 400;
        la.printAllHigherThanNum(num);
    }
    
    public void testUniqueIpVisitOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        String someday = "Sep 27";
        ArrayList<String> al = la.uniqueIpVisitOnDay(someday);
        //for(String s : al){
          //  System.out.println("unique ips on " + someday + " is " + s);
        //}
        System.out.println("size of the array list is " + al.size());
    }
    
    public void testCountUniqueIpInRange(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        int low = 200;
        int high =299 ;
        int count = la.countUniquePsInRange(low, high);
        System.out.println("no of ips in the range of status " + low + " and " + high + " are " + count); 
        
    }
    
    public void testCountVisitPerIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log.txt");
        HashMap<String,Integer> counts = la.countVisitPerIp();
        System.out.println(counts);
    }
    
    public void testMostNumberVisitByIp(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitPerIp();
        int max = la.mostNumberVisitByIp(counts);
        System.out.println("max count of the ip is " + max);
    }
    
    public void testIpsMostVisit(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,Integer> counts = la.countVisitPerIp();
        ArrayList<String> ips = la.iPsMostVisit(counts);
        System.out.println(ips);
    }
    
    public void testIpForDays(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog3-short_log");
        HashMap<String,ArrayList<String>> ipDays = la.iPsForDays();
        System.out.println(ipDays);
    }
    
    public void testDayWithMostIpVisit(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> ipDays = la.iPsForDays();
        //System.out.println(ipDays);
        String mostDay = la.daysWithMostIpVisits(ipDays);
        System.out.println("day with the maximun number of ip calls is " + mostDay);
    }
    
    public void testIPsWithMostVisitsOnDay(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String,ArrayList<String>> ipDays = la.iPsForDays();
        String date = "Sep 30";
        ArrayList<String> ips = la.iPsWithMostVisitsOnDay(ipDays, date);
        System.out.println(ips);
    }
}
