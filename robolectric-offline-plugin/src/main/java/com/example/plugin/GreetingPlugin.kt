package com.example.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class GreetingPlugin : Plugin<Project?> {
    fun apply(project: Project) {
        project.getTasks().register("greet") { task ->
            task.doLast { s ->
                System.out.println("Hello from plugin 'com.example.plugin.greeting'")
            }
        }
    }
}
