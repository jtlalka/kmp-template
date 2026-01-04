plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.gradleBuildConfig)
    alias(libs.plugins.kotlinMultiplatform)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.bundles.compose)
            implementation(libs.bundles.navigation)
        }
        commonTest.dependencies {
            implementation(libs.bundles.unitTest)
        }
    }
}

android {
    namespace = "kmp.template.navigation"

    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
