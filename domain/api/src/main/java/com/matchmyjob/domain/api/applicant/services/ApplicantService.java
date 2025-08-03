package com.matchmyjob.domain.api.applicant.services;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import reactor.core.publisher.Mono;

public interface ApplicantService  {
  Mono<ApplicantDTO> getByUsername(Username username);
}
