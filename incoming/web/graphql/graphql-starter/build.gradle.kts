plugins {
    id("com.matchmyjob.java-lib-conventions")
}

dependencies {
    api("com.netflix.graphql.dgs:dgs-starter")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
}