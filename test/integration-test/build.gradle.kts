plugins {
    id("com.matchmyjob.java-conventions")
}

dependencies {
    testImplementation(project(":app"))
    testImplementation(project(":domain"))
    testImplementation("com.netflix.graphql.dgs:dgs-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.netflix.graphql.dgs:dgs-starter-test")
    testImplementation(project(":incoming:web:graphql:applicant-graphql"))
}

tasks.test {
    description = "Runs integration tests"
    shouldRunAfter(":build")
}