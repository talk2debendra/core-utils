/**
 * 
 */
package com.codingvine.core.base.utils;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import lombok.experimental.UtilityClass;

/**
 * @author debendra.dhinda
 */
@UtilityClass
public class CodingVineUtils {

	public static int generateOTP() {
		return ThreadLocalRandom.current().nextInt(100000, 1000000);
	}

	public static int generateOTPOfLength(int length) {
		return (int) ThreadLocalRandom.current().nextDouble(Math.pow(10, length - 1), Math.pow(10, length));
	}

	public static int generateDefaultOtpOfLength(int length) {

		int otp = 0;
		int count = 1;

		for (int i = 0; i < length; i++) {

			otp = (otp * 10) + count;

			count++;
		}

		return otp;
	}

	/**
	 * Return a random integer number between the given range inclusive @start and excluding @end
	 * 
	 * @param start
	 *            - the smallest random number required
	 * @param end
	 *            - one less than the maximum random number required
	 * @return random integer number between the given range inclusive @start and excluding @end
	 * @throws IllegalArgumentException
	 *             - if start is greater than equal to end
	 */
	public static int getRandomNumberBetweenRange(int start, int end) {
		return ThreadLocalRandom.current().nextInt(start, end);
	}

	public static String trimmedOrNull(String str) {
		return StringUtils.isBlank(str) ? null : str.trim();
	}

	public static boolean isValidEmail(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		return EmailValidator.getInstance().isValid(email);
	}

	public static String generateUniqueId(int length) {
		return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
	}

	public static String generateUniqueId() {
		return UUID.randomUUID().toString();
	}

	public static double roundToPlaces(double value, int places) {
		double numberToDivide = 1;

		while (places > 0) {
			numberToDivide *= 10;
			places--;
		}

		Double number = Double.valueOf(numberToDivide);
		return Math.round(value * number) / number;
	}


}