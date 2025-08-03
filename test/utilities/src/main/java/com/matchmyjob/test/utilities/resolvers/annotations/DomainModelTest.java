package com.matchmyjob.test.utilities.resolvers.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.matchmyjob.test.utilities.resolvers.domain.DomainModelResolver;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@Target(TYPE)
@ExtendWith(DomainModelResolver.class)
public @interface DomainModelTest {}
