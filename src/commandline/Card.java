package commandline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Card {
	//use a HashMap to link 40 cards with position 0~39th 
	private HashMap<Integer, String> card = new HashMap<Integer, String>();
	//ArrayList deck used to store the Description of cards 
	private ArrayList<String> deck = new ArrayList<String>();
	// the following 5 ArrayLists store values of 5 categories
	private ArrayList<Integer> size = new ArrayList<Integer>();
	private ArrayList<Integer> speed = new ArrayList<Integer>();
	private ArrayList<Integer> range = new ArrayList<Integer>();
	private ArrayList<Integer> firepower = new ArrayList<Integer>();
	private ArrayList<Integer> cargo = new ArrayList<Integer>();
	//this ArrayList store card number used to be shuffled, its value link to card name one on one 
	private ArrayList<Integer> cardNumber = new ArrayList<Integer>();
	//the following 5 ArrayList store cards shared by deck to every player
	private ArrayList<Integer> player = new ArrayList<Integer>();
	private ArrayList<Integer> aiPlayer1 = new ArrayList<Integer>();
	private ArrayList<Integer> aiPlayer2 = new ArrayList<Integer>();
	private ArrayList<Integer> aiPlayer3 = new ArrayList<Integer>();
	private ArrayList<Integer> aiPlayer4= new ArrayList<Integer>();
	//common deck
	private ArrayList<Integer> commonDeck = new ArrayList<Integer>();
	
	public void readStar() {
		
		try { 
			BufferedReader bfReader = new BufferedReader(new FileReader("StarCitizenDeck.txt"));
	        bfReader.readLine();//the header will not be read
	        String line = null; 
	        while((line=bfReader.readLine())!=null){ 
	            String item[] = line.split(" ");//data in file is divided by space
	            cardName.add(item[0]);
	            size.add(Integer.valueOf(item[1]));
	            speed.add(Integer.valueOf(item[2]));
	            range.add(Integer.valueOf(item[3]));
	            firepower.add(Integer.valueOf(item[4]));
	            cargo.add(Integer.valueOf(item[5]));
	        }      
	        bfReader.close();
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
	Collections.shuffle(cardNumber);
	}
	 
		
		public void ShareCards() {	
			for (int i = 0; i < cardNumber.size();i++) {
				if(i%5==0) {
					player.add(cardNumber.get(i));
				}else if(i%5==1) {
					aiPlayer1.add(cardNumber.get(i));
				}else if (i%5==2) {
					aiPlayer2.add(cardNumber.get(i));
				}else if (i%5==3) {
					aiPlayer3.add(cardNumber.get(i));
				}else {
					aiPlayer4.add(cardNumber.get(i));
				}
			}
		
		}
		
		
//////////////GETTERS////////////////////////
		public ArrayList<String> getCardName() {
			return cardName;
		}
		public ArrayList<Integer> getSize() {
			return size;
		}
		public ArrayList<Integer> getSpeed() {
			return speed;
		}
		public ArrayList<Integer> getRange() {
			return range;
		}
		public ArrayList<Integer> getFirepower() {
			return firepower;
		}
		public ArrayList<Integer> getCargo() {
			return cargo;
		}
		public ArrayList<Integer> getPlayer() {
			return player;
		}
		public ArrayList<Integer> getAIPlayer1() {
			return aiPlayer1;
		}	
		public ArrayList<Integer> getAIPlayer2() {
			return aiPlayer2;
		}	
		public ArrayList<Integer> getAIPlayer3() {
			return aiPlayer3;
		}	
		public ArrayList<Integer> getAIPlayer4() {
			return aiPlayer4;
		}

		public ArrayList<Integer> getCommonDeck() {
			return commonDeck;
		}

		public void setCommonDeck(ArrayList<Integer> commonDeck) {
			this.commonDeck = commonDeck;
		}
		
		
///////////////////////LOG////////////////////////////////////////
		
		
		public String cardLog() {
			String log = "\r\nComplete deck: " + card + "\r\n---------------------------------------"
					+ "\r\nShuffled deck: " + cardNumber + "\r\n---------------------------------------"
					+ "\r\nPlayer: " + player + "\r\n---------------------------------------"
					+ "\r\nAIPlayer1: " + aiPlayer1 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer2: " + aiPlayer2 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer3: " + aiPlayer3 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer4: " + aiPlayer4 + "\r\n---------------------------------------";
			return log;		
		}
		
		// creates new file and overwrites existing
		public void createLogFile() {
			FileWriter fileW = null;
			try {
				fileW = new FileWriter("toptrumps.log");
				fileW.write("---------------------------------------"
						  + "\r\n------------TOP TRUMPS LOG-------------"
						  + "\r\n---------------------------------------\n");
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(fileW!=null) 
					try {
						fileW.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
			}
		}//createLogFile-end

	// required data sent to print as it is created and appended to log file
		public void printToFile(String line) {
			FileWriter fileW = null;
			try {
				fileW = new FileWriter("toptrumps.log", true);
				fileW.append(line);
			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(fileW!=null) 
					try {
						fileW.close();
					}catch (IOException e) {
						e.printStackTrace();
					}
				}
		}//printToFile-end

		
	}


