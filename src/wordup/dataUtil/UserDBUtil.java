/**
 * 
 */
package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wordup.business.User;

/**
 * @author anuba
 *
 */
public class UserDBUtil {

	public static int insert(User user) {
		Connection conn;

		PreparedStatement ps;
		String query = "insert into User (firstName, lastName, email, password, profession, type) values (?, ?, ?, ?, ?, ?)";
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
		    System.out.println("Another error..");
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
			}
			ps.close();
			conn.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getStackTrace());
			return null;
		} catch (ClassNotFoundException e) {
		    System.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		} 
	}

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
			}
			ps.close();
			conn.close();
			return user;
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
