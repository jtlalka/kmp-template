package plugin.extension

import org.gradle.api.artifacts.VersionCatalog
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal val VersionCatalog.java: Int
    get() = getInt(alias = "java")

internal val VersionCatalog.jvmTarget: JvmTarget
    get() = JvmTarget.fromTarget(java.toString())

internal fun VersionCatalog.getPluginId(alias: String): String =
    findPlugin(alias).get().get().pluginId

internal fun VersionCatalog.getInt(alias: String): Int =
    findVersion(alias).get().requiredVersion.toInt()
