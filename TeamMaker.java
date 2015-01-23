//Alex Taipale, 2014.01.18
//Run with Java 1.6 and openCSV-2.3
//Built to determine equally matched teams for Ultimate Frisbee Hat Tournaments
//from an CSV file giving 4 columns of data in the order: 1. Player Number
// 2. Rating (1-5) 3. Height (cm) 4. M or F. Done without modifying the original
//file. 

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.Arrays;

//running old version of openCSV because I am on an old computer that 
//only supports Java 1.6
import au.com.bytecode.opencsv.CSVReader;

import java.util.List;
import java.util.LinkedList;
 
public class TeamMaker
{
  
   public static void main(String[] args) throws Exception
   {
      //Build reader instance
      //Read data.csv
      //Default seperator is comma
      //Default quote character is double quote
      //Start reading from line number 2 (line numbers start from zero)
      CSVReader reader = new CSVReader(new FileReader("sample_player_data.csv"), ',' , '"' , 1);
    
     //Read CSV line by line, while sorting the list by adding first males 
      //then females to the front of Linked List. 
      String[] nextLine;
      LinkedList<String[]> allRows = new LinkedList();
      while ((nextLine = reader.readNext()) != null) {
         if (nextLine != null){
        	 if(nextLine[3].equals("M")) {
        		 allRows.addFirst(nextLine);
        	 }else{
        		 allRows.add(nextLine);
        	 }
         }
       }

      for(String[] row : allRows) {
      System.out.println(Arrays.toString(row));
      }
      
      //Eventually change to input from scanner for desired size of team. 
      //Right now assume 5 people per team
      //Find number of teams that I can make
      int NumberOfTeams = allRows.size()/5;
      System.out.println(NumberOfTeams);
      
      
      //Sort into teams based on proximity to average, prioritizing equal
      //gender ratio, then rating, then height
     
      //Create LinkedList Array to hold the forming teams, needs weird declaration format 
      //because Java doesnt like LinkedList arrays apparently
      LinkedList<String[]>[] TeamsSorted = (LinkedList<String[]>[]) new LinkedList<?>[NumberOfTeams];
      //Create Player Bank that will shrink as players are added to teams
      //how do i access the inner linkedlist??? maybe make everything an array instead of a list?
      LinkedList<String[]> PlayerBank = allRows;
      
      //make method to determine if below/above average and then add it to list?
      //method to determine difference from average and prioritize group with most
      //important difference category, allow it to search for an equalizer 1st
      
      FindDiff(allRows, allRows);
//      for (String[] row : PlayerBank) {
//    	  for(int i)
//    	  TeamsSorted[0].add(row);
//      }
      
      
      
      FindAverage(allRows);
   }
  
   
   
   //For calculating the average score of a list, returns a double array 
   //containing average rating, average height, and average gender number
   public static double[] FindAverage(LinkedList<String[]> playerData) {
	   double[] average = new double[3];
	   
	   //In the future I would like to make the code independent of a fixed
	   //table structure and remove repetition with a for loop, but for this 
	   //version, I am going to use what I know about my table structure to 
	   //simplify int vs string analysis 
	   
	 //sum data !!!! Not summing correctly, not sure why, slightly lower value
	   for(String[] row : playerData) {
			   average[0] = average[0] + Integer.parseInt(row[1]);
			   average[1] = average[1] + Integer.parseInt(row[2]);
			   
			   //To quantify the gender ratio of each team, I will add 1 for 
			   //females added to the team and strive for an 'average gender'
			   //of 0.5 for each team
			   if (row[3].equals("F")) {
				   average[2] = average[2] + 1;
			   }
		}
	   
	   //Average sum
	   for( int i=0; i < average.length; i++) {
		   average[i]=average[i]/playerData.size();
	   }
		   
	   System.out.println(Arrays.toString(average));
	   return average;
	}

   //Method to be called to find the difference between one team being built
   //and the average stats, to be called in main method iterating over each team
   //in the linkedlist array
   public static double[] FindDiff(LinkedList<String[]> Team, LinkedList<String[]> allRows) {
	   double[] difference = new double[3];
	   for(int i = 0; i < FindAverage(Team).length; i++) {
		   difference[i]=FindAverage(Team)[i]-FindAverage(allRows)[i];
	   }
	   System.out.println(Arrays.toString(difference));
	   return difference;
   }
   
  
	 
}

   
   

