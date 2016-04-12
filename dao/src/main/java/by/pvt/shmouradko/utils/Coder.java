/**
 * 
 */
package by.pvt.shmouradko.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import by.pvt.shmouradko.model.logging.ShopLogger;

/**
 * @author Shmouradko Sergey
 *
 */
public class Coder {
	private static String ALGORITHM = "MD5";
	private static int POSITIVE = 1;

	public static String getHashCode(String password) {
		try {
			MessageDigest dig = MessageDigest.getInstance(ALGORITHM);
			// generating of hashcode
			BigInteger hash = new BigInteger(POSITIVE, dig.digest(password.getBytes()));
			// converting to string of 32 symbols, which represents hex number
			return String.format("%032x", hash);

		} catch (NoSuchAlgorithmException e) {
			// ShopLogger.getInstance(Coder.class).writeError("NoSuchAlgorithm " +
			// ALGORITHM);
		}
		throw new RuntimeException("Exception at Coder: NoSuchAlgorithm");
	}
}
