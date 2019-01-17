package commandline;

import java.util.ArrayList;
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
	
	public static void main(String[] args) {
//		create object
		TopTrumpsCLIApplication topTrumps = new TopTrumpsCLIApplication();
		
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		boolean writeGameLogsToFile = true; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) {
//			writeGameLogsToFile=true; // Command line selection
			
//		}
		
		Scanner input = new Scanner(System.in);
		
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
			userWantsToQuit = true;
		}
		}
		input.close();
		System.exit(0);
	}//main-end

	public void playGame(boolean logsToFile) {
		System.out.println("\nGame start\n");
		// State
		boolean exitGame = false; // flag to check whether the user wants to exit the game
		Deck deck = new Deck(); 
		dealCards(deck, logsToFile); 
		// choose first player randomly
		Random rand = new Random();
		int firstPlay = rand.nextInt(5) + 1;
		System.out.println(firstPlay);	
		// initialise round 1
		int round = 1;
		int activePlayer = firstPlay;
		// Loop until the user wants to exit the game
		while (!exitGame) {
			
			System.out.println("Round " + round);
			printWhosTurn(activePlayer);
		// code for testing		
			System.out.println("round again?");
			Scanner input = new Scanner(System.in);
			String answer = input.nextLine();
			System.out.println("who won?");
			activePlayer = input.nextInt();
			if(answer.equals("n")) {
				exitGame=true; // use this when the user wants to exit the game
				deck.printToFile(deck.cardLog());
			}else {
				round++;
			}
				}//while-end
		
		return;
	}//playGame-end
	
	public Deck dealCards(Deck deck, boolean logsToFile) {
	// load, shuffle, and deal the cards
		deck.readStar();
		deck.shuffleCards();
		deck.ShareCards();
		// if flag selected print info to log
		if(logsToFile) {
			deck.createLogFile();
			deck.printToFile(deck.cardLog());
		}
		return deck;
	}
	
	public void printWhosTurn(int player) {
		if(player == 1) {
			System.out.println("It's your turn to choose a category");
		}else {
			System.out.println("It's the computers turn to choose a category");
		}
	}
	
	
	
	public void printStats() {
//--------------------------------
// DATABASE WIZARDRY TO GO HERE	
//--------------------------------
		System.out.println("these are stats");
		return;
	}//printStats-end
	


}
