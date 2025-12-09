package kmp.template.app.init

import kmp.template.environment.Environment
import kmp.template.environment.EnvironmentType
import kotlin.experimental.ExperimentalNativeApi
import platform.Foundation.NSBundle

internal class IosEnvironment : Environment {

    override val type = EnvironmentType.IOS

    override val applicationId = getInfoByKey("CFBundleIdentifier")

    override val versionName = getInfoByKey("CFBundleShortVersionString")

    override val versionCode = getInfoByKey("CFBundleVersion")

    private fun getInfoByKey(key: String): String =
        NSBundle.mainBundle
            .infoDictionary?.get(key) as? String ?: ""

    @OptIn(ExperimentalNativeApi::class)
    override val debug: Boolean = Platform.isDebugBinary
}
