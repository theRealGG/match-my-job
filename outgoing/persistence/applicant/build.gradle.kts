plugins {
    id("com.matchmyjob.java-lib-conventions")
}

dependencies {
    implementation(project(":domain:api"))
    implementation("org.springframework.boot:spring-boot-starter")
}