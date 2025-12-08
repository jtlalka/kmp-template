import configuration.AndroidBuildConfiguration
import configuration.PlatformTargetsConvention
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleBuildConfigPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        AndroidBuildConfiguration().init(target)
        PlatformTargetsConvention().init(target)
    }
}
