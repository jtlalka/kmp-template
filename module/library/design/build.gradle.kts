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
        }
    }
}

android {
    namespace = "kmp.template.design"

    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
