package com.matchmyjob.incoming.web.graphql.applicant.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchmyjob.domain.api.applicant.models.dtos.ApplicantDTO;
import com.matchmyjob.test.utilities.resolvers.annotations.DomainModelTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DomainModelTest
class DomainToOutgoingMapperTest {

  private final DomainToOutgoingMapper mapper = new DomainToOutgoingMapperImpl();

  @Test
  @DisplayName("should map domain model to graphql model")
  void shouldMapDomainModelToGraphql(ApplicantDTO applicant) {
    assertThat(mapper.toApplicant(applicant)).usingRecursiveComparison().isEqualTo(applicant);
  }
}
