package com.matchmyjob

import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED

plugins {
    java
    jacoco
    id("io.freefair.lombok")
    id("io.spring.dependency-management")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

java {
    toolchain {
        languageVersion.set(
            JavaLanguageVersion.of(libs.findVersion("jdk").get().toString())
        )

    }
}

jacoco {
    toolVersion = "0.8.13"
}

lombok {
    version = "1.18.30"
}

dependencyManagement {
    imports {
        mavenBom(
            "com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:${
                libs.findVersion("dgs-platform").get()
            }"
        )
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

dependencies {
    annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation(libs.findLibrary("vavr").get())
    implementation("io.projectreactor:reactor-core")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    // see: https://stackoverflow.com/questions/79640685/cannot-build-project-after-upgrading-spring-boot-to-3-5
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation(libs.findBundle("test-core").get())
}


tasks.compileJava {
    @Suppress("SpellCheckingInspection")
    options.compilerArgs.addAll(
        listOf(
            "-Xlint:all",
            "-Amapstruct.defaultComponentModel=spring",
            "-parameters"
        )
    )
    options.encoding = "UTF-8"
    options.annotationProcessorPath = configurations.annotationProcessor.get()
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    classDirectories.setFrom(
        files(classDirectories.files.map {
            fileTree(it) {
                exclude("**/generated/**")
            }
        })
    )
}


tasks.test {
    useJUnitPlatform()
    testLogging {
        events = setOf(FAILED)
        exceptionFormat = FULL
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Implementation-Title" to project.name,
                "Implementation-Version" to project.version,
            )
        )
    }
}

repositories {
    mavenCentral()
}