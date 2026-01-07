package plugin

import plugin.configuration.AndroidBuildConfiguration
import plugin.configuration.DetektPluginConfiguration
import plugin.configuration.KmpTargetsConfiguration
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A Gradle plugin that centralizes build logic for a multi-platform project.
 *
 * This plugin applies a series of configurations to a given [Project]:
 * - Applies common Android build configurations via [AndroidBuildConfiguration].
 * - Sets up the Detekt static analysis tool using [DetektPluginConfiguration].
 * - Configures Kotlin Multiplatform (KMP) targets via [KmpTargetsConfiguration].
 *
 * This approach encapsulates and reuses build logic, making Gradle scripts cleaner
 * and more maintainable across different modules.
 *
 * @see AndroidBuildConfiguration
 * @see DetektPluginConfiguration
 * @see KmpTargetsConfiguration
 */
class BuildLogicPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        AndroidBuildConfiguration().init(this)
        DetektPluginConfiguration().init(this)
        KmpTargetsConfiguration().init(this)
    }
}
