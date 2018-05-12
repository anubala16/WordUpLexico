package wordup.dataUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import wordup.business.Response;
import wordup.business.Attempt;

/**
 * Handles all access to Response table in database 
 * @author anusha balaji 
 *
 */
public class ResponseDBUtil {

	/**
	 * Inserts the given response in the database 
	 * @param response
	 * @return row count affected 
	 */
	public static int insert(Response response) {
		Connection conn;

		PreparedStatement ps;
		String query = "Insert into Response (userResponse, cardID, attemptID) values (?, ?, ?)"; 
		int result = 0; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			////system.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			////system.out.println("Another error..");
			throw new IllegalStateException("Cannot find the driver in the classpath!", e);
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/wordup", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setString(1, response.getUserResp());
			ps.setInt(2, response.getCardID());
			ps.setInt(3, response.getAttemptID());
			//system.out.println("inserting response");
			result = ps.executeUpdate();
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
	 * gets response with the id given if it exists 
	 * @param ResponseID id of the response to get 
	 * @return Response response with given id 
	 */
	public static Response getResponseByID(int ResponseID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Response where responseID = ?";
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, ResponseID);
			rs = ps.executeQuery();
			Response r = null;
			if (rs.next()) {
				r = new Response();
				r.setUserResp(rs.getString("userResponse"));
				r.setCardID(rs.getInt("cardID"));
				r.setAttemptID(rs.getInt("attemptID"));
				r.setResponseID(rs.getInt("responseID"));
			}
			ps.close();
			conn.close();
			return r;
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
	 * Gets responses for the provided id of attempt 
	 * @param attemptID id of the attempt 
	 * @return arraylist of responses for the attempt 
	 */
	public static ArrayList<Response> getAttemptResponses(int attemptID) {
		Connection conn = null;
		PreparedStatement ps = null;
		String query = "select * from Response where attemptID = ? order by cardID asc";
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/WordUp", "root", "root");
			ps = conn.prepareStatement(query);
			ps.setInt(1, attemptID);
			rs = ps.executeQuery();
			ArrayList<Response> responses = new ArrayList<Response>();
			Response r = null;
			while (rs.next()) {
				r = new Response();
				r.setUserResp(rs.getString("userResponse"));
				r.setCardID(rs.getInt("cardID"));
				r.setAttemptID(rs.getInt("attemptID"));
				r.setResponseID(rs.getInt("responseID"));
				responses.add(r);
			}
			ps.close();
			conn.close();
			return responses;
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
