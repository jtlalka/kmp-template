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

    /**
             * Retrieve a string value from the main bundle's Info.plist for the given key.
             *
             * @param key The Info.plist key to look up (for example `"CFBundleIdentifier"`).
             * @return The string value associated with `key`, or an empty string if the key is missing or not a string.
             */
            private fun getInfoByKey(key: String): String =
        NSBundle.mainBundle
            .infoDictionary?.get(key) as? String ?: ""

    @OptIn(ExperimentalNativeApi::class)
    override val debug: Boolean = Platform.isDebugBinary
}