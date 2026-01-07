plugins {
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        register("gradle-build-logic") {
            id = "gradle-build-logic"
            implementationClass = "plugin.BuildLogicPlugin"
        }
    }
}

kotlin {
    jvmToolchain(libs.versions.java.get().toInt())
}

dependencies {
    // Java version
    requireNotNull(libs.versions.java)

    // Used versions
    requireNotNull(libs.versions.android.sdk.compile)
    requireNotNull(libs.versions.android.sdk.min)
    requireNotNull(libs.versions.android.sdk.target)

    // Used plugins
    requireNotNull(libs.plugins.androidApplication)
    requireNotNull(libs.plugins.androidLibrary)
    requireNotNull(libs.plugins.kotlinMultiplatform)

    // Compilation
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}
