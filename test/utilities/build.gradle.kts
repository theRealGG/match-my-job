plugins {
    id("com.matchmyjob.java-lib-conventions")
}


dependencies {
    implementation(project(":domain:api"))
    implementation(libs.bundles.test.core)
    implementation(libs.vavr)
    implementation(libs.datafaker)
}