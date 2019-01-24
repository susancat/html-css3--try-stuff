package commandline;

import java.util.ArrayList;

public class Player {
	private String pName = "";
	private ArrayList<Integer> hand = new ArrayList<Integer>();
	
	public void addCard(int c) {
		hand.add(c);
	}
	
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
	
	
}
