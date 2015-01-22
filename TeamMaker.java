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
	 
}

   
   

