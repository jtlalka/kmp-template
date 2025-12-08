import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

java {
    sourceCompatibility = JavaVersion.valueOf("VERSION_" + libs.versions.java.get())
    targetCompatibility = JavaVersion.valueOf("VERSION_" + libs.versions.java.get())
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.java.get())
    }
}

gradlePlugin {
    plugins {
        register("gradleBuildConfigPlugin") {
            id = "gradleBuildConfigPlugin"
            implementationClass = "GradleBuildConfigPlugin"
            version = "1.0.0"
        }
    }
}

beforeEvaluate {
    // Java version
    requireNotNull(libs.versions.java)

    // Used plugins
    requireNotNull(libs.plugins.androidApplication)
    requireNotNull(libs.plugins.androidLibrary)
    requireNotNull(libs.plugins.kotlinMultiplatform)

    // Used versions
    requireNotNull(libs.versions.android.sdk.compile)
    requireNotNull(libs.versions.android.sdk.min)
    requireNotNull(libs.versions.android.sdk.target)
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}
