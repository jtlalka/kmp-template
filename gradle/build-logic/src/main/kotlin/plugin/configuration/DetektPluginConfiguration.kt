package plugin.configuration

import dev.detekt.gradle.Detekt
import dev.detekt.gradle.extensions.DetektExtension
import dev.detekt.gradle.plugin.DetektPlugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import plugin.BuildLogicConfiguration
import plugin.extension.java
import plugin.extension.libs

internal class DetektPluginConfiguration : BuildLogicConfiguration {

    override fun init(project: Project) = with(project) {
        if (file("src").isDirectory) {
            setupDetektPlugin()
        }
    }

    private fun Project.setupDetektPlugin() {
        pluginManager.apply(DetektPlugin::class.java)

        extensions.configure<DetektExtension> {
            config.setFrom("$rootDir/gradle/code-style/detekt.yml")

            val sourceDirectories = file("src")
                .listFiles { file -> file.isDirectory }
                ?.filterNot { it.name.endsWith("Test") }
                ?.map { file("src/${it.name}/kotlin") }
                ?.filter { it.exists() && it.isDirectory }
                ?: emptyList()

            source.setFrom(sourceDirectories)
        }

        tasks.withType(Detekt::class.java).configureEach {
            jvmTarget.set(libs.java.toString())

            reports {
                checkstyle.required.set(true)
                html.required.set(true)
                markdown.required.set(false)
                sarif.required.set(false)
            }
        }
    }
}
