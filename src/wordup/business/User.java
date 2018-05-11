package wordup.business;

import java.io.Serializable;

public class User implements Serializable {

	private int userID, type;
   	private String firstName;
    private String lastName;
    private String email;
    private String password; 
    private String profession;
   //private String pwd; 
    private String salt;

    public User() {
        super();
    	firstName = "";
        lastName = "";
        email = "";
        type = 1;
        password = "";
        profession = ""; 
        salt = ""; 
        //pwd = "";
    }

    /**
     * 
     * @param firstName first name of user 
     * @param lastName last name of user 
     * @param email email id of user - unique 
     * @param password hashed and salted password 
     * @param prof profession of user ("admin", "student", "teaching professional", "other (academia)", "other") 
     * @param type type of user; 0 for admin, 1 for other 
     * @param pwd plain-text password - will be deleted after development 
     * @param salt random salt to hash user passwords 
     */
    public User(String firstName, String lastName, String email, String password, String prof, int type, String salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profession = prof;
        this.type = type;
        //this.pwd = pwd;
        this.salt = salt;
    }

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
	 * @return the pwd
	 *
	public String getPwd() {
		return pwd;
	}

	/**
	 * @param pwd the pwd to set
	 *
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

}
