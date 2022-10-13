package com.noterror.app.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 담당자 : 강시혁
 * 기능별 로그, 시간 기록 AOP
 */
@Aspect
public class LogAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Around("execution(* com.noterror.app.api..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        logger.info("REQUEST : {}({})", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName().toString());

        Object result = joinPoint.proceed();

        long finish = System.currentTimeMillis();
        long timeMs = finish - start;

        logger.info("RESPONSE : {}({}) = {} ({}ms)", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result, timeMs);

        return result;
    }
}