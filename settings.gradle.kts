rootProject.name = "match-my-job"

include("app", "configuration", "starter")
include("domain", "domain:api", "domain:core")
include("test:utilities", "test:integration-test")
include(":outgoing", ":outgoing:persistence", ":outgoing:persistence:applicant")
include(
    ":incoming",
    ":incoming:web",
    ":incoming:web:graphql",
    ":incoming:web:graphql:applicant-graphql",
    ":incoming:web:graphql:graphql-starter"
)


includeBuild("build-logic")