package com.matchmyjob

plugins {
    id("com.matchmyjob.java-conventions")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}

springBoot {
    buildInfo {
        // Excludes time from build-info in favor of build cache
        // See https://docs.spring.io/spring-boot/gradle-plugin/integrating-with-actuator.html
        excludes.set(setOf("time"))
    }
}
tasks.bootJar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}