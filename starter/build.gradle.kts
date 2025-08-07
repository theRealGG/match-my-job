plugins {
    id("com.matchmyjob.java-lib-conventions")
}

dependencies {

    listOf(":domain", ":incoming", ":configuration", ":outgoing")
        .forEach { api(project(it)) }
}