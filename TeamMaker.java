//Alex Taipale, 2014.01.18
//Run with Java 1.6 and openCSV-2.3
//Built to determine equally matched teams for Ultimate Frisbee Hat Tournaments
//from an CSV file giving 4 columns of data in the order: 1. Player Number
// 2. Rating (1-5) 3. Height (cm) 4. M or F. Done without modifying the original
//file. 

import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    
      //Read CSV line by line creating and adding Player objects to my list
      String[] nextLine;
      List<Player> allRows = new ArrayList<Player>();
      while ((nextLine = reader.readNext()) != null) {
         if (nextLine != null){
              allRows.add(new Player(Integer.parseInt(nextLine[0]), Double.parseDouble(nextLine[1]), Double.parseDouble(nextLine[2]), nextLine[3]));
         }
       }
      
      //Sort the list using my Player Comparator, ranking first by gender, then
      //rating, then height
      Collections.sort(allRows, new Player());
      
      //Print out sorted list of players
      System.out.println("Sorted List of All Players:");
      for(Player a : allRows) {
          System.out.println(a.GetPlayerNumber()+ ": " + a.GetPlayerRating() + 
        		  " " + a.GetPlayerHeight() + " " + a.GetPlayerGender());
      }
      
      //Eventually change to input from scanner for desired size of team. 
      //Right now assume 5 people per team
      //Find number of teams that I can make
      int NumberOfTeams = allRows.size()/5;
      System.out.println("Number of Teams: " + NumberOfTeams);
      
      //Make a List of Maps to store my teams as they sort
      List<Map<String, Player>> TeamsSorted = new ArrayList<Map<String, Player>>();
      
      int j=0; //to iterate through entire list of players allRows
      int positionInMap = 1; //tracks which while loop round we are on
  	  while(j<allRows.size()) {
	    	for(int i = 0; i<NumberOfTeams; i++) {
    		    //create empty teams if it is the first iteration
    		    if(j<NumberOfTeams){
    			    TeamsSorted.add(new LinkedHashMap<String, Player>());
    		    }
    		    //adds players to team if there are still unclaimed players
			    if(j<allRows.size()){
				    //The loop checks for odd/even rotation to add players
				    //in a snake pattern to ensure even teams
				    if(positionInMap%2 != 0) {
				  	    TeamsSorted.get(i).put(i + "." + positionInMap, allRows.get(j));
				    } else {
					    TeamsSorted.get(NumberOfTeams-1-i).put(NumberOfTeams-1-i 
							  + "." + positionInMap, allRows.get(j));
				    }
    		    }   
	    	    j++;
		    }
	  	  positionInMap++;
	    } 	
  	  
  	  //eventually improve choose algorithm to utilize FindAverage and allow 
  	  //lowest ranked teams to pick higher ranked and/or leftover players
      
	   //In the future I would like to make the code independent of a fixed
	   //table structure, but for this version, I am going to use what I know 
	   //about my table structure to simplify sorting.
  	  
      //Print out the sorted teams and find averages of teams (maybe move to 
      //separate method later)
  	  
      int i = 0; //keep track of which map we are on to get value
      //Create an array to hold the arrays containing the average metrics for 
      //each team
      double[][] average = new double[TeamsSorted.size()][3];
      //iterate through each team printing the list of players and simultaneously
      //summing player stats into an array to find average stats
      for(Map<String, Player> a : TeamsSorted) {
    	  //iterate through each Player on each team
    	  System.out.println("Team " + i + ": ");
		  for(int k =1; k<=a.size(); k++){
			  System.out.println(a.get(i + "." + k).GetPlayerNumber() + ": " + 
			      a.get(i + "." + k).GetPlayerRating() + ", " +  
				  a.get(i + "." + k).GetPlayerHeight() + ", " + 
			      a.get(i + "." + k).GetPlayerGender());
			  average[i][0] = average[i][0] + a.get(i + "." + k).GetPlayerRating();
			  average[i][1] = average[i][1] + a.get(i + "." + k).GetPlayerHeight();
			  if (a.get(i + "." + k).GetPlayerGender().equals("F")) {
				   average[i][2] = average[i][2] + 1;
			   }
		  }
		  i++;
       }
      
      int teamPositionSum = 0; //keep track of which team I am in
      double[] sum = new double[3]; //to store sum of skills 
      double[] totalAverage = new double[3]; //to store average of skills
      //sum all averages to get a total sum to check my work
      for(Map<String, Player> a : TeamsSorted) {
	      for( int metric=0; metric < 3; metric++) {
	    	  sum[metric]= sum[metric] + average[teamPositionSum][metric];
		   }
	      teamPositionSum++;
	  }
      //average all player scores to check my work
      for( int metric=0; metric < 3; metric++) {
    	  totalAverage[metric]= sum[metric]/allRows.size();
	   }

      System.out.println("Player Skill Sum: " + Arrays.toString(sum));
      System.out.println("Total Player Skill Average: " + Arrays.toString(totalAverage));
      
      //Average sum of skills on each team and print averages
      int teamPosition = 0;
      for(Map<String, Player> a : TeamsSorted) {
	      for( int metric=0; metric < 3; metric++) {
	    	  average[teamPosition][metric]=average[teamPosition][metric]/a.size();
		   }
	      System.out.print("Team Average " + teamPosition + ": ");
	      System.out.println(Arrays.toString(average[teamPosition]));
	      teamPosition++;
	  }
   }	 
}


   
   

