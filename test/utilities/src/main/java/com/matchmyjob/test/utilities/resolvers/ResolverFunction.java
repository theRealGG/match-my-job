package com.matchmyjob.test.utilities.resolvers;

import io.vavr.Function1;
import org.junit.jupiter.api.extension.ExtensionContext;

@FunctionalInterface
public interface ResolverFunction extends Function1<ExtensionContext, Object> {}
