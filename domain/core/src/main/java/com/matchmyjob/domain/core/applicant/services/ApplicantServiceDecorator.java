package com.matchmyjob.domain.core.applicant.services;

import static com.matchmyjob.domain.core.applicant.validators.UsernameValidator.MIN_LENGTH;
import static com.matchmyjob.domain.core.applicant.validators.UsernameValidator.NON_NULL;
import static java.util.function.Predicate.not;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.applicant.services.ApplicantService;
import com.matchmyjob.domain.api.core.exceptions.MatchMyJobException;
import com.matchmyjob.domain.api.core.exceptions.TechnicalException;
import com.matchmyjob.domain.api.core.exceptions.ValidationException;
import com.matchmyjob.domain.api.core.validators.ValidatorService;
import com.matchmyjob.domain.core.validators.GenericValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class ApplicantServiceDecorator implements ApplicantService {

  private final ApplicantService applicantService;

  private final ValidatorService<Username> usernameValidator =
      GenericValidatorService.of(NON_NULL, MIN_LENGTH);

  @Override
  public Mono<ApplicantDTO> getByUsername(Username username) {
    var violations = usernameValidator.validate(username);
    if (violations.isInvalid()) {
      throw new ValidationException(violations.getError());
    }
    return applicantService
        .getByUsername(username)
        .doFirst(() -> log.info("Trying to retrieve username"))
        .onErrorMap(not(MatchMyJobException.class::isInstance), TechnicalException::new)
        .doOnError(throwable -> log.error("Error retrieving applicant by username", throwable));
  }
}
