
import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class DatabaseConnect {
	static Connection c = null;
	private int gamenumber;
	private int gameHuWin;
	private int gameAiWin;
	private int gamedraws;
	private int gameLen;
	
	
	public static void DatabaseOpen() {       
		try {          
			Class.forName("org.postgresql.Driver");          
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/MainTest",            
					"postgres", "Psql8544");         
			System.out.println("Opened database successfully"); 
		} catch ( Exception e ) { 
			System.exit(0); 
		}
	} 
	public static void DatabaseClose() {
		try {
			c.close();
			System.out.println("Data Base Close");
		}catch ( Exception e ) { 
			System.exit(0); 
	}
   }
	
	public static void insertValues(int gnumber, int humanwin, int aiwin, int draws, int gamelength) {
		int gameIdNum = 0;
		gameIdNum = getGameId(gameIdNum);
		gameIdNum++;
		try {
			
			Statement stmt = c.createStatement();
			
		String insertquery = "INSERT INTO Game(GameId, GamesNo, HumanWin, AIWin, NoDraws, GameLength)"
				+ "VALUES ('"+gameIdNum+"','"+gnumber+"','"+humanwin+"', '"+aiwin+"', '"+draws+"', '"+gamelength+"')";
	    stmt.executeUpdate(insertquery);
		System.out.println("Values Inserted");
		stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void GameNumberdb() {
		try {
			 
			 PreparedStatement stmtGsum =  c.prepareStatement("SELECT SUM(GamesNo) As Total_games FROM   Game");
			 ResultSet result = stmtGsum.executeQuery();
			 result.next();
		     String Gamesum = result.getString(1);
		     System.out.println(Gamesum);
			 stmtGsum.close();
			 if (stmtGsum.isClosed()) {System.out.println("Game total it is closed");}
			 
			 PreparedStatement stmtHwin = c.prepareStatement("SELECT SUM(HumanWin) As Human_score FROM   Game");
			 ResultSet getHquery = stmtHwin.executeQuery();
	         getHquery.next();
	         String Hwinsum = getHquery.getString(1);
			 System.out.println(Hwinsum);
			 stmtHwin.close();
			 if (stmtHwin.isClosed()) {System.out.println("Human win it is closed");}
			 
			 PreparedStatement stmtAiwin = c.prepareStatement("SELECT SUM(AIWin) As AI_score FROM   Game");
			 ResultSet getAiquery = stmtAiwin.executeQuery();
	         getAiquery.next();
	         String Aiwinsum = getAiquery.getString(1);
			 System.out.println(Aiwinsum);
			 stmtAiwin.close();
			 if (stmtAiwin.isClosed()) {System.out.println(" Ai win sql it is closed");}
			 
			 PreparedStatement stmtDraws = c.prepareStatement("SELECT AVG(NoDraws) As Ave_Draws  FROM   Game");
			 ResultSet getDraws = stmtDraws.executeQuery();
	         getDraws.next();
	         String AveDraws = getDraws.getString(1);
			 System.out.println(AveDraws);
			 stmtDraws.close();
			 if (stmtDraws.isClosed()) {System.out.println("Graw ave it is closed");}
			 
			 PreparedStatement stmtGlength = c.prepareStatement("SELECT MAX(GameLength) As Length_Game FROM   Game");
			 ResultSet getGlquery = stmtGlength.executeQuery();
			 getGlquery.next();
	         String Gamelength = getGlquery.getString(1);
			 System.out.println(Gamelength);
			 stmtGlength.close();
			 if (stmtGlength.isClosed()) {System.out.println("Game length it is closed");}
			 
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	public int getGameNumber() {
		return gamenumber;
	}
	public int getHumanWins() {
		return gameHuWin;
	}
	public int getAiWins() {
		return gameAiWin;
	}
	public int getDrawsAve() {
		return gamedraws;
	}
	public int getTotalGameTime() {
		return gameLen;
	}
	public static int getGameId(int gameIdint) {
		/*
		 * This Method get game id from database
		 * so the program know the previous game 
		 * Id number and can assign a new Number
		 * to new game, it is called by insertvalues
		 * method.
		 */
		try {
			PreparedStatement stmtGId = c.prepareStatement("SELECT MAX(GameId) As Game_Id  FROM   Game");
			ResultSet result = stmtGId.executeQuery();
			result.next();
		    String gameId = result.getString(1);
		    gameIdint = Integer.parseInt(gameId);
		    System.out.println("This is gameId" + gameIdint);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gameIdint;
	}
}