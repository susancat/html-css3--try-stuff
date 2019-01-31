package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication  {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	
// system in scanner in main scope for use throughout class
	static Scanner input = new Scanner(System.in);
	
	//=========================================DB
	// variables for database
	   private static int numberOfGames=0;
	   private static int numberofHumanWin =0;
	   private static int numberofAIWin = 0;
	   private static int numberOfDraws =0;
	   private static int numberOfRounds = 0;
	 //=========================================DB

/////////////////////////////MAIN////////////////////////////////////
/*
 * The main method gives the user the option of playing the game or viewing statistics
 * it then calls the relevant method based on the users input. The user can also quit
 * the application by typing 'quit'
 */
	public static void main(String[] args) {
//		create instance so methods do not have to be static
		TopTrumpsCLIApplication topTrumps = new TopTrumpsCLIApplication();
		
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
	}//main-end
	
	
// variables for game information
	private String finalWinner;
	private String finalWinningCard;
	private int finalCategory;

///////////////////PLAY GAME/////////////////////////
	private void playGame(boolean logsToFile) {
		System.out.println("\nGame start\n");
		// State
		boolean exitGame = false; // flag to check whether the user wants to exit the game
		boolean playerIn = true;
		Deck deck = new Deck(); 
		dealCards(deck, logsToFile); 
		// choose first player randomly 
		Random rand = new Random();
		int firstPlay = rand.nextInt(4);
		// initialise round 1
		int round = 1;
		String activePlayer = deck.getPlayers().get(firstPlay).getPName();
		// Loop until the user wants to exit the game
		while (!exitGame) {
// check if player is still in game at the start of each round
			playerIn = checkPlayerIn(playerIn, deck);
			System.out.println("\nRound " + round + "\nPlayers have drawn their cards");
// if player in print card details
			if(playerIn) {
				String activeCardName = getActiveCardName(deck);
				String activeCardStats = getActiveCardStats(deck);
				System.out.println("\nYour card is " + activeCardName
											+ "\n" + activeCardStats);
			}
// place all players top cards in a common deck
			deck.setCommonDeck();
// determine who's turn it is to chose category										
			printWhosTurn(activePlayer, deck);
			
//	active player chooses category
			finalCategory = chooseCategory(activePlayer);
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
			
// winner is determine from category chosen
			finalWinner = setFinalWinner(deck, logsToFile);
			
//	add common deck to winners array and check for overall win or print draw
			if(finalWinner.equals("draw")) {
				System.out.println("This round was a draw, the common deck has " 
										+ deck.getCommonDeck().size() + " cards");
				// Database connection variable
				//=========================================DB
				numberOfDraws++;
				//=========================================DB
			}else {
				// add common deck to winners array
				addCommonDeck(deck);
				activePlayer = finalWinner;
				int winningCardIndex = winningCardIndex(deck);
				finalWinningCard = deck.getCardDeck().get(winningCardIndex).getCardName();
				// check for overall game win
				if (deck.getPlayers().size() == 1) {
					System.out.println("\n********GAME OVER********" + "\nThe overall winner was "+ finalWinner
							+ "\nThe winning card was " + finalWinningCard
							+ "\n" + deck.getCardDeck().get(winningCardIndex).toString(finalCategory));
					
					//=========================================DB
					if (finalWinner.startsWith("A")){
						numberofAIWin++;
						}else {
							numberofHumanWin++;
							numberOfGames++;
//							DatabaseConnect.DatabaseOpen();
//							DatabaseConnect.insertValues(numberOfGames,numberofHumanWin,numberofAIWin,numberOfDraws,numberOfRounds);
//							DatabaseConnect.DatabaseOpen();
					//=========================================DB
						}
					exitGame=true; // use this when the user wants to exit the game
				}else {
					System.out.println(finalWinner + " won this round\n"
							+ "The winning card was " + finalWinningCard
							+ "\n" + deck.getCardDeck().get(winningCardIndex).toString(finalCategory));
				}

			}
			round++;
			// Round get increment from here to database
			// Database connection variable 
			//=========================================DB
			numberOfRounds++;
			//=========================================DB
		// clear players top cards 
			deck.clearActiveCards();
		}//while-end
			
	}//playGame-end

///////////////DEAL CARDS////////////////////////////////////
	private Deck dealCards(Deck deck, boolean logsToFile) {
	// load, shuffle, and deal the cards
		deck.loadDeck();
		deck.shuffleCards();
		deck.ShareCards();
	//=============================================LOG
		if(logsToFile) {
			deck.createLogFile();
			deck.printToFile(deck.fullDeckLog());
		}
	//=============================================LOG
		return deck;
	}//deal-end
	
//////////////////WHOS TURN?////////////////////////////////////
	private void printWhosTurn(String activePlayer, Deck deck) {
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
	}//turn-end
	
	
///////////////////ADD COMMON DECK////////////////////////////
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
	}
	
//////////////CHOOSE CATEGORY//////////////////
	private int chooseCategory(String activePlayer) {
		int cat = -1;
		if(!activePlayer.startsWith("A")) {
			cat = input.nextInt();
		}else {
			Random rand = new Random();
			cat = rand.nextInt(5) + 1;
		}
		return cat;
	}//cat-end
	
/////////////PRINT CATEGORY/////////////////
	private String printCategory(int cat) {
		String category = "";
		if(cat == 1) {
			category = "Size";
		}else if(cat == 2) {
			category = "Speed";
		}else if(cat == 3) {
			category = "Range";
		}else if(cat == 4) {
			category = "Firepower";
		}else {
			category = "Cargo";
		}
		return category;
	}//print cat-end
	
//////////////SET WINNER/////////////////
	public String setFinalWinner(Deck deck, boolean logsToFile) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
		String winner = "";

		for(Player p : deck.getPlayers()) {
			scores.add(deck.getCardDeck().get(p.getHand().get(0)).getCatScores().get(finalCategory -1));
		}
		int maxScoreIndex = scores.indexOf(Collections.max(scores));
		Collections.sort(scores);
		int max = scores.get(scores.size() -1);
		if(scores.size() == 1) {
			winner = deck.getPlayers().get(maxScoreIndex).getPName();
		}else if(scores.get(scores.size() -2) == max) {
					winner = "draw";
		}else {
			winner = deck.getPlayers().get(maxScoreIndex).getPName();
		}
		//================================================================LOG
		if(logsToFile) deck.printToFile(deck.printWinner(winner, maxScoreIndex, printCategory(finalCategory), finalCategory));
		//================================================================LOG	
		return winner;
	}// setWinner-end
	
////////////////CHECK PLAYER IN GAME/////////////////////
	public Boolean checkPlayerIn(boolean playerIn, Deck deck) {
		playerIn = false;
		for(Player p : deck.getPlayers()) {
			if(!p.getPName().startsWith("A")) {
				playerIn = true;
			}
		}
		return playerIn;
	}// checkPlayer-end
	
//////////////SET WINNING CARD//////////////////////	
	public int winningCardIndex(Deck deck) {
		int cardIndex = 0;
		for(Player p : deck.getPlayers()) {
			if(p.getPName().equals(this.finalWinner)) {
				cardIndex = p.getHand().get(0);
			}
		}
		return cardIndex;
	}// winningCard-end
	
/////////////////GET ACTIVE CARD NAME/////////////////
	public String getActiveCardName(Deck deck) {
		String activeCardName = deck.getCardDeck().get(deck.getPlayers().get(0).getHand().get(0)).getCardName();
		return activeCardName;
	}// getCardName-end
	
//////////////////GET ACTIVE CARD STATS//////////////
	public String getActiveCardStats(Deck deck) {
		String activeCardStats = deck.getCardDeck().get(deck.getPlayers().get(0).getHand().get(0)).toString(0);
		return activeCardStats;
	}//getStats-end

///////////////////PRINT GAME STATISTICS////////////////
	private void printStats() {
		//=========================================DB
//		DatabaseConnect.DatabaseOpen();
//		DatabaseConnect.GameNumberdb();
//		DatabaseConnect.DatabaseClose();
		//=========================================DB
		return;
	}//printStats-end




}//	TopTrumpsCLIApplication-END
