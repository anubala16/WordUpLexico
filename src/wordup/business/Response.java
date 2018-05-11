package wordup.business;

import java.io.Serializable;

public class Response implements Serializable {

	private int responseID, cardID, attemptID;
	private String userResp;
	
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
	 * @param responseID the responseID to set
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
	 * @param cardID the cardID to set
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
	 * @param attemptID the attemptID to set
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
	 * @param userResp the userResp to set
	 */
	public void setUserResp(String userResp) {
		this.userResp = userResp;
	}
	
}
