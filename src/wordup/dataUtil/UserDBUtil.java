/**
 * 
 */
package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.User;

/**
 * Handles access to the User table in database 
 * @author anushabalaji 
 *
 */
public class UserDBUtil {

	/**
	 * inserts the given user in the database 
	 * @param user new user 
	 * @return row count affected 
	 */
	public static int insert(User user) {
		Connection conn;

		PreparedStatement ps;
		String query = "insert into User (firstName, lastName, email, password, profession, type, salt) values (?, ?, ?, ?, ?, ?, ?)";
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    //system.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    //system.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getProfession());
			ps.setInt(6, user.getType());
			//ps.setString(7, user.getPwd());
			ps.setString(8, user.getSalt());
			int result = ps.executeUpdate();
			ps.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println(e.getStackTrace());
			return 0;
		}
	}

	/**
	 * retrieves the user with the given email if one exists 
	 * used when authenticating users during the login process 
	 * @param email email id of the user 
	 * @return user if one exists or null 
	 */
	public static User getUserByEmail(String email) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from user where email = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			User user = null;
			if (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setProfession(rs.getString("profession"));
				user.setUserID(rs.getInt("userID"));
				user.setType(rs.getInt("type"));
				user.setSalt(rs.getString("salt"));
				//user.setPwd(rs.getString("pwd"));
			}
			ps.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
		    //system.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		} 
	}

	/**
	 * Gets user by id 
	 * @param userID id of the user to be retrieved 
	 * @return user if one exists or null if no user is found with the given id 
	 */
	public static User getUserByID(int userID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from user where userID = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			User user = null;
			if (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setProfession(rs.getString("profession"));
				user.setUserID(rs.getInt("userID"));
				user.setType(rs.getInt("type"));
				user.setSalt(rs.getString("salt"));
				//user.setPwd(rs.getString("pwd"));
				
			}
			ps.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
		    //system.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		} 
	}
	
	/**
	 * gets all users in the system 
	 * admin feature 
	 * @return users in the system 
	 */
	public static ArrayList<User> getUsers() {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from user";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			ArrayList<User> users = new ArrayList<User>();
			User user = null;
			while (rs.next()) {
				user = new User();
				user.setFirstName(rs.getString("firstName"));
				user.setLastName(rs.getString("lastName"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setProfession(rs.getString("profession"));
				user.setUserID(rs.getInt("userID"));
				user.setType(rs.getInt("type")); 
				user.setSalt(rs.getString("salt"));
				//user.setPwd(rs.getString("pwd"));
				users.add(user);
			}
			ps.close();
			conn.close();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			//system.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
		    //system.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		} 
	}

}
