package kmp.template.app.init

import kmp.template.app.BuildConfig
import kmp.template.environment.Environment
import kmp.template.environment.EnvironmentType

internal class AndroidEnvironment : Environment {

    override val type = EnvironmentType.ANDROID

    override val applicationId = BuildConfig.APPLICATION_ID

    override val versionName = BuildConfig.VERSION_NAME

    override val versionCode = BuildConfig.VERSION_CODE.toString()

    override val debug: Boolean = BuildConfig.DEBUG
}
