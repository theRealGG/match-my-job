import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    id("java-lib-conventions")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED)
        exceptionFormat = FULL
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    // see: https://stackoverflow.com/questions/79640685/cannot-build-project-after-upgrading-spring-boot-to-3-5
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}