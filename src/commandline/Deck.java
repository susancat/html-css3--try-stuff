package commandline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Deck {
	//use a HashMap to link 40 cards with position 0~39th 
	private HashMap<Integer, String> card = new HashMap<Integer, String>();
	//ArrayList deck used to store the Description of cards 
	private ArrayList<String> cardName = new ArrayList<String>();
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
	for (String name: cardName) {
		card.put(index,cardName.get(index));
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

		/*
 * 		setCommonDeck loads the card number of index 0 of each player in to
 * 		the corresponding indexes of the array. The numbers are loaded in reverse 
 * 		order in to the lefthand side of the array (0) so that 0-4 of the common deck 
 * 		always points to the correct player, if we agree that 0 = player and 1-4 are used 
 *		for the ai players
 */
		public void setCommonDeck() {
			commonDeck.add(0, aiPlayer4.get(0));
			aiPlayer4.remove(0);
			commonDeck.add(0, aiPlayer3.get(0));
			aiPlayer3.remove(0);
			commonDeck.add(0, aiPlayer2.get(0));
			aiPlayer2.remove(0);
			commonDeck.add(0, aiPlayer1.get(0));
			aiPlayer1.remove(0);
			commonDeck.add(0, player.get(0));
			player.remove(0);
		}
		
		
///////////////////////LOGS////////////////////////////////////////
		
		
		public String fullDeckLog() {
			String log = "\r\n------------DECK LOG-------------------"
					+ "\r\nComplete deck: " + card + "\r\n---------------------------------------"
					+ "\r\nShuffled deck: " + cardNumber + "\r\n---------------------------------------";
			return log;
		}
		
		public String playersDeckLog() {
			String log = "\r\n-----PLAYERS DECK LOG------------------"
					+"\r\nPlayer: " + player + "\r\n---------------------------------------"
					+ "\r\nAIPlayer1: " + aiPlayer1 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer2: " + aiPlayer2 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer3: " + aiPlayer3 + "\r\n---------------------------------------"
					+ "\r\nAIPlayer4: " + aiPlayer4 + "\r\n---------------------------------------";
			return log;		
		}
		
		public String activeCardsLog() {
			String log = "\r\n---------ACTIVE CARD LOG---------------"
					+ "\r\nPlayer: " + cardName.get(player.get(0)) + ", size: " + size.get(player.get(0)) 
					+ ", speed: " + speed.get(player.get(0)) + ", range: " + range.get(player.get(0))
					+ ", firepower: " + firepower.get(player.get(0)) + ", cargo: " + cargo.get(player.get(0))
					+ "\r\n---------------------------------------"
					+ "\r\nAIPlayer1: " + cardName.get(aiPlayer1.get(0)) + ", size: " + size.get(aiPlayer1.get(0)) 
					+ ", speed: " + speed.get(aiPlayer1.get(0)) + ", range: " + range.get(aiPlayer1.get(0))
					+ ", firepower: " + firepower.get(aiPlayer1.get(0)) + ", cargo: " + cargo.get(aiPlayer1.get(0))
					+ "\r\n---------------------------------------"
					+ "\r\nAIPlayer2: " + cardName.get(aiPlayer2.get(0)) + ", size: " + size.get(aiPlayer2.get(0)) 
					+ ", speed: " + speed.get(aiPlayer2.get(0)) + ", range: " + range.get(aiPlayer2.get(0))
					+ ", firepower: " + firepower.get(aiPlayer2.get(0)) + ", cargo: " + cargo.get(aiPlayer2.get(0))
					+ "\r\n---------------------------------------"
					+ "\r\nAIPlayer3: " + cardName.get(aiPlayer3.get(0)) + ", size: " + size.get(aiPlayer3.get(0)) 
					+ ", speed: " + speed.get(aiPlayer3.get(0)) + ", range: " + range.get(aiPlayer3.get(0))
					+ ", firepower: " + firepower.get(aiPlayer3.get(0)) + ", cargo: " + cargo.get(aiPlayer3.get(0))
					+ "\r\n---------------------------------------"
					+ "\r\nAIPlayer4: " + cardName.get(aiPlayer4.get(0)) + ", size: " + size.get(aiPlayer4.get(0)) 
					+ ", speed: " + speed.get(aiPlayer4.get(0)) + ", range: " + range.get(aiPlayer4.get(0))
					+ ", firepower: " + firepower.get(aiPlayer4.get(0)) + ", cargo: " + cargo.get(aiPlayer4.get(0))
					+ "\r\n---------------------------------------";
			return log;
		}
		
		public String printCommonDeck() {
			String log = "\r\n-------------COMMON DECK---------------"
					+ "\r\nCommon deck: " + commonDeck + "\r\n---------------------------------------";
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


