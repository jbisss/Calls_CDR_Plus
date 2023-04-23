package ru.learnup.nexigntask.callscdrplus.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class TimeMeasurer {

    /**
     * Определяем поинткат - метод execute() в основных сервисах
     */
    @Pointcut(value = "execution(* ru.learnup.nexigntask.callscdrplus.service.mainservices.*.execute(..))")
    private void mainServicesExecuteMethod() {}

    /**
     * Выводим информацию о времени работы методов execute()
     *
     * @param pjp ссылка на исполняемый метод
     * @return результат работы исполняемого метода
     */
    @Around("mainServicesExecuteMethod()")
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
