package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Attempt;
import wordup.business.Lesson;
/**
 * DAO class for accessing attempt table related queries 
 * @author anusha balaji 
 */
public class AttemptDBUtil {

	/**
	 * Inserts given attempt in db
	 * @param attempt
	 * @return
	 */
	public static int insert(Attempt attempt) {
		Connection conn;

		PreparedStatement ps;
		String query = "Insert into Attempt (lessonID, userID, timestamp, score, count) values (?, ?, ?, ?, ?)"; 
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
			ps.setInt(1, attempt.getLessonID());
			ps.setInt(2, attempt.getUserID());
			ps.setDate(3, attempt.getTimestamp());
			ps.setInt(4, attempt.getScore());
			ps.setInt(5, attempt.getCount());
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
	 * Only updates the attempt's score - used when grading a quiz 
	 * @param attempt new attempt with score 
	 * @return rowCount rows affected 
	 */
	public static int update(Attempt attempt) {
		Connection conn;

		PreparedStatement ps;
		String query = "Update Attempt set score = ? where attemptID = ?"; 
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
			ps.setInt(1, attempt.getScore());
			ps.setInt(2, attempt.getAttemptID());
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
	 * Getter for Attempt using ID 
	 * @param attemptID
	 * @return
	 */
	public static Attempt getAttemptById(int attemptID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt where attemptID = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, attemptID);
			rs = ps.executeQuery();
			Attempt a = null;
			if (rs.next()) {
				a = new Attempt();
				a.setLessonID(rs.getInt("lessonID"));
				a.setUserID(rs.getInt("userID"));
				a.setAttemptID(rs.getInt("attemptID"));
				a.setTimestamp(rs.getDate("timestamp"));
				a.setScore(rs.getInt("score"));
				a.setCount(rs.getInt("count"));
			}
			ps.close();
			conn.close();
			return a;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	/**
	 * Getter for attempt using userID, lessonID, and attemptCount - unique combo, returns 0 or 1 always
	 * @param lessonID
	 * @param userID
	 * @param count
	 * @return
	 */
	public static Attempt getAttempt(int lessonID, int userID, int count) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt where lessonID = ? and userID = ? and count = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			ps.setInt(2, userID);
			ps.setInt(3, count);
			rs = ps.executeQuery();
			Attempt a = null;
			if (rs.next()) {
				System.out.println("Found it!");
				a = new Attempt();
				a.setLessonID(rs.getInt("lessonID"));
				a.setUserID(rs.getInt("userID"));
				a.setAttemptID(rs.getInt("attemptID"));
				a.setTimestamp(rs.getDate("timestamp"));
				a.setScore(rs.getInt("score"));
				a.setCount(rs.getInt("count"));
			}
			ps.close();
			conn.close();
			return a;
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
	 * GEtter for attemopts based on lesson id and userid
	 * @param lessonID
	 * @param userID
	 * @return attempts for the user and lesson combo. 
	 */
	public static ArrayList<Attempt> getAttempts(int lessonID, int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt where lessonID = ? and userID = ?";
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			ps.setInt(2, userID);
			rs = ps.executeQuery();
			ArrayList<Attempt> attempts = new ArrayList<Attempt>();
			Attempt a = null;
			while (rs.next()) {
				a = new Attempt();
				a.setLessonID(rs.getInt("lessonID"));
				a.setUserID(rs.getInt("userID"));
				a.setAttemptID(rs.getInt("attemptID"));
				a.setTimestamp(rs.getDate("timestamp"));
				a.setScore(rs.getInt("score"));
				a.setCount(rs.getInt("count"));
				attempts.add(a);
			}
			ps.close();
			conn.close();
			return attempts;
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
	 * Joins attempt and lesson table to get more details related to lesson when dislaying the attempt
	 * @param userID
	 * @return
	 */
	public static ArrayList<LessonAttempt> getLessonAttempts(int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt inner join Lesson on Attempt.lessonID = Lesson.lessonID where Attempt.userID = ?";
		ResultSet rs = null;
		ArrayList<LessonAttempt> lessonAttempts = new ArrayList<LessonAttempt>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			Lesson l = null;
			Attempt a = null;
			while (rs.next()) {
				l = new Lesson();
				a = new Attempt();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				
				a.setLessonID(rs.getInt("lessonID"));
				a.setUserID(rs.getInt("userID"));
				a.setAttemptID(rs.getInt("attemptID"));
				a.setTimestamp(rs.getDate("timestamp"));
				a.setScore(rs.getInt("score"));
				a.setCount(rs.getInt("count"));
				
				int cardCount = LessonDBUtil.getCardCount(l.getLessonID());
				
				LessonAttempt la = new LessonAttempt(l, a, cardCount);
				lessonAttempts.add(la);
			}
			ps.close();
			conn.close();
			return lessonAttempts;
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
	 * Represents the join between a lesson and attmpt object
	 * @author anusha balaji 
	 *
	 */
	public static class LessonAttempt {
		private Lesson lesson; 
		private Attempt attempt;
		private int qCount;
		private double percent;
		
		public LessonAttempt (Lesson lesson, Attempt attempt, int cardCount) {
			this.lesson = lesson;
			this.attempt = attempt;
			this.qCount = cardCount;
			this.percent = (double) attempt.getScore() * 100 / qCount;
		}
		
		public Lesson getLesson(){
			return this.lesson;
		}
		
		public Attempt getAttempt() {
			return this.attempt;
		}
		
		public int getqCount() {
			return qCount;
		}

		public double getPercent() {
			return this.percent;
		}
	}

}
