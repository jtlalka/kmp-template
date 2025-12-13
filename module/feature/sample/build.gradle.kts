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
            implementation(projects.module.library.design)
            implementation(projects.module.library.environment)
            implementation(projects.module.library.foundation)

            implementation(libs.androidx.lifecycle.runtime)
            implementation(libs.androidx.lifecycle.viewmodel)

            implementation(libs.bundles.compose)
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
