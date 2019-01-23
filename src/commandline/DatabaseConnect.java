package commandline;
import java.sql.Connection;  
import java.sql.DriverManager; 
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
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Earlytest",            
					"postgres", "Psql8544");         
			System.out.println("Opened database successfully"); 
		} catch ( Exception e ) { 
			e.printStackTrace();
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
		try {
			Statement stmt = c.createStatement();
		
		String insertquery = "INSERT INTO Game(GamesNo, HumanWin, AIWin, NoDraws, GameLength)"
				+ "VALUES ('"+gnumber+"','"+humanwin+"', '"+aiwin+"', '"+draws+"', '"+gamelength+"')";
	    stmt.executeUpdate(insertquery);
		System.out.println("Values Inserted");
		stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void GameNumberdb() {
		try {
			 
			 Statement stmtGsum = c.createStatement();
			 String getnumbergame = "SELECT SUM(GamesNo) FROM  Game";
			 ResultSet getquery = stmtGsum.executeQuery(getnumbergame);
	         getquery.next();
	         //===================== 
	         int Gametotalsum = getquery.getInt("GamesNo");
			 System.out.println(Gametotalsum);
			 //======================
			 stmtGsum.close();
			 
			 Statement stmtHwin = c.createStatement();
			 String getHwin = "SELECT SUM(HumanWin) As Human_score FROM   Game";
			 ResultSet getHquery = stmtHwin.executeQuery(getHwin);
	         getHquery.next();
	         int Hwinsum = getHquery.getInt("HumanWin");
			 System.out.println(Hwinsum);
			 stmtHwin.close();
			 
			 Statement stmtAiwin = c.createStatement();
			 String getAiwin = "SELECT SUM(AIWin) As AI_score FROM   Game";
			 ResultSet getAiquery = stmtAiwin.executeQuery(getAiwin);
	         getAiquery.next();
	         int Aiwinsum = getAiquery.getInt("AIWin");
			 System.out.println(Aiwinsum);
			 stmtAiwin.close();
			 
			 Statement stmtDraws = c.createStatement();
			 String Drawquery = "SELECT AVG(NoDraws) As Ave_Draws  FROM   Game";
			 ResultSet getDraws = stmtDraws.executeQuery(Drawquery);
	         getDraws.next();
	         double AveDraws = getDraws.getDouble("NoDraws");
			 System.out.println(AveDraws);
			 stmtDraws.close();
			 
			 Statement stmtGlength = c.createStatement();
			 String Glengthquery = "SELECT MAX(GameLength) As Length_Game FROM   Game";
			 ResultSet getGlquery = stmtGlength.executeQuery(Glengthquery);
			 getGlquery.next();
	         int Gamelength = getGlquery.getInt("GameLength");
			 System.out.println(Gamelength);
			 stmtGlength.close();
			 
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
}