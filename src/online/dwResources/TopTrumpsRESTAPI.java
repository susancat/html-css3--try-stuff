package online.dwResources;
import commandline.*;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
	TopTrumpsCLIApplication topTrumps;
	Deck deck;
	Boolean logsToFile = false;
	DatabaseConnect db;
	
	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) throws Exception{
		this.topTrumps = new TopTrumpsCLIApplication();
		this.deck = new Deck();
		topTrumps.dealCards(deck, logsToFile);
		topTrumps.setActivePlayer(deck.getPlayers().get(0).getPName());
		topTrumps.setRound(1);
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("http://localhost:7777/toptrumps"));
	}
	

//======================GET ACTIVE CARDS==================================	
	@GET
	@Path("/playerActiveCard") 
	/**
	 * This method first checks whether the player is still in the game using
	 * the checkForPlayer method. It then either passes 'o' flag or the 
	 * first card obj in the players array as a String.
	 * @return
	 * @throws IOException
	 */
	public String activePCard() throws IOException {
		String json;
		String pName = "You";
		int index = checkForPlayer(pName);
		if(index == -1) { json= "o"; }
		else { Card c = deck.getCardDeck().get(deck.getPlayers().get(index).getHand().get(0)); 
		json = oWriter.writeValueAsString(c); }
		return json;
	}
	// As above for AI1
	@GET
	@Path("/ai1ActiveCard")
	public String activeAI1Card() throws IOException {
		String json;
		String pName = "AIPlayer1";
		int index = checkForPlayer(pName);
		if(index == -1) { json = "o"; }
		else { Card c = deck.getCardDeck().get(deck.getPlayers().get(index).getHand().get(0)); 
		json = oWriter.writeValueAsString(c); }
		return json;
	}
	// As above for AI2
	@GET
	@Path("/ai2ActiveCard")
	public String activeAI2Card() throws IOException {
		String json;
		String pName = "AIPlayer2";
		int index = checkForPlayer(pName);
		if(index == -1) { json = "Player out!"; }
		else { Card c = deck.getCardDeck().get(deck.getPlayers().get(index).getHand().get(0)); 
		json = oWriter.writeValueAsString(c); }
		return json;
	}
	// AS above for AI3
	@GET
	@Path("/ai3ActiveCard")
	public String activeAI3Card() throws IOException {
		String json;
		String pName = "AIPlayer3";
		int index = checkForPlayer(pName);
		if(index == -1) { json = "Player out!"; }
		else { Card c = deck.getCardDeck().get(deck.getPlayers().get(index).getHand().get(0)); 
		json = oWriter.writeValueAsString(c); }
		return json;
	}
	// AS above for AI4
	@GET
	@Path("/ai4ActiveCard")
	public String activeAI4Card() throws IOException {
		String json;
		String pName = "AIPlayer4";
		int index = checkForPlayer(pName);
		if(index == -1) { json = "Player out!"; }
		else { Card c = deck.getCardDeck().get(deck.getPlayers().get(index).getHand().get(0)); 
		json = oWriter.writeValueAsString(c); }
		return json;
	}
//==================================active-cards-end
	
//==============CHECK FOR PLAYERS===================
/**
 * Method to check whether player is in the game,
 * returns -1 if not
 * @param name
 * @return
 */
	public int checkForPlayer(String name) {
		int index = -1;
		for(Player p : deck.getPlayers()) {
			if(p.getPName().equals(name)) {
				index = deck.getPlayers().indexOf(p);
			}
		}
		System.out.println(index);
		return index;
	}//=================player-check-end
	
//==============WHOS CHOOSES CATEGORY====================
/**
 * This method
 * @return
 * @throws IOException
 */
	@GET
	@Path("/whosTurn")
	public int whosTurn() throws IOException {
		int json = 0;
		if(!(topTrumps.getActivePlayer().startsWith("A"))) {
			json = -1;
		}else {
			topTrumps.setFinalCategory(topTrumps.firstPlay() + 1);
			playRound(json);	
		}
		return json;
	}//================whos-turn-end
	

	@GET
	@Path("/playRound")
	public String playRound(int category) throws IOException{
		topTrumps.checkWin(deck);
		deck.setCommonDeck();
		topTrumps.setRoundWinner(topTrumps.setWinner(deck, logsToFile));
		topTrumps.setFinalWinner(topTrumps.getRoundWinner());
		topTrumps.setActivePlayer(topTrumps.getRoundWinner());
		topTrumps.setWinningCardIndex(topTrumps.winningCardIndex(deck));
		topTrumps.setFinalWinningCard(deck.getCardDeck().get(topTrumps.getWinningCardIndex()).getCardName());
		topTrumps.setRound(topTrumps.getRound() + 1);
		deck.clearActiveCards();
		String json = oWriter.writeValueAsString(topTrumps);
		return json;
	}
	
	public void loadStats() throws Exception{
		db = new DatabaseConnect();
		db.DatabaseOpen();
		db.totalNumberGames();
		db.totalHumanWins();
		db.totalAIWins();
		db.avgNumberDraws();
		db.maxGameLength();
		db.DatabaseClose();
	}
	
	@GET 
	@Path("/getStatistics")
	public String getStatistics() throws Exception {
		loadStats();
		String dbString = oWriter.writeValueAsString(this.db);
		return dbString;
	}
//===========================DELETE WHEN READY=====================================	
	@GET
	@Path("/helloJSONList")
	/**
	 * Here is an example of a simple REST get request that returns a String.
	 * We also illustrate here how we can convert Java objects to JSON strings.
	 * @return - List of words as JSON
	 * @throws IOException
	 */
	public String helloJSONList() throws IOException {
		
		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add("Hello");
		listOfWords.add("World!");
		
		// We can turn arbatory Java objects directly into JSON strings using
		// Jackson seralization, assuming that the Java objects are not too complex.
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);
		
		return listAsJSONString;
	}
	
	@GET
	@Path("/helloWord")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String helloWord(@QueryParam("Word") String Word) throws IOException {
		return "Hello "+Word;
	}
//======================================================================DELETE ABOVE	
	
	
	
}
