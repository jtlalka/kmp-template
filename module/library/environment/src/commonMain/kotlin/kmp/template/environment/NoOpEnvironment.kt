package kmp.template.environment

data class NoOpEnvironment(
    override val type: EnvironmentType,
    override val applicationId: String = "application",
    override val versionName: String = "1.0.0",
    override val versionCode: String = "1",
    override val debug: Boolean = false
) : Environment
