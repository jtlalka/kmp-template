package kmp.template.environment

interface Environment {

    val type: EnvironmentType

    val applicationId: String

    val versionName: String

    val versionCode: String

    val debug: Boolean
}
