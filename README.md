# TeamMaker
A program to sort a CSV file containing stats of Ultimate Frisbee players (player number, rating, height, gender) for a Hat tournament. It will sort the list of players into teams of 5-6 players that are evenly matched. It uses an algorithm that first sorts the list of players by gender, rating and height, and then pulls players in a snake pattern into new teams. It then prints the teams, the total player stats sum and average, and the averages of the individual teams. In the future I would like to use the FindAverage method to inform the algorithm about which team is the weakest and needs to take a stronger player from the list. 

I ran this program in a project in Eclipse. All you need is the TeamMaker.java, Player.java in the same default package folder, and the sample csv file that I've uploaded in the TeamMaker github folder (if you want to run your own csv file, you must name it "sample_player_data.csv" and it must be in the format of starting row 2, column 1: player number, column 2: rating (1-5), column 3: height (cm), column 4: M or F. 

Player Number	Rating (1-5)	Height (cm)	Gender (M or F)
      1 	        4	          170	           F

Compile and run in Eclipse. You may need to downlad OpenCSV in order to import. I ran this with Java 1.6 and openCSV-2.3, so it may only work with these older versions-- you can downlad openCSV2.3 here-- http://grepcode.com/snapshot/repo1.maven.org/maven2/net.sf.opencsv/opencsv/2.3 
