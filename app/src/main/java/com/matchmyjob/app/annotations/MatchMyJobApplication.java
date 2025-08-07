package com.matchmyjob.app.annotations;

import static com.matchmyjob.app.infrastructure.InfrastructureConstants.BASE_PACKAGE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
public @interface MatchMyJobApplication {}
