package extension

import org.gradle.api.artifacts.VersionCatalog
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

internal val VersionCatalog.java: Int
    get() = getVersion(alias = "java")

internal val VersionCatalog.jvmTarget: JvmTarget
    get() = JvmTarget.fromTarget(java.toString())

internal fun VersionCatalog.getPluginId(alias: String): String =
    findPlugin(alias).get().get().pluginId

internal fun VersionCatalog.getVersion(alias: String): Int =
    findVersion(alias).get().requiredVersion.toInt()
