package com.matchmyjob.domain.api.applicant.models.validators;

import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.core.validators.ValidatorFunction;

@FunctionalInterface
public interface UsernameValidatorFunction extends ValidatorFunction<Username> {}
