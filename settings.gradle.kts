pluginManagement {
    includeBuild("$rootDir/gradle/build-logic")

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
    ":module:feature:sample",
    ":module:library:design",
    ":module:library:environment",
    ":module:library:foundation",
    ":module:library:navigation",
    ":module:library:network",
    ":module:library:preferences"
)
