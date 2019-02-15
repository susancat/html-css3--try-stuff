package commandline;

import java.util.ArrayList;
/**
 * Class for storing card as obj
 */
public class Card {
	//Card variables matching attributes of card
	private String cardName = "";
	private int size = 0;
	private int speed = 0;
	private int range = 0;
	private int firepower = 0;
	private int cargo = 0;
	
//================CARD CONSTRUCTOR=====================
/**
 * A constructor for creating a card obj
 * @param cardName
 * @param size
 * @param speed
 * @param range
 * @param firepower
 * @param cargo
 */
	public Card(String cardName, int size, int speed, int range, int firepower, int cargo) {
		this.cardName = cardName;
		this.size = size;
		this.speed = speed;
		this.range = range;
		this.firepower = firepower;
		this.cargo = cargo;
	}//======================card-end
	
//=================EMPTY CARD CONSTRUCTOR==================
	public Card() {
		
	}//======card-end
	
//==============CATEGORY VALUES ARRAY===================
/**
 * This array loads the values of each attribute in to an array.
 * Used in TopTrumpsCLIApplication.setWinner() to help determine
 * winner of round.
 * @return
 */
	public ArrayList<Integer> catScores() {
		ArrayList<Integer> catScores = new ArrayList<Integer>();
		catScores.add(size);
		catScores.add(speed);
		catScores.add(range);
		catScores.add(firepower);
		catScores.add(cargo);
		return catScores;
	}//===================catScores-end
	
//==============PRINT STRING FOR LOG===================
/**
 * This method prints out the card obj to String in Log
 * format
 * @return
 */
	public String log() {
		return cardName + " " + size + " " + speed + " "
				+ range + " " + firepower + " " + cargo;
	}//=========================================log-end
	
//=================TO STRING=========================
/**
 * The card obj toString method includes if statements
 * that point to a category if that category number is passed.
 * This allows the toString to be used to show winning card with
 * category. To print without arrow, we just pass an int that isn't 1-5
 * @param cat
 * @return
 */
	public String toString(int cat) {
		String line =  "\t> Size: " + size;
		if(cat == 1) line = line + " <----";
		line = line + "\n\t> Speed: " + speed;
		if(cat == 2) line = line + " <----";
		line = line + "\n\t> Range: " + range;
		if(cat == 3) line = line + " <----";
		line = line + "\n\t> Firepower: " + firepower;
		if(cat == 4) line = line + " <----";
		line = line +  "\n\t> Cargo: " + cargo;
		if(cat == 5) line = line + " <----";
		return line;
	}//====================================toString-end
	
	
//==================GETTERS AND SETTERS==================
/**
 * These are not used in CLIApplication but are important for
 * restAPI when Stringifying obj
 * @return
 */

	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getFirepower() {
		return firepower;
	}
	public void setFirepower(int firepower) {
		this.firepower = firepower;
	}
	public int getCargo() {
		return cargo;
	}
	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

//==============================================g&s-end	

}//=======CARD-END
