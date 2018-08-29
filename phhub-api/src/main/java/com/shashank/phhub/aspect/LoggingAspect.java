package com.shashank.phhub.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

	@Before("execution(* com.shashank.phhub.controller.*.*(..))")
	public void before(JoinPoint joinPoint) {
		// Advice
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		log.info("Inside " + joinPoint.getClass() + "#" + method.getName() + " Having parametere "
				+ method.getParameters());
	}

	@Around("@annotation(com.shashank.phhub.custom.annotation.TrackTime)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		long startTime = System.currentTimeMillis();

		joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;
		log.info("Time Taken by {} is {}", joinPoint, timeTaken);
	}
}
