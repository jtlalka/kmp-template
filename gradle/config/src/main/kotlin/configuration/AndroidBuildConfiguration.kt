package configuration

import GradleConfiguration
import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import extension.getPluginId
import extension.getVersion
import extension.java
import extension.libs
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure

internal class AndroidBuildConfiguration : GradleConfiguration {

    private val VersionCatalog.androidApplicationPluginId
        get() = getPluginId(alias = "androidApplication")

    private val VersionCatalog.androidLibraryPluginId
        get() = getPluginId(alias = "androidLibrary")

    private val Project.androidCompileSdk: Int
        get() = libs.getVersion(alias = "android-sdk-compile")

    private val Project.androidMinSdk: Int
        get() = libs.getVersion(alias = "android-sdk-min")

    private val Project.androidTargetSdk: Int
        get() = libs.getVersion(alias = "android-sdk-target")

    private val Project.javaVersion: JavaVersion
        get() = JavaVersion.valueOf("VERSION_" + libs.java)

    override fun init(project: Project) = with(project) {
        pluginManager.withPlugin(libs.androidApplicationPluginId) {
            setupAndroidApplication()
        }
        pluginManager.withPlugin(libs.androidLibraryPluginId) {
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
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
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
                sourceCompatibility = javaVersion
                targetCompatibility = javaVersion
            }
        }
    }
}
