package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Lesson;

public class LessonDBUtil {

	public static int insert(Lesson lesson) {
		Connection conn;

		PreparedStatement ps;
		String query = "Insert into Lesson (title, file, subject, subject2, subject3, level, creatorID, dateCreated) values (?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			// System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, lesson.getTitle());
			ps.setString(2, lesson.getFilePath());
			ps.setString(3, lesson.getSubject());
			ps.setString(4, lesson.getSubject2());
			ps.setString(5, lesson.getSubject3());
			ps.setString(6, lesson.getAccessLevel());
			ps.setInt(7, lesson.getAuthorID());
			ps.setDate(8, lesson.getDateCreated());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return 0;
		}
	}

	public static Lesson getLessonByFileAndTitle(String filePath, String title) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Lesson where file = ? and title = ?";
		ResultSet rs = null;

		// ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, filePath);
			ps.setString(2, title);
			rs = ps.executeQuery();
			Lesson l = null;
			if (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				// lessons.add(l);
			}
			ps.close();
			conn.close();
			return l;
			// return lessons;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static Lesson getLessonByID(int lessonID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Lesson where lessonID = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			rs = ps.executeQuery();
			Lesson l = null;
			if (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
			}
			ps.close();
			conn.close();
			return l;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static int update(Lesson lesson) {
		Connection conn;

		PreparedStatement ps;
		String query = "update Lesson set subject = ?, subject2 = ?, subject3 = ?, level = ? where lessonid=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			// System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, lesson.getSubject());
			ps.setString(2, lesson.getSubject2());
			ps.setString(3, lesson.getSubject3());
			ps.setString(4, lesson.getAccessLevel());
			ps.setInt(5, lesson.getLessonID());
			System.out.println("updating lesson..." + lesson.getLessonID());
			System.out.println(lesson.getSubject() + " " + lesson.getSubject2() + " " + lesson.getSubject3() + " "
					+ lesson.getAccessLevel());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return 0;
		}
	}

	public static int deleteCards(int lessonID) {
		Connection conn;

		PreparedStatement ps;
		String query = "delete from Card where lessonid=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			// System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error deleting cards...");
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());

			return 0;
		}
	}

	public static ArrayList<Lesson> getLessonsByUser(int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Lesson where creatorID = ?";
		ResultSet rs = null;
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			Lesson l = null;
			while (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				lessons.add(l);
			}
			ps.close();
			conn.close();
			return lessons;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static ArrayList<LessonAuthor> getLessonAuthors() {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Lesson inner join User on Lesson.creatorID = User.userID";
		ResultSet rs = null;
		ArrayList<LessonAuthor> lessons = new ArrayList<LessonAuthor>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			Lesson l = null;
			LessonAuthor la = null;
			ArrayList<LessonAuthor> las = new ArrayList<LessonAuthor>();
			while (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				
				String authorName = rs.getString("User.firstName") + " " + rs.getString("User.lastName");
				
				la = new LessonAuthor(l, authorName);
				
				las.add(la);
			}
			ps.close();
			conn.close();
			return las;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static int getCardCount(int lessonID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select count(*) from card where lessonID=?";
		ResultSet rs = null;
		ArrayList<Lesson> lessons = new ArrayList<Lesson>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			rs = ps.executeQuery();
			int result = 0;
			if (rs.next()) {
				result = rs.getInt("count(*)");
				if (result == 0) {
					System.out.println("card count method is tricky");
					result = rs.getInt(0);
				}
			}
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return 0;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static ArrayList<LessonAuthor> getCatalog(int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select User.firstName, User.lastName, Lesson.lessonID, Lesson.title, Lesson.file, Lesson.creatorID, Lesson.subject, Lesson.level, Lesson.subject2, Lesson.subject3, Lesson.dateCreated from Lesson inner join User on Lesson.creatorID = User.userID where Lesson.creatorID = ? or Lesson.level = ?";
		ResultSet rs = null;
		ArrayList<LessonAuthor> lessons = new ArrayList<LessonAuthor>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			ps.setString(2, "public");
			rs = ps.executeQuery();
			Lesson l = null;
			while (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				String authorName = rs.getString("User.firstName") + " " + rs.getString("User.lastName");
				LessonAuthor la = new LessonAuthor(l, authorName);
				lessons.add(la);
			}
			ps.close();
			conn.close();
			return lessons;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static LessonAuthor getLessonAuthor(int lessonID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select User.firstName, User.lastName, Lesson.lessonID, Lesson.title, Lesson.file, Lesson.creatorID, Lesson.subject, Lesson.level, Lesson.subject2, Lesson.subject3, Lesson.dateCreated from Lesson inner join User on Lesson.creatorID = User.userID where Lesson.lessonID = ?";
		ResultSet rs = null;
		ArrayList<LessonAuthor> lessons = new ArrayList<LessonAuthor>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, lessonID);
			rs = ps.executeQuery();
			Lesson l = null;
			LessonAuthor la = null;
			while (rs.next()) {
				l = new Lesson();
				l.setTitle(rs.getString("title"));
				l.setFilePath(rs.getString("file"));
				l.setSubject(rs.getString("subject"));
				l.setAccessLevel(rs.getString("level"));
				l.setSubject2(rs.getString("subject2"));
				l.setSubject3(rs.getString("subject3"));
				l.setAuthorID(rs.getInt("creatorID"));
				l.setDateCreated(rs.getDate("dateCreated"));
				l.setLessonID(rs.getInt("lessonID"));
				String authorName = rs.getString("User.firstName") + " " + rs.getString("User.lastName");
				la = new LessonAuthor(l, authorName);
			}
			ps.close();
			conn.close();
			return la;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
	}

	public static class LessonAuthor {
		private Lesson lesson;
		private String author;

		public LessonAuthor(Lesson lesson, String authorName) {
			this.lesson = lesson;
			this.author = authorName;
		}

		public Lesson getLesson() {
			return this.lesson;
		}

		public String getAuthor() {
			return this.author;
		}
	}

}
