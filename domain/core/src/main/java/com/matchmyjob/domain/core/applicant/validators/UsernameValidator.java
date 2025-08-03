package com.matchmyjob.domain.core.applicant.validators;

import static io.vavr.control.Validation.invalid;
import static io.vavr.control.Validation.valid;
import static java.util.Objects.isNull;

import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.applicant.models.validators.UsernameValidatorFunction;
import com.matchmyjob.domain.core.error.ErrorFactory;

public class UsernameValidator {

  public static final UsernameValidatorFunction NON_NULL =
      username ->
          isNull(username) || isNull(username.username())
              ? invalid(ErrorFactory.validationError("No username provided"))
              : valid(username);

  public static final UsernameValidatorFunction MIN_LENGTH =
      username ->
          username.username().length() >= Username.MIN_LENGTH
              ? valid(username)
              : invalid(
                  ErrorFactory.validationError(
                      "Username length should be at least {} characters", Username.MIN_LENGTH));
}
