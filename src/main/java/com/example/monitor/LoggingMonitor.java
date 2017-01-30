package com.example.monitor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LoggingMonitor {

	@Around("execution(* com.example.service..*.*(..))")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        final Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        Object retVal = null;

        try {
            StringBuffer sb = new StringBuffer();

            sb.append("Start method ");
            sb.append(joinPoint.getSignature().getName());
            sb.append("(");

            Object[] args = joinPoint.getArgs();
            for (int i = 0; i < args.length; i++) {
                sb.append(args[i]).append(",");
            }
            if (args.length > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }

            sb.append(")");

            logger.trace(sb.toString());

            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            retVal = joinPoint.proceed();

            stopWatch.stop();

            StringBuffer endMessageStringBuffer = new StringBuffer();
            endMessageStringBuffer.append("Finish method ");
            endMessageStringBuffer.append(joinPoint.getSignature().getName());
            endMessageStringBuffer.append("(..); execution time: ");
            endMessageStringBuffer.append(stopWatch.getTotalTimeMillis());
            endMessageStringBuffer.append(" ms;");

            logger.info(endMessageStringBuffer.toString());
        } catch (Throwable e) {
            logger.error("DANGER", e);
            throw e;
        }

        return retVal;
    }
}
