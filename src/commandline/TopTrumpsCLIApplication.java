package commandline;

import java.io.FileWriter;
import java.io.IOException;
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
		
		boolean writeGameLogsToFile = false; // Should we write game logs to file?
//		if (args[0].equalsIgnoreCase("true")) {
//			writeGameLogsToFile=true; // Command line selection
//			topTrumps.createLogFile();
//		}
		
		Scanner input = new Scanner(System.in);
		
		while(!userWantsToQuit) {
		System.out.println("Do you want to see past results or play a game?\n"
				+ "\t1: Print Game Statistics\n\t2: Play game\n"
				+ "Enter the number for your selection"
				+ "\nor type 'quit' to exit application:");
		String choice = input.next();
		if(choice.equals("1")) {
			topTrumps.playGame(writeGameLogsToFile);
		}
		else if(choice.equals("2")) {
			topTrumps.printStats();
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
//		// State
//				boolean exitGame = false; // flag to check whether the user wants to exit the game
//				
//				// Loop until the user wants to exit the game
//				while (!exitGame) {
//					
//					// ----------------------------------------------------
//					// Add your game logic here based on the requirements
//					// ----------------------------------------------------
//					
//					exitGame=true; // use this when the user wants to exit the game
//				}//while-end
		
		return;
	}//playGame-end
	
	public void printStats() {
//--------------------------------
// DATABASE WIZARDRY TO GO HERE	
//--------------------------------
		System.out.println("stats");
		return;
	}//printStats-end
	

// creates new file and overwrites existing
	void createLogFile() {
		FileWriter fileW = null;
		try {
			fileW = new FileWriter("toptrumps.log");
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
