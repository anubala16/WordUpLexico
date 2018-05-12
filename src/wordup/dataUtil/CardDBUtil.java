package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Card;
import wordup.business.Lesson;
/**
 * DAO class for Card table 
 * @author anusha balaji 
 *
 */
public class CardDBUtil {

	/**
	 * Insert a card object into the mysql database 
	 * @param card car to insert 
	 * @return row count affected 
	 */
	public static int insert(Card card) {
		Connection conn;

		PreparedStatement ps;
		String query = "Insert into Card (word, description, lessonID) values (?, ?, ?)"; 
		int result = 0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			//System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, card.getWord());
			ps.setString(2, card.getDescription());
			ps.setInt(3, card.getLessonID());
			System.out.println("inserting card: " + card.getWord() + " (" + card.getDescription() + ") for lesson " + card.getLessonID());
			result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return 0;
		}
	}
	
	/**
	 * Gets card with the given id 
	 * @param cardID id of the card to be retrieved from database 
	 * @return Card from database 
	 */
	public static Card getCardByID(int cardID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Card where cardID = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, cardID);
			rs = ps.executeQuery();
			Card c = null;
			if (rs.next()) {
				c = new Card();
				c.setWord(rs.getString("word"));
				c.setDescription(rs.getString("description"));
				c.setLessonID(rs.getInt("lessonID"));
				c.setCardID(rs.getInt("cardID"));
			}
			ps.close();
			conn.close();
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	/**
	 * Gets all the cards belonging to the lesson with the given id 
	 * @param lessonID id of the lesson 
	 * @return cards for the lesson 
	 */
	public static ArrayList<Card> getLessonCards(int lessonID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Card where lessonID = ?";
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			rs = ps.executeQuery();
			ArrayList<Card> cards = new ArrayList<Card>();
			Card c = null;
			while (rs.next()) {
				c = new Card();
				c.setWord(rs.getString("word"));
				c.setDescription(rs.getString("description"));
				c.setLessonID(rs.getInt("lessonID"));
				c.setCardID(rs.getInt("cardID"));
				cards.add(c);
			}
			ps.close();
			conn.close();
			return cards;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

}
