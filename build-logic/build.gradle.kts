import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}


val kotlinVersion = "KOTLIN_${
    libs.versions.kotlin.get().substringBeforeLast(".").replace(".", "_")
}";

gradlePlugin {
    plugins {
        register("graphql-codegen") {
            id = "com.matchmyjob.graphql.codegen"
            implementationClass = "com.matchmyjob.plugins.GraphqlCodegenPlugin"
        }
    }
}


kotlin {
    jvmToolchain {
        languageVersion.set(
            JavaLanguageVersion.of(libs.versions.jdk.get())
        )
    }
    compilerOptions {
        @Suppress("SpellCheckingInspection")
        freeCompilerArgs.add("-Xjsr305=strict")
        allWarningsAsErrors = true
        jvmTarget.set(JvmTarget.valueOf("JVM_${libs.versions.jdk.get()}"))
        languageVersion.set(
            KotlinVersion.valueOf(
                kotlinVersion
            )
        )
        apiVersion.set(
            KotlinVersion.valueOf(
                kotlinVersion
            )
        )
    }
}

dependencies {
    // spring boot dependencies
    implementation(libs.spring.boot.gradle.plugin)
    implementation(libs.spring.dependency.management.plugin)
    // dev dependencies
    implementation(libs.lombok.plugin)
    implementation(libs.dgs.codegen.plugin)
}
