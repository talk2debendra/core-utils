package com.codingvine.core.sqljpa.specification.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CriteriaOperation {

	TRUE("true"),
	FALSE("false"),
	LIKE("like"),
	EQ("="),
	NOT_EQ("!="),
	GTE(">="),
	GT(">"),
	LTE("<="),
	LT("<"),
	DATE_EQ("="),
	DATE_GTE(">="),
	DATE_GT(">"),
	DATE_LTE("<="),
	DATE_LT("<"),
	LOCAL_DATE_EQ("="),
	LOCAL_DATE_GT(">"),
	LOCAL_DATE_LT("<"),
	LOCAL_DATE_GTE(">="),
	LOCAL_DATE_LTE("<="),
	IN("in"),
	REGEXP("regexp"),
	FIND_IN_SET("find_in_set"),
	ENUM_EQ("="),
	ENUM_IN("in"),
	IS_NULL("is null");

	private String operation;

}