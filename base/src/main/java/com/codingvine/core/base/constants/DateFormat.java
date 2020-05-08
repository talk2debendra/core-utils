/**
 * 
 */
package com.codingvine.core.base.constants;

import lombok.Getter;

/**
 * @author debendra.dhinda
 *
 */
@Getter
public enum DateFormat {

	YYYY_HIFEN_MM_HIFEN_DD("yyyy-MM-dd"),
	COMMENT_FORMAT("dd/MM/YYYY (HH:mm)"),
	MONTH_FULL_NAME("MMMM"),
	WEEK_OF_YEAR("w"),
	YEAR_IN_WEEK_OF_YEAR("YYYY"),
	DAY_OF_MONTH("d"),
	DD_MMM_YYYY("dd-MMM-yyyy"),
	DD_SPACE_MMM_SPACE_YYYY("dd MMM yyyy");

	private String value;

	DateFormat(String value) {
		this.value = value;
	}

}