package wordup.business;

import java.io.Serializable;

/**
 * User response POJO for storing user answers for a particular attempt in a
 * quiz Allows users to review and email score reports
 * 
 * @author abalaji
 *
 */
public class Response implements Serializable {

	/**
	 * Primary key and foreign keys to uniquely identify a user response in
	 * database
	 */
	private int responseID, cardID, attemptID;
	/**
	 * actual user response
	 */
	private String userResp;

	/**
	 * Default constructor
	 */
	public Response() {
		super();
	}

	/**
	 * @param cardID
	 * @param attemptID
	 * @param userResp
	 */
	public Response(int cardID, int attemptID, String userResp) {
		this.cardID = cardID;
		this.attemptID = attemptID;
		this.userResp = userResp;
	}

	/**
	 * @return the responseID
	 */
	public int getResponseID() {
		return responseID;
	}

	/**
	 * @param responseID
	 *            the responseID to set
	 */
	public void setResponseID(int responseID) {
		this.responseID = responseID;
	}

	/**
	 * @return the cardID
	 */
	public int getCardID() {
		return cardID;
	}

	/**
	 * @param cardID
	 *            the cardID to set foregin key to associate response to a
	 *            question/card
	 */
	public void setCardID(int cardID) {
		this.cardID = cardID;
	}

	/**
	 * @return the attemptID
	 */
	public int getAttemptID() {
		return attemptID;
	}

	/**
	 * @param attemptID
	 *            the attemptID to set
	 */
	public void setAttemptID(int attemptID) {
		this.attemptID = attemptID;
	}

	/**
	 * @return the userResp
	 */
	public String getUserResp() {
		return userResp;
	}

	/**
	 * @param userResp
	 *            the userResp to set
	 */
	public void setUserResp(String userResp) {
		this.userResp = userResp;
	}

	public String toString() {
		return "Response " + responseID + ": " + userResp + " for Card " + cardID + " (in attempt " + attemptID + ") ";
	}
}
