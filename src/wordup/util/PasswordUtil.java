package wordup.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.Base64;

/**
 * Utility class for password and salt hashing; 
 * used from Chapter 17 Slides based on course text by Murach 
 */
public class PasswordUtil {
	
	/**
	 * Hashes the given plaintext string 
	 * @param password string to hash 
	 * @return hashed password 
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashPassword(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.reset();
		md.update(password.getBytes());
		byte[] mdArray = md.digest();
		StringBuilder sb = new StringBuilder(mdArray.length * 2);
		for (byte b : mdArray) {
			int v = b & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString();
	}

	/**
	 * Returns a random salt for pwd hashing 
	 * @return randomly generated salt value 
	 */
	public static String getSalt() {
		Random r = new SecureRandom();
		byte[] saltBytes = new byte[32];
		r.nextBytes(saltBytes);
		return Base64.getEncoder().encodeToString(saltBytes);
	}

	/**
	 * Returns the hashed version of the given password and random hash 
	 * @param password string to hash 
	 * @return hash and salt pwd 
	 * @throws NoSuchAlgorithmException
	 */
	public static String hashAndSaltPassword(String password)
            throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password + salt);
    }

}