package com.matchmyjob.test.utilities.resolvers.domain;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.core.models.dtos.NameDTO;
import com.matchmyjob.test.utilities.resolvers.AbstractParameterResolver;
import org.junit.jupiter.api.extension.ExtensionContext;

public class DomainModelResolver extends AbstractParameterResolver {

  public DomainModelResolver() {
    parameters.put(Username.class, DomainModelResolver::username);
    parameters.put(ApplicantDTO.class, DomainModelResolver::applicant);
  }

  private static ApplicantDTO applicant(
      @SuppressWarnings("unused") ExtensionContext extensionContext) {
    return ApplicantDTO.builder()
        .name(
            NameDTO.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .build())
        .build();
  }

  private static Username username(@SuppressWarnings("unused") ExtensionContext extensionContext) {
    return new Username(faker.internet().username());
  }
}
