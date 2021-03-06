package commandline;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication  {
	
//====================================MAIN=======================================
/**
* This main method is called by TopTrumps.java when the user specifies that they want to run in
* command line mode. The contents of args[0] is whether we should write game logs to a file.
* The main method gives the user the option of playing the game or viewing statistics
* it then calls the relevant method based on the users input. The user can also quit
* the application by typing 'quit'
* @param args
* 
*/
	public static void main(String[] args) {
		// create instance so methods do not have to be static
		TopTrumpsCLIApplication topTrumps = new TopTrumpsCLIApplication();
		// If the game database doesn't exist, a new one is created
		topTrumps.db = new DatabaseConnect();
		try {
			topTrumps.db.createTable();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) {
			writeGameLogsToFile=true; // Command line selection
		}
		
		while(!userWantsToQuit) {
			System.out.println("\nDo you want to see past results or play a game?\n"
								+ "\t1: Print Game Statistics\n\t2: Play game\n"
								+ "Enter the number for your selection"
								+ "\nor type 'quit' to exit application:");
			String choice = input.next();
			if(choice.equals("1")) {
				topTrumps.printStats();
			}
			else if(choice.equals("2")) {
				topTrumps.playGame(writeGameLogsToFile);
			}
			else if(choice.equals("quit")) {
				System.out.println("You exited the game");
				userWantsToQuit = true;
			}
		}
		input.close();
		System.exit(0);
	}//===============================main-end
	
	// system-in scanner in main scope for use throughout class when needed
	static Scanner input = new Scanner(System.in);
		
	//=========================================DB
	// variables for database
	private DatabaseConnect db;
	private int humanRoundWin =0;
	private int ai1RoundWin = 0;
	private int ai2RoundWin = 0;
	private int ai3RoundWin = 0;
	private int ai4RoundWin = 0;
	private int numberOfDraws = 0;
	private int numberOfRounds = 0;
	//=========================================DB
	
	// variables for game information
	private int aiPlayers = 4;
	private int round;
	private String activePlayer;
	private String roundWinner;
	private String finalWinner;
	private String finalWinningCard;
	private int winningCardIndex;
	private int finalCategory;
	private boolean exitGame = false; // flag to check whether the user wants to exit the game
	private boolean playerIn = true; // flag to check if human player is stiil in
	
//============================PLAY GAME===================================
/**
 * After the intitial set-up for the first round, the playGame method loops each round until 
 * a winner is decided or the user quits
 * @param logsToFile
 */
	private void playGame(boolean logsToFile) {
		exitGame = false;
		System.out.println("\nGame start\n");
		Deck deck = new Deck(); 		//create deck 
		dealCards(deck, logsToFile); 	//and deal cards
		int firstPlay = firstPlay();	//choose first player randomly 
		round = 1;						//initialise round 1
		activePlayer = deck.getPlayers().get(firstPlay).getPName(); //set active player
		//================= start of game loop
		while (!exitGame) {
			playerIn = checkPlayerIn(deck); //At the beginning of each round checks are performed to see
			checkWin(deck);					//if the human player is still in the game and if anybody has won
			if(exitGame) {
				System.out.println("\n********GAME OVER********" + "\nThe overall winner was "+ finalWinner
									+ "\nThe winning card was " + finalWinningCard
									+ "\n" + deck.getCardDeck().get(winningCardIndex).toString(finalCategory));
				break;// exit the game loop
			}
			//then the option is given to continue to next round or end game
			if(!(round == 1)) {
				if(playerIn) {
					System.out.println("Enter any key to continue game, or type 'end' to exit");
					String s = input.next();
					if(s.equals("end")) {
						resetCounters();//reset database counters on exit
						break;//exit game loop
					}
				}
			}
			System.out.println("\nRound " + round + "\nPlayers have drawn their cards");
			
			if(playerIn) { printActiveCard(deck); }	// if player in print card details
			deck.setCommonDeck();					// place all players top cards in a common deck
			printWhosTurn(deck);					// determine who's turn it is to chose category	
			finalCategory = chooseCategory(deck);	//	active player chooses category
			System.out.println("The category is " + printCategory(finalCategory));
			
			//============================================LOG
			if(logsToFile) {
				deck.printToFile(deck.printRoundNumber(round));
				deck.printToFile(deck.playersDeckLog());
				deck.printToFile(deck.printCommonDeck());
				deck.printToFile(deck.activeCardsLog());
				deck.printToFile(deck.printFinalCategory(printCategory(finalCategory), finalCategory));
			}
			//=============================================LOG
		
			setWinner(deck, logsToFile);// winner is determine from category chosen
			//check for draw
			if(roundWinner.equals("draw")) {
				System.out.println("This round was a draw, the common deck has " 
										+ deck.getCommonDeck().size() + " cards");
	
			}else {
				finalWinner = roundWinner;
				activePlayer = roundWinner;	// set active player
				addCommonDeck(deck);		// add common deck to winners array
				winningCardIndex = winningCardIndex(deck);
				finalWinningCard = deck.getCardDeck().get(winningCardIndex).getCardName();
				System.out.println(finalWinner + " won this round\n"
							+ "The winning card was " + finalWinningCard
							+ "\n" + deck.getCardDeck().get(winningCardIndex).toString(finalCategory));
			}
			round++;//Round gets incremented here for next round in game
			//=========================================DB
			numberOfRounds++;// Number of rounds played gets incremented for database
			//=========================================DB
			deck.clearActiveCards();// clear players top cards 
		}//======end of game loop
			
	}//===========================================================playGame-end

//=====================DEAL CARDS===========================
/**
 * Methods are called from Deck class to load cards in to deck from file,
 * shuffle the cards, and then deal the cards between players
 * @param deck
 * @param logsToFile
 * @return
 */
	public Deck dealCards(Deck deck, boolean logsToFile) {
		deck.loadDeck();
		deck.shuffleCards();
		deck.ShareCards(aiPlayers);
	//=============================================LOG
		if(logsToFile) {
			deck.createLogFile();
			deck.printToFile(deck.fullDeckLog());
		}
	//=============================================LOG
		return deck;
	}//===========deal-end
	
//==========FIRST PLAY=================
/**
 * Method to pick first player at random from numbers
 * between 0-4 (representing array indexes)
 * @return
 */
	public int firstPlay() {
		Random rand = new Random();
		int firstPlay = rand.nextInt(4);
		return firstPlay;
	}//==========first-play-end
	
//===========================WHOS TURN?==========================
/**
 * Method to print who's turn it is.
 * @param deck
 */
	public void printWhosTurn(Deck deck) {
		if(!activePlayer.startsWith("A")) {
			System.out.println("It's your turn to choose a category, your choices are:\n"
								+ "\t1. Size\n"
								+ "\t2. Speed\n"
								+ "\t3. Range\n"
								+ "\t4. Firepower\n"
								+ "\t5. Cargo\n"
								+ "Enter the number for your attribute: ");
		}else {
			System.out.println("It's the computers turn to choose a category");
		}
	}//=============turn-end
	
	
//=========================ADD COMMON DECK===========================
/**
 * Method adds the contents of the common deck to the winners array
 * and then clears it for next round
 * @param deck
 */
	public void addCommonDeck(Deck deck) {
		int i = 0;
		for(Player p : deck.getPlayers()) {
			if(p.getPName().equals(finalWinner)) {
				for(int j  : deck.getCommonDeck()) {
					p.getHand().add((deck.getCommonDeck().get(i)));
					i++;
				}
			}
		}
		deck.getCommonDeck().clear();
	}//=========common-deck-end
	
//=====================CHOOSE CATEGORY===========================
/**
 * method to choose category. If the active players name does not begin with 'A'
 * i.e. not AI, then the user must input a number between 1 and 5 otherwise a random number
 * between 1 and 5 is chosen by computer (alternatively you could have the computer choosing
 * the max category score from the AI's active card, but its not always best to choose the highest
 * score when playing the game. Potential here for some interesting category choice algorithms!)
 * @param deck
 * @return
 */
	public int chooseCategory(Deck deck) {
		int cat = -1;
		if(!activePlayer.startsWith("A")) {
			while(true) {
                try {
                    cat = input.nextInt();
                    } catch(InputMismatchException e) {
                        input.nextLine();
                    }
                if(cat == 1 || cat == 2 || cat == 3 || cat == 4 || cat == 5) {
                	break;
                }
                else {
                	System.out.println("Please enter whole number between 1 to 5");
                }
            }
		}else {
			Random rand = new Random();
			cat = rand.nextInt(5) + 1;
		}
		return cat;
	}//===============cat-end
	
//=============PRINT CATEGORY==================
/**
 * method to print category name of chosen category
 * @param cat
 * @return
 */
	private String printCategory(int cat) {
		String category = "";
		if(cat == 1) category = "Size";
		if(cat == 2) category = "Speed";
		if(cat == 3) category = "Range";
		if(cat == 4) category = "Firepower";
		if(cat == 5) category = "Cargo";
		return category;
	}//====================print cat-end
	
//===================SET WINNER====================
/**
 * This method first loops through the players array and adds
 * the score of the chosen category to a new scores array by finding
 * the card in cardDeck that matches the players first card and getting the 
 * score from the index of the cards catScores array, given by finalCatergory
 * minus 1 as category is passed as number 1-5, but array index is 0-4 
 * @param deck
 * @param logsToFile
 * @return
 */
	public void setWinner(Deck deck, boolean logsToFile) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
	
		for(Player p : deck.getPlayers()) {
			scores.add(deck.getCardDeck().get(p.getHand().get(0)).catScores().get(finalCategory -1));
		}
		int maxScoreIndex = scores.indexOf(Collections.max(scores));
		Collections.sort(scores);
		int max = scores.get(scores.size() -1);
		if(scores.size() == 1) {
			roundWinner = deck.getPlayers().get(maxScoreIndex).getPName();
		}else if(scores.get(scores.size() -2) == max) {
					roundWinner = "draw";
		}else {
			roundWinner = deck.getPlayers().get(maxScoreIndex).getPName();
		}
		//================================================================LOG
		if(logsToFile) deck.printToFile(deck.printWinner(roundWinner, maxScoreIndex, printCategory(finalCategory), finalCategory));
		//================================================================LOG	
		incrementGameVariables();
	}//=======================setWinner-end
	
//========INCREMENT DATABASE VARIABLES FOR GAME STATS======
/**
 * Method increment variables for game statistics
 */
	public void incrementGameVariables() {
		if(roundWinner.equals("You")) {
			humanRoundWin++;
		}else if(roundWinner.charAt(roundWinner.length() -1) == '1') {
			ai1RoundWin++;
		}else if(roundWinner.charAt(roundWinner.length() -1) == '2') {
			ai2RoundWin++;
		}else if(roundWinner.charAt(roundWinner.length() -1) == '3') {
			ai3RoundWin++;
		}else if(roundWinner.charAt(roundWinner.length() -1) == '4') {
			ai4RoundWin++;
		}else {
			numberOfDraws++;
		}
	}//====increment-var-end
	
	
//==============CHECK FOR WIN=======================
/**
 * A method to check if anybody has won the game.
 * It does this simply by checking if there is only one
 * player left in the players array
 */
	public void checkWin(Deck deck) {
		if (deck.getPlayers().size() == 1) {
			insertDbValues();//insert stats
			resetCounters();//reset database counters for next game
			exitGame=true; 
		}
	}//=====================checkWin-end
	
//===================RESET DB COUNTERS
/**
 * Method to reset database counters.
 */
	public void resetCounters() {
		//numberOfRounds = 0;
		humanRoundWin = 0;
		ai1RoundWin = 0;
		ai2RoundWin = 0;
		ai3RoundWin = 0;
		ai4RoundWin = 0;
		numberOfDraws = 0;
		numberOfRounds = 0;
	}//===reset-end
	
//===============CHECK PLAYER IN GAME=======================
/**
 * Methods loops through players array to see if any players names
 * do not start with 'A' (as in AI)
 * @param deck
 * @return
 */
	public Boolean checkPlayerIn(Deck deck) {
		playerIn = false;
		for(Player p : deck.getPlayers()) {
			if(!p.getPName().startsWith("A")) {
				playerIn = true;
			}
		}
		return playerIn;
	}//===============checkPlayer-end
	
//==============SET WINNING CARD INDEX=====================
/**
 * This method finds the index by finding the player whose name
 * matches finalWinner and retrieving the contents of index0 of 
 * their hand array, which is an index number for the corresponding
 * card in cardDeck array  
 * @param deck
 * @return
 */
	public int winningCardIndex(Deck deck) {
		int cardIndex = 0;
		for(Player p : deck.getPlayers()) {
			if(p.getPName().equals(this.finalWinner)) {
				cardIndex = p.getHand().get(0);
			}
		}
		return cardIndex;
	}//==============winningCard-end
	
//===================PRINT ACTIVE CARD=========================
/**
 * The method gets the card name and attributes of the card obj in cardDeck array 
 * with matching index to first card in players hand array
 * @param deck
 * @return
 */
	public void printActiveCard(Deck deck) {
		String activeCardName = deck.getCardDeck().get(deck.getPlayers().get(0).getHand().get(0)).getCardName();
		String activeCardStats = deck.getCardDeck().get(deck.getPlayers().get(0).getHand().get(0)).toString(0);
		System.out.println("\nYour card is " + activeCardName
				+ "\n" + activeCardStats);
	}//=============printCard-end
	
	
//===============INSERT VALUES TO DATABASE====================
/**
 * Method for inserting game statistics to database
 */
	public void insertDbValues() {
		try {
			db.DatabaseOpen();
			db.insertValues(humanRoundWin, ai1RoundWin, ai2RoundWin, ai3RoundWin, ai4RoundWin, numberOfDraws, numberOfRounds, finalWinner);
			db.DatabaseOpen();
			}catch(SQLException e) { e.printStackTrace(); }
	}//=====================insert-end

//=====================PRINT GAME STATISTICS==================
/**
 * Method for printing statistics. Throws a Null pointer if there
 * is no database, in which case we let the user know to play a game, 
 * which will create a database if one does not exist
 */
	public void printStats() {
		//=========================================DB
		try {
		db.DatabaseOpen();
		System.out.println("\n-----------Game Stats-----------"
				+ "\nNumber of games played overall: " + db.totalNumberGames()
				+ "\nHow many times the computer has won: " + db.totalAIWins()
				+ "\nHow many times the human has won: " + db.totalHumanWins()
				+ "\nThe average number of draws: " + db.avgNumberDraws()
				+ "\nThe largest number of rounds played in a single game: " + db.maxGameLength()
				+ "\n--------------------------------\n");
		db.DatabaseClose();
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(NumberFormatException e){
			System.out.println("You need to complete a game first!");
		}
		//=========================================DB
		return;
	}//===============printStats-end
	
	
//=========GETTERS AND SETTERS=====================
/**
 * Getters and setters are for the variables that we want to pass 
 * when we create game objects in restAPI
 * @return
 */
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public String getActivePlayer() {
		return activePlayer;
	}
	public void setActivePlayer(String activePlayer) {
		this.activePlayer = activePlayer;
	}
	public String getRoundWinner() {
		return roundWinner;
	}
	public void setRoundWinner(String roundWinner) {
		this.roundWinner = roundWinner;
	}
	public String getFinalWinner() {
		return finalWinner;
	}
	public void setFinalWinner(String finalWinner) {
		this.finalWinner = finalWinner;
	}
	public String getFinalWinningCard() {
		return finalWinningCard;
	}
	public void setFinalWinningCard(String finalWinningCard) {
		this.finalWinningCard = finalWinningCard;
	}
	public int getWinningCardIndex() {
		return winningCardIndex;
	}
	public void setWinningCardIndex(int winningCardIndex) {
		this.winningCardIndex = winningCardIndex;
	}
	public int getFinalCategory() {
		return finalCategory;
	}
	public void setFinalCategory(int finalCategory) {
		this.finalCategory = finalCategory;
	}
	public boolean getExitGame() {
		return exitGame;
	}
	public void setExitGame(boolean exitGame) {
		this.exitGame = exitGame;
	}
	public boolean isPlayerIn() {
		return playerIn;
	}
	public void setPlayerIn(boolean playerIn) {
		this.playerIn = playerIn;
	}

//=================================================g&s-end

	


}//	TopTrumpsCLIApplication-END
