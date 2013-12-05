package dk.kea.si.movies.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

public class AppUtils {


	/**
	 * Hashes the string using sha 256 algorithm.
	 * Source: www.stackoverflow.com
	 * 
	 * @param str	string to be hashed.
	 * @return		hex string representation of the hash.
	 */
	public static String sha256(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(str.getBytes("UTF-8"));
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new ApplicationException(e);
		} catch (UnsupportedEncodingException e) {
			throw new ApplicationException(e);
		}
	}

	public static String generateSalt() {	
		Random random = new SecureRandom();
		byte bytes[] = new byte[32];
		random.nextBytes(bytes);
		return DatatypeConverter.printHexBinary(bytes);
	}
}
