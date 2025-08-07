plugins {
    id("com.matchmyjob.java-conventions")
}

dependencies {
    testImplementation(project(":app"))
    testImplementation("com.netflix.graphql.dgs:dgs-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("com.netflix.graphql.dgs:dgs-starter-test")
    testImplementation(project(":starter"))

}

tasks.test {
    description = "Runs integration tests"
    shouldRunAfter(":build")
}