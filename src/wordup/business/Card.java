/**
 * 
 */
package wordup.business;

import java.io.Serializable;

/**
 * @author anuba
 *
 */
public class Card implements Serializable {

	/**
	 * Word - The vocab word token (before the colon character in the input file line) 
	 * Description - the definition/description for the given word 
	 */
	private String word, description; 
	
	/**
	 * lessonID - id of the lesson this card belongs to 
	 */
	private int cardID, lessonID;
	
	/**
	 * Default constructor for the POJO 
	 */
	public Card() {
		super();
		
	}

	/**
	 * 
	 * @param word
	 * @param description
	 * @param lessonID
	 */
	public Card(String word, String description, int lessonID) {
		this.word = word;
		this.description = description;
		this.lessonID = lessonID;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	
}
