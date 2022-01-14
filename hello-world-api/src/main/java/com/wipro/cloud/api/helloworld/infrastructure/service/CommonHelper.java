package com.wipro.cloud.api.helloworld.infrastructure.service;


import java.util.Base64;
import java.util.Optional;
import java.util.stream.IntStream;

public class CommonHelper {
    private CommonHelper() {

	}

	private static final String ALPHABET_P = "P";
	private static final String ALPHABET_D = "D";

	/**
	 * This method is used for converting Password Expiry period from String format
	 * to integer format.
	 * 
	 * @param passwordExpiryPeriod : String
	 * @return integer
	 */
	public static int getPasswordExpiryPeriod(final String passwordExpiryPeriod) {
		int passwordExpiry = 0;

		if (null != passwordExpiryPeriod) {
			String passExpiryTime = passwordExpiryPeriod.substring(passwordExpiryPeriod.indexOf(ALPHABET_P) + 1,
					passwordExpiryPeriod.lastIndexOf(ALPHABET_D));
			passwordExpiry = Integer.parseInt(passExpiryTime);
		}

		return passwordExpiry;
	}

	public static <T> boolean nullCheck(T g1) {
		return Optional.ofNullable(g1).isPresent();
	}

	public static boolean nullCheckAndEmpty(String str) {
		return Optional.ofNullable(str).isPresent() && !str.isEmpty();
	}

	public static boolean isBlank(String str) {
		return !Optional.ofNullable(str).isPresent() || str.trim().isEmpty();
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String leftPadWithZeroesFor(String input, int actualLength) {
		if (!isBlank(input)) {
			int lengthOfString = input.trim().length();
			int noOfZeroesToPad = actualLength - lengthOfString;
			String paddedZeroes = IntStream.rangeClosed(1, noOfZeroesToPad).collect(StringBuilder::new,
					(stringBuffer, index) -> stringBuffer.append("0"), StringBuilder::append).toString();
			return paddedZeroes.concat(input);
		}
		return input;

	}

	/**
	 * 
	 */
	public static String decodeString(String encodedString) {
		return new String(Base64.getDecoder().decode(encodedString.getBytes()));
	}
	/**
	 * 
	 */
	public static String encodeString(String input) {
		return (Base64.getEncoder().encodeToString(input.getBytes()));
	}
 
}
