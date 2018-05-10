/**
 * 
 */
package wordup.business;

import java.io.Serializable;
import java.sql.Date;

/**
 * Bean for Lesson 
 * @author abalaji
 *
 */
public class Lesson implements Serializable {

	private String title, filePath, subject, subject2, subject3, accessLevel; 
	
	private int lessonID, authorID; 
	
	private Date dateCreated;
	
	/**
	 * default
	 */
	public Lesson() {
		long time = System.currentTimeMillis();
		dateCreated = new Date(time);
	}

	/**
	 * Minimal constructor with all the required fields 
	 * @param title
	 * @param filePath
	 * @param subject
	 * @param accessLevel
	 * @param authorID
	 */
	public Lesson(String title, String filePath, String subject, String accessLevel, int authorID) {
		this.title = title;
		this.filePath = filePath;
		this.subject = subject;
		this.accessLevel = accessLevel;
		this.authorID = authorID;
		long time = System.currentTimeMillis();
		this.dateCreated = new Date(time);
	}
	
	/**
	 * Minimal constructor with all the required fields 
	 * @param title
	 * @param filePath
	 * @param subject
	 * @param accessLevel
	 * @param authorID
	 */
	public Lesson(String title, String filePath, String subject, String subject2, String subject3, String accessLevel, int authorID) {
		this.title = title;
		this.filePath = filePath;
		this.subject = subject;
		this.subject2 = subject2;
		this.subject3 = subject3;
		this.accessLevel = accessLevel;
		this.authorID = authorID;
		long time = System.currentTimeMillis();
		this.dateCreated = new Date(time);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the subject2
	 */
	public String getSubject2() {
		return subject2;
	}

	/**
	 * @param subject2 the subject2 to set
	 */
	public void setSubject2(String subject2) {
		this.subject2 = subject2;
	}

	/**
	 * @return the subject3
	 */
	public String getSubject3() {
		return subject3;
	}

	/**
	 * @param subject3 the subject3 to set
	 */
	public void setSubject3(String subject3) {
		this.subject3 = subject3;
	}

	/**
	 * @return the accessLevel
	 */
	public String getAccessLevel() {
		return accessLevel;
	}

	/**
	 * @param accessLevel the accessLevel to set
	 */
	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
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
	 * @return the authorID
	 */
	public int getAuthorID() {
		return authorID;
	}

	/**
	 * @param authorID the authorID to set
	 */
	public void setAuthorID(int authorID) {
		this.authorID = authorID;
	}

	/**
	 * @return the dateCreated
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String toString() {
		return title + " (" + filePath + ") " + accessLevel + " lesson for " + subject;
	}
}
