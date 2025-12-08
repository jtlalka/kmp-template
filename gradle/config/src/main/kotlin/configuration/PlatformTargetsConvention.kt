package configuration

import GradleConfiguration
import extension.getPluginId
import extension.isApplicationModule
import extension.jvmTarget
import extension.libs
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

@OptIn(ExperimentalWasmDsl::class)
internal class PlatformTargetsConvention : GradleConfiguration {

    private val VersionCatalog.kotlinMultiplatformPluginId
        get() = getPluginId(alias = "kotlinMultiplatform")

    override fun init(project: Project) = with(project) {
        pluginManager.withPlugin(libs.kotlinMultiplatformPluginId) {
            setupKotlinMultiplatformPlugin()
        }
    }

    private fun Project.setupKotlinMultiplatformPlugin() {
        extensions.configure<KotlinMultiplatformExtension> {
            androidTarget {
                compilerOptions {
                    jvmTarget.set(libs.jvmTarget)
                }
            }
            iosTarget {
                if (isApplicationModule) {
                    binaries.framework {
                        baseName = APP_IOS_BASE_NAME
                        isStatic = true
                    }
                }
            }
            jvm()
            wasmJs {
                browser {
                    commonWebpackConfig {
                        outputFileName = "app.js"
                    }
                }
                binaries.executable()
            }
        }
    }

    private fun KotlinMultiplatformExtension.iosTarget(
        target: KotlinNativeTarget.() -> Unit
    ) {
        iosX64(target)
        iosArm64(target)
        iosSimulatorArm64(target)
    }

    private companion object Companion {
        const val APP_IOS_BASE_NAME = "App"
    }
}
