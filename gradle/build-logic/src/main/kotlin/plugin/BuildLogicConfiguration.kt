package plugin

import org.gradle.api.Project

internal interface BuildLogicConfiguration {

    fun init(project: Project)
}
