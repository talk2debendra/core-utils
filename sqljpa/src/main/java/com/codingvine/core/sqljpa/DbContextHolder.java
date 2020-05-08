package com.codingvine.core.sqljpa;

import org.springframework.core.NamedThreadLocal;

import lombok.experimental.UtilityClass;

@UtilityClass
public class DbContextHolder {

	private static final ThreadLocal<DbType> contextHolder = new NamedThreadLocal<>("db type context");

	public static void setDbType(DbType dbType) {
		if (dbType == null) {
			throw new NullPointerException();
		}

		contextHolder.set(dbType);

	}

	public static DbType getDbType() {
		return contextHolder.get();
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}