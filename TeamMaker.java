//Alex Taipale, 2014.01.18
//Run with Java 1.6 and openCSV-2.3
//Built to determine equally matched teams for Ultimate Frisbee Hat Tournaments
//from an CSV file giving....

import java.io.FileReader;
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
    
     //Read CSV line by line and add males then females
      //to the front of Linked List
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
      
//      for(String[] row : allRows) {
//          System.out.println(row[3]);
//       }
      //Sort my LinkedList by the most important quality to balance-- gender
      //iterates over allRows per row
//      for(String[] row : allRows) {
//    	  //If the player is male, shuffle him to the top of the list
//    	  
//    	  if (row[3].equals("M")) {
//    		  allRows.addFirst(row);
//    		  System.out.println("x");
//    	  }
//      }
      for(String[] row : allRows) {
      System.out.println(Arrays.toString(row));
      }
   }
}
