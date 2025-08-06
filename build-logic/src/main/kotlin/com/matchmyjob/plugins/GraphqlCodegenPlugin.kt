package com.matchmyjob.plugins

import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.withType

class GraphqlCodegenPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.pluginManager.apply {
            apply("com.netflix.dgs.codegen")
        }

        val extension = target.extensions.create<GraphqlCodegenExtension>("graphqlCodegen")

        target.afterEvaluate {
            target.tasks.withType<GenerateJavaTask>() {
                packageName = extension.packageName
                generateClient = true
            }
        }
    }
}

open class GraphqlCodegenExtension {
    var packageName: String = "com.matchmyjob.outgoing.graphql.generated"
}