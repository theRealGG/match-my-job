plugins {
    `java-lib-conventions`
    `test-lib-conventions`
}

dependencies {
    implementation(platform(libs.spring.boot.bom))
    implementation("org.slf4j:slf4j-api")
    implementation(libs.vavr)
    implementation(project(":domain:api"))
    implementation("io.projectreactor:reactor-core")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(libs.bundles.test.core)
    testImplementation(project(":test:utilities"))
    testImplementation(libs.datafaker)
}