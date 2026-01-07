package plugin.configuration

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import plugin.BuildLogicConfiguration
import plugin.extension.getInt
import plugin.extension.getPluginId
import plugin.extension.java
import plugin.extension.libs

internal class AndroidBuildConfiguration : BuildLogicConfiguration {

    private val Project.androidCompileSdk: Int
        get() = libs.getInt(alias = "android-sdk-compile")

    private val Project.androidMinSdk: Int
        get() = libs.getInt(alias = "android-sdk-min")

    private val Project.androidTargetSdk: Int
        get() = libs.getInt(alias = "android-sdk-target")

    override fun init(project: Project) = with(project) {
        pluginManager.withPlugin(libs.getPluginId("androidApplication")) {
            setupAndroidApplication()
        }
        pluginManager.withPlugin(libs.getPluginId("androidLibrary")) {
            setupAndroidLibrary()
        }
    }

    private fun Project.setupAndroidApplication() {
        extensions.configure<ApplicationExtension> {
            compileSdk = androidCompileSdk

            defaultConfig {
                minSdk = androidMinSdk
                targetSdk = androidTargetSdk
            }
            compileOptions {
                sourceCompatibility = JavaVersion.toVersion(libs.java)
                targetCompatibility = JavaVersion.toVersion(libs.java)
            }
        }
    }

    private fun Project.setupAndroidLibrary() {
        extensions.configure<LibraryExtension> {
            compileSdk = androidCompileSdk

            defaultConfig {
                minSdk = androidMinSdk
            }
            compileOptions {
                sourceCompatibility = JavaVersion.toVersion(libs.java)
                targetCompatibility = JavaVersion.toVersion(libs.java)
            }
        }
    }
}
