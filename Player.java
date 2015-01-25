import java.util.Comparator;


public class Player implements Comparator<Player>{
	public int playerNumber;
	public double rating;
	public double height;
	public String gender;
	
	public Player() {
	}
	
	public Player (int playerNumber, double rating, double height, String gender ) {
		this.playerNumber = playerNumber;
		this.rating = rating;
		this.height = height;
		this.gender = gender;
	}
	
	public int GetPlayerNumber() {
		return playerNumber;
	}
	
	public double GetPlayerRating() {
		return rating;
	}
	
	public double GetPlayerHeight(){
		return height;
	}
	
	public String GetPlayerGender(){
		return gender;
	}
	
	public int compare(Player a, Player b){
		if(a.gender.equals("F") && b.gender.equals("M")) {
			return 1;
		}if (a.gender.equals(b.gender) && a.rating > b.rating) {
			return 1;
		}if (a.gender.equals(b.gender) && a.rating == b.rating && a.height > b.height) {
			return 1;
		} if (a.gender.equals(b.gender) && a.rating == b.rating && a.height == b.height) {
			return 0;
		} else {
			return -1;
		}
	}
}
