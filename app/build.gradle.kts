plugins {
    id("com.matchmyjob.java-conventions")
    id("com.matchmyjob.spring-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation(project(":domain"))
    implementation(project(":outgoing"))
    implementation("io.projectreactor:reactor-core")
}