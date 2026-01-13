package plugin.configuration

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import plugin.BuildLogicConfiguration
import plugin.extension.getPluginId
import plugin.extension.isApplicationModule
import plugin.extension.jvmTarget
import plugin.extension.libs

internal class KmpTargetsConfiguration : BuildLogicConfiguration {

    override fun init(project: Project) = with(project) {
        pluginManager.withPlugin(libs.getPluginId(alias = "kotlinMultiplatform")) {
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
                        baseName = "App"
                        isStatic = false
                        linkerOpts.add("-lsqlite3")
                    }
                }
            }

            jvm()

            @OptIn(ExperimentalWasmDsl::class)
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
}
