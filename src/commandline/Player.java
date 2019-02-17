package commandline;

import java.util.ArrayList;
/**
 * Class for player with hand of cards
 */
public class Player {
	private String pName = "";
	//cards are stored as int to match index position of card in cardDeck.
	//Possible coupling issue here(?)
	private ArrayList<Integer> hand = new ArrayList<Integer>();
	
//===========ADD CARD====================
/**
 * Method for adding card to players array	
 * @param c
 */
	public void addCard(int c) {
		hand.add(c);
	}//======================addCard-end
	
//===========GETTERS AND SETTERS==============
	
	public Player(String pName) {
		this.pName = pName;
	}
	public String getPName() {
		return pName;
	}
	public void setName(String pName) {
		this.pName = pName;
	}
	public ArrayList<Integer> getHand() {
		return hand;
	}
	public void setHand(ArrayList<Integer> hand) {
		this.hand = hand;
	}

//==================================g&s-end
	
}//========PLAYER-END
