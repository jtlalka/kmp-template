pluginManagement {
    includeBuild("$rootDir/gradle/config")

    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "kmp-template"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

include(
    ":app",
    ":module:library:environment",
    ":module:library:foundation"
)
