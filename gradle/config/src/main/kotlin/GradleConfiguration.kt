import org.gradle.api.Project

internal interface GradleConfiguration {

    fun init(project: Project)
}
