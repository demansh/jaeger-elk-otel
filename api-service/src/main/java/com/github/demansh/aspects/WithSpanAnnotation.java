package com.github.demansh.aspects;

import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WithSpanAnnotation {

    private final OpenTelemetry openTelemetry;
    private final Tracer tracer;

    public WithSpanAnnotation(OpenTelemetry openTelemetry) {
        this.openTelemetry = openTelemetry;
        tracer = openTelemetry.getTracer("api-service");
    }

    @Around("@annotation(WithSpan)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        Span span = tracer.spanBuilder(joinPoint.getClass().getName()).startSpan();
        try(Scope scope = span.makeCurrent()) {
            return joinPoint.proceed();
        } catch (Throwable t) {
            span.recordException(t);
            throw t;
        } finally {
            span.end();
        }
    }
}
