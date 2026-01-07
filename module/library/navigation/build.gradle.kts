plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.gradleBuildLogic)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.mokkery)
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
}
