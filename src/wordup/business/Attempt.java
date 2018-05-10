package wordup.business;

import java.sql.Date;

public class Attempt {

	private int attemptID, lessonID, userID, score, count;
	private Date timestamp;
	
	/**
	 * Default constructor for Attempt POJO
	 */
	public Attempt () {
		super();
	}
	
	/**
	 * @param lessonID
	 * @param userID
	 * @param score
	 * @param timestamp
	 */
	public Attempt(int lessonID, int userID, int count) {
		this.lessonID = lessonID;
		this.userID = userID;
		this.score = 0;
		this.count = count;
		long time = System.currentTimeMillis();
		this.timestamp = new Date(time);
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
	 * @return the lessonID
	 */
	public int getLessonID() {
		return lessonID;
	}

	/**
	 * @param lessonID the lessonID to set
	 */
	public void setLessonID(int lessonID) {
		this.lessonID = lessonID;
	}

	/**
	 * @return the userID
	 */
	public int getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}
