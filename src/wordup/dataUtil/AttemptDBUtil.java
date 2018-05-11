package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Attempt;

public class AttemptDBUtil {

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
			System.out.println("inserting attempt: " + attempt.getLessonID() + " " + attempt.getUserID() + " " + attempt.getCount() + " " + attempt.getScore());
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
			System.out.println("Updating attempt...");
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
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static Attempt getAttempt(int lessonID, int userID, int count) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt where lessonID = ? and userID = ? and count = ?";
		ResultSet rs = null;

		try {
			System.out.println("Retrieving the new attempt: " + lessonID + " " + userID + " " + count);
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
	
	public static ArrayList<Attempt> getAttempts(int lessonID, int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Attempt where lessonID = ? and userID = ?";
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			System.out.println("Getting user attempts: " + lessonID + " " + userID);
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

}
