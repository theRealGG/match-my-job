plugins {
    `java-lib-conventions`
}


dependencies {
    implementation(project(":domain:api"))
    implementation(platform(libs.spring.boot.bom))
    implementation(libs.bundles.test.core)
    implementation(libs.vavr)
    implementation(libs.datafaker)
}