package commandline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	
// system in scanner in main scope for use throughout class
	static Scanner input = new Scanner(System.in);

///////////////////MAIN////////////////////////////////////
	public static void main(String[] args) {
//		create instance so methods do not have to be static
		TopTrumpsCLIApplication topTrumps = new TopTrumpsCLIApplication();
		
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		boolean writeGameLogsToFile = true; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) {
//			writeGameLogsToFile=true; // Command line selection
//		}
		
		while(!userWantsToQuit) {
		System.out.println("Do you want to see past results or play a game?\n"
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
		 // DatabaseConnect.DatabaseOpen();
		 // DatabaseConnect.insertValues(NumberofGames,HumanWin,AiWin,NumberofDraws,LengthofGames);
		 // DatabaseConnect.DatabaseOpen();
			userWantsToQuit = true;
			
		}
		}
		input.close();
		System.exit(0);
	}//main-end

///////////////////PLAY GAME/////////////////////////
	private void playGame(boolean logsToFile) {
		System.out.println("\nGame start\n");
		// State
		boolean exitGame = false; // flag to check whether the user wants to exit the game
		Deck deck = new Deck(); 
		dealCards(deck, logsToFile); 
		// choose first player randomly
		Random rand = new Random();
		int firstPlay = rand.nextInt(5) + 1;
		// initialise round 1
		int round = 1;
		int activePlayer = firstPlay;
		// Loop until the user wants to exit the game
		while (!exitGame) {
			System.out.println("Round " + round + "\nPlayers have drawn their cards");
			printActiveCard(deck, logsToFile);
			printWhosTurn(activePlayer);
			if(logsToFile) {
				deck.printToFile(deck.playersDeckLog());
				deck.printToFile(deck.activeCardsLog());
			}
			deck.setCommonDeck();
			if(logsToFile) deck.printToFile(deck.printCommonDeck());
			int category = chooseCategory(activePlayer);
			System.out.println("The category is " + printCategory(category));
			int winnerIndex = roundWinner(category, deck);
			if(winnerIndex == -1) {
				System.out.println("This round was a draw, common pile has " 
						+ deck.getCommonDeck().size() + " cards");
			}else {
				printRoundWinner(winnerIndex, deck);
				printWinningCard(category, winnerIndex, deck);
			}
		
			
			
			
			
		// code for testing		
			System.out.println("round again?");
			String answer = input.nextLine();
			System.out.println("who won?");
			activePlayer = input.nextInt();
			if(answer.equals("n")) {
				exitGame=true; // use this when the user wants to exit the game
			}else {
				round++;
			}
				}//while-end
		
		return;
	}//playGame-end
	
///////////////DEAL CARDS////////////////////////////////////
	private Deck dealCards(Deck deck, boolean logsToFile) {
	// load, shuffle, and deal the cards
		deck.readStar();
		deck.shuffleCards();
		deck.ShareCards();
		// if flag selected print info to log
		if(logsToFile) {
			deck.createLogFile();
			deck.printToFile(deck.fullDeckLog());
		}
		return deck;
	}//deal-end
	
//////////////////WHOS TURN?////////////////////////////////////
	private void printWhosTurn(int player) {
		if(player == 1) {
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
	
////////////////////////////ACTIVE CARD///////////////////////////////	
	private void printActiveCard(Deck deck, boolean logsToFile) {
		int activeCard = deck.getPlayer().get(0);
		System.out.println("You drew: " + deck.getCardName().get(activeCard) 
							+ "\n\t> Size: " + deck.getSize().get(activeCard)
							+ "\n\t> Speed: " + deck.getSpeed().get(activeCard)
							+ "\n\t> Range: " + deck.getRange().get(activeCard)
							+ "\n\t> Firepower: " + deck.getFirepower().get(activeCard)
							+ "\n\t> Cargo: " + deck.getCargo().get(activeCard));
	}//active-end
	
//////////////CATEGORY//////////////////
	private int chooseCategory(int ap) {
		int cat = -1;
		if(ap == 1) {
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
	
//////////////WINNER OF ROUND/////////////////////
	private int roundWinner(int cat, Deck deck) {
		ArrayList<Integer> scores = new ArrayList<Integer>();
			for(int i=0; i <= 4; i++) {
				if(cat == 1) {
					scores.add(deck.getSize().get(deck.getCommonDeck().get(i)));
				}else if(cat== 2) {
					scores.add(deck.getSpeed().get(deck.getCommonDeck().get(i)));
				}else if(cat == 3) {
					scores.add(deck.getRange().get(deck.getCommonDeck().get(i)));
				}else if(cat == 4) {
					scores.add(deck.getFirepower().get(deck.getCommonDeck().get(i)));
				}else {
					scores.add(deck.getCargo().get(deck.getCommonDeck().get(i)));
				}
			}
		int winner = scores.indexOf(Collections.max(scores));
		for(int i = 0; i <= 4; i++) {
			if(scores.get(i) == scores.get(winner) && i != winner) {
				winner = -1;
				break;
			}
		}
		return winner;
	}//round win-end
	
///////////////PRINT ROUND WINNER////////////////
	private void printRoundWinner(int index, Deck deck) {
		String winner = "";
		if(index == 0) {
			winner = "You are ";
		}else if(index == 1) {
			winner = "AI player 1 is ";
		}else if(index == 2) {
			winner = "AI player 2 is ";
		}else if(index == 3) {
			winner = "AI player 3 is ";
		}else winner = "AI player 4 is ";
		System.out.println(winner + "the winner of this round\n"
				+ "The winning card is " + deck.getCardName().get(deck.getCommonDeck().get(index)));
	}//print round win-end


	private void printWinningCard(int cat, int index, Deck deck) {
		if(cat == 1) {
			System.out.println("\t> Size: " + String.valueOf(deck.getSize().get(deck.getCommonDeck().get(index))) + " <----\n"
					+ "\t> Speed: " + String.valueOf(deck.getSpeed().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Range: " + String.valueOf(deck.getRange().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Firepower: " + String.valueOf(deck.getFirepower().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Cargo: " + String.valueOf(deck.getCargo().get(deck.getCommonDeck().get(index))));
		}else if(cat == 2) {
			System.out.println("\t> Size: " + String.valueOf(deck.getSize().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Speed: " + String.valueOf(deck.getSpeed().get(deck.getCommonDeck().get(index))) + " <----\n"
					+ "\t> Range: " + String.valueOf(deck.getRange().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Firepower: " + String.valueOf(deck.getFirepower().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Cargo: " + String.valueOf(deck.getCargo().get(deck.getCommonDeck().get(index))));
		}else if(cat == 3) {
			System.out.println("\t> Size: " + String.valueOf(deck.getSize().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Speed: " + String.valueOf(deck.getSpeed().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Range: " + String.valueOf(deck.getRange().get(deck.getCommonDeck().get(index))) + " <----\n"
					+ "\t> Firepower: " + String.valueOf(deck.getFirepower().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Cargo: " + String.valueOf(deck.getCargo().get(deck.getCommonDeck().get(index))));
		}else if(cat == 4) {
			System.out.println("\t> Size: " + String.valueOf(deck.getSize().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Speed: " + String.valueOf(deck.getSpeed().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Range: " + String.valueOf(deck.getRange().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Firepower: " + String.valueOf(deck.getFirepower().get(deck.getCommonDeck().get(index))) + " <----\n"
					+ "\t> Cargo: " + String.valueOf(deck.getCargo().get(deck.getCommonDeck().get(index))));
		}else {
			System.out.println("\t> Size: " + String.valueOf(deck.getSize().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Speed: " + String.valueOf(deck.getSpeed().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Range: " + String.valueOf(deck.getRange().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Firepower: " + String.valueOf(deck.getFirepower().get(deck.getCommonDeck().get(index))) + "\n"
					+ "\t> Cargo: " + String.valueOf(deck.getCargo().get(deck.getCommonDeck().get(index))) + " <----");
		}
	
}

///////////////////PRINT GAME STATISTICS////////////////
	private void printStats() {
//--------------------------------
// DATABASE WIZARDRY TO GO HERE	
//--------------------------------
		System.out.println("these are stats");
		return;
	}//printStats-end



}
