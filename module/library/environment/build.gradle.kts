plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.gradleBuildLogic)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        commonTest.dependencies {
            implementation(libs.bundles.unitTest)
        }
    }
}

android {
    namespace = "kmp.template.environment"
}
