plugins {
    base
    idea
    jacoco
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}



jacoco {
    toolVersion = "0.8.13"
}

idea {
    module.isDownloadJavadoc = true
    module.isDownloadSources = true
}

tasks {
    wrapper {
        distributionType = Wrapper.DistributionType.ALL
    }
}

tasks.register("report") {
    group = "verification"
    description = "Collects all test reports"
    dependsOn(":app:test", ":test:integration-test:test")
}

tasks.named("build") {
    finalizedBy(":test:integration-test:test")
}

tasks.register<JacocoReport>("jacocoRootReport") {
    group = "verification"
    description = "Generates aggregate JaCoCo coverage report for all subprojects"

    dependsOn(subprojects.flatMap {
        it.tasks.matching { t -> t.name == "test" }
    })

    val execFiles = files(
        subprojects.map {
            fileTree("${it.buildDir}/jacoco").matching {
                include("*.exec")
            }
        }
    )

    executionData.setFrom(execFiles)

    // Aggregate all source and class dirs
    val sourceDirs = files(
        subprojects.mapNotNull {
            it.extensions.findByType<SourceSetContainer>()
                ?.findByName("main")
                ?.allSource
                ?.srcDirs
        }.flatten()
    )

    val classDirs = files(
        subprojects.mapNotNull {
            it.extensions.findByType<SourceSetContainer>()
                ?.findByName("main")
                ?.output
                ?.classesDirs
        }.map { it.files }.flatten()
    )

    sourceDirectories.setFrom(sourceDirs)
    classDirectories.setFrom(classDirs.files.map {
        fileTree(it) {
            exclude("**/generated/**")
        }
    })

    reports {
        html.required.set(true)
        html.outputLocation.set(layout.buildDirectory.dir("reports/jacoco/html"))

        xml.required.set(true)
        xml.outputLocation.set(layout.buildDirectory.file("reports/jacoco/jacoco.xml"))

        csv.required.set(false)
    }
}
