package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Lesson;

/**
 * Handles all database interactions with Lesson table and fulfills user
 * requests related too them
 * 
 * @author anuba
 *
 */
public class LessonDBUtil {

	/**
	 * Inserts the given le3sson in the database
	 * 
	 * @param lesson
	 *            new lesson to be inserted
	 * @return
	 */
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

	/**
	 * Gets the lesson correspondng to the given title and filepath **Note:
	 * lesson title and filepath are a superkey
	 * 
	 * @param filePath
	 *            absolute path for the input data file
	 * @param title
	 *            title of the lesson
	 * @return lesson with the given title and file path
	 */
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

	/**
	 * Gets the lesson by id
	 * 
	 * @param lessonID
	 *            id of the lesson to get
	 * @return lesson with the given id
	 */
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

	/**
	 * Updates the subjects and access level for a lesson
	 * 
	 * @param lesson
	 *            new, updated lesson
	 * @return row count affected
	 */
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

	/**
	 * Deletes the cards related to the given lesson
	 * 
	 * @param lessonID
	 *            id of the lesson
	 * @return row count affected
	 */
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

	/**
	 * Gets LessonAuthors, used for admin "View lessons" feature LessonAuthor is
	 * a custom object with the lesson and author name pulled from a join query
	 * between lesson and user table on the user and creator id fields
	 * 
	 * @return all lessons with their author names in the system
	 */
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

	/**
	 * Returns the number of cards for the lesson
	 * 
	 * @param lessonID
	 *            id of the lesson for which the cards must be counted and
	 *            returned
	 * @return cardCount for the lesosn with the given id
	 */
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

	/**
	 * Gets the catalog of lessons for the given user (this is different from
	 * "View All Lessons" for admins) as users can't view any other user's
	 * private quizzes
	 * 
	 * @param userID
	 * @return
	 */
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

	/**
	 * Gets the lesson along with the author name given the lesson id 
	 * Used when generating score reports for the user 
	 * @param lessonID id of the lesson whose LessonAuthor must be got 
	 * @return LessonAuthor (lesson + author name)
	 */
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

	/**
	 * Utility class for LessonDBUtil to capture a Lesson and its author name 
	 * Used when displaying lessons to users in order to improve readability and user experience. 
	 * @author abalaji 
	 */
	public static class LessonAuthor {
		
		/** Lesson object */
		private Lesson lesson;
		/** author's full name for the corresponding lesson */
		private String author;

		/**
		 * constructor 
		 * @param lesson lesson for this object 
		 * @param authorName for this lesson 
		 */
		public LessonAuthor(Lesson lesson, String authorName) {
			this.lesson = lesson;
			this.author = authorName;
		}

		/**
		 * get lesson object 
		 * @return
		 */
		public Lesson getLesson() {
			return this.lesson;
		}

		/**
		 * getter for authorName 
		 * @return
		 */
		public String getAuthor() {
			return this.author;
		}
	}

}
