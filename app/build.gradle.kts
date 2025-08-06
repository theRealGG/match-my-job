plugins {
    id("com.matchmyjob.java-conventions")
    id("com.matchmyjob.spring-conventions")
    //  id("com.matchmyjob.test-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    listOf(":domain", ":outgoing", ":incoming")
        .map { project(it) }
        .forEach { implementation(it) }
}