package com.matchmyjob.domain.core.applicant.services;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.core.exceptions.BusinessException;
import com.matchmyjob.domain.api.core.exceptions.TechnicalException;
import com.matchmyjob.domain.api.core.exceptions.ValidationException;
import com.matchmyjob.test.utilities.resolvers.annotations.DomainModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@DomainModelTest
class ApplicantServiceTest {

  @InjectMocks private ApplicantServiceDecorator applicantService;
  @Mock private ApplicantServiceCore applicantServiceCore;

  @Nested
  @DisplayName("Test get applicant by username")
  class TestGetApplicantByUsername {

    @Test
    @DisplayName("should get applicant by username for happy path")
    void shouldGetApplicantByUsername(Username username, ApplicantDTO applicant) {
      when(applicantServiceCore.getByUsername(eq(username))).thenReturn(Mono.just(applicant));
      var actualApplicant = applicantService.getByUsername(username);
      StepVerifier.create(actualApplicant).expectNext(applicant).verifyComplete();
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {"test"})
    @DisplayName("should reject invalid username")
    void shouldRejectApplicantWithInvalidUsername(String username) {
      assertThatThrownBy(() -> applicantService.getByUsername(new Username(username)))
          .isInstanceOf(ValidationException.class);
    }

    @Test
    @DisplayName("should propagate domain error")
    void shouldPropagateErrors(Username username) {
      when(applicantServiceCore.getByUsername(eq(username)))
          .thenReturn(Mono.error(BusinessException::new));
      StepVerifier.create(applicantService.getByUsername(username))
          .expectError(BusinessException.class)
          .verify();
    }

    @Test
    @DisplayName("should map unknown error to domain error")
    void shouldMapExceptionToDomainError(Username username) {
      when(applicantServiceCore.getByUsername(eq(username)))
          .thenReturn(Mono.error(IllegalArgumentException::new));
      StepVerifier.create(applicantService.getByUsername(username))
          .expectError(TechnicalException.class)
          .verify();
    }
  }
}
