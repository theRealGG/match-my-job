package com.matchmyjob.test.utilities.resolvers;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import net.datafaker.Faker;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class AbstractParameterResolver implements ParameterResolver {

  protected static final Faker faker = new Faker(Locale.ENGLISH);

  protected Map<Class<?>, ResolverFunction> parameters = new HashMap<>();

  @Override
  public boolean supportsParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameters.containsKey(parameterContext.getParameter().getType());
  }

  @Override
  public Object resolveParameter(
      ParameterContext parameterContext, ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameters.get(parameterContext.getParameter().getType()).apply(extensionContext);
  }
}
