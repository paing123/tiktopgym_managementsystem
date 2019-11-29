package com.tiktop.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Aspect
@Configuration
public class MemberAccessObject {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Before("execution(* com.tiktop.services.*.*(..))")
	public void afterReturning(JoinPoint joinPoint) {
		logger.info("Check for user access ");
		logger.info("Allowed execution for {}",joinPoint);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info(""+auth);
	}
}
