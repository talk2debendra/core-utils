package com.codingvine.core.sqljpa;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAttributeSource;

/**
 * Around advice for methods that are marked as {@link Transactional}
 * that remembers the {@link TransactionDefinition} for other components
 * to use.
 * <p>
 * This is used by {@link TransactionDefinitionRoutingDataSource} to
 * determine if the current transaction is read-only.
 * </p>
 *
 * @see TransactionDefinitionRoutingDataSource
 * @see #currentTransactionDefinition()
 * @see #isCurrentTransactionReadOnly()
 */
@Aspect
@Component
public class TransactionDefinitionInterceptor implements Ordered {

	private Logger logger = LoggerFactory.getLogger(TransactionDefinitionInterceptor.class);

	private final TransactionAttributeSource transactionAttributeSource;

	private int order = Ordered.LOWEST_PRECEDENCE - 10; // before Spring's TransactionInterceptor

	public TransactionDefinitionInterceptor(
			TransactionAttributeSource transactionAttributeSource) {
		this.transactionAttributeSource = transactionAttributeSource;
	}

	@Around("@annotation(transactional)")
	public Object rememberTransactionDefinition(ProceedingJoinPoint joinPoint, Transactional transactional) throws Throwable {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		Class<?> targetClass = joinPoint.getTarget().getClass();
		TransactionDefinition transactionDefintion = transactionAttributeSource.getTransactionAttribute(method, targetClass);
		boolean restore = false;

		if (logger.isTraceEnabled()) {
			logger.trace("is transaction readonly-> {0}." , transactionDefintion.isReadOnly());
		}

		if (transactionDefintion.isReadOnly()) {
			// Store state to allow other components to determine if TX is read-only
			DbContextHolder.setDbType(DbType.REPLICA);
			restore = true;
		}
		try {
			return joinPoint.proceed();
		} finally {
			if (restore) {
				DbContextHolder.setDbType(DbType.MASTER);
			}
		}
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public int getOrder() {
		return order;
	}

}