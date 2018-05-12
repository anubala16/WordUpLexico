package wordup.business;

import java.io.Serializable;

/**
 * User POJO to represent a user in system user can create/update lessons,
 * admins can view users and lessons in system, user can take quizzes and access
 * their scores, etc.
 * 
 * @author anusha balaji
 *
 */
public class User implements Serializable {

	// primary key and type of user (1 for customer, 0 for admin
	private int userID, type;
	
	// first name of user
	private String firstName;
	
	// last name of user
	private String lastName;
	
	// unique, emailid of user, used for log in
	private String email;
	
	// salt + hash password
	private String password;
	
	// student, teacher, academia, other user; collected for future intelligence
	// in the app
	private String profession;

	// private String pwd;
	
	// salt for the password (used during log in authentication) 
	private String salt;

	/**
	 * Default constructor
	 */
	public User() {
		super();
		firstName = "";
		lastName = "";
		email = "";
		type = 1;
		password = "";
		profession = "";
		salt = "";
		// pwd = "";
	}

	/**
	 * Overloaded constructor for user with required attributes 
	 * @param firstName
	 *            first name of user
	 * @param lastName
	 *            last name of user
	 * @param email
	 *            email id of user - unique
	 * @param password
	 *            hashed and salted password
	 * @param prof
	 *            profession of user ("admin", "student", "teaching
	 *            professional", "other (academia)", "other")
	 * @param type
	 *            type of user; 0 for admin, 1 for other
	 * @param pwd
	 *            plain-text password - will be deleted after development
	 * @param salt
	 *            random salt to hash user passwords
	 */
	public User(String firstName, String lastName, String email, String password, String prof, int type, String salt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.profession = prof;
		this.type = type;
		// this.pwd = pwd;
		this.salt = salt;
	}

	/**
	 * Forllowing are getters and setter for all the private instance variables 
	 * @return
	 */
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

}
