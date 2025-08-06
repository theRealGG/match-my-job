package com.matchmyjob.integration.test.incoming.applicant;

import static org.assertj.core.api.Assertions.assertThat;

import com.matchmyjob.integration.test.incoming.AbstractDgsTest;
import com.matchmymjob.incoming.graphql.applicant.generated.types.Applicant;
import com.matchmymjob.incoming.graphql.applicant.generated.types.Name;
import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ApplicantIT extends AbstractDgsTest {

  @Autowired DgsQueryExecutor dgsQueryExecutor;

  @Test
  @DisplayName("should retrieve applicant information")
  public void retrieveApplicantInformation() {
    @Language("GraphQL")
    var query =
        """
        {
            applicant(username: "username") {
                name {
                    firstname
                    lastname
                    nickname
                }
            }
        }
        """;
    var applicant =
        dgsQueryExecutor.executeAndExtractJsonPathAsObject(
            query, "data.applicant", Applicant.class);

    assertThat(applicant)
        .isNotNull()
        .extracting(Applicant::getName)
        .usingRecursiveComparison()
        .isEqualTo(new Name("John", "Doe", null));
  }
}
