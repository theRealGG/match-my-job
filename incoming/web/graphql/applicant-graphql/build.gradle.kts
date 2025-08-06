plugins {
    id("com.matchmyjob.java-lib-conventions")
    id("com.matchmyjob.graphql.codegen")
}

graphqlCodegen {
    packageName = "com.matchmymjob.incoming.graphql.applicant.generated"
}

dependencies {
    implementation(project(":domain:api"))
    implementation(project(":incoming:web:graphql:graphql-starter"))
    testImplementation(project(":test:utilities"))
}