plugins {
    id("com.matchmyjob.java-lib-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-json")
    implementation(libs.vavr.jackson.module)
}