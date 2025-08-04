rootProject.name = "match-my-job"

include("app")
include("domain", "domain:api", "domain:core")
include("test:utilities")
include(":outgoing", ":outgoing:persistence", ":outgoing:persistence:applicant")

includeBuild("build-logic")