package com.codingvine.core.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurrentUser {

	private String token;

	private String userId;

	private String userUuid;
	
	private String firstName;

	private String lastName;

	private String mobile;

	private String isoCode;

	private String email;
	
}