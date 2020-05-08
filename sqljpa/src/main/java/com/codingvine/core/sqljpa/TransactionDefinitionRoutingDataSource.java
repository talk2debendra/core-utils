package com.codingvine.core.sqljpa;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
 * AbstractRoutingDataSource} implementation that routes to a read-only data
 * source if the current transaction is read-only (i.e.
 * <code>&#64;Transactional(readOnly=true)</code>). This is useful for
 * scaling-up read-heavy database applications.
 * <p>
 * <b>Note:</b> In order for this to have access to the current transaction and
 * determine if it is read-only, it is necessary to include a
 * {@link TransactionDefinitionInterceptor} which will intercept
 * {@link Transactional} methods.
 * </p>
 *
 * @see TransactionDefinitionInterceptor
 */
public class TransactionDefinitionRoutingDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}

}