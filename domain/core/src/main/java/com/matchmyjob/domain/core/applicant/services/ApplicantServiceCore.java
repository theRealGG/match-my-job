package com.matchmyjob.domain.core.applicant.services;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.applicant.repositories.ApplicantRepository;
import com.matchmyjob.domain.api.applicant.services.ApplicantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class ApplicantServiceCore implements ApplicantService {

  private final ApplicantRepository applicantRepository;

  @Override
  public Mono<ApplicantDTO> getByUsername(Username username) {
    return applicantRepository.getByUsername(username);
  }
}
