package com.codingvine.core.sqljpa.specification.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SearchCriteria {

	private String key;
	private CriteriaOperation operation;
	private Object value;

}