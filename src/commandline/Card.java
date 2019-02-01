package commandline;

import java.util.ArrayList;

public class Card {
	private String cardName = "";
	private ArrayList<Integer> catScores;	
	
	public Card(String cardName, int size, int speed, int range, int firepower, int cargo) {
		this.cardName = cardName;
		this.catScores = new ArrayList<Integer> ();
		catScores.add(size);
		catScores.add(speed);
		catScores.add(range);
		catScores.add(firepower);
		catScores.add(cargo);
	}
	
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	public ArrayList<Integer> getCatScores() {
		return catScores;
	}

	public void setCatScores(ArrayList<Integer> catScores) {
		this.catScores = catScores;
	}

	public String log() {
		return cardName + " " + catScores.get(0) + " " + catScores.get(1) + " "
				+ catScores.get(2) + " " + catScores.get(3) + " " + catScores.get(4);
	}
	
	public String toString(int cat) {
		String line =  "\t> Size: " + catScores.get(0);
		if(cat == 1) line = line + " <----";
		line = line + "\n\t> Speed: " + catScores.get(1);
		if(cat == 2) line = line + " <----";
		line = line + "\n\t> Range: " + catScores.get(2);
		if(cat == 3) line = line + " <----";
		line = line + "\n\t> Firepower: " + catScores.get(3);
		if(cat == 4) line = line + " <----";
		line = line +  "\n\t> Cargo: " + catScores.get(4);
		if(cat == 5) line = line + " <----";
		return line;
	}
}
