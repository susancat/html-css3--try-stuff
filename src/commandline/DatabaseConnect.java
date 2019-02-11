package commandline;
import java.sql.Connection;  

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class DatabaseConnect {
	Connection c = null;
	private int gamenumber;
	private int gameHuWin;
	private int gameAiWin;
	private double gamedraws;
	private int gameLen;
	private boolean verifydbopen;
	private boolean verifydbclose;
	private boolean confirmstatementclosure;
	
	
	public void DatabaseOpen() {       
		try {          
			Class.forName("org.postgresql.Driver");          
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TopTrumps",            
					"postgres", "freekshow");         
			
			if(c !=null) 
			{
			verifydbopen = true;
		//	System.out.println("Opened database successfully");
			}else {
				verifydbopen = false;
			}
		} catch ( Exception e ) { 
			e.printStackTrace(); 
		}
	} 
	public void DatabaseClose() {
		try {
			c.close();
			if(c.isClosed()) {
				verifydbclose = true;
			}else {
				verifydbclose = false;
			}
		//	System.out.println("Data Base Close");
		}catch ( Exception e ) { 
			e.printStackTrace(); 
		}
   }
	
	public void insertValues(int humanwin, int aiwin, int draws, int gamelength) throws SQLException {
		Statement stmt = c.createStatement();
		String insertquery = "INSERT INTO Game(HumanWin, AIWin, NoDraws, GameLength)"
				+ "VALUES ('"+humanwin+"', '"+aiwin+"', '"+draws+"', '"+gamelength+"')";
	    stmt.executeUpdate(insertquery);
		// System.out.println("Values Inserted");
		stmt.close();
	
	}
	
	public String totalNumberGames() throws SQLException {
			 PreparedStatement stmtGsum =  c.prepareStatement("SELECT COUNT(*) As Total_games FROM Game");
			 ResultSet result = stmtGsum.executeQuery();
			 result.next();
		     String gameSum = result.getString(1);
//		     System.out.println(gameSum);
		     gamenumber = Integer.parseInt(gameSum);
			 stmtGsum.close();
			 if (stmtGsum.isClosed()) {confirmstatementclosure = true;
			                          }else{confirmstatementclosure = false;}
			 return gameSum;
	}
			 
	public String totalHumanWins() throws SQLException {
			 PreparedStatement stmtHwin = c.prepareStatement("SELECT SUM(HumanWin) As Human_score FROM Game");
			 ResultSet getHquery = stmtHwin.executeQuery();
	         getHquery.next();
	         String hWinSum = getHquery.getString(1);
//			 System.out.println(hWinSum);
			 gameHuWin = Integer.parseInt(hWinSum);
			 stmtHwin.close();
			 if (stmtHwin.isClosed()) {confirmstatementclosure = true;
			                          }else{confirmstatementclosure = false;}
			 return hWinSum;
	}
	
	public String totalAIWins() throws SQLException {		 
			 PreparedStatement stmtAiwin = c.prepareStatement("SELECT SUM(AIWin) As AI_score FROM Game");
			 ResultSet getAiquery = stmtAiwin.executeQuery();
	         getAiquery.next();
	         String aiWinSum = getAiquery.getString(1);
//			 System.out.println(aiWinSum);
			 gameAiWin = Integer.parseInt(aiWinSum);
			 stmtAiwin.close();
			 if (stmtAiwin.isClosed()) {confirmstatementclosure = true;
                                       }else{confirmstatementclosure = false;}
			 return aiWinSum;
	}
			 
	public String avgNumberDraws() throws SQLException {
			 PreparedStatement stmtDraws = c.prepareStatement("SELECT AVG(NoDraws) As Ave_Draws  FROM Game");
			 ResultSet getDraws = stmtDraws.executeQuery();
	         getDraws.next();
	         String avgDraws = String.format("%5.2f", getDraws.getDouble(1));
//			 System.out.println(avgDraws);
			 gamedraws = Double.parseDouble(avgDraws);
			 stmtDraws.close();
			 if (stmtDraws.isClosed()) {confirmstatementclosure = true;
                                       }else{confirmstatementclosure = false;}
			 return avgDraws;
	}
			 
	public String maxGameLength() throws SQLException {
			 PreparedStatement stmtGlength = c.prepareStatement("SELECT MAX(GameLength) As Length_Game FROM Game");
			 ResultSet getGlquery = stmtGlength.executeQuery();
			 getGlquery.next();
	         String gameLength = getGlquery.getString(1);
//			 System.out.println(gameLength);
			 gameLen = Integer.parseInt(gameLength);
			 stmtGlength.close();
			 if (stmtGlength.isClosed()) {confirmstatementclosure = true;
                                         }else{confirmstatementclosure = false;}
			 return gameLength;
			 
	}
	
	public void createTable() throws SQLException {
		DatabaseOpen();
		Statement stmt = c.createStatement();
		if (c != null) {
		String sql = "CREATE TABLE IF NOT EXISTS Game(GamesNo serial, HumanWin integer, "
				+ "AIWin integer, NoDraws integer, GameLength integer)";
		stmt.executeUpdate(sql);
		
		}
		DatabaseClose();
		}


	/*
	 * These getter and setter have been created
	 * for junit testing and in case if they are needed
	 * at some point though game structure does not require 
	 * them, other wise print statements will have to be 
	 * written in above method which will affect game in cli
	 * version. 
	 */
	public int getGameNumber() {
		return gamenumber;
	}
	public int getHumanWins() {
		return gameHuWin;
	}
	public int getAiWins() {
		return gameAiWin;
	}
	public double getDrawsAve() {
		return gamedraws;
	}
	public int getTotalGameTime() {
		return gameLen;
	}
	public boolean isDBopen() {
		return verifydbopen;
		}
	public boolean isDBclose() {
		return verifydbclose;
	}
	public boolean areStatementClose() {
		return confirmstatementclosure;
	}
	
}
	
