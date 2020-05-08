package com.codingvine.core.base.utils;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

import com.codingvine.core.base.constants.CodingVineConstants;
import com.codingvine.core.base.constants.DateFormat;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtil {

	public static String customDateFormatter(Date dateInput, DateFormat dateFormat) {

		if (dateInput != null) {
			SimpleDateFormat formatterOutput = new SimpleDateFormat(dateFormat.getValue());
			return formatterOutput.format(dateInput);
		}

		return null;
	}

	public static String customDateFormatter(LocalDate dateInput, DateFormat dateFormat) {

		if (dateInput != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.getValue());
			return formatter.format(dateInput);
		}

		return null;
	}

	public static Date customDateParser(String dateInput, DateFormat dateFormat) {

		if (dateInput != null) {
			SimpleDateFormat formatterOutput = new SimpleDateFormat(dateFormat.getValue());
			try {
				return formatterOutput.parse(dateInput);
			} catch (ParseException e) {
				// Ignore
			}
		}

		return null;
	}

	public static String changeDateFormat (String dateInput, DateFormat inputDateFormat, DateFormat outputDateFormat) {

		if (dateInput != null) {
			SimpleDateFormat formatterOutput = new SimpleDateFormat(inputDateFormat.getValue());
			try {
				return customDateFormatter(formatterOutput.parse(dateInput), outputDateFormat);
			} catch (ParseException e) {
				// Ignore
			}
		}

		return null;
	}

	public static Date convertToDate(LocalDateTime localdateTime) {
		return Date.from(localdateTime.atZone(ZoneId.of(CodingVineConstants.IST_TIMEZONE)).toInstant());
	}

	public static LocalDateTime convertToLocalDateTime(Date date) {
		if (date == null) {
			return null;
		}

		ZoneId zoneId = ZoneId.of(CodingVineConstants.IST_TIMEZONE);
		Instant instant = date.toInstant();
		return instant.atZone(zoneId).toLocalDateTime();
	}

	/**
	 * Method to generate a list of all LocalDate between given date range. It includes the startDate and endDate both in list
	 *
	 * @param startDate
	 * @param endDate
	 * @return list of LocalDate including start and end date
	 */
	public static List<LocalDate> getAllLocalDatesForRange(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<>();

		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			dates.add(startDate);
			startDate = startDate.plusDays(1);
		}

		return dates;
	}

	public static List<LocalDate> getAllLocalDatesBetweenRange(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<>();

		while (!startDate.isEqual(endDate)) {
			dates.add(startDate);
			startDate = startDate.plusDays(1);
		}

		return dates;
	}

	/**
	 * Method to generate a list of all Date between given date range. It includes the startDate and endDate both in list
	 *
	 * @param startDate
	 * @param endDate
	 * @return list of Date including start and end date
	 */
	public static List<Date> getAllDatesForRange(LocalDate startDate, LocalDate endDate) {
		List<Date> dates = new ArrayList<>();

		while (startDate.isBefore(endDate) || startDate.isEqual(endDate)) {
			dates.add(DateUtil.convertToDate(startDate));
			startDate = startDate.plusDays(1);
		}

		return dates;
	}

	public static List<Date> getAllDatesBetweenRange(LocalDate startDate, LocalDate endDate) {
		List<Date> dates = new ArrayList<>();

		while (startDate.isBefore(endDate)) {
			dates.add(DateUtil.convertToDate(startDate));
			startDate = startDate.plusDays(1);
		}

		return dates;
	}

	public static Date getYesterDate(Date date) {
		Date yesterday = null;
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(Calendar.DATE, -1);
			yesterday = cal.getTime();
		}
		return yesterday;
	}

	public static Date convertToDate(LocalDate localdate) {

		if (localdate == null) {
			return null;
		}

		return Date.from(localdate.atStartOfDay(ZoneId.of(CodingVineConstants.IST_TIMEZONE)).toInstant());
	}

	public static LocalDate convertToLocalDate(long timestamp) {
		return Instant.ofEpochMilli(timestamp).atZone(ZoneId.of(CodingVineConstants.IST_TIMEZONE)).toLocalDate();
	}

	public static Date convertToDate(LocalTime localTime) {

		if (localTime == null) {
			return null;
		}

		return Date.from(localTime.atDate(LocalDate.now()).atZone(ZoneId.of(CodingVineConstants.IST_TIMEZONE)).toInstant());
	}

	public static Integer yearsBetween(LocalDate one, LocalDate two) {

		if (Objects.isNull(one) || Objects.isNull(two)) {
			return null;
		}

		return Period.between(one, two).getYears();
	}

	public static LocalDate convertToLocalDate(Date date) {
		if (date == null) {
			return null;
		}

		if (date instanceof java.sql.Date) {
			return ((java.sql.Date) date).toLocalDate();
		}

		ZoneId zoneId = ZoneId.of(CodingVineConstants.IST_TIMEZONE);
		Instant instant = date.toInstant();
		return instant.atZone(zoneId).toLocalDate();
	}

	public static LocalTime convertToLocalTime(Date date) {
		if (date == null) {
			return null;
		}

		if (date instanceof java.sql.Date) {
			return new Time(((java.sql.Date) date).getTime()).toLocalTime();
		}

		ZoneId zoneId = ZoneId.of(CodingVineConstants.IST_TIMEZONE);
		Instant instant = date.toInstant();
		return instant.atZone(zoneId).toLocalTime();
	}

	public static long daysBetween(Date one, Date two) {
		long difference = ((one.getTime() - two.getTime()) / CodingVineConstants.MILLI_SECONDS_IN_DAY);
		return Math.abs(difference);
	}

	public static int getMaxDaysInMonth(LocalDate date) {
		return date.lengthOfMonth();
	}

	public static int getDaysInCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getMTDDaysCount() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getLaterDate(Date d1, Date d2) {

		if (d1 == null && d2 == null) {
			return null;
		}

		if (d1 == null) {
			return d2;
		}

		if (d2 == null) {
			return d1;
		}

		return d1.after(d2) ? d1 : d2;
	}

	public static LocalDate getLaterLocalDate(LocalDate d1, LocalDate d2) {

		if (d1 == null && d2 == null) {
			return null;
		}

		if (d1 == null) {
			return d2;
		}

		if (d2 == null) {
			return d1;
		}

		return d1.isAfter(d2) ? d1 : d2;
	}

	public static LocalDate getEarlierLocalDate(LocalDate d1, LocalDate d2) {

		if (d1 == null && d2 == null) {
			return null;
		}

		if (d1 == null) {
			return d2;
		}

		if (d2 == null) {
			return d1;
		}

		return d1.isAfter(d2) ? d2 : d1;
	}

	public static List<String> getListOfMonths(LocalDate startDate, LocalDate endDate) {
		LinkedHashSet<String> monthsList = new LinkedHashSet<>();
		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			monthsList.add(customDateFormatter(date, DateFormat.MONTH_FULL_NAME));
		}
		return new ArrayList<>(monthsList);
	}

	public static List<String> getYearWeekSqlListOfWeeks(LocalDate startDate, LocalDate endDate) {
		LinkedHashSet<String> weeksList = new LinkedHashSet<>();
		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			Integer weekNumber = Integer.parseInt(customDateFormatter(date, DateFormat.WEEK_OF_YEAR));
			String week;
			if (weekNumber < 10) {
				week = "0" + weekNumber.toString();
			} else {
				week = weekNumber.toString();
			}
			weeksList.add(customDateFormatter(date, DateFormat.YEAR_IN_WEEK_OF_YEAR) + week);
		}

		return new ArrayList<>(weeksList);
	}

	public static List<String> getFormattedListOfWeeksFromWeekOne(LocalDate startDate, LocalDate endDate) {
		List<String> weeksList = getYearWeekSqlListOfWeeks(startDate, endDate);

		List<String> weekListInFromOne = new ArrayList<>();
		for (Integer i = 1; i <=  weeksList.size() ; i++) {
			weekListInFromOne.add("Week " + i.toString());
		}

		return weekListInFromOne;
	}

	public static List<String> getListOfDates(LocalDate startDate, LocalDate endDate) {
		LinkedHashSet<String> dateList = new LinkedHashSet<>();
		for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
			dateList.add(customDateFormatter(date, DateFormat.YYYY_HIFEN_MM_HIFEN_DD));
		}
		return new ArrayList<>(dateList);
	}

	public static Integer getCountOfDates(LocalDate startDate, LocalDate endDate) {
		return getListOfDates(startDate, endDate).size();
	}

	public static LocalDate getCurrentMonthStartDate() {
		return LocalDate.now().withDayOfMonth(1);
	}

	public static Date getNMinutesBackTime(Integer minutes){
		return getNMinutesBackTime(new Date(), minutes);
	}

	public static Date getNMinutesBackTime(Date date, Integer minutes){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, -(minutes));
		return cal.getTime();
	}

}