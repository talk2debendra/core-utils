/**
 * 
 */
package com.codingvine.core.base.constants;

import java.time.ZoneId;

/**
 * @author debendra.dhinda
 *
 */

public class CodingVineConstants {

	public static final String DATE_SEPARATOR = "-";
	public static final String IST_TIMEZONE = "Asia/Kolkata";
	public static final ZoneId IST_TIMEZONEID = ZoneId.of(IST_TIMEZONE);
	public static final String INDIA_COUNTRY_CODE = "91";
	public static final String INDIA_ISO_CODE = "IN";
	public static final int PRICE_ROUND_OFF_DIGITS = 2;

	public static final long SECONDS_IN_DAY = 86400;
	public static final long MILLI_SECONDS_IN_DAY = SECONDS_IN_DAY * 1000;

	public static final String MESSAGE_ID = "messageId";

	public static final String GUID = "guid";
}