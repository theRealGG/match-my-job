package com.matchmyjob.outgoing.persistence.applicant.repositories;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.domain.api.applicant.models.incoming.Username;
import com.matchmyjob.domain.api.applicant.repositories.ApplicantRepository;
import com.matchmyjob.domain.api.core.models.dtos.NameDTO;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ApplicantR2dbcRepository implements ApplicantRepository {

  @Override
  public Mono<ApplicantDTO> getByUsername(Username username) {
    return Mono.just(ApplicantDTO.builder().name(new NameDTO("John", "Doe", null)).build());
  }
}
