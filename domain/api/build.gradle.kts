plugins {
    `java-lib-conventions`
}

dependencies {
    implementation(platform(libs.spring.boot.bom))
    implementation("org.slf4j:slf4j-api")
    implementation(libs.vavr)
    implementation("io.projectreactor:reactor-core")
}