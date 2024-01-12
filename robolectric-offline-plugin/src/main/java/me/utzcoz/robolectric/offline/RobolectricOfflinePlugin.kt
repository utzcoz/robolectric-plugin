package me.utzcoz.robolectric.offline

import org.gradle.api.Plugin
import org.gradle.api.Project

class RobolectricOfflinePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.tasks.register("greet") { task ->
            task.doLast { s -> println("Hello from plugin 'com.example.plugin.greeting'") }
        }
    }
}
