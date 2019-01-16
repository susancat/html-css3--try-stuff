package commandline;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Card {
	//use a HashMap to link 40 cards with position 0~39th 
	private HashMap<Integer, String> card = new HashMap<Integer, String>();
	//ArrayList deck used to store the Description of cards 
	private ArrayList<String> deck = new ArrayList<String>();
	// the following 5 ArrayLists store values of 5 categories
	private ArrayList<String> Size = new ArrayList<String>();
	private ArrayList<String> Speed = new ArrayList<String>();
	private ArrayList<String> Range = new ArrayList<String>();
	private ArrayList<String> Firepower = new ArrayList<String>();
	private ArrayList<String> Cargo = new ArrayList<String>();
	//this ArrayList store card number used to be shuffled, its value link to card name one on one 
	private ArrayList<Integer> cardNumber = new ArrayList<Integer>();
	//the following 5 ArrayList store cards shared by deck to every player
	private ArrayList<Integer> Player = new ArrayList<Integer>();
	private ArrayList<Integer> AIPlayer1 = new ArrayList<Integer>();
	private ArrayList<Integer> AIPlayer2 = new ArrayList<Integer>();
	private ArrayList<Integer> AIPlayer3 = new ArrayList<Integer>();
	private ArrayList<Integer> AIPlayer4= new ArrayList<Integer>();
	
	public void readStar() {
		
		try { 
			BufferedReader bfReader = new BufferedReader(new FileReader("StarCitizenDeck.txt"));
	        bfReader.readLine();//the header will not be read
	        String line = null; 
	        while((line=bfReader.readLine())!=null){ 
	            String item[] = line.split(" ");//data in file is divided by space
	            cardName.add(item[0]);
	            Size.add(item[1]);
	            Speed.add(item[2]);
	            Range.add(item[3]);
	            Firepower.add(item[4]);
	            Cargo.add(item[5]);
	        }      
	        bfReader.close();
	        System.out.println(Size);
	        System.out.println(Speed);
	        System.out.println(Range);
	        System.out.println(Firepower);
	        System.out.println(Cargo);
	     }catch (Exception e) { 
	                e.printStackTrace(); 
	      }
	}
	
	public void shuffleCards() {
	int index = 0;
	for (String name: deck) {
		card.put(index,deck.get(index));
		cardNumber.add(index);
		index++;
	}
	
	System.out.println(card);

	Collections.shuffle(cardNumber);
	System.out.println(cardNumber);
	}
	 
		
		public void ShareCards() {	
			for (int i = 0; i < cardNumber.size();i++) {
				if(i%5==0) {
					Player.add(cardNumber.get(i));
				}else if(i%5==1) {
					AIPlayer1.add(cardNumber.get(i));
				}else if (i%5==2) {
					AIPlayer2.add(cardNumber.get(i));
				}else if (i%5==3) {
					AIPlayer3.add(cardNumber.get(i));
				}else {
					AIPlayer4.add(cardNumber.get(i));
				}
			}
		
		}
	}


