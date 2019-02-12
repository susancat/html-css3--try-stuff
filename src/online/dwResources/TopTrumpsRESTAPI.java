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
		Desktop d = Desktop.getDesktop();
		d.browse(new URI("http://localhost:7777/toptrumps"));
	}
	
	// ----------------------------------------------------
	// Add relevant API methods here
	// ----------------------------------------------------
	
	
	@GET
	@Path("/playerActiveCard") 
	public String activePCard() throws IOException {
		Card c = deck.getCardDeck().get(deck.getPlayers().get(0).getHand().get(0));
		String JSONc = oWriter.writeValueAsString(c);
		return JSONc;
	}
	
	@GET
	@Path("/ai1ActiveCard")
	public String activeAI1Card() throws IOException {
		Card c = deck.getCardDeck().get(deck.getPlayers().get(1).getHand().get(0));
		String JSONc = oWriter.writeValueAsString(c);
		return JSONc;
	}
	
	
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
	
}
