package edu.hrbust.waterLogger.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
@Order(0)
public class ControllerLogAspect {
    @Pointcut("execution(public * edu.hrbust.waterLogger.controller..*.*(..))")
    public void controllerPoint() {}

    @Around("controllerPoint()")
    public Object doControllerAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            HttpServletRequest request =
                    ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            log.info("Controller层 request url={}, ip={}, params={}", request.getRequestURL(), request.getRemoteAddr(),
                    request.getParameterMap());
            Object[] params = joinPoint.getArgs();
            log.info("Controller层 className={}, methodName={}, params={}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(params));
            result = joinPoint.proceed();
        } finally {
            log.info("Controller层 耗时={}(ms), className={}, methodName={}, result={}",
                    System.currentTimeMillis() - startTime, joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
        }
        return result;
    }
}