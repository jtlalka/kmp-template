plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.gradleBuildLogic)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.module.library.design)
            implementation(projects.module.library.environment)
            implementation(projects.module.library.foundation)
            implementation(projects.module.library.navigation)
            implementation(projects.module.library.preferences)

            implementation(libs.bundles.compose)
            implementation(libs.bundles.lifecycle)
            implementation(libs.bundles.navigation)
            implementation(libs.koin.compose)
            implementation(libs.koin.viewmodel)

            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "kmp.template.feature.sample"

    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
