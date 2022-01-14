package com.wipro.cloud.api.helloworld.infrastructure.service;

import java.util.Arrays;
import java.util.Optional;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * LoggingAspect enables aspect oriented styled logging for
 * <ol>
 * <li>Method Entry and Exit logs</li>
 * <li>Exception logs</li>
 * </ol>
 * The logging is enabled for service, controller packages. In order to enable
 * logging for packages, edit the pointcut execution expressions.
 * 
 * @author LBG_ET
 * @since 0.1
 */
@Aspect
@Configuration
public class LoggingAspect {
	private final Logger logger = LoggerFactory.getLogger("logger.HelloWorldApi.dev");

	@Around(" " + "execution(* com.lbg.cbo.HelloWorldApi.service..domain..*(..)) ")
	public Object logMethodEntryAndExit(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.debug("Arguments passed to method:[{}] of {} are: {}", joinPoint.getSignature().getName(),
				joinPoint.getTarget(), joinPoint.getArgs());
		final Object result               = joinPoint.proceed();
		final String jsonSerialisedResult = JsonUtils.serialize(result);

		logger.debug("Output from the method {} is: {}", joinPoint.getSignature().getName(), jsonSerialisedResult);
		return result;
	}

	@AfterThrowing(pointcut = "execution(* com.lbg.cbo.HelloWorldApi.service..domain..*(..)) || "
			+ "execution(* com.lbg.cbo.HelloWorldApi..controller..*(..)) || "
			+ "execution(* com.lbg.cbo.HelloWorldApi.infrastructure..service..*(..)) "
			+ "", throwing = "exception")
	public void logExceptionDetails(JoinPoint joinPoint, Throwable exception) {
		final String exceptionStack = getExceptionWithTraces(exception);
		logger.error(" Caught exception in method {} of class {} with arguments {} \n with Exception : {}  ",
				joinPoint.getSignature().getName(), joinPoint.getTarget(), joinPoint.getArgs(), exceptionStack);

	}

	/**
	 * This method combines the exception message, trace stack and cause for logging
	 * purpose.
	 * 
	 * @param exception : Throwable
	 * @return message : String
	 */
	private String getExceptionWithTraces(final Throwable exception) {
		final StringBuilder       message       = new StringBuilder();
		final Optional<Throwable> optnException = Optional.ofNullable(exception);
		if (optnException.isPresent()) {
			optnException.map(Throwable::toString).ifPresent(message::append);
			optnException.map(Throwable::getStackTrace).ifPresent(stactTraceArr -> Arrays.stream(stactTraceArr)
					.forEach(traceObj -> message.append("\n\t at ").append(traceObj)));
			optnException.map(Throwable::getCause)
					.ifPresent(cause -> message.append("\n Caused by: ").append(getExceptionWithTraces(cause)));
		}
		return message.toString();
	}

}
