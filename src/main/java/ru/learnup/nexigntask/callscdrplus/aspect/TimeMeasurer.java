package ru.learnup.nexigntask.callscdrplus.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class TimeMeasurer {

    @Pointcut(value = "execution(* ru.learnup.nexigntask.callscdrplus.service.*.execute(..))")
    private void generatorCdrServiceClassMethods() {}

    @Around("generatorCdrServiceClassMethods()")
    public Object measureMethodTime(ProceedingJoinPoint pjp){
        long startTime = System.currentTimeMillis();
        Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        log.info("Method {}.{}() duration: {} ms", pjp.getSignature().getDeclaringType(), pjp.getSignature().getName(), duration);
        return result;
    }
}
