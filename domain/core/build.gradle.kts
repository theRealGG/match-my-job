plugins {
    id("com.matchmyjob.java-lib-conventions")
}

dependencies {
    implementation("org.slf4j:slf4j-api")
    implementation(project(":domain:api"))
    testImplementation("io.projectreactor:reactor-test")
    testImplementation(project(":test:utilities"))
    testImplementation(libs.datafaker)
}